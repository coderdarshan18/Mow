package com.motocross.moveonwheels.RetrofitApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    //////////////getting all categories///////////////
//    @GET("androidHome.php")
//    Call<Users> getHome();


    //////////////getting all Bannner///////////////
    @GET("banners.php")
    Call<Users> getBanners();


    /////phone register//////

    @GET("phone_registration.php")
    Call<Users> performPhoneRegistration(
            @Query("user_phone") String user_phone

    );
}
