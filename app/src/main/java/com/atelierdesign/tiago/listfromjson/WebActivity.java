package com.atelierdesign.tiago.listfromjson;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends ActionBarActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);



        webView = (WebView) findViewById(R.id.WebActivity);

        String linkfromintent = getIntent().getExtras().getString("URL");

        //Extras
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);

        // para não saltar p o browser
        webView.setWebViewClient(new WebViewClient() {
            //vou override o metodo original
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true; //assim evita que abra outro browser
            }

            //so para fazer o efeito do progress  - truque giro
            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.progressbar).setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        //abrir a webview
        webView.loadUrl("http://www.reddit.com" + linkfromintent);


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




}
