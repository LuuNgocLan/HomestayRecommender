package com.ripple.effects.fb.java.module.search;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.homestay.Homestay;

import java.util.List;

public interface ISearchContract {
    interface IView extends IBaseView {
        void onSearchSuccess(List<Homestay> homestays);

        void onError();
    }

    interface IPresenter extends IBasePresenter<IView> {
        void search(String keyword);
    }
}
