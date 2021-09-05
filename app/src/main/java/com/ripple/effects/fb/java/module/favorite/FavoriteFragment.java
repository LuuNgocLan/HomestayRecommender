package com.ripple.effects.fb.java.module.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.favorite.Favorite;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.detail.DetailActivity;
import com.ripple.effects.fb.java.module.favorite.adapter.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ripple.effects.fb.java.utils.AppUtils.ID_HOMESTAY;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteFragment
        extends BaseFragment
        implements IFavoriteContract.IFavoriteView, IBaseItemListener,IFavoriteProtocol {

    IFavoriteContract.IFavoritePresenter mIFavoritePresenter;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_favorite)
    RecyclerView mRvFavorite;

    private FavoriteAdapter mAdapter;
    private List<Favorite> mFavorites = new ArrayList<>();

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
//        initRecyclerView();
//        requestLoadFavoriteData();
        return view;
    }

    private void initRecyclerView() {
        mAdapter = new FavoriteAdapter(getContext(), mFavorites);
        mAdapter.setIBaseItemListener(this);
        mRvFavorite.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvFavorite.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mIFavoritePresenter != null) {
           requestLoadFavoriteData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void smoothScrollToTop() {
        mRvFavorite.smoothScrollToPosition(0);
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
    public void onSuccess(List<Favorite> favorites) {
        if (favorites != null) {
            mFavorites = favorites;
            initRecyclerView();
        }

    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Get My Favorite Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailHomestay(String idHomestay) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(ID_HOMESTAY, idHomestay);
        startActivity(intent);
    }

    @Override
    public void onClickFavoriteHomestay(String idHomestay, boolean isFavorite) {

    }

    @Override
    public void onUpdateFavoriteSuccess() {

    }

    @Override
    public void onUpdateFavoriteFailed() {

    }
}
