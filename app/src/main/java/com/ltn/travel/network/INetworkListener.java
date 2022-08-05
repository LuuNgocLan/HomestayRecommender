package com.ltn.travel.network;

public interface INetworkListener<T extends Object> {

    public void onFinished(T data);

    public void onError(Throwable throwable);
}
