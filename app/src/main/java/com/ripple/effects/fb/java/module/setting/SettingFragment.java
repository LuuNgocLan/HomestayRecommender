package com.ripple.effects.fb.java.module.setting;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;

import butterknife.ButterKnife;

public class SettingFragment extends BaseFragment implements ISettingContract.ISettingView {

    private ISettingContract.ISettingPresenter mISettingPresenter;
    private IBaseListener mIBaseListener;

    public static SettingFragment newInstance(IBaseListener iBaseListener) {
        SettingFragment fragment = new SettingFragment();
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
        return view;
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mISettingPresenter = new SettingPresenter(getContext());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_setting;
    }

    public void setIBaseListener(IBaseListener IBaseListener) {
        mIBaseListener = IBaseListener;
    }

    public void smoothScrollToTop() {

    }

    public void requestLoadSettingData() {

    }
}
