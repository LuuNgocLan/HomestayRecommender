package com.ripple.effects.fb.java.module.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private IDiscoverContract.IDiscoverPresenter mIHomePresenter;
    private IBaseListener mIBaseListener;

    @BindView(R.id.rv_recommend)
    DiscreteScrollView mRvRecommend;
    @BindView(R.id.rv_spots)
    RecyclerView mRvSpots;
    @BindView(R.id.rv_destinations)
    RecyclerView mRvDestinations;

    private RecommendAdapter mRecommendAdapter;
    private AllSpotsAdapter mAllSpotsAdapter;
    private DestinationsAdapter mDestinationsAdapter;

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
        mIHomePresenter = new DiscoverPresenter(getContext());
        mIHomePresenter.onCreate(this);
        mIHomePresenter.loadDataStatic();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mRecommendAdapter = new RecommendAdapter(getContext());
        mRecommendAdapter.setIBaseItemListener(this);
        mRvRecommend.setAdapter(mRecommendAdapter);
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
        if (mIHomePresenter != null) {
            mIHomePresenter.onStart();
        }
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mIHomePresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_discover;
    }

    public void setIBaseListener(IBaseListener IBaseListener) {
        mIBaseListener = IBaseListener;
    }

    public void requestLoadHomeData() {
        if (mIHomePresenter != null) {
        }
    }

    public void smoothScrollToTop() {
        // TODO: 4/3/19 smoothScrollToTop
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
            mAllSpotsAdapter = new AllSpotsAdapter(getContext(), 80, homestayList);
            mAllSpotsAdapter.setIBaseItemListener(this);
            mRvSpots.setLayoutManager(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            mRvSpots.setAdapter(mAllSpotsAdapter);
        }
    }

    @Override
    public void onSuccessRecommend(List<Homestay> homestayList) {

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
    public void onClickFavoriteHomestay(int isHomestay, boolean isFavorite) {
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
}
