package com.base.java.core.verify;

public class CommonAppVerify {
    private String appId;
    private String appSecret;
    private IVerifyListener iVerifyListener;

    public CommonAppVerify(String appId, IVerifyListener iVerifyListener) {
        this.appId = appId;
        this.iVerifyListener = iVerifyListener;
    }

    public CommonAppVerifyBuilder setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return new CommonAppVerifyBuilder(appId, appSecret, iAppVerifyListener);
    }

    private CommonAppVerifyBuilder.IAppVerifyListener iAppVerifyListener = new CommonAppVerifyBuilder.IAppVerifyListener() {
        @Override
        public void appVerifyBuild(boolean success) {
            iVerifyListener.appSignBuild(success);
        }

        @Override
        public void appVerifyReturn(boolean success, CommonAppVerifyBuilder.VerifyState verifyState) {
            iVerifyListener.appSignVerify(success ? State.VERIFIED : State.REJECT);
        }
    };

    public interface IVerifyListener {
        void appSignVerify(State state);

        void appSignBuild(boolean success);
    }

    public enum State {
        WAITING, VERIFIED, REJECT
    }
}
