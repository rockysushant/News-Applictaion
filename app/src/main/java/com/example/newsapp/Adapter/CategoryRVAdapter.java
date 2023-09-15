package com.example.newsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Modal.CategoryRVModal;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private final ArrayList<CategoryRVModal> categoryRVModals;
    private  Context context;
    private final CategoriesClickInterface categoriesClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategoriesClickInterface categoriesClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categoriesClickInterface = categoriesClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRVModal categoryRVModal = categoryRVModals.get(position);
        holder.categoryTV.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageUrl()).into(holder.categoryIV);

        holder.itemView.setOnClickListener(

                view -> categoriesClickInterface.onCategoryClick(position)
        );

    }

    @Override
    public int getItemCount() {
        return  categoryRVModals.size();
    }

    public interface CategoriesClickInterface{
        void onCategoryClick(int position);
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder{
        private final TextView categoryTV;
        private final ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            categoryIV = itemView.findViewById(R.id.idIVCategory);


        }
    }
}
