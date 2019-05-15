package com.ripple.effects.fb.java.module.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.discover.adapter.AllSpotsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllSpotsFragment extends BaseFragment implements IBaseItemListener {

    @BindView(R.id.rv_spots)
    RecyclerView rvSpots;

    private AllSpotsAdapter mAdapter;

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
        mAdapter = new AllSpotsAdapter(getContext(), 0);
        mAdapter.setIBaseItemListener(this);
        rvSpots.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvSpots.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_all_spots;
    }

    @Override
    public void openDetailHomestay(int idHomestay) {

    }

    @Override
    public void onClickFavoriteHomestay(int isHomestay, boolean isFavorite) {

    }
}
