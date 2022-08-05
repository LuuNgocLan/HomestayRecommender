package com.ltn.travel.module.filter;

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
import com.ltn.travel.module.filter.adapter.HighlightAdapter;

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
