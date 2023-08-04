package com.example.newsapp.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.Fragments.Accounts.Location;
import com.example.newsapp.Fragments.Accounts.Message;
import com.example.newsapp.Fragments.Accounts.Myprofile;
import com.example.newsapp.Fragments.Accounts.Request;
import com.example.newsapp.Fragments.Accounts.Settings;
import com.example.newsapp.LOGIN.login;
import com.example.newsapp.MainActivity;
import com.example.newsapp.R;


public class Account extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView myProfile = view.findViewById(R.id.myProfile);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).loadFrag(new Myprofile(), false);

            }
        });



        TextView message = view.findViewById(R.id.messgae);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFrag(new Message(), false);

            }
        });





        TextView request = view.findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFrag(new Request(), false);

            }
        });




        TextView location = view.findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFrag(new Location(), false);

            }
        });


        TextView settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFrag(new Settings(), false);

            }
        });


        TextView btnLogout = view.findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//
//
//                editor.putBoolean("flag", false);
//                editor.apply();





                Intent i = new Intent(requireContext(),login.class);
                startActivity(i);
                requireActivity().finish();
            }
        });



    }




}