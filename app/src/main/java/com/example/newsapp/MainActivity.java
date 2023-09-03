package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.newsapp.Fragments.Account;
import com.example.newsapp.Fragments.Home;
import com.example.newsapp.Fragments.Notification;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    BottomNavigationView bnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bnView = findViewById(R.id.bnView);

        bnView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.home){
                loadFrag(new Home(),false);

            }else if(id==R.id.notification){
                loadFrag(new Notification(),false);

            }else if(id==R.id.account){
                loadFrag(new Account(),false);

            }
            return true;

        });
        bnView.setSelectedItemId(R.id.home);

    }


    //  LOG OUT DIALOUGE

//    public  void showAlertDialog(View v){
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("The Daily Planet");
//        alert.setMessage("Do you want to exit!!");
//        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }


    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if(fragment instanceof Account || fragment instanceof Notification){
            bnView.setSelectedItemId(R.id.home);
            loadFrag(new Home(), false);
        }else if (fragment instanceof Home){
            finishAffinity();
        }else{
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @SuppressLint("SuspiciousIndentation")
    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction()
                .setCustomAnimations( R.anim.slide_in_right, // enter
                R.anim.slide_out_right // exit
//                R.anim.slide_in_left,   // popEnter
//                R.anim.slide_out_left
                );

        if(flag) {
            ft.add(R.id.frame_layout, fragment);
            fm.popBackStack();
        }else
            ft.replace(R.id.frame_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}