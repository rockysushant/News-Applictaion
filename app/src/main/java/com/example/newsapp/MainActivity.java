package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.Adapter.CategoryRVAdapter;
import com.example.newsapp.Adapter.NewsRVAdapter;
import com.example.newsapp.Api.RetrofitAPI;
import com.example.newsapp.Fragments.Account;
import com.example.newsapp.Fragments.Home;
import com.example.newsapp.Fragments.Notification;
import com.example.newsapp.Modal.CategoryRVModal;
import com.example.newsapp.Modal.NewsModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {
    BottomNavigationView bnView;

//    private RecyclerView newsRv, categoryRv;
//    private ProgressBar loadingPB;
//    private ArrayList<Articles> articlesArrayList;
//    private ArrayList<CategoryRVModal> categoryRVModalArrayList = new ArrayList<>();
//    private CategoryRVAdapter categoryRVAdapter;
//    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        newsRv = findViewById(R.id.idRvNews);
//        categoryRv = findViewById(R.id.idRvCategories);
//        loadingPB = findViewById(R.id.idProgress);
//        articlesArrayList = new ArrayList<>();
//
//        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
//        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
//        newsRv.setLayoutManager(new LinearLayoutManager(this));
//        newsRv.setAdapter(newsRVAdapter);
//        categoryRv.setAdapter(categoryRVAdapter);
//        getCategories();
//        getNews("Science");
//        newsRVAdapter.notifyDataSetChanged();


        bnView = findViewById(R.id.bnView);

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.home){
                    loadFrag(new Home(),false);
//                    getSupportActionBar().hide();


                }else if(id==R.id.notification){

                    loadFrag(new Notification(),false);
//                    getSupportActionBar().show();
//                    getSupportActionBar().setTitle(" All Categories");


                }else if(id==R.id.account){

                    loadFrag(new Account(),false);
//                    getSupportActionBar().show();
//                    getSupportActionBar().setTitle(" All Categories");
                }
                return true;
            }
        });

        bnView.setSelectedItemId(R.id.home);

    }


    @SuppressLint("SuspiciousIndentation")
    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag) {
            ft.add(R.id.frame_layout, fragment);
            fm.popBackStack();
        }else
            ft.replace(R.id.frame_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();

    }




//
//
//    private void getCategories(){
//        categoryRVModalArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1570900808791-d530855f79e3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YWxsfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8c2NpZW5jZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1477332552946-cfb384aeaf1c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aGVhbHRofGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnRzfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Z2VuZXJhbHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1664575599736-c5197c684128?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGJ1c2luZXNzfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
//        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://media.istockphoto.com/id/1425960645/photo/sports-fans-watching-a-match-and-celebrating-at-a-bar-rooftop.webp?b=1&s=170667a&w=0&k=20&c=_6Bq-zXMnwK5Te6WRNs1B9zx5d31leU9nufqry1vaA8="));
//
//
//        categoryRVAdapter.notifyDataSetChanged();
//    }
//
//    private void getNews(String category){
//        loadingPB.setVisibility(View.VISIBLE);
//        articlesArrayList.clear();
//
//        String categoryURL = "v2/top-headlines?category=" +category+ "&apiKey=c020f1d1cda04895ae5c3b4834c4f463";
//        String url = "v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=c020f1d1cda04895ae5c3b4834c4f463";
//        String BASE_URL = "https://newsapi.org/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
//        Call<NewsModal> call;
//        if(category.equals("ALL")){
//            call = retrofitAPI.getAllNews(url);
//
//        }else{
//            call  = retrofitAPI.getNewsByCategory(categoryURL);
//
//        }
//
//        call.enqueue(new Callback<NewsModal>() {
//
//
//
//            @Override
//            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
//                Log.d("url", response.raw().request().url().toString());
//                NewsModal newsModal = response.body();
//                loadingPB.setVisibility(View.GONE);
//                ArrayList<Articles> articles = newsModal.getArticles();
//                for(int i = 0; i<articles.size(); i++){
//                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),
//                            articles.get(i).getUrl(),articles.get(i).getContent()));
//
//                }
//
//                newsRVAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<NewsModal> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Fail to get News", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//    @Override
//    public void onCategoryClick(int position) {
//        String category = categoryRVModalArrayList.get(position).getCategory();
//        getNews(category);
//    }
}