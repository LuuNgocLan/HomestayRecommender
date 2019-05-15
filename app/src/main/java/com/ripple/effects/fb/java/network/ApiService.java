package com.ripple.effects.fb.java.network;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiService<T> {

    private Context mContext;
    private WSInterface mWSInterface;
    private List<Disposable> mDisposables = new ArrayList<>();

    public ApiService(Context context) {
        this.mContext = context;

        NetworkDataCenter.Builder builder = NetworkDataCenter.getInstance().getBuilder();
        if (builder == null)
            return;
        mWSInterface = ServiceGenerator.createService(WSInterface.class, builder.getBaseURL(), builder.getHeaders());
    }

    public ApiService(String baseURL, HashMap<String, String> header) {
        mWSInterface = ServiceGenerator.createService(WSInterface.class, baseURL, header);
    }

    public void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    public void onDestroy() {
        for (Disposable disposable : mDisposables) {
            if (disposable != null && !disposable.isDisposed())
                disposable.dispose();
        }
    }

    public WSInterface getService() {
        return mWSInterface;
    }

    public void callApi(final Observable observable, final INetworkListener iNetworkListener) {
        final NetworkDataCenter.Builder builder = NetworkDataCenter.getInstance().getBuilder();
        if (TextUtils.isEmpty(builder.getAccessToken())) {
            throw new RuntimeException("Please provide access token");
        } else {
            callApiWithToken(observable, iNetworkListener);
        }

    }

    private void callApiWithToken(Observable observable, final INetworkListener iNetworkListener) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        addDisposable(disposable);
                    }

                    @Override
                    public void onNext(Object data) {
                        iNetworkListener.onFinished(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iNetworkListener.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
