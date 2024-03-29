package com.ltn.travel.module.discover.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ltn.travel.R;
import com.ltn.travel.module.base.IBaseItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllDestinationFragment extends BaseFragment implements IBaseItemListener {

    @BindView(R.id.rv_destinations)
    RecyclerView mRvDestinations;

    private DestinationsAdapter mAdapter;

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
        mAdapter = new DestinationsAdapter(getContext(),0);
        mAdapter.setIBaseItemListener(this);
        mRvDestinations.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvDestinations.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_all_destinations;
    }

    @Override
    public void openDetailHomestay(String idHomestay) {

    }

    @Override
    public void onClickFavoriteHomestay(String isHomestay, boolean isFavorite) {

    }
}
