package com.motocross.moveonwheels.RetrofitApi;

import com.google.gson.annotations.SerializedName;
import com.motocross.moveonwheels.Models.BannerModel;
import com.motocross.moveonwheels.Models.ImageModel;

import java.util.List;

public class Users {

    @SerializedName("response")
    private String Response;

    @SerializedName("user_id")
    private String UserId;

    @SerializedName("banners")
    private List<BannerModel> banners;

    public Users(String response, String userId, List<BannerModel> banners) {
        Response = response;
        UserId = userId;
        this.banners = banners;
    }

    public String getResponse() {
        return Response;
    }

    public String getUserId() {
        return UserId;
    }

    public List<BannerModel> getBanners() {
        return banners;
    }
}


//    @SerializedName("status")
//    private  String Status;
//
//    @SerializedName("message")
//    private  String Message;
//
//    @SerializedName("androidHome")
//    private List<ImageModel> home;


