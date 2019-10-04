package com.bangalore.sahicareer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.adapter.PrivatejobsAdapter;
import com.bangalore.sahicareer.bean.InternshipBean;
import com.bangalore.sahicareer.bean.PrivatejobBean;
import com.bangalore.sahicareer.utils.CallNowDialog;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PrivateJobCategory extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    LinearLayout ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;

    private List<PrivatejobBean> privatejoblist = new ArrayList<>();
    private RecyclerView rv_privatejobs;
    private PrivatejobsAdapter mAdapter;

    Spinner sp_category,sp_location;
    ArrayAdapter<CharSequence> adapterCategory;
    String[] strarray_category = {"Full Time Jobs","Freelancers","Internships","Non-Profit NGOs","Other Jobs","Part Time Jobs","Placement - Recruitment Agencies","Summer Trainees - Interns","Work Abroad","Work From Home"};

    ArrayAdapter<CharSequence> adapterLocation;
    String[] strarray_location;

    private String TAG = PrivateJobCategory.class.getSimpleName();
    Dialog dialog;
    String cnt_selectedsubject,str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;

    ProgressDialog dialogshow;
    String str_sp_location,str_sp_category;
    TextView txt_privatejob_totalcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_private_job_category);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ll_footer_home=(LinearLayout)findViewById(R.id.private_ll_footer_home);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.private_ll_footer_callnow);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.private_ll_footer_contactus);
        ll_footer_plans=(LinearLayout)findViewById(R.id.private_ll_footer_plans);
        ll_footer_profile=(LinearLayout)findViewById(R.id.private_ll_footer_profile);

        txt_privatejob_totalcount=(TextView)findViewById(R.id.tv_privatejob_totalcount);

        sp_category  =(Spinner)findViewById(R.id.sp_privatejobs_category);
        sp_location  =(Spinner)findViewById(R.id.sp_privatejobs_location);

        adapterCategory=  new ArrayAdapter<CharSequence>(PrivateJobCategory.this,R.layout.my_spinner_items,strarray_category);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(adapterCategory);

        dialogshow = new ProgressDialog(PrivateJobCategory.this);

        new LocationsAPI().execute();

        new PrivateJobsAllDataAPI().execute();

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        nv = (NavigationView)findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nv_item_home:
                        Intent i = new Intent(PrivateJobCategory.this, MainActivity.class);
                        startActivity(i);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(PrivateJobCategory.this, CareerAssessment.class);
                        startActivity(j);
                        dl.closeDrawers();
                        break;
                    case R.id.nv_item_counselling:
                        Intent k= new Intent(PrivateJobCategory.this, Career_counselling.class);
                        startActivity(k);
                        dl.closeDrawers();
                        break;
                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(PrivateJobCategory.this, Jobsandinternships.class);
                        startActivity(l);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent resume_service = new Intent(PrivateJobCategory.this, ResumeService.class);
                        startActivity(resume_service);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_learn_english:
                        Intent learn_english = new Intent(PrivateJobCategory.this, LearnEnglishPage.class);
                        startActivity(learn_english);
                        dl.closeDrawers();
                        break;
                    default:
                        return true;
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
                        Intent aboutus = new Intent(PrivateJobCategory.this, MainActivity.class);
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
                        CallNowDialog callnowdialog = new CallNowDialog();
                        callnowdialog.showDialog(PrivateJobCategory.this);
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
                        contactUsDialog.showDialog(PrivateJobCategory.this);

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

                        Intent aboutus = new Intent(PrivateJobCategory.this, MembershipPlanpage.class);
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

                        Intent aboutus = new Intent(PrivateJobCategory.this, ProfilePage.class);
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






       /* adapterLocation=  new ArrayAdapter<CharSequence>(PrivateJobCategory.this,R.layout.my_spinner_items,strarray_location);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_location.setAdapter(adapterLocation);*/

        rv_privatejobs = (RecyclerView) findViewById(R.id.rv_privatejobs);

        mAdapter = new PrivatejobsAdapter(privatejoblist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_privatejobs.setLayoutManager(mLayoutManager);
        rv_privatejobs.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_privatejobs.setAdapter(mAdapter);

        rv_privatejobs.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_privatejobs, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PrivatejobBean privatejobs = privatejoblist.get(position);
                Toast.makeText(getApplicationContext(), privatejobs.getPrivatejob_url(), Toast.LENGTH_SHORT).show();
                Globalvariables.privatejob_url=privatejobs.getPrivatejob_url();
                Intent myIntent = new Intent(PrivateJobCategory.this, PrivateJoburlPage.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
       // prepareMovieData();

        sp_location.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        // Ensure you call it only once works for JELLY_BEAN and later
                        sp_location.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // add the listener
                        sp_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                // check if pos has changed
                                // then do your work
                                str_sp_location  = sp_location.getSelectedItem().toString();
                                new PrivateJobsAllDataAPI().execute();
                                   /* sp_language.setBackgroundResource(R.drawable.roundededittext);
                                    sp_modeofselection.setBackgroundResource(R.drawable.roundedspinner);*/
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                    /*sp_language.setBackgroundResource(R.drawable.roundededittext);
                                    sp_modeofselection.setBackgroundResource(R.drawable.roundedspinner);*/
                            }

                        });

                    }
                });

        sp_category.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        // Ensure you call it only once works for JELLY_BEAN and later
                        sp_category.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // add the listener
                        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                // check if pos has changed
                                // then do your work

                                str_sp_category  = sp_category.getSelectedItem().toString();
                                new PrivateJobsAllDataAPI().execute();
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                    /*sp_language.setBackgroundResource(R.drawable.roundededittext);
                                    sp_modeofselection.setBackgroundResource(R.drawable.roundedspinner);*/
                            }

                        });

                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;
       /* switch(item.getItemId()) {
            case R.id.action_name:
                //your code
                Intent k= new Intent(CareerAssessment.this, Career_counselling.class);
                startActivity(k);
                break;
        }
*/
        return super.onOptionsItemSelected(item);
    }


    public class ContactUsDialog {

        public void showDialog(Activity activity) {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.contactus_customlayout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Button mDialogSubmit = dialog.findViewById(R.id.cntbtn_submit);
            final EditText cnt_name=dialog.findViewById(R.id.et_cntname);
            final EditText cnt_mobile=dialog.findViewById(R.id.et_cntmobile);
            final EditText cnt_email=dialog.findViewById(R.id.et_cntemail);
            final EditText cnt_message=dialog.findViewById(R.id.et_cntmessage);

            cnt_name.setText(Globalvariables.username, TextView.BufferType.EDITABLE);
            cnt_mobile.setText(Globalvariables.mobileno, TextView.BufferType.EDITABLE);
            cnt_email.setText(Globalvariables.email, TextView.BufferType.EDITABLE);

            String[] strarray_selectsubject = {"Others","Partners Inquiry", "Careers"};
            ArrayAdapter<CharSequence> adapterLanguage;

            final Spinner sp_subjects =(Spinner)dialog.findViewById(R.id.spinner_subjects);


            adapterLanguage =    new ArrayAdapter<CharSequence>(PrivateJobCategory.this,R.layout.my_spinner_items,strarray_selectsubject);
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

                        if (Globalvariables.progressDialog == null) {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(PrivateJobCategory.this);
                            Globalvariables.progressDialog.show();
                        } else {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(PrivateJobCategory.this);
                            Globalvariables.progressDialog.show();
                        }
                    }
                }
            });

            Button mDialogOk = dialog.findViewById(R.id.cntbtn_cancel);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            dialog.show();
        }
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
                    String msg = jsonObject.getString("msg");
                    if (msg.equals("Email sent successfully")) {
                        Globalvariables.progressDialog.dismiss();
                        dialog.cancel();
                        Toast toast = Toast.makeText(getApplicationContext(), "Thank you for getting in touch!\n" +
                                "One of our executive will get back to you shortly.\n", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(14);
                        toast.show();
                    }

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

    public class LocationsAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/get-privatejob-cities"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                /*postDataParams.put("user_name", str_cnt_name);
                postDataParams.put("user_email", str_cnt_email);
                postDataParams.put("subject", str_cnt_subject);
                postDataParams.put("message", str_cnt_message);
                postDataParams.put("user_mobile", str_cnt_mobile);*/

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

                JSONArray arr = new JSONArray(result);
                List<String> list = new ArrayList<String>();
                list.add("Bangalore");
                for(int i = 0; i < arr.length(); i++){

                    list.add(arr.getJSONObject(i).getString("city"));
                }

                strarray_location = list.toArray(new String[0]);
                adapterLocation=  new ArrayAdapter<CharSequence>(PrivateJobCategory.this,R.layout.my_spinner_items,strarray_location);
                adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_location.setAdapter(adapterLocation);
                   /* JSONObject object = new JSONObject(result);
                    JSONArray Jarray  = object.getJSONArray("cities");

                    for (int i = 0; i < Jarray.length(); i++)
                    {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                    }*/

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
    public class PrivateJobsAllDataAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){

            privatejoblist.clear();

            dialogshow.setMessage("Fetching details...");
            dialogshow.setCancelable(false);
            dialogshow.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialogshow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogshow.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/get-privatejobs"); // here is your URL path

                //str_sp_location  = sp_location.getSelectedItem().toString();

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("city", str_sp_location);
                if (str_sp_category != null) {

                    postDataParams.put("category", str_sp_category);
                }
                else{
                    str_sp_category="Full Time Jobs";
                    postDataParams.put("category", str_sp_category);
                }




                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(30000 /* milliseconds */);
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

            /*try {
                JSONObject jsonObject = new JSONObject(result);
                String cities = jsonObject.getString("cities");*/

            try {


                   /* ArrayList<String> stringArray = new ArrayList<String>();
                    JSONArray jsonArray = new JSONArray(result);
                    for(int i = 0, count = jsonArray.length(); i< count; i++)
                    {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            stringArray.add(jsonObject.toString());
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }*/

                JSONArray jsonarry = new JSONArray(result);
                for(int i = 0; i < jsonarry.length(); i++){

                    PrivatejobBean PrivateJobItems = new PrivatejobBean();
                    /*for (int i = 0; i < jsonArr.length(); i++)
                    {
                        JSONObject jsonObj1 = jsonArr.getJSONObject(i);
*/
                    JSONObject jsonObj = jsonarry.getJSONObject(i);
                    String privatejob_id=jsonObj.getString("id");
                    String privatejob_title = jsonObj.getString("title");
                    String privatejob_salary = jsonObj.getString("salary");
                    String privatejob_url = jsonObj.getString("url");
                    String privatejob_category = jsonObj.getString("category");
                    String privatejob_city = jsonObj.getString("city");
                    String privatejob_designation = jsonObj.getString("designation");


                    PrivateJobItems.setPrivatejob_id(privatejob_id);
                    PrivateJobItems.setPrivatejob_title(privatejob_title);

                    if(privatejob_salary.equals("")){
                        PrivateJobItems.setPrivatejob_salary("Not Defined");
                    }
                    else{
                        PrivateJobItems.setPrivatejob_salary(privatejob_salary);
                    }
                    PrivateJobItems.setPrivatejob_url(privatejob_url);
                    PrivateJobItems.setPrivatejob_category(privatejob_category);
                    PrivateJobItems.setPrivatejob_location(privatejob_city);
                    PrivateJobItems.setPrivatejob_designation(privatejob_designation);
                    //InternItems.set(imei);
                    // GlobalVariables.imei=imei;

                    privatejoblist.add(PrivateJobItems);
                }
                //Globalvariables.progressDialog.dismiss();
                int jobcount=jsonarry.length();
                txt_privatejob_totalcount.setText(""+jobcount);
                mAdapter.notifyDataSetChanged();


            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                       /* Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();*/
                    }
                });

            }
            dialogshow.dismiss();
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

}