package com.auribises.gw2019android2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.auribises.gw2019android2.model.News;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

// STEP-1 public class NewsAPIRecyclerAdapter
// STEP-2 extends RecyclerView.Adapter<NewsAPIRecyclerAdapter.ViewHolder>

public class NewsAPIRecyclerAdapter extends RecyclerView.Adapter<NewsAPIRecyclerAdapter.ViewHolder>{

    // STEP-4 create Constructor
    Context context;
    int resource;
    ArrayList<News> newsList;

    public NewsAPIRecyclerAdapter(Context context, int resource, ArrayList<News> newsList){
        this.context = context;
        this.resource = resource;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    // will be executed n number of times from 0 to n-1s
    // n is size of ArrayList
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        News news = newsList.get(position);
        holder.txtTitle.setText(news.title);
        holder.txtAuthor.setText(news.author);

        Picasso.get().load(news.urlToImage).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    // STEP-2
    // Nested Class
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtTitle;
        TextView txtAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtAuthor = itemView.findViewById(R.id.textViewAuthor);
        }
    }

}
