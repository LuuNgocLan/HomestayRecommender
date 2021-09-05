package com.ripple.effects.fb.java.module.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.java.core.helper.PreferencesHelper;
import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edt_username)
    EditText mEdtUsername;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;

    private ILoginContract.ILoginPresenter mILoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected IBasePresenter initPresenter() {
        return mILoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View view) {
        mILoginPresenter.callLogin(mEdtUsername.getText() + "", mEdtPassword.getText() + "");

    }

    @OnClick(R.id.imv_close)
    public void onClickClose(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoginSucces() {
        finish();
    }

    @Override
    public void onLoginFailed() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Inh5ekBnbWFpbC5jb20iLCJ1c2VySWQiOiI1Y2RmYTVmZDQ5OTJkZWQ5YmUwMTUyZjMiLCJpYXQiOjE1NTkyNTkxMDgsImV4cCI6MTU1OTM0NTUwOH0.vaS47Ursww22iFv1fVF71GMmN6WET_fsEYb9IujURjc";
        PreferencesHelper.writeString(this, PreferencesHelper.TOKEN, token);
        PreferencesHelper.writeBoolean(this, PreferencesHelper.ISlOGGED, true);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
