package com.example.newsapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.Adapter.CategoryRVAdapter;
import com.example.newsapp.R;



import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.newsapp.Adapter.CategoryRVAdapter;
import com.example.newsapp.Adapter.NewsRVAdapter;
import com.example.newsapp.Api.RetrofitAPI;
import com.example.newsapp.Articles;
import com.example.newsapp.Modal.CategoryRVModal;
import com.example.newsapp.Modal.NewsModal;
import com.example.newsapp.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Notification extends Fragment  implements CategoryRVAdapter.CategoriesClickInterface {

    private RecyclerView newsRv, categoryRv;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private final ArrayList<CategoryRVModal> categoryRVModalArrayList = new ArrayList<>();
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    ArrayAdapter<String> arrayAdapter;

    ListView listView;
    String[] name = {"All","Technology","Science","tech","hello"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);




    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRv = view.findViewById(R.id.idRvNews);
        categoryRv = view.findViewById(R.id.idRvCategories);
        loadingPB = view.findViewById(R.id.idProgress);

        articlesArrayList = new ArrayList<>();

        newsRVAdapter = new NewsRVAdapter(articlesArrayList, requireContext());
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList, requireContext(), this::onCategoryClick);
        newsRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        categoryRv.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        newsRv.setAdapter(newsRVAdapter);
        categoryRv.setAdapter(categoryRVAdapter);
        getCategories();

        getNews("science");

        newsRVAdapter.notifyDataSetChanged();

        listView = view.findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        requireActivity().getMenuInflater().inflate(R.menu.nav_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem;
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);

                return true;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1693115823976-7809af2b2c87?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8c2NpZW5jZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1477332552946-cfb384aeaf1c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aGVhbHRofGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnRzfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Z2VuZXJhbHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1664575599736-c5197c684128?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGJ1c2luZXNzfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://media.istockphoto.com/id/1425960645/photo/sports-fans-watching-a-match-and-celebrating-at-a-bar-rooftop.webp?b=1&s=170667a&w=0&k=20&c=_6Bq-zXMnwK5Te6WRNs1B9zx5d31leU9nufqry1vaA8="));

        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){

        category = category.toLowerCase();
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        String categoryURL = "v4/top-headlines?category=" +category+ "&lang=en&country=in&size=100&max=100&apikey=b4a658af58409c3a4ab6747ad68208bf";
        String url = "v4/top-headlines?category=general&lang=en&country=in&max=1000&apikey=b4a658af58409c3a4ab6747ad68208bf";
        String BASE_URL = "https://gnews.io/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("ALL")){
            call = retrofitAPI.getAllNews(url);

        }else{
            call  = retrofitAPI.getNewsByCategory(categoryURL);

        }

        call.enqueue(new Callback<NewsModal>() {


            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<NewsModal> call, @NonNull Response<NewsModal> response) {

                Log.d("url", response.raw().request().url().toString());
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);

                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i = 0; i<articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl(),articles.get(i).getContent()));

                }

                newsRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<NewsModal> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Fail to get News", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModalArrayList.get(position).getCategory();
        getNews(category);
    }



}