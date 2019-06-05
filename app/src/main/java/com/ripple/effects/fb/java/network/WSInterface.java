package com.ripple.effects.fb.java.network;

import com.ripple.effects.fb.java.models.detailhomestay.DetailHomestayResponse;
import com.ripple.effects.fb.java.models.favorite.FavoriteResponse;
import com.ripple.effects.fb.java.models.favorite.ListFavoriteResponse;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;
import com.ripple.effects.fb.java.models.login.LoginResponse;
import com.ripple.effects.fb.java.models.profile.ProfileResponse;
import com.ripple.effects.fb.java.models.search.SearchResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WSInterface {
    @GET("homestay/")
    Call<HomestayResponse> getAllSpots();

    @POST("homestay/search")
    Call<SearchResponse> search(@Body RequestBody body);

    @GET("homestay/{homestayId}")
    Call<DetailHomestayResponse> getDetailHomestay(@Path("homestayId") String homestayId);

    @POST("homestay/top_spots")
    Call<HomestayResponse> getTopScoreOfHomestay();

    @POST("user/login")
    Call<LoginResponse> login(@Body RequestBody body);

    @GET("api/favorite/")
    Call<ListFavoriteResponse> getMyFavorite(@Header("Authorization") String token);

    @POST("api/favorite/")
    Call<FavoriteResponse> favorite(@Header("Authorization") String token,
                                    @Body RequestBody body);

    @GET("user/profile")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);


}
