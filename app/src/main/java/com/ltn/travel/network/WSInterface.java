package com.ltn.travel.network;

import com.ltn.travel.models.detailhomestay.DetailHomestayResponse;
import com.ltn.travel.models.favorite.FavoriteResponse;
import com.ltn.travel.models.favorite.ListFavoriteResponse;
import com.ltn.travel.models.homestay.HomestayResponse;
import com.ltn.travel.models.login.LoginResponse;
import com.ltn.travel.models.profile.ProfileResponse;
import com.ltn.travel.models.search.SearchResponse;

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
