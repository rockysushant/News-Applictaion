package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.Articles;
import com.example.newsapp.NewsDetailActivity;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class NewsRVAdapter  extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {
    private final ArrayList<Articles> articlesArrayList;
    private final Context context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {

        Articles articles = articlesArrayList.get(position);
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());

        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);

        holder.itemView.setOnClickListener(
                view -> {

            Intent intent  = new Intent(context, NewsDetailActivity.class);
            intent.putExtra("title",articles.getTitle());
            intent.putExtra("content",articles.getContent());
            intent.putExtra("desc",articles.getDescription());
            intent.putExtra("image",articles.getUrlToImage());
            intent.putExtra("url",articles.getUrl());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTV,subTitleTV;
        private final ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV = itemView.findViewById(R.id.idTVSubTitle);
            newsIV = itemView.findViewById(R.id.idIvNews);
        }
    }
}
