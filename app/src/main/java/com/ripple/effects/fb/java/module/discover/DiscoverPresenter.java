package com.ripple.effects.fb.java.module.discover;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;
import com.ripple.effects.fb.java.network.ApiService;
import com.ripple.effects.fb.java.network.WSInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverPresenter implements IDiscoverContract.IDiscoverPresenter {

    private Context mContext;
    private IDiscoverContract.IDiscoverView mIHomeView;
    private boolean isRefresh;
    private WSInterface mApiService = ApiService.getClient().create(WSInterface.class);

    public DiscoverPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IDiscoverContract.IDiscoverView iBaseView) {
        this.mIHomeView = iBaseView;
        isRefresh = true;
    }

    @Override
    public void onDestroy() {
        this.mIHomeView = null;
    }


    @Override
    public void onStart() {
    }

    @Override
    public void loadDataStatic() {
        WSInterface apiService = ApiService.getClient().create(WSInterface.class);

        Call<HomestayResponse> call = apiService.getTopScoreOfHomestay();
        call.enqueue(new Callback<HomestayResponse>() {
            @Override
            public void onResponse(retrofit2.Call<HomestayResponse> call, Response<HomestayResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<Homestay> homestays = response.body().getData().getHomestays();

                        if(mIHomeView!=null){
                            mIHomeView.onSuccesSpots(homestays);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<HomestayResponse> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void loadDataRecommendation() {

    }
}
