package com.bangalore.sahicareer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bangalore.sahicareer.app.AppController;
import com.bangalore.sahicareer.utils.Globalvariables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = MainActivity.class.getSimpleName();

    ScrollView sv;
    LinearLayout ll_assessment_test,ll_counselling,ll_jobs_and_internships,ll_careerinformation,ll_resume_service,ll_learning_english,ll_mentorship,
                 ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile,
                 ll_trustedlogo_institute,ll_trustedlogo_resume,ll_trustedlogo_testtaken,ll_trustedlogo_activeusers,ll_trustedlogo_counselled,ll_trustedlogo_mentorship,
                 ll_represent_school_college,ll_represent_institutions;

    int sv_rotatecount=0,custmersayschangecount=0;
    HorizontalScrollView sv_topimages,sv_trustedcompanies;
    Handler handler = new Handler();
    Runnable runnablelive = null;
    TextView tv_customersays1,tv_customersays2,tv_customersays3,tv_customername1,tv_customername2,tv_customername3;
    Button hsv_btn_careerassessment,hsv_btn_careercounselling,hsv_btn_resumeservice,hsv_btn_jobsandinternships,hsv_btn_knowledgecentre,
            hsv_btn_schoolandcolleges,hsv_btn_mentorship,btn_letsgetstarted,btn_needhelp,btn_for_institution;
    String cnt_selectedsubject,str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.6F);

    Dialog progree_dialog,dialog,callnow_dialog,contactus_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);


        sv=(ScrollView)findViewById(R.id.main_scrollview);

        progree_dialog = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.remove_border_for_progress_dialog, null);
        progree_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progree_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progree_dialog.setContentView(view);


        ll_assessment_test=(LinearLayout)findViewById(R.id.ll_service_assessment_test);
        ll_counselling=(LinearLayout)findViewById(R.id.ll_service_counselling);
        ll_careerinformation=(LinearLayout)findViewById(R.id.ll_service_information);
        ll_jobs_and_internships=(LinearLayout)findViewById(R.id.ll_service_jobs_and_intern);
        ll_resume_service=(LinearLayout)findViewById(R.id.ll_service_resume_service);
        ll_mentorship=(LinearLayout)findViewById(R.id.ll_service_mentorship);
        ll_learning_english=(LinearLayout)findViewById(R.id.ll_service_Learning_english);


        btn_letsgetstarted=(Button) findViewById(R.id.bt_letsgetstarted);
        btn_letsgetstarted.setOnClickListener(this);

        btn_for_institution=(Button) findViewById(R.id.bt_needhelp);
        btn_for_institution.setOnClickListener(this);

        btn_needhelp=(Button) findViewById(R.id.bt_needhelp);
        btn_needhelp.setOnClickListener(this);

        ll_footer_home=(LinearLayout)findViewById(R.id.ll_footer_home);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.ll_footer_callnow);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.ll_footer_contactus);
        ll_footer_plans=(LinearLayout)findViewById(R.id.ll_footer_plans);
        ll_footer_profile=(LinearLayout)findViewById(R.id.ll_footer_profile);


        ll_trustedlogo_institute=(LinearLayout)findViewById(R.id.ll_trustedlogo_institute);
        ll_trustedlogo_resume=(LinearLayout)findViewById(R.id.ll_trustedlogo_resume);
        ll_trustedlogo_testtaken=(LinearLayout)findViewById(R.id.ll_trustedlogo_testtaken);
        ll_trustedlogo_activeusers=(LinearLayout)findViewById(R.id.ll_trustedlogo_activeusers);
        ll_trustedlogo_counselled=(LinearLayout)findViewById(R.id.ll_trustedlogo_counselled);
        ll_trustedlogo_mentorship=(LinearLayout)findViewById(R.id.ll_trustedlogo_mentorship);

        hsv_btn_careerassessment=(Button)findViewById(R.id.hsv_btn_careerassessment);
        hsv_btn_careerassessment.setOnClickListener(this);

        hsv_btn_careercounselling=(Button)findViewById(R.id.hsv_btn_careercounselling);
        hsv_btn_careercounselling.setOnClickListener(this);

        hsv_btn_resumeservice=(Button)findViewById(R.id.hsv_btn_resumeservice);
        hsv_btn_resumeservice.setOnClickListener(this);

        hsv_btn_jobsandinternships=(Button)findViewById(R.id.hsv_btn_jobsandinternship);
        hsv_btn_jobsandinternships.setOnClickListener(this);

        hsv_btn_knowledgecentre=(Button)findViewById(R.id.hsv_btn_knowledgecentre);
        hsv_btn_knowledgecentre.setOnClickListener(this);

        hsv_btn_schoolandcolleges=(Button)findViewById(R.id.hsv_btn_schoolandcolleges);
        hsv_btn_schoolandcolleges.setOnClickListener(this);

        hsv_btn_mentorship=(Button)findViewById(R.id.hsv_btn_mentorship);
        hsv_btn_mentorship.setOnClickListener(this);

       /* Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the time of the blink with this parameter
        anim.setStartOffset(50);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        btn_letsgetstarted.startAnimation(anim);*/
        /*ll_represent_school_college=(LinearLayout) findViewById(R.id.ma_ll_represent_school_and_college);
        ll_represent_school_college.setOnClickListener(this);

        ll_represent_institutions=(LinearLayout) findViewById(R.id.ma_ll_represent_institution);
        ll_represent_institutions.setOnClickListener(this);*/

        //new ProfileDetailsAPI().execute();


        ll_assessment_test.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent counselling = new Intent(MainActivity.this, CareerAssessment.class);
                        startActivity(counselling);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        ll_counselling.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        Intent counselling = new Intent(MainActivity.this, Career_counselling.class);
                        startActivity(counselling);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_jobs_and_internships.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent counselling = new Intent(MainActivity.this, Jobsandinternships.class);
                        startActivity(counselling);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_resume_service.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent counselling = new Intent(MainActivity.this, ResumeService.class);
                        startActivity(counselling);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_learning_english.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent counselling = new Intent(MainActivity.this, LearnEnglishPage.class);
                        startActivity(counselling);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_footer_home.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        sv.fullScroll(ScrollView.FOCUS_UP);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_footer_callnow.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        /*Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:18001031610"));
                        startActivity(intent);*/
                        CallNowDialog callnowdialog = new CallNowDialog();
                        callnowdialog.showDialog(MainActivity.this);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_footer_contactus.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        ContactUsDialog contactUsDialog = new ContactUsDialog();
                        contactUsDialog.showDialog(MainActivity.this);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_footer_plans.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent aboutus = new Intent(MainActivity.this, MembershipPlanpage.class);
                        startActivity(aboutus);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_footer_profile.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        Intent aboutus = new Intent(MainActivity.this, ProfilePage.class);
                        startActivity(aboutus);
                        //new ProfileDetailsAPI().execute();

                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
        ll_trustedlogo_institute.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        ContactUsDialog contactUsDialog1 = new ContactUsDialog();
                        contactUsDialog1.showDialog(MainActivity.this);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_trustedlogo_resume.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        Intent testtaken = new Intent(MainActivity.this, ResumeService.class);
                        startActivity(testtaken);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
        ll_trustedlogo_testtaken.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        Intent testtaken = new Intent(MainActivity.this, CareerAssessment.class);
                        startActivity(testtaken);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_trustedlogo_testtaken.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        Intent testtaken = new Intent(MainActivity.this, CareerAssessment.class);
                        startActivity(testtaken);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_trustedlogo_testtaken.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent testtaken = new Intent(MainActivity.this, CareerAssessment.class);
                        startActivity(testtaken);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ll_trustedlogo_counselled.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click

                        Intent counselled = new Intent(MainActivity.this, Career_counselling.class);
                        startActivity(counselled);
                    case MotionEvent.ACTION_CANCEL: {
                        LinearLayout view = (LinearLayout) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });



    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.hsv_btn_careerassessment:
                //imagebutton.setPadding(30, 30, 30, 30);
                //hsv_btn_careerassessment.setAlpha(.5f);

                view.startAnimation(buttonClick);
                Intent assessment = new Intent(MainActivity.this, CareerAssessment.class);
                startActivity(assessment);
                break;

            case R.id.hsv_btn_careercounselling:

                view.startAnimation(buttonClick);
                Intent counselling = new Intent(MainActivity.this, Career_counselling.class);
                startActivity(counselling);
                break;

            case R.id.hsv_btn_resumeservice:

                view.startAnimation(buttonClick);
                Intent resumeservice = new Intent(MainActivity.this, ResumeService.class);
                startActivity(resumeservice);
                break;

            case R.id.hsv_btn_jobsandinternship:

                view.startAnimation(buttonClick);
                Intent jobsandinternship = new Intent(MainActivity.this, Jobsandinternships.class);
                startActivity(jobsandinternship);
                break;

            case R.id.hsv_btn_knowledgecentre:

                Toast toast = Toast.makeText(getApplicationContext(), "This page is coming soon", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                //v1.setTextColor(Color.RED);
                //v1.setTextSize(14);
                toast.show();
                break;

            case R.id.hsv_btn_schoolandcolleges:

                Toast toast1 = Toast.makeText(getApplicationContext(), "This page is coming soon", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                TextView v2 = (TextView) toast1.getView().findViewById(android.R.id.message);
                //v1.setTextColor(Color.RED);
                //v1.setTextSize(14);
                toast1.show();
                break;


            case R.id.hsv_btn_mentorship:

                Toast toast2 = Toast.makeText(getApplicationContext(), "This page is coming soon", Toast.LENGTH_SHORT);
                toast2.setGravity(Gravity.CENTER, 0, 0);
                TextView v3= (TextView) toast2.getView().findViewById(android.R.id.message);
                //v1.setTextColor(Color.RED);
                //v1.setTextSize(14);
                toast2.show();
                break;

/*
            case R.id.ma_ll_represent_school_and_college:

                view.startAnimation(buttonClick);
                Intent represent_school_college = new Intent(MainActivity.this, Land_Iam_parent.class);
                startActivity(represent_school_college);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;*/

           /* case R.id.ma_ll_represent_institution:

                view.startAnimation(buttonClick);
                Intent represent_institution = new Intent(MainActivity.this, Land_Iam_parent.class);
                startActivity(represent_institution);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;*/

            case R.id.bt_letsgetstarted:

                view.startAnimation(buttonClick);
                Intent letget = new Intent(MainActivity.this, LetsgetstartedPage.class);
                startActivity(letget);
                break;


            case R.id.bt_needhelp:

                view.startAnimation(buttonClick);
                Intent needhelp = new Intent(MainActivity.this, LetsgetsStartedMain.class);
                startActivity(needhelp);
                break;
            default:
                break;
        }



    }

    public class CallNowDialog {

        public void showDialog(Activity activity) {
            callnow_dialog = new Dialog(activity);
            callnow_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            callnow_dialog.setCancelable(false);
            callnow_dialog.setContentView(R.layout.callus_customlayout);
            callnow_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Button callnow = callnow_dialog.findViewById(R.id.frmCallnow);
            //final EditText et_mobileno=dialog.findViewById(R.id.edit_mobileno);
            callnow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callnow_dialog.dismiss();
                  Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:18001031610"));
                        startActivity(intent);

                }
            });

            Button cancel = callnow_dialog.findViewById(R.id.frmCancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    callnow_dialog.cancel();
                }
            });

            callnow_dialog.show();
        }
    }
    public class ContactUsDialog {

        public void showDialog(Activity activity) {
            contactus_dialog = new Dialog(activity);
            contactus_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            contactus_dialog.setCancelable(false);
            contactus_dialog.setContentView(R.layout.contactus_customlayout);
            contactus_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Button mDialogSubmit = contactus_dialog.findViewById(R.id.cntbtn_submit);
            final EditText cnt_name=contactus_dialog.findViewById(R.id.et_cntname);
            final EditText cnt_mobile=contactus_dialog.findViewById(R.id.et_cntmobile);
            final EditText cnt_email=contactus_dialog.findViewById(R.id.et_cntemail);
            final EditText cnt_message=contactus_dialog.findViewById(R.id.et_cntmessage);

            cnt_name.setText(Globalvariables.username, TextView.BufferType.EDITABLE);
            cnt_mobile.setText(Globalvariables.mobileno, TextView.BufferType.EDITABLE);
            cnt_email.setText(Globalvariables.email, TextView.BufferType.EDITABLE);

            String[] strarray_selectsubject = {"Others","Partners Inquiry", "Careers"};
            ArrayAdapter<CharSequence> adapterLanguage;

            final Spinner sp_subjects =(Spinner)contactus_dialog.findViewById(R.id.spinner_subjects);


            adapterLanguage =    new ArrayAdapter<CharSequence>(MainActivity.this,R.layout.my_spinner_items,strarray_selectsubject);
            adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_subjects.setAdapter(adapterLanguage);

            mDialogSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    str_cnt_name=cnt_name.getText().toString();
                    str_cnt_mobile=cnt_mobile.getText().toString();
                    str_cnt_email=cnt_email.getText().toString();
                    str_cnt_message=cnt_message.getText().toString();
                    str_cnt_subject  = sp_subjects.getSelectedItem().toString();

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    cnt_selectedsubject  = sp_subjects.getSelectedItem().toString();
                    if(str_cnt_name.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Name is Required.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();

                    }
                    else if(str_cnt_mobile.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Mobile No. is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();

                    }
                    else if(str_cnt_mobile.length()<10){
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Mobile No.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();

                    }
                    else if(str_cnt_email.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Email is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();

                    }
                    else if(!str_cnt_email.matches(emailPattern)){
                        Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }else if(str_cnt_message.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Message is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();
                    }
                    else{
                       /* Toast toast = Toast.makeText(getApplicationContext(), ""+str_cnt_name+":"+str_cnt_mobile+":"+str_cnt_email+":"+cnt_selectedsubject+":"+str_cnt_message, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.GREEN);
                        v1.setTextSize(14);
                        toast.show();
                        // do it here main function*/
                        new CountactUsAPI().execute();
                        progree_dialog.show();

                    }
                }
            });

            Button mDialogOk = contactus_dialog.findViewById(R.id.cntbtn_cancel);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    contactus_dialog.cancel();
                }
            });
            contactus_dialog.show();
        }
    }
    private void TimerForScrolviewRotattion() {

        handler = new Handler();
        runnablelive = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                //dou you work here
                if(sv_rotatecount==0) {
                    sv_trustedcompanies.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    sv_rotatecount=1;
                }else if(sv_rotatecount==1) {
                    sv_trustedcompanies.fullScroll(HorizontalScrollView.FOCUS_LEFT);
                    sv_rotatecount=0;
                }

                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(runnablelive, 10000);
    }

    private void TimerForCustomersays() {

        handler = new Handler();
        runnablelive = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                if(custmersayschangecount==0) {
                    TranslateAnimation animObj= new TranslateAnimation(0,tv_customersays1.getWidth(), 0, 0);
                    animObj.setDuration(2000);
                    tv_customersays1.startAnimation(animObj);
                    tv_customersays2.startAnimation(animObj);
                    tv_customersays3.startAnimation(animObj);

                    tv_customername1.startAnimation(animObj);
                    tv_customername2.startAnimation(animObj);
                    tv_customername3.startAnimation(animObj);

                    tv_customersays1.setText(R.string.customersays4);
                    tv_customername1.setText(R.string.customername4);
                    tv_customersays2.setText(R.string.customersays5);
                    tv_customername2.setText(R.string.customername5);
                    tv_customersays3.setText(R.string.customersays6);
                    tv_customername3.setText(R.string.customername6);
                    custmersayschangecount=1;
                }else if(custmersayschangecount==1) {
                    TranslateAnimation animObj= new TranslateAnimation(0,tv_customersays1.getWidth(), 0, 0);
                    animObj.setDuration(2000);
                    tv_customersays1.startAnimation(animObj);
                    tv_customersays2.startAnimation(animObj);
                    tv_customersays3.startAnimation(animObj);

                    tv_customername1.startAnimation(animObj);
                    tv_customername2.startAnimation(animObj);
                    tv_customername3.startAnimation(animObj);

                    tv_customersays1.setText(R.string.customersays1);
                    tv_customername1.setText(R.string.customername1);
                    tv_customersays2.setText(R.string.customersays2);
                    tv_customername2.setText(R.string.customername2);
                    tv_customersays3.setText(R.string.customersays3);
                    tv_customername3.setText(R.string.customername3);
                    custmersayschangecount=0;
                }
                handler.postDelayed(this, 20000);
            }
        };
        handler.postDelayed(runnablelive, 20000);
    }
    public class CountactUsAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/contact-team"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_name", str_cnt_name);
                postDataParams.put("user_email", str_cnt_email);
                postDataParams.put("subject", str_cnt_subject);
                postDataParams.put("message", str_cnt_message);
                postDataParams.put("user_mobile", str_cnt_mobile);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("success");

                if(status.equals("True")){
                    /*Globalvariables.progressDialog.dismiss();
                    dialog.cancel();*/
                    Toast toast = Toast.makeText(getApplicationContext(), "Thank you for getting in touch!\n" +
                            "One of our executive will get back to you shortly.\n", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                    contactus_dialog.dismiss();

                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Something Went Wrong! Please Try after Sometime...", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                }
                progree_dialog.dismiss();


            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

        }
    }

    public class ProfileDetailsAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/user-profile"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", Globalvariables.user_id);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("success");
                if(status.equals("True")) {
                    String username = jsonObject.getString("username");
                    Globalvariables.username=username;
                    String mobileno = jsonObject.getString("usermobile");
                    Globalvariables.mobileno=mobileno;
                    String Email_id = jsonObject.getString("useremail");
                    Globalvariables.email=Email_id;
                    String current_page = jsonObject.getString("current_page");
                    Globalvariables.current_page=current_page;
                }
                else{
                    String msg = jsonObject.getString("msg");
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                }

            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        String aa=result.toString();

        return result.toString();

    }
    public void onBackPressed() {

    }



}