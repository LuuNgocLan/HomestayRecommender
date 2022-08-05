package com.ltn.travel.module.discover;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ltn.travel.models.data.DataCenter;
import com.ltn.travel.models.homestay.Homestay;
import com.ltn.travel.models.homestay.HomestayResponse;
import com.ltn.travel.network.ApiService;
import com.ltn.travel.network.WSInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverPresenter implements IDiscoverContract.IDiscoverPresenter {

    private Context mContext;
    private IDiscoverContract.IDiscoverView mIHomeView;
    private boolean isRefresh;
    private DataCenter mDataCenter = DataCenter.getInstance();
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

        Call<HomestayResponse> call = apiService.getAllSpots();
        call.enqueue(new Callback<HomestayResponse>() {
            @Override
            public void onResponse(retrofit2.Call<HomestayResponse> call, Response<HomestayResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<Homestay> homestays = response.body().getData().getHomestays();
                        if (mIHomeView != null) {
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
        WSInterface apiService = ApiService.getClient().create(WSInterface.class);

        Call<HomestayResponse> call = apiService.getTopScoreOfHomestay();
        call.enqueue(new Callback<HomestayResponse>() {
            @Override
            public void onResponse(retrofit2.Call<HomestayResponse> call, Response<HomestayResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        Log.d("Recommend", "Success!");
                        List<Homestay> homestays = response.body().getData().getHomestays();
                        if (mIHomeView != null) {
                            mIHomeView.onSuccessRecommend(homestays);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<HomestayResponse> call, Throwable t) {
                Log.d("Recommend", "Failed");
            }
        });

    }

    @Override
    public void updateFavorite(String idHomestay) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("homestayId", idHomestay);
            jsonObject.put("isFavorite", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        mDataCenter.favorite(body, new DataCenter.OnFavoriteListener() {
            @Override
            public void onUpdateFavoriteSuccess() {
                if (mIHomeView != null) {
                    mIHomeView.onUpdateFavoriteSuccess();
                }
            }

            @Override
            public void onUpdateFavoriteFailed() {
                if (mIHomeView != null) {
                    mIHomeView.onUpdateFavoriteFailed();
                }
            }
        });
    }
}
