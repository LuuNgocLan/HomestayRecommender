package com.ripple.effects.fb.java.models.homestay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("homestays")
    @Expose
    private List<Homestay> homestays = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Homestay> getHomestays() {
        return homestays;
    }

    public void setHomestays(List<Homestay> homestays) {
        this.homestays = homestays;
    }
}
