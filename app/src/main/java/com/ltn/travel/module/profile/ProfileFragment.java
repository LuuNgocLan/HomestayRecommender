package com.ltn.travel.module.profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.base.java.IBaseListener;
import com.base.java.core.helper.ImageHelper;
import com.base.java.core.helper.PreferencesHelper;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ltn.travel.R;
import com.ltn.travel.models.profile.Profile;
import com.ltn.travel.module.login.LoginActivity;
import com.ltn.travel.network.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment
        extends BaseFragment
        implements IProfileContract.IProfileView {

    public static final int LOGIN_CODE = 3001;

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.spinner_currency)
    Spinner mSpnCurrency;
    @BindView(R.id.spinner_language)
    Spinner mSpnLanguage;
    @BindView(R.id.spinner_measure)
    Spinner mSpnMeasure;
    @BindView(R.id.imv_avatar)
    ImageView mImvAvatar;

    private String currencies[] = {"VND", "USD"};
    private String languages[] = {"ENGLISH", "VIETNAMESE"};
    private String measure[] = {"Km"};

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
        initSpinner();
        mISavePresenter.getProfile();
        return view;
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter_currency = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, currencies);
        adapter_currency.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpnCurrency.setAdapter(adapter_currency);
        mSpnCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.CURRENCY, currencies[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.CURRENCY, currencies[0]);
            }
        });

        ArrayAdapter<String> adapter_language = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, languages);
        adapter_currency.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpnLanguage.setAdapter(adapter_language);
        mSpnLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.LANGUAGE, languages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.LANGUAGE, currencies[0]);
            }
        });

        ArrayAdapter<String> adapter_measure = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, measure);
        adapter_measure.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpnMeasure.setAdapter(adapter_measure);
        mSpnMeasure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.MEASURE, measure[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                PreferencesHelper.writeString(getContext(), PreferencesHelper.MEASURE, currencies[0]);
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void smoothScrollToTop() {

    }

    public void requestLoadSaveData() {

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View view) {
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), LOGIN_CODE);
    }

    @Override
    public void onGetProfileSuccess(Profile profile) {
        if (profile != null) {
            mBtnLogin.setVisibility(View.GONE);
            mBtnRegister.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(profile.getUsername())) {
                mTvName.setText("HELLO " + profile.getUsername());
            }
            if (!TextUtils.isEmpty(profile.getAvatar())) {
                ImageHelper.load(getContext(), mImvAvatar, R.drawable.ic_avatar_default, Constant.baseUrl + profile.getAvatar());
            }
        }
    }

    @Override
    public void onError() {

    }
}
