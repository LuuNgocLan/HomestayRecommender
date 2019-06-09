package com.ripple.effects.fb.java.module.tutorial;

import android.content.Context;

public class TutorialPresenter implements ITutorialContract.ITutorialPresenter {

    private ITutorialContract.ITutorialView mITutorialView;
    private Context mContext;

    public TutorialPresenter(Context context){
        this.mContext = context;
    }

    @Override
    public void onCreate(ITutorialContract.ITutorialView iBaseView) {

    }

    @Override
    public void onDestroy() {

    }
}
