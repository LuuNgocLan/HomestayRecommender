package com.ripple.effects.fb.java.models.detailhomestay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHomestayResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("homestay")
    @Expose
    private Homestay homestay;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Homestay getHomestay() {
        return homestay;
    }

    public void setHomestay(Homestay homestay) {
        this.homestay = homestay;
    }

}
