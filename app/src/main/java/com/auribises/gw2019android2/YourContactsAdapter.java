package com.auribises.gw2019android2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YourContactsAdapter extends RecyclerView.Adapter<YourContactsAdapter.ViewHolder>{


    Context context;
    int resource;
    ArrayList<Contact> objects;

    public YourContactsAdapter(Context context, int resource, ArrayList<Contact> objects) {

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    // work n number of times
    // 0 to n-1, where n is size of ArrayList
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = objects.get(position);
        holder.imageView.setBackgroundResource(contact.image);
        holder.txtName.setText(contact.name);
        holder.txtPhone.setText(contact.phone);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // ViewHolder Represents ListItem Elements
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtName;
        TextView txtPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }

}
