package com.ripple.effects.fb.java.module.discover.allSpots;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.base.java.mvp.IBaseView;
import com.google.gson.Gson;
import com.ripple.effects.fb.java.models.data.DataCenter;
import com.ripple.effects.fb.java.models.homestay.Data;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;
import com.ripple.effects.fb.java.network.ApiService;
import com.ripple.effects.fb.java.network.WSInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ripple.effects.fb.java.FakeData.loadJSONFromAsset;

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
