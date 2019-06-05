package com.ripple.effects.fb.java.module.search;

import android.content.Context;
import android.util.Log;

import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;
import com.ripple.effects.fb.java.models.search.SearchResponse;
import com.ripple.effects.fb.java.network.ApiService;
import com.ripple.effects.fb.java.network.WSInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements ISearchContract.IPresenter {
    private Context mContext;
    private ISearchContract.IView mIView;

    public SearchPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void search(String keyword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        WSInterface apiService = ApiService.getClient().create(WSInterface.class);
        Call<SearchResponse> call = apiService.search(body);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<Homestay> homestays = response.body().getData();
                        if (homestays != null && mIView != null) {
                            mIView.onSearchSuccess(homestays);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("Search", t.getMessage());
                if (mIView != null) {
                    mIView.onError();
                }
            }
        });

    }

    @Override
    public void onCreate(ISearchContract.IView iBaseView) {
        mIView = iBaseView;
    }

    @Override
    public void onDestroy() {

    }
}
