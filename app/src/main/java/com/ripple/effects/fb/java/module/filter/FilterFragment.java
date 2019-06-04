package com.ripple.effects.fb.java.module.filter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.filter.adapter.HighlightAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterFragment extends BaseFragment {

    @BindView(R.id.rv_highlight)
    RecyclerView mRvHighLight;

    private HighlightAdapter mAdapter;

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
        mAdapter = new HighlightAdapter(getContext());
        mRvHighLight.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvHighLight.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_option_filter;
    }

}
