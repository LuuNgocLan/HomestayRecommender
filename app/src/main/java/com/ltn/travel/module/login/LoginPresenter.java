package com.ltn.travel.module.login;

import android.content.Context;
import android.util.Log;

import com.ltn.travel.models.login.LoginResponse;
import com.ltn.travel.network.ApiService;
import com.ltn.travel.network.WSInterface;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginContract.ILoginPresenter {

    private Context mContext;
    private ILoginContract.ILoginView mView;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void callLogin(String username, String password) {

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), passDataLoginToJson(username, password).toString());

        WSInterface apiService = ApiService.getClient().create(WSInterface.class);
        Call<LoginResponse> call = apiService.login(body);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("Login", String.valueOf(response.code()));
                if (response.code() >= 300) {
                    mView.onLoginFailed();
                } else if (response.code() == 200) {
                    if (response.body().getCode() == 422) {
                        mView.onLoginFailed();
                    }
                    if (response.body().getCode() == 500) {
                        mView.onLoginFailed();
                    }
                    if (response.body().getCode() == 200) {
                        //Save token
                        mView.onLoginSucces();
                    }
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("Login", t.getMessage());
                mView.onLoginFailed();
            }
        });
    }

    @Override
    public void onCreate(ILoginContract.ILoginView iBaseView) {
        mView = iBaseView;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    private JSONObject passDataLoginToJson(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
