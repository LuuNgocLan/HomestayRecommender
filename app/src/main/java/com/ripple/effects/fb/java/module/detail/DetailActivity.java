package com.ripple.effects.fb.java.module.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.base.java.core.utils.DimenUtils;
import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.Homestay;
import com.ripple.effects.fb.java.module.detail.adapter.DetailHomestayAdapter;
import com.ripple.effects.fb.java.module.detail.adapter.HomestayDetailSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.imv_center)
    ImageView mImvCenter;
    @BindView(R.id.bottom_sheet)
    LinearLayout mLlBottomSheet;
    @BindView(R.id.rvDetail)
    RecyclerView mRvDetailHomeStay;
    @BindView(R.id.imv_close)
    ImageView mImvClose;
    @BindView(R.id.imv_option)
    ImageView mImvOption;

    BottomSheetBehavior mBottomSheetBehavior;

    private DetailPresenter mDetailPresenter;
    private DetailHomestayAdapter mAdapter;
    private Homestay mHomestay = new Homestay();
    private List<HomestayDetailSection> mSections = new ArrayList<>();

    private boolean mIsShowBottomSheet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mDetailPresenter = new DetailPresenter(this);
        mBottomSheetBehavior = BottomSheetBehavior.from(mLlBottomSheet);
        initRecyclerView(savedInstanceState);
        setEvent();
    }

    private void setEvent() {

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mBottomSheetBehavior.setFitToContents(true);
                        mRvDetailHomeStay.smoothScrollToPosition(0);
                        mLlBottomSheet.setBackgroundColor(getResources().getColor(R.color.paint_white));
                        setColorIconToolbar(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        mLlBottomSheet.setBackground(getResources().getDrawable(R.drawable.background_bottomsheet));
                        mRvDetailHomeStay.smoothScrollToPosition(0);
                        setColorIconToolbar(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void setColorIconToolbar(int state) {
        switch (state) {
            case BottomSheetBehavior.STATE_EXPANDED:
                mImvClose.setImageResource(R.drawable.ic_close_black);
                mImvOption.setImageResource(R.drawable.ic_option_black);
                break;
            default:
                mImvClose.setImageResource(R.drawable.ic_close);
                mImvOption.setImageResource(R.drawable.ic_option);
        }

    }

    private void initRecyclerView(Bundle savedInstanceState) {
        initSections();
        mAdapter = new DetailHomestayAdapter(savedInstanceState, getApplicationContext(), mHomestay, mSections);
        mRvDetailHomeStay.setLayoutManager(new LinearLayoutManager(mLlBottomSheet.getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRvDetailHomeStay.setAdapter(mAdapter);
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
        return R.layout.activity_detail;
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
        if (!mIsShowBottomSheet) {
            mBottomSheetBehavior.setPeekHeight((int) DimenUtils.dpToPx(336));
            mRvDetailHomeStay.scrollToPosition(0);
            TransitionManager.beginDelayedTransition(mLlBottomSheet);
        } else {
            mBottomSheetBehavior.setPeekHeight((int) DimenUtils.dpToPx(0));
            TransitionManager.beginDelayedTransition(mLlBottomSheet);
        }
        mIsShowBottomSheet = !mIsShowBottomSheet;
    }

}
