package com.motocross.moveonwheels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.motocross.moveonwheels.Adapter.BannerAdapter;
import com.motocross.moveonwheels.Fragement.homeFragment;
import com.motocross.moveonwheels.Fragement.locationFragment;
import com.motocross.moveonwheels.Fragement.monthlyFragment;
import com.motocross.moveonwheels.Fragement.profileFragment;
import com.motocross.moveonwheels.Fragement.tarrifFragment;
import com.motocross.moveonwheels.Models.BannerModel;
import com.motocross.moveonwheels.RetrofitApi.ApiClient;
import com.motocross.moveonwheels.RetrofitApi.ApiInterface;
import com.motocross.moveonwheels.RetrofitApi.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    public static ApiInterface apiInterface;

    private RecyclerView recyclerViewBanner;
    private BannerAdapter bannerAdapter;
    private List<BannerModel> bannerModelList;
    DatePickerDialog datePickerDialog;
    Button  btnSearch;
    TextView pickupdate,returnDate,pickupTime,returnTime;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomNavigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
            apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        }

        pickupdate = (TextView) findViewById(R.id.pickDate);
        returnDate = (TextView) findViewById(R.id.dropDate);
        pickupTime =(TextView)  findViewById(R.id.pickTime);
        returnTime =(TextView)  findViewById(R.id.dropTime);
        btnSearch = (Button) findViewById(R.id.btnSearch);

//        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
//        currentMinute = calendar.get(Calendar.MINUTE);
//                SimpleDateFormat format = new SimpleDateFormat("HH/mm/ss");
//                String time = "Current Time:"+format.format(calendar.getTime());
//                viewHolder.edTime.setText(time);

//

        pickupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(HomeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                pickupdate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(HomeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                returnDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        pickupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
//                SimpleDateFormat format = new SimpleDateFormat("HH/mm/ss");
//                String time = format.format(calendar.getTime());
//                pickupTime.setText(time);

                timePickerDialog = new TimePickerDialog(HomeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        pickupTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
//                         pickupTime.setText(String.format("%02d",hourOfDay));


                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();


            }



        });


        returnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
//                SimpleDateFormat format = new SimpleDateFormat("HH/mm/ss");
//                String time = "Current Time:"+format.format(calendar.getTime());
//                viewHolder.edTime.setText(time);

                timePickerDialog = new TimePickerDialog(HomeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        returnTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
//                        returnTime.setText(String.format("%02d",hourOfDay));


                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();


            }



        });









        init();

    }



    private void init(){
        recyclerViewBanner = (RecyclerView)findViewById(R.id.recylerView2);
        LinearLayoutManager layoutManagerBanner = new LinearLayoutManager(HomeActivity.this);
        layoutManagerBanner.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewBanner.setLayoutManager(layoutManagerBanner);

        bannerModelList = new ArrayList<>();
        Call<Users> bannerCall = apiInterface.getBanners();
        bannerCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                bannerModelList = response.body().getBanners();
                bannerAdapter = new BannerAdapter(bannerModelList,HomeActivity.this);
                recyclerViewBanner.setAdapter(bannerAdapter);
                bannerAdapter.notifyDataSetChanged();
                autoScroll();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }
    public void autoScroll(){
        final int speedScroll =0;
        final Handler handler = new Handler();
        final Runnable runnable= new Runnable() {
            int count=0;
            @Override
            public void run() {
                if(count == bannerAdapter.getItemCount())
                    count=0;
                if(count < bannerAdapter.getItemCount()){
                    recyclerViewBanner.smoothScrollToPosition(++count);
                    handler.postDelayed(this,speedScroll);
                }

            }
        };
        handler.postDelayed(runnable,speedScroll);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment= new homeFragment();
                            break;

                        case R.id.monthly:
                            selectedFragment=new monthlyFragment();
                            break;

                        case R.id.tarrif:
                            selectedFragment=new tarrifFragment();
                            break;

                        case R.id.location:
                            selectedFragment = new locationFragment();
                            break;

                        case R.id.profile:
                            selectedFragment = new profileFragment();
                            break;

                    }
                    /////////replacing by defalut fragment on home activity//////////
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                            selectedFragment).commit();
                    return true;
                }
            };
}