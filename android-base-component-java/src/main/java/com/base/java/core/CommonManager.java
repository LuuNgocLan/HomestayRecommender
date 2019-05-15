package com.base.java.core;

import android.content.Context;

import com.base.java.core.verify.CommonAppVerify;
import com.base.java.core.verify.IVerifyStateListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CommonManager {
    private static volatile CommonManager commonManager;
    private WeakReference<Context> context;
    private CommonAppVerify.State state = CommonAppVerify.State.WAITING;
    private boolean showDebug = false;
    private boolean appSignBuild = false;
    private ArrayList<IVerifyStateListener> iVerifyStateListeners = new ArrayList<>();

    private CommonManager(Context context) {
        this.context = new WeakReference<>(context);
    }

    public static CommonManager init(Context context) {
        commonManager = new CommonManager(context);
        return commonManager;
    }

    public CommonManager showDebug(boolean showDebug) {
        this.showDebug = showDebug;
        return this;
    }

    public CommonAppVerify setAppId(String appId) {
        return new CommonAppVerify(appId, iVerifyListener);
    }

    protected Context getContext() {
        return context.get();
    }

    public CommonManager checkVerifyState(IVerifyStateListener iVerifyStateListener) {
        if (iVerifyStateListener != null && state != CommonAppVerify.State.WAITING) {
            iVerifyStateListener.verifyStateChecked(state == CommonAppVerify.State.VERIFIED);
        } else {
            iVerifyStateListeners.add(iVerifyStateListener);
        }
        return this;
    }

    public CommonManager addConfig(ModuleConfig moduleConfig) {
        moduleConfig.moduleConfig();
        return this;
    }

    public boolean isShowDebug() {
        return showDebug;
    }

    public static CommonManager getInstance() {
        if (commonManager == null || !commonManager.appSignBuild) {
            throw new RuntimeException(Constant.exceptionInit);
        }
        return commonManager;
    }

    CommonAppVerify.IVerifyListener iVerifyListener = new CommonAppVerify.IVerifyListener() {
        @Override
        public void appSignVerify(CommonAppVerify.State commonState) {
            state = commonState;
            for (IVerifyStateListener iVerifyStateListener : iVerifyStateListeners) {
                iVerifyStateListener.verifyStateChecked(state == CommonAppVerify.State.VERIFIED);
            }
        }

        @Override
        public void appSignBuild(boolean success) {
            appSignBuild = success;
        }
    };
}
