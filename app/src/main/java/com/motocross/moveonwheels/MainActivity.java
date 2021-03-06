package com.motocross.moveonwheels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.motocross.moveonwheels.Models.ImageModel;
import com.motocross.moveonwheels.RetrofitApi.ApiClient;
import com.motocross.moveonwheels.RetrofitApi.ApiInterface;
import com.motocross.moveonwheels.RetrofitApi.Users;
import com.motocross.moveonwheels.Session.SessionManager;
import com.motocross.moveonwheels.phoneLoginRegister.PhoneRegisterActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ImageModel> imageModelList;
   // private ImageAdpter imageAdpter;
    private LinearLayout emailContinue,phoneContinue;
    SessionManager sessionManager;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        /////////hide status////
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ///////hide status end//////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        emailContinue=(LinearLayout) findViewById(R.id.linear1);
        phoneContinue=(LinearLayout) findViewById(R.id.linear2);



        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setKeepScreenOn(true);
        recyclerView.setHasFixedSize(true);

//        plateModelList = new ArrayList<>();
//        Call<Users> platesCall = apiInterface.getPlates();
//        platesCall.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//                plateModelList=response.body().getPlates();
//                plateAdpter = new PlateAdpter(plateModelList,MainActivity.this);
//                recyclerView.setAdapter(plateAdpter);
//                plateAdpter.notifyDataSetChanged();
//                //////////call autoscroll start/////////
//                autoScroll();
//
//                //////////call autoscroll end/////////
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//
//            }
//        });
//        imageModelList = new ArrayList<>();
//        Call<Users> imageCall = apiInterface.getHome();
//        imageCall.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//                imageModelList = response.body().getHome();
//                imageAdpter = new ImageAdpter(imageModelList,MainActivity.this);
//                recyclerView.setAdapter(imageAdpter);
//                imageAdpter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//
//            }
//        });
//



        //////continue with email start///////
        emailContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });

        /////end of email////////

        ///start of phone////
        phoneContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneRegisterActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });

        ////end of phone/////

    }
//    public void autoScroll(){
//        final int speedScroll =0;
//        final Handler handler = new Handler();
//        final Runnable runnable= new Runnable() {
//            int count=0;
//            @Override
//            public void run() {
//                if(count == plateAdpter.getItemCount())
//                    count=0;
//                if(count < plateAdpter.getItemCount()){
//                    recyclerView.smoothScrollToPosition(++count);
//                    handler.postDelayed(this,speedScroll);
//                }
//
//            }
//        };
//        handler.postDelayed(runnable,speedScroll);
//    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sessionManager.isLogin()){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            Animatoo.animateSwipeLeft(this);
        }
        else {

        }

    }
}