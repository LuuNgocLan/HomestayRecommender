package com.ripple.effects.fb.java.module.detail;

import android.content.Context;
import android.widget.Toast;

import com.ripple.effects.fb.java.models.detailhomestay.DetailHomestayResponse;
import com.ripple.effects.fb.java.models.detailhomestay.Homestay;
import com.ripple.effects.fb.java.network.ApiService;
import com.ripple.effects.fb.java.network.WSInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements IDetailContract.IDetailPresenter {

    private Context mContext;
    private IDetailContract.IDetailView mView;

    public DetailPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IDetailContract.IDetailView iBaseView) {
        this.mView = iBaseView;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void loadData(String homestayId) {
        WSInterface apiService = ApiService.getClient().create(WSInterface.class);

        Call<DetailHomestayResponse> call = apiService.getDetailHomestay(homestayId);
        call.enqueue(new Callback<DetailHomestayResponse>() {
            @Override
            public void onResponse(Call<DetailHomestayResponse> call, Response<DetailHomestayResponse> response) {

                if (response.code() == 200) {
                    if (response.body() != null && response.body().getHomestay() != null) {
                        Homestay homestay = response.body().getHomestay();
                        if (mView != null) {
                            mView.onSucces(homestay);
                        }
                    }
                } else {
                    mView.onError();
                }
            }

            @Override
            public void onFailure(Call<DetailHomestayResponse> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
