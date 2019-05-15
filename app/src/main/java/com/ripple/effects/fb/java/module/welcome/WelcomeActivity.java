package com.ripple.effects.fb.java.module.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.login.LoginActivity;
import com.ripple.effects.fb.java.module.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_login_with_fb)
    Button mBtnLoginFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
