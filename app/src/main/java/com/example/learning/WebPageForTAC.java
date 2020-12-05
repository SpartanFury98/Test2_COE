package com.example.learning;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageForTAC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page_for_t_a_c);

        // make the back button work
        Toolbar signUpToolBar = findViewById(R.id.termsAndCondToolBar);
        setSupportActionBar(signUpToolBar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        // web page to load
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.apple.com/legal/internet-services/itunes/us/terms.html"); //  apple terms and conditions but the link works
    }
}