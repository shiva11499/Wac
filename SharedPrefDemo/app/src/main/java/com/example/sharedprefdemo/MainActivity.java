package com.example.sharedprefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static String namekey = "namekey";
    public static String passkey = "passkey";
    public static final String myPref = "mypref";
    EditText name,pass;
    Button btn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btn = findViewById(R.id.btnClick);
        webView = findViewById(R.id.webview);

        webView.loadUrl("https://www.google.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(namekey)) {
            name.setText(sharedPreferences.getString(namekey,""));
        }
        if(sharedPreferences.contains(passkey)) {
            pass.setText(sharedPreferences.getString(passkey,""));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = pass.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(namekey,n);
                editor.putString(passkey,p);
                editor.apply();
                editor.commit();
            }
        });

    }
}
