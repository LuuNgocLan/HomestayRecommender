package com.ltn.travel.module.discover.allSpots;

import android.content.Context;

import com.ltn.travel.models.data.DataCenter;

public class AllSpotsPresenter implements IAllSpotsContract.Presenter {
    private Context mContext;
    private IAllSpotsContract.View mView;
    private DataCenter mDataCenter = DataCenter.getInstance();

    public AllSpotsPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(IAllSpotsContract.View iBaseView) {
        mView = iBaseView;
    }

    @Override
    public void fetchData() {

        mDataCenter.getAllSpots(new DataCenter.OnGetDataListener() {
            @Override
            public void onSuccess() {
                if (mView != null) {
                    mView.onGetDataSuccess(mDataCenter.getHomestayList());
                }
            }

            @Override
            public void onError() {
                mView.onLoading();
            }
        });
//        WSInterface apiService = ApiService.getClient().create(WSInterface.class);
//
//        Call<HomestayResponse> call = apiService.getAllSpots();
//        call.enqueue(new Callback<HomestayResponse>() {
//            @Override
//            public void onResponse(Call<HomestayResponse> call, Response<HomestayResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body() != null) {
//                        List<Homestay> homestays = response.body().getData().getHomestays();
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HomestayResponse> call, Throwable t) {
//
//            }
//        });

    }

    @Override
    public void onDestroy() {

    }

}
