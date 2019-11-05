package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NewsAPIActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StringBuffer jsonResponse;

    void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        //FetchNewsTask task = new FetchNewsTask();
        //task.execute();

        new FetchNewsTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_api);
        initViews();
    }

    /*
    class FetchNewsThread extends Thread{
        @Override
        public void run() {

        }
    }*/

    class FetchNewsTask extends AsyncTask{ // Asynchronously | Runs in Background


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try{

                String newsApiUrl = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=31c21508fad64116acd229c10ac11e84";
                URL url = new URL(newsApiUrl);

                URLConnection urlConnection = url.openConnection(); // Send Request
                InputStream inputStream = urlConnection.getInputStream(); // Reading Response

                //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                jsonResponse = new StringBuffer();

                String line = "";

                while( (line = bufferedReader.readLine()) !=null ){
                    jsonResponse.append(line);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Log.i("JSONRESPONSE",jsonResponse.toString());
            Toast.makeText(NewsAPIActivity.this, jsonResponse.toString(), Toast.LENGTH_SHORT).show();

        }
    }
}
