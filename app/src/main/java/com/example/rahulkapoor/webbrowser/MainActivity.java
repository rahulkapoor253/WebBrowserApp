package com.example.rahulkapoor.webbrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    private WebView wb;
    private String url= null;
    private EditText usertypedurl;
    private Button btngo;
    private Button btnback, btnforward, btnreload, btnclear;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb = (WebView) findViewById(R.id.wb_disp);

        WebSettings websettings = wb.getSettings();
        websettings.setJavaScriptEnabled(true);

        usertypedurl = (EditText) findViewById(R.id.et_url);
        btngo = (Button) findViewById(R.id.btn_go);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading);
        btngo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tempurl = usertypedurl.getText().toString();

                if(!tempurl.startsWith("http://")){
                    tempurl  = "http://" + tempurl;//we will add http:// to our url if we just typed www.google.com;
                }

                //to hide the keyboard asa go is pressed;
                InputMethodManager inputmanager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                //it gets the keyboard and stores in the input manager;
                inputmanager.hideSoftInputFromWindow(usertypedurl.getWindowToken(), 0);//0 means ok! to hide the keyboard;

            url = tempurl;
                wb.loadUrl(url);
                wb.setWebViewClient(new WebBrowser());//to prevent the link to open in differnt browser;
                wb.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        progressBar.setProgress(newProgress);
                        if(newProgress == 100)
                        {
                            progressBar.setVisibility(View.GONE);//if it is loaded already;
                        }
                        else
                        {
                            progressBar.setVisibility(View.VISIBLE);//if it is currently getting loaded;
                        }

                    }
                });

            }
        });

        btnback = (Button) findViewById(R.id.btn_back);
        btnforward = (Button) findViewById(R.id.btn_forward);
        btnreload = (Button) findViewById(R.id.btn_refresh);
        btnclear = (Button) findViewById(R.id.btn_clear);

        btnforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wb.canGoForward())
                {
                    wb.goForward();//it will go forward to the previously loaded page;
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wb.canGoBack())
                {
                    wb.goBack();//it will go back to the previouly loaded page;
                }
            }
        });

        btnreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wb.reload();//it will reload the current page again;
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wb.clearHistory();//it will clear the history of web browser;
            }
        });


    }
}
