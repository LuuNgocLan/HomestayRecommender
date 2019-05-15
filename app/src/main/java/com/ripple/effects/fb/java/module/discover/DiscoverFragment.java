package com.ripple.effects.fb.java.module.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.detail.DetailActivity;
import com.ripple.effects.fb.java.module.discover.adapter.AllDestinationFragment;
import com.ripple.effects.fb.java.module.discover.adapter.AllSpotsAdapter;
import com.ripple.effects.fb.java.module.discover.adapter.DestinationsAdapter;
import com.ripple.effects.fb.java.module.discover.adapter.RecommendAdapter;
import com.ripple.effects.fb.java.module.filter.FilterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverFragment extends BaseFragment implements IDiscoverContract.IDiscoverView, IBaseItemListener {

    private IDiscoverContract.IDiscoverPresenter mIHomePresenter;
    private IBaseListener mIBaseListener;

    @BindView(R.id.rv_recommend)
    RecyclerView mRvRecommend;
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
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mRecommendAdapter = new RecommendAdapter(getContext());
        mRecommendAdapter.setIBaseItemListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        mRvRecommend.setLayoutManager(layoutManager);
        mRvRecommend.setAdapter(mRecommendAdapter);

        mAllSpotsAdapter = new AllSpotsAdapter(getContext(), 80);
        mAllSpotsAdapter.setIBaseItemListener(this);
        mRvSpots.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvSpots.setAdapter(mAllSpotsAdapter);

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
        return mIHomePresenter = new DiscoverPresenter(getContext());
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
            mIHomePresenter.fetchData();
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
    public void loadData() {

    }

    @Override
    public void openDetailHomestay(int idHomestay) {
        startActivity(new Intent(getContext(), DetailActivity.class));
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
