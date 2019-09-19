package com.bangalore.sahicareer;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bangalore.sahicareer.utils.Globalvariables;

public class PrivacyPolicyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        if(Globalvariables.selected_tc_pp_status.equals("terms_conditions")) {
            ab.setTitle("Terms & Conditions");
            setContentView(R.layout.activity_terms_and_conditions);
        }

        if(Globalvariables.selected_tc_pp_status.equals("privacy_policy")) {
            ab.setTitle("Privacy Policy");
            setContentView(R.layout.activity_privacy_policy_page);
        }

    }


    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        PrivacyPolicyPage.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    public void onBackPressed() {
        PrivacyPolicyPage.this.overridePendingTransition(0,0);
        finish();
    }
}
