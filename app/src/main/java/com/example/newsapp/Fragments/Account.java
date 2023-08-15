package com.example.newsapp.Fragments;

import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.Fragments.Accounts.Location;
import com.example.newsapp.Fragments.Accounts.Message;
import com.example.newsapp.Fragments.Accounts.Myprofile;
import com.example.newsapp.Fragments.Accounts.Request;
import com.example.newsapp.Fragments.Accounts.Settings;
import com.example.newsapp.LOGIN.login;
import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Account extends Fragment {

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;
    private TextView title, desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private void initializeFirebaseInstance() {
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
    }

    private void getUserDataFromFirebase() {
        DocumentReference documentReference = fstore.collection("user").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = documentSnapshot.getString("fName");
                title.setText(userName);
                desc.setText(documentSnapshot.getString("phone"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("failure got the data", e.toString());
            }
        });
    }

    private void initView(View view){
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFirebaseInstance();
        initView(view);
        getUserDataFromFirebase();
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



        //LOG - OUT


        TextView btnLogout = view.findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences pref = requireContext().getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();


               editor.clear();
                editor.apply();


                Toast.makeText(requireContext(),"LOg Out Successfully",Toast.LENGTH_SHORT).show();
                Intent  iHome  = new Intent(requireContext(), login.class);
                startActivity(iHome);
                requireActivity().finish();







//
//
//                Intent i = new Intent(requireContext(),login.class);
//                startActivity(i);
//                requireActivity().finish();

//                Toast.makeText(login.thi)
            }
        });






    }




}