package com.bangalore.sahicareer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class NoInternetConnection extends AppCompatActivity implements View.OnClickListener {
TextView tv_wifi_connection_retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_no_internet_connection);
        tv_wifi_connection_retry=(TextView)findViewById(R.id.txt_wifi_retry);
        tv_wifi_connection_retry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txt_wifi_retry:

                Intent assessment = new Intent(NoInternetConnection.this, Loadingpage.class);
                startActivity(assessment);
                break;

                default:
                    break;
        }
    }
    public void onBackPressed() {

    }
}
