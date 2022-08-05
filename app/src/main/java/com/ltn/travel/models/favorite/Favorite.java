package com.ltn.travel.models.favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ltn.travel.models.detailhomestay.Homestay;

public class Favorite {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("homestay")
    @Expose
    private Homestay homestay;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Homestay getHomestay() {
        return homestay;
    }

    public void setHomestay(Homestay homestay) {
        this.homestay = homestay;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
