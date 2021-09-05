package com.ripple.effects.fb.java.module.detail;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.java.core.helper.ImageHelper;
import com.base.java.core.utils.ScreenUtils;
import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.detailhomestay.Homestay;
import com.ripple.effects.fb.java.module.detail.adapter.DetailHomestayAdapter;
import com.ripple.effects.fb.java.module.detail.adapter.HomestayDetailSection;
import com.ripple.effects.fb.java.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ripple.effects.fb.java.utils.AppUtils.ID_HOMESTAY;
import static com.ripple.effects.fb.java.utils.AppUtils.dipToPx;

public class DetailActivity extends BaseActivity implements IDetailContract.IDetailView,
        NestedScrollView.OnScrollChangeListener, DetailHomestayAdapter.OnClickReviewTag {

    @BindView(R.id.imv_center)
    ImageView mImvCenter;
    @BindView(R.id.rvDetail)
    RecyclerView mRvDetailHomeStay;
    @BindView(R.id.imv_close)
    ImageView mImvClose;
    @BindView(R.id.imv_option)
    ImageView mImvOption;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.name_homestay)
    TextView mTvNameHomestay;

    private DetailPresenter mDetailPresenter;
    private DetailHomestayAdapter mAdapter;
    private Homestay mHomestay = new Homestay();
    private List<HomestayDetailSection> mSections = new ArrayList<>();

    private String mIdHomestay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_new);
        ButterKnife.bind(this);

        getArgumentIdHomestay();

        mDetailPresenter = new DetailPresenter(this);
        mDetailPresenter.onCreate(this);
        setEvent();

        /**
         * this id get from the list homestay
         */
        mDetailPresenter.loadData(mIdHomestay);
    }

    private void getArgumentIdHomestay() {
        mIdHomestay = getIntent().getStringExtra(ID_HOMESTAY);
    }

    private void setEvent() {
        mNestedScrollView.setOnScrollChangeListener(this);
    }

    private void initRecyclerView() {
        initSections();
        mAdapter = new DetailHomestayAdapter(this, getApplicationContext(), mHomestay, mSections);
        mRvDetailHomeStay.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        mRvDetailHomeStay.setAdapter(mAdapter);
        mAdapter.setOnClickReviewTag(this);
    }

    private void initSections() {
        mSections.add(HomestayDetailSection.INFOR);
        mSections.add(HomestayDetailSection.FEATURE);
        mSections.add(HomestayDetailSection.MAP);
        mSections.add(HomestayDetailSection.REVIEW);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mDetailPresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detail_new;
    }

    @OnClick(R.id.imv_close)

    public void onClose(View view) {
        onBackPressed();
    }

    @OnClick(R.id.imv_option)
    public void onOptionClick(View view) {
    }

    @OnClick(R.id.imv_center)
    public void onClickImage(View view) {

    }

    @Override
    public void onSucces(Homestay homestay) {
        if (homestay != null) {
            if (homestay.getImageCenter() != null) {
                ImageHelper.load(getApplicationContext(), mImvCenter, homestay.getImageCenter());

            }
            mHomestay = homestay;
            initRecyclerView();

            if (!TextUtils.isEmpty(homestay.getName())) {
                mTvNameHomestay.setText(homestay.getName());
            }
//            if (!TextUtils.isEmpty(homestay.getNumReviews())) {
//                mTvReviewNum.setText(homestay.getNumReviews());
//            }
//            if (!TextUtils.isEmpty(homestay.getReviewScore() + "")) {
//                mTvStarHomestay.setText(homestay.getReviewScore() + "");
//            }
//            if (!TextUtils.isEmpty(homestay.getPrice())) {
//                mTvPriceHomestay.setText(homestay.getPrice());
//            }
//
//            if (homestay.getReview() != null && homestay.getReview().size() > 0) {
//                List<String> imageUrl = new ArrayList<>();
//                for (int i = 0; i < homestay.getReview().size(); i++) {
//                    imageUrl.add(homestay.getReview().get(i).getAvatar());
//                }
//                mListAvatar.setDataImage(imageUrl);
//            }


        }
    }

    @Override
    public void onError() {
        Toast.makeText(getApplicationContext(), "Error from server!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowloading() {

    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int scrollOldX, int scrollOldY) {
        int scrollViewHeight = dipToPx(getApplication(), 400);
        int headerHeight = dipToPx(getApplication(), 61 + 24);

        if (scrollViewHeight - ScreenUtils.getScreenHeight(getApplicationContext()) > headerHeight) {
            mTvNameHomestay.setAlpha(scrollY == 0 ? 0 : 1);
        } else if (scrollY < scrollViewHeight - headerHeight) {
            mTvNameHomestay.setAlpha(0);
        } else {
            mTvNameHomestay.setAlpha(1);
        }

        ViewUtils.setTintColor(mImvClose, mTvNameHomestay.getAlpha() == 0 ? 0xFFFFFFFF : 0xFF000000);
        ViewUtils.setTintColor(mImvOption, mTvNameHomestay.getAlpha() == 0 ? 0xFFFFFFFF : 0xFF000000);

    }

    @Override
    public void onClickReviewTag() {
        mRvDetailHomeStay.smoothScrollToPosition(3);
    }

    public void scrollToTop(){
        mRvDetailHomeStay.smoothScrollToPosition(0);
    }
}
