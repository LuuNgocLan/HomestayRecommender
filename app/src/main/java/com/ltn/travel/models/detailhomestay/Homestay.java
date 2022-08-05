package com.ltn.travel.models.detailhomestay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Homestay {

    @SerializedName("about")
    @Expose
    private List<String> about = null;
    @SerializedName("house_facilities")
    @Expose
    private List<String> houseFacilities = null;
    @SerializedName("available_for")
    @Expose
    private List<String> availableFor = null;
    @SerializedName("area_facilities")
    @Expose
    private List<String> areaFacilities = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("num_reviews")
    @Expose
    private Integer numReviews;
    @SerializedName("review_score")
    @Expose
    private Double reviewScore;
    @SerializedName("image_center")
    @Expose
    private String imageCenter;
    @SerializedName("list_review")
    @Expose
    private List<Review> review = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("house_rules")
    @Expose
    private String houseRules;
    @SerializedName("meals")
    @Expose
    private String meals;
    @SerializedName("about_area")
    @Expose
    private String aboutArea;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<String> getAbout() {
        return about;
    }

    public void setAbout(List<String> about) {
        this.about = about;
    }

    public List<String> getHouseFacilities() {
        return houseFacilities;
    }

    public void setHouseFacilities(List<String> houseFacilities) {
        this.houseFacilities = houseFacilities;
    }

    public List<String> getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(List<String> availableFor) {
        this.availableFor = availableFor;
    }

    public List<String> getAreaFacilities() {
        return areaFacilities;
    }

    public void setAreaFacilities(List<String> areaFacilities) {
        this.areaFacilities = areaFacilities;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
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

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(String houseRules) {
        this.houseRules = houseRules;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getAboutArea() {
        return aboutArea;
    }

    public void setAboutArea(String aboutArea) {
        this.aboutArea = aboutArea;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
