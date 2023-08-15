package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.newsapp.Fragments.Home;
import com.example.newsapp.LOGIN.login;
import com.example.newsapp.MainActivity;
import com.example.newsapp.R;

public class Splash_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        // BLACK HO JATA HAI

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(() -> {

            SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
            boolean check =   pref.getBoolean("flag",false);

            Intent iNext;

            if(check){   // { FOR USER LOGGED IN}

               iNext  = new Intent(Splash_Screen.this, MainActivity.class);

           }else{  // {FOR FIRST TIME LOGIN}

               iNext = new Intent(Splash_Screen.this,login.class);
           }

           startActivity(iNext);
           finish();

        },2000);
    }
}

