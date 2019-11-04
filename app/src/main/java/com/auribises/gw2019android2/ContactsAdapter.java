package com.auribises.gw2019android2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}*/

public class ContactsAdapter extends ArrayAdapter<Contact> {

    Context context;
    int resource;
    ArrayList<Contact> objects, temp;

    public ContactsAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

        temp = new ArrayList<>();
        temp.addAll(objects);
    }


    // getView will be executed n number of times. (0 to 4)
    // n is the size of objects ArrayList
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;


        view = LayoutInflater.from(context).inflate(resource, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtPhone = view.findViewById(R.id.textViewPhone);

        Contact contact = objects.get(position);
        imageView.setBackgroundResource(contact.image);
        txtName.setText(contact.name);
        txtPhone.setText(contact.phone);


        return view;

    }

    public void filter(String data){

        objects.clear();

        if(data.isEmpty()){
            objects.addAll(temp);
        }else{

            for(Contact contact : temp){
                if(contact.name.toLowerCase().contains(data.toLowerCase()) || contact.phone.contains(data)){
                    objects.add(contact);
                }
            }

        }

        notifyDataSetChanged(); // Refresh the ListView

    }
}
