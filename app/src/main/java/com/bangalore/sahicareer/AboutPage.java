package com.bangalore.sahicareer;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bangalore.sahicareer.utils.Globalvariables;

public class AboutPage extends AppCompatActivity implements View.OnClickListener{
LinearLayout ll_abt_about_us,ll_abt_privacy_policy,ll_abt_terms_conditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_about_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("About");

        ll_abt_about_us=(LinearLayout)findViewById(R.id.ll_abt_about_us);
        ll_abt_about_us.setOnClickListener(this);
        ll_abt_privacy_policy=(LinearLayout)findViewById(R.id.ll_abt_privacy_policy);
        ll_abt_privacy_policy.setOnClickListener(this);
        ll_abt_terms_conditions=(LinearLayout)findViewById(R.id.ll_abt_terms_conditions);
        ll_abt_terms_conditions.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_abt_about_us:
                Intent about_us = new Intent(AboutPage.this, AboutUs.class);
                startActivity(about_us);
                break;
            case R.id.ll_abt_privacy_policy:
                Globalvariables.selected_tc_pp_status="privacy_policy";
                Intent privacy_policy = new Intent(AboutPage.this, PrivacyPolicyPage.class);
                startActivity(privacy_policy);
                break;
            case R.id.ll_abt_terms_conditions:
                Globalvariables.selected_tc_pp_status="terms_conditions";
                Intent terms_condition = new Intent(AboutPage.this, PrivacyPolicyPage.class);
                startActivity(terms_condition);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        finish();
        return true;
    }


}
