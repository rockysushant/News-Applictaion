package com.example.newsapp.Fragments.Accounts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;

public class Settings extends Fragment {



    Button button;
    RatingBar ratingBar;
    float myRating = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        button = view.findViewById(R.id.button);
        ratingBar = view.findViewById(R.id.ratingBar);
        myRating =  (int) ratingBar.getRating();


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int)v;
                String message = null;


                switch (rating){
                    case 1:
                        message = "Sorry to hear this";
                        break;
                    case 2:
                        message = "You always accept Suggestion";
                        break;

                    case 3:
                        message = "Good Enough";
                        break;

                    case 4:
                        message = "Great! Thank You ";
                        break;

                    case 5:
                        message = "Awesome ! You Are Great";
                        break;


                }


//                Toast.makeText(Settings.this,message,Toast.LENGTH_SHORT).show();

            }
        });



//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Settings.this.String.valueOf(myRating),Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}