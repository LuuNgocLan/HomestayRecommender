package com.base.java.mvp;

public interface IBasePresenter<V>{
    void onCreate(V iBaseView);

    void onDestroy();
}
