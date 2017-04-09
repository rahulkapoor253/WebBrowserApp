package com.example.rahulkapoor.webbrowser;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by rahulkapoor on 09/04/17.
 */

class WebBrowser extends WebViewClient {

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//it should load the url in the same browser tha we are using;
        view.loadUrl(url);
        return true;
    }
}
