package com.ripple.effects.fb.java.models.homestay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homestay {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("review_score")
    @Expose
    private Double reviewScore;
    @SerializedName("image_center")
    @Expose
    private String imageCenter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getImageCenter() {
        return imageCenter;
    }

    public void setImageCenter(String imageCenter) {
        this.imageCenter = imageCenter;
    }

}
