package com.bangalore.sahicareer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class MainLandingPage extends AppCompatActivity implements View.OnClickListener{
Button bt_9th_10th,bt_11th_12th,bt_iam_in_college,bt_looking_for_job,bt_iam_working_professional,
        bt_iam_parent,bt_i_represent_school,bt_i_represent_college,iam_just_exploring;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main_landing_page);


        bt_9th_10th=(Button)findViewById(R.id.mlp_btn_9th_10th_class);
        bt_9th_10th.setOnClickListener(this);

        bt_11th_12th=(Button)findViewById(R.id.mlp_btn_11th_12th_class);
        bt_11th_12th.setOnClickListener(this);

        bt_iam_in_college=(Button)findViewById(R.id.mlp_btn_iam_in_college);
        bt_iam_in_college.setOnClickListener(this);

        bt_looking_for_job=(Button)findViewById(R.id.mlp_btn_looking_for_job);
        bt_looking_for_job.setOnClickListener(this);

        bt_iam_working_professional=(Button)findViewById(R.id.mlp_btn_iam_working_professional);
        bt_iam_working_professional.setOnClickListener(this);

        bt_iam_parent=(Button)findViewById(R.id.mlp_btn_iam_parent);
        bt_iam_parent.setOnClickListener(this);

        bt_i_represent_school=(Button)findViewById(R.id.mlp_btn_represent_school);
        bt_i_represent_school.setOnClickListener(this);

        bt_i_represent_college=(Button)findViewById(R.id.mlp_btn_represent_college);
        bt_i_represent_college.setOnClickListener(this);

        iam_just_exploring=(Button)findViewById(R.id.mlp_btn_iam_just_exploring);
        iam_just_exploring.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mlp_btn_9th_10th_class:

                Intent land_10th_11th = new Intent(MainLandingPage.this, Land_10th_11th_class.class);
                startActivity(land_10th_11th);
               // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_11th_12th_class:

                Intent land_11th_12th = new Intent(MainLandingPage.this, Land_10th_11th_class.class);
                startActivity(land_11th_12th);
               // overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                break;

            case R.id.mlp_btn_iam_in_college:

                Intent land_iam_in_college = new Intent(MainLandingPage.this, Land_iam_in_college.class);
                startActivity(land_iam_in_college);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_looking_for_job:

                Intent land_looking_for_job = new Intent(MainLandingPage.this, Land_looking_for_job.class);
                startActivity(land_looking_for_job);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_iam_working_professional:

                Intent iam_working_professional = new Intent(MainLandingPage.this, Land_iam_working_professional.class);
                startActivity(iam_working_professional);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_iam_parent:

                Intent iam_parent = new Intent(MainLandingPage.this, Land_Iam_parent.class);
                startActivity(iam_parent);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
            case R.id.mlp_btn_represent_school:

                Intent represent_school = new Intent(MainLandingPage.this, Land_Iam_parent.class);
                startActivity(represent_school);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_represent_college:

                Intent represent_college = new Intent(MainLandingPage.this, Land_Iam_parent.class);
                startActivity(represent_college);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;

            case R.id.mlp_btn_iam_just_exploring:

                Intent iam_just_exploring = new Intent(MainLandingPage.this, MainActivity.class);
                startActivity(iam_just_exploring);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;


            default:
                break;
        }



    }

    public void onBackPressed() {

        /*//Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            *//*super.onBackPressed();
            return;*//*
        }*/

    }

}
