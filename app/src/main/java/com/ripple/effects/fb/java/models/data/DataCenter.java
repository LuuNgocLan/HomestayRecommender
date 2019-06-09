package com.ripple.effects.fb.java.models.data;

import android.util.Log;

import com.ripple.effects.fb.java.models.favorite.Favorite;
import com.ripple.effects.fb.java.models.favorite.FavoriteResponse;
import com.ripple.effects.fb.java.models.favorite.ListFavoriteResponse;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;
import com.ripple.effects.fb.java.models.profile.Profile;
import com.ripple.effects.fb.java.models.profile.ProfileResponse;
import com.ripple.effects.fb.java.network.ApiService;
import com.ripple.effects.fb.java.network.WSInterface;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class wil hold all data personal
 */
public class DataCenter {

    private OnGetDataListener mOnGetDataListener;
    private static DataCenter instance;
    private WSInterface apiService = ApiService.getClient().create(WSInterface.class);
    public static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Inh5ekBnbWFpbC5jb20iLCJ1c2VySWQiOiI1Y2RmYTVmZDQ5OTJkZWQ5YmUwMTUyZjMiLCJpYXQiOjE1NjAwNzUwMjIsImV4cCI6MTU2MDE4MzAyMn0.6SsTk1jtRcLzMKMHI74iyB0GtiliyVkZK8-AswII7Rk";

    public DataCenter() {
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }

    /**
     * Attributes
     */
    private List<Favorite> mFavorites;
    private List<Homestay> mHomestayList;
    private Profile mProfile;

    public List<Favorite> getFavorites() {
        return mFavorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        mFavorites = favorites;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Homestay> getHomestayList() {
        return mHomestayList;
    }

    public void setHomestayList(List<Homestay> homestayList) {
        mHomestayList = homestayList;
    }

    public Profile getProfile() {
        return mProfile;
    }

    public void setProfile(Profile profile) {
        mProfile = profile;
    }

    public void setOnGetDataListener(OnGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
    }

    public void getMyFavorite() {
        retrofit2.Call<ListFavoriteResponse> call = apiService.getMyFavorite(token);
        call.enqueue(new Callback<ListFavoriteResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ListFavoriteResponse> call, Response<ListFavoriteResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<Favorite> favorites = response.body().getFavorite();
                        mFavorites = favorites;
                    }
                }
            }

            @Override
            public void onFailure(Call<ListFavoriteResponse> call, Throwable t) {
                Log.d("Favorite", t.getMessage());

            }
        });
    }

    public void favorite(RequestBody body, OnFavoriteListener onFavoriteListener) {
        retrofit2.Call<FavoriteResponse> call = apiService.favorite(token, body);
        call.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(retrofit2.Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                Log.d("Favorite", response.code() + " " + response.body().getMessage());
                if (response.code() == 201) {
                    if (response.body().getCode() == 201) {
                        if (onFavoriteListener != null) {
                            onFavoriteListener.onUpdateFavoriteSuccess();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                Log.d("Favorite", t.getMessage());
                if (onFavoriteListener != null) {
                    onFavoriteListener.onUpdateFavoriteFailed();
                }
            }
        });
    }

    public boolean isFavoritedHomestay(String idHomestay) {
        if (mFavorites != null) {
            for (Favorite favorite : mFavorites) {
                if (favorite.getHomestay().getId().equals(idHomestay)) return true;
            }
        }
        return false;
    }

    public void getProfile(OnGetDataListener onGetDataListener) {
        retrofit2.Call<ProfileResponse> call = apiService.getProfile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ProfileResponse> call, Response<ProfileResponse> response) {
                Log.d("Profile", response.code() + " ");
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        if (onGetDataListener != null) {
                            mProfile = response.body().getProfile();
                            onGetDataListener.onSuccess();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.d("Profile", t.getMessage());
                if (onGetDataListener != null) {
                    mProfile = null;
                    onGetDataListener.onError();
                }
            }
        });
    }

    public void getAllSpots(OnGetDataListener onGetDataListener) {

        Call<HomestayResponse> call = apiService.getAllSpots();
        call.enqueue(new Callback<HomestayResponse>() {
            @Override
            public void onResponse(Call<HomestayResponse> call, Response<HomestayResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<Homestay> homestays = response.body().getData().getHomestays();
                        if (onGetDataListener != null && homestays != null && homestays.size() > 0) {
                            mHomestayList = homestays;
                            onGetDataListener.onSuccess();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HomestayResponse> call, Throwable t) {
                if (onGetDataListener != null) {
                    onGetDataListener.onError();
                }
            }
        });
    }

    public interface OnGetDataListener {
        void onSuccess();

        void onError();
    }

    public interface OnFavoriteListener {
        void onUpdateFavoriteSuccess();

        void onUpdateFavoriteFailed();
    }

}
