package com.ripple.effects.fb.java.network;

import com.ripple.effects.fb.java.models.detailhomestay.DetailHomestayResponse;
import com.ripple.effects.fb.java.models.homestay.HomestayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WSInterface {
    @GET("homestay/")
    Call<HomestayResponse> getAllSpots();

    @GET("homestay/{homestayId}")
    Call<DetailHomestayResponse> getDetailHomestay(@Path("homestayId") String homestayId);

    @POST("homestay/top_spots")
    Call<HomestayResponse> getTopScoreOfHomestay();
}
