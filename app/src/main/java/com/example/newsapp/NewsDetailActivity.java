package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title, desc, content, imageUrl, url;


    private  TextView titleTV, subDescTV, contentTV;


    private ImageView newsIV;
    private Button readNewsBtn;
    private AppCompatImageView shareIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        titleTV = findViewById(R.id.idTVTitle);
        subDescTV = findViewById(R.id.idTVSubDesc);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVNews);
        readNewsBtn = findViewById(R.id.idBtnReadNews);
        shareIcon = findViewById(R.id.shareIcon);
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageUrl).into(newsIV);


//        readNewsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news-final-6a6d1.firebaseapp.com/"));
//                startActivity(i);
//            }
//
//        });


        readNewsBtn.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        });


        shareIcon.setOnClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        });


    }



}