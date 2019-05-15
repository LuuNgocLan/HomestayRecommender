package com.ripple.effects.fb.java.module.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment
        extends BaseFragment
        implements IProfileContract.IProfileView {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;


    IProfileContract.IProfilePresenter mISavePresenter;

    @Override
    protected IBasePresenter initPresenter() {
        mISavePresenter = new ProfilePresenter(getContext());
        return mISavePresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment newInstance(IBaseListener iBaseListener) {
        ProfileFragment fragment = new ProfileFragment();
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
    public void onDestroy() {
        super.onDestroy();
    }

    public void smoothScrollToTop() {

    }

    public void requestLoadSaveData() {

    }
}
