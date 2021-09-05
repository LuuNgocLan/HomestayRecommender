package com.ripple.effects.fb.java.module.discover;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.base.java.IBaseListener;
import com.base.java.core.utils.DimenUtils;
import com.base.java.core.utils.ScreenUtils;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.detail.DetailActivity;
import com.ripple.effects.fb.java.module.discover.adapter.AllDestinationFragment;
import com.ripple.effects.fb.java.module.discover.adapter.AllSpotsAdapter;
import com.ripple.effects.fb.java.module.discover.adapter.DestinationsAdapter;
import com.ripple.effects.fb.java.module.discover.adapter.RecommendAdapter;
import com.ripple.effects.fb.java.module.discover.allSpots.AllSpotsFragment;
import com.ripple.effects.fb.java.module.filter.FilterFragment;
import com.ripple.effects.fb.java.module.main.IMainListener;
import com.ripple.effects.fb.java.module.search.SearchFragment;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ripple.effects.fb.java.utils.AppUtils.ID_HOMESTAY;

public class DiscoverFragment extends BaseFragment implements IDiscoverContract.IDiscoverView, IBaseItemListener {

    private IDiscoverContract.IDiscoverPresenter mIDiscoverPresenter;
    private IBaseListener mIBaseListener;

    @BindView(R.id.rv_recommend)
    DiscreteScrollView mRvRecommend;
    @BindView(R.id.rv_spots)
    RecyclerView mRvSpots;
    @BindView(R.id.rv_destinations)
    RecyclerView mRvDestinations;
    @BindView(R.id.tv_spots_num)
    TextView mTvSpotsNum;
    @BindView(R.id.container)
    ConstraintLayout mContainer;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    private RecommendAdapter mRecommendAdapter;
    private AllSpotsAdapter mAllSpotsAdapter;
    private DestinationsAdapter mDestinationsAdapter;
    private IMainListener mIMainListener;

    public static DiscoverFragment newInstance(IBaseListener iBaseListener) {
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setIBaseListener(iBaseListener);
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        mIDiscoverPresenter = new DiscoverPresenter(getContext());
        mIDiscoverPresenter.onCreate(this);
        mIDiscoverPresenter.loadDataStatic();
        mIDiscoverPresenter.loadDataRecommendation();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mRvRecommend.setOrientation(DSVOrientation.HORIZONTAL);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mRvRecommend.getLayoutParams();
        if (DimenUtils.pxToDp(ScreenUtils.getScreenHeight(getContext())) > ScreenUtils.STANDARD_HEIGHT_DENSITY_DP) {
            layoutParams.height = (int) ((ScreenUtils.getScreenWidth(getContext()) - DimenUtils.dpToPx(80)) * 1.1f);
            mRvRecommend.setLayoutParams(layoutParams);
            mRvRecommend.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(1.1f)
                    .build());
        } else {
            layoutParams.height = (int) ((ScreenUtils.getScreenWidth(getContext()) - DimenUtils.dpToPx(80)) * 1f);
            mRvRecommend.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(1f)
                    .setMinScale(0.85f)
                    .setPivotX(Pivot.X.LEFT)
                    .build());
        }

        mRvRecommend
                .addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
                    @Override
                    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

                    }
                });

        mDestinationsAdapter = new DestinationsAdapter(getContext(), 40);
        mRvDestinations.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvDestinations.setAdapter(mDestinationsAdapter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mIDiscoverPresenter != null) {
            mIDiscoverPresenter.onStart();
        }
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mIDiscoverPresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_discover;
    }

    public void setIBaseListener(IBaseListener IBaseListener) {
        mIBaseListener = IBaseListener;
    }

    public void requestLoadHomeData() {
        if (mIDiscoverPresenter != null) {
        }
    }

    public void smoothScrollToTop() {
        mNestedScrollView.smoothScrollTo(0, 0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage() {
    }

    @Override
    public void onSuccesSpots(List<Homestay> homestayList) {
        if (homestayList != null && homestayList.size() > 0) {
            mTvSpotsNum.setText(homestayList.size() + " Spots");
            mAllSpotsAdapter = new AllSpotsAdapter(getContext(), 80, homestayList);
            mAllSpotsAdapter.setIBaseItemListener(this);
            mRvSpots.setLayoutManager(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            mRvSpots.setAdapter(mAllSpotsAdapter);
        }
    }

    @Override
    public void onSuccessRecommend(List<Homestay> homestayList) {
        Toast.makeText(getContext(), homestayList.size() + "", Toast.LENGTH_SHORT).show();
        if (homestayList != null && homestayList.size() > 0) {
            mRecommendAdapter = new RecommendAdapter(getContext(), homestayList);
            mRecommendAdapter.setIBaseItemListener(this);
            mRvRecommend.setAdapter(mRecommendAdapter);
        }
    }

    @Override
    public void onSuccessDestinations() {

    }

    @Override
    public void openDetailHomestay(String idHomestay) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(ID_HOMESTAY, idHomestay);
        startActivity(intent);
    }

    @Override
    public void onClickFavoriteHomestay(String idHomestay, boolean isFavorite) {
        if (mIDiscoverPresenter != null)
            mIDiscoverPresenter.updateFavorite(idHomestay);
    }

    @OnClick(R.id.imv_setting)
    public void onClickOption(View view) {
        if (mFragmentListener != null) {
            mFragmentListener.addChildFragment(new FilterFragment(), true, true);
        }
    }

    @OnClick(R.id.imv_search)
    public void onClickSearch(View view) {
        if (mFragmentListener != null) {
            mFragmentListener.addChildFragment(new SearchFragment(), true, true);
        }
    }

    @OnClick(R.id.imv_see_more_spot)
    public void onClickSeeMoreSpots(View view) {
        if (mFragmentListener != null) {
            mFragmentListener.addChildFragment(new AllSpotsFragment(), true, true);
        }
    }

    @OnClick(R.id.imv_see_more_destination)
    public void onClickSeeMoreDestination(View view) {
        if (mFragmentListener != null) {
            mFragmentListener.addChildFragment(new AllDestinationFragment(), true, true);
        }
    }

    @Override
    public void onUpdateFavoriteSuccess() {
        if (mIMainListener != null) {
            mIMainListener.onRefreshData();
        }
        Toast.makeText(getContext(), "Favorite Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateFavoriteFailed() {
        Toast.makeText(getContext(), "Favorite Failed!", Toast.LENGTH_SHORT).show();

    }

    public void setIMainListener(IMainListener iMainListener) {
        mIMainListener = iMainListener;
    }
}
