package com.ripple.effects.fb.java.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ripple.effects.fb.java.models.homestay.Homestay;

import java.util.List;

public class SearchResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Homestay> data = null;

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

    public List<Homestay> getData() {
        return data;
    }

    public void setData(List<Homestay> data) {
        this.data = data;
    }
}
