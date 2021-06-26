package com.motocross.moveonwheels.Models;

public class BannerModel {

    public BannerModel(){

    }
    private String image_url,id;

    public BannerModel(String image_url, String id) {
        this.image_url = image_url;
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
