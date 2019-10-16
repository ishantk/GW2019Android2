package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1, 101, 0, "All Songs");
                                                            // Action Item
        menu.add(1, 201, 0, "Artists").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(1, 301, 0, "Recently Played");
        menu.add(1, 401, 0, "Favourites");
        menu.add(1, 501, 0, "Mostly Played");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Toast.makeText(this, "Item ID: "+id, Toast.LENGTH_SHORT).show();

        switch (id){
            case 101:
                Intent intent = new Intent(HomeActivity.this, ActivityOne.class);
                startActivity(intent);
                break;

            case 201:

                break;

            case 301:

                break;

            case 401:

                break;

            case 501:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
