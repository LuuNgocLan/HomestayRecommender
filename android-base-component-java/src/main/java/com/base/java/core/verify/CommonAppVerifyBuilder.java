package com.base.java.core.verify;

public class CommonAppVerifyBuilder {
    private String appId;
    private String appSecret;
    private IAppVerifyListener iAppVerifyListener;

    protected CommonAppVerifyBuilder(String appId, String appSecret, IAppVerifyListener iAppVerifyListener) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.iAppVerifyListener = iAppVerifyListener;
    }

    public void build() {
        iAppVerifyListener.appVerifyBuild(true);
        startVerify();
    }

    /**
     * add verify method here
     */
    private void startVerify() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (iAppVerifyListener != null) {
                    if (appId.equals("test") && appSecret.equals("test")) {
                        iAppVerifyListener.appVerifyReturn(true, VerifyState.NONE);
                    } else {
                        iAppVerifyListener.appVerifyReturn(false, VerifyState.ERROR);
                    }
                }
            }
        }, 5000);
    }

    protected interface IAppVerifyListener {
        void appVerifyBuild(boolean success);

        void appVerifyReturn(boolean success, VerifyState verifyState);
    }

    public enum VerifyState {
        EXPIRE, ERROR, TEMPLATE, NONE
    }
}
