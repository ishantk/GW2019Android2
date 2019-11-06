package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.auribises.gw2019android2.model.News;

public class NewsAPIActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StringBuffer jsonResponse;

    ArrayList<News> newsList;

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


    void parseJSONResponse(){

        try{

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            String status = jsonObject.getString("status");
            int total = jsonObject.getInt("totalResults");

            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            newsList = new ArrayList<>();

            //for(int i=0;i<total;i++){
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jObj = jsonArray.getJSONObject(i);
                News news = new News();
                news.author = jObj.getString("author");
                news.title = jObj.getString("title");
                news.description = jObj.getString("description");
                news.url = jObj.getString("url");
                news.urlToImage = jObj.getString("urlToImage");
                newsList.add(news);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // Nested or Inner Class
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

            parseJSONResponse();

        }
    }
}
