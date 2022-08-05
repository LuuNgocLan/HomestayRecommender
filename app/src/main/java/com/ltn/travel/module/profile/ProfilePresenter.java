package com.ltn.travel.module.profile;

import android.content.Context;

import com.ltn.travel.models.data.DataCenter;

public class ProfilePresenter implements IProfileContract.IProfilePresenter {

    private IProfileContract.IProfileView mIProfileView;
    private Context mContext;
    private DataCenter mDataCenter = DataCenter.getInstance();

    public ProfilePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IProfileContract.IProfileView iBaseView) {
        this.mIProfileView = (IProfileContract.IProfileView) iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mIProfileView = null;
    }

    @Override
    public void getProfile() {
        mDataCenter.getProfile(new DataCenter.OnGetDataListener() {
            @Override
            public void onSuccess() {
                if (mIProfileView != null) {
                    mIProfileView.onGetProfileSuccess(mDataCenter.getProfile());
                }
            }

            @Override
            public void onError() {
                mIProfileView.onError();
            }
        });
    }
}
