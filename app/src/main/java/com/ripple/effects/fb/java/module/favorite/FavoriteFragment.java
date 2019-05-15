package com.ripple.effects.fb.java.module.favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.favorite.adapter.FavoriteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment
        extends BaseFragment
        implements IFavoriteContract.IFavoriteView, IBaseItemListener {

    IFavoriteContract.IFavoritePresenter mIFavoritePresenter;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_favorite)
    RecyclerView mRvFavorite;

    private FavoriteAdapter mAdapter;

    @Override
    protected IBasePresenter initPresenter() {
        mIFavoritePresenter = new FavoritePresenter(getContext());
        return mIFavoritePresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_favorite;
    }

    public static FavoriteFragment newInstance(IBaseListener iBaseListener) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        requestLoadFavoriteData();
        return view;
    }

    private void initRecyclerView() {
        mAdapter = new FavoriteAdapter(getContext());
        mAdapter.setIBaseItemListener(this);
        mRvFavorite.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvFavorite.setAdapter(mAdapter);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (mIFavoritePresenter != null) {
//            mIFavoritePresenter.onStart();
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void smoothScrollToTop() {

    }

    public void requestLoadFavoriteData() {
        if (mIFavoritePresenter != null) {
            mIFavoritePresenter.fetchData();
        }
    }

    @Override
    public void showLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage() {
    }

    @Override
    public void openDetailHomestay(int idHomestay) {

    }

    @Override
    public void onClickFavoriteHomestay(int isHomestay, boolean isFavorite) {

    }
}
