package com.motocross.moveonwheels.Models;

public class ImageModel {

    public ImageModel(){
        ////////empty constructer ///////
    }
    String image_url;

    public ImageModel(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
