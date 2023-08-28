package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

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

//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }else if(id==R.id.notification){
                loadFrag(new Notification(),false);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }else if(id==R.id.account){
                loadFrag(new Account(),false);
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
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



    @SuppressLint("SuspiciousIndentation")
    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().setCustomAnimations( R.anim.slide_in_left,  // enter
                R.anim.slide_in_right,  // exit
                R.anim.slide_out_right,   // popEnter
                R.anim.slide_out_left );

        if(flag) {
            ft.add(R.id.frame_layout, fragment);
            fm.popBackStack();
        }else
            ft.replace(R.id.frame_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}