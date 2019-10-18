package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setTitle("News");

        webView = findViewById(R.id.webView);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client); // We wish to make WebView as a Client
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.amazon.com/");
    }
}
