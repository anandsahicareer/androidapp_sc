package com.bangalore.sahicareer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.utils.CallNowDialog;
import com.bangalore.sahicareer.utils.Globalvariables;

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
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class CareerAssessment extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    ScrollView sv;

    private String TAG = CareerAssessment.class.getSimpleName();

    Button bt_take_free_test,bt_start_assessment,bt_report_pdf_a,bt_report_pdf_b,bt_report_pdf_c,bt_report_pdf_d,bt_report_pdf_e,bt_report_pdf_f;
    LinearLayout ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;
    RadioButton rb_class_9_10,rb_class_11_12,rb_college_students,rb_job_seekeres;

    String cnt_selectedsubject,rbclickedvalue,str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;

    //ProgressDialog progressDialog;
    Dialog dialog,progree_dialog,contactus_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_career_assessment);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        //getSupportActionBar().setIcon(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progree_dialog = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.remove_border_for_progress_dialog, null);
        progree_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progree_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progree_dialog.setContentView(view);


        sv=(ScrollView)findViewById(R.id.CA_main_scrollview);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        bt_take_free_test=(Button)findViewById(R.id.CA_btn_take_freetest);
        bt_take_free_test.setOnClickListener(this);

        bt_start_assessment=(Button)findViewById(R.id.ca_start_assessment);
        bt_start_assessment.setOnClickListener(this);

        bt_report_pdf_a=(Button)findViewById(R.id.btn_report_pdf_a);
        bt_report_pdf_a.setOnClickListener(this);

        bt_report_pdf_b=(Button)findViewById(R.id.btn_report_pdf_b);
        bt_report_pdf_b.setOnClickListener(this);

        bt_report_pdf_c=(Button)findViewById(R.id.btn_report_pdf_c);
        bt_report_pdf_c.setOnClickListener(this);

        bt_report_pdf_d=(Button)findViewById(R.id.btn_report_pdf_d);
        bt_report_pdf_d.setOnClickListener(this);

        bt_report_pdf_e=(Button)findViewById(R.id.btn_report_pdf_e);
        bt_report_pdf_e.setOnClickListener(this);

        bt_report_pdf_f=(Button)findViewById(R.id.btn_report_pdf_f);
        bt_report_pdf_f.setOnClickListener(this);

        rb_class_9_10 = (RadioButton) findViewById(R.id.radio_class9_10);
        rb_class_9_10.setOnCheckedChangeListener(this);
        rb_class_11_12 = (RadioButton) findViewById(R.id.radio_class11_12);
        rb_class_11_12.setOnCheckedChangeListener(this);
        rb_college_students=(RadioButton) findViewById(R.id.radio_class_college);
        rb_college_students.setOnCheckedChangeListener(this);
        rb_job_seekeres=(RadioButton) findViewById(R.id.radio_job_seeker);
        rb_job_seekeres.setOnCheckedChangeListener(this);

        ll_footer_home=(LinearLayout)findViewById(R.id.ca_ll_footer_home);
        ll_footer_home.setOnClickListener(this);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.ca_ll_footer_callnow);
        ll_footer_callnow.setOnClickListener(this);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.ca_ll_footer_contactus);
        ll_footer_contactus.setOnClickListener(this);
        ll_footer_plans=(LinearLayout)findViewById(R.id.ca_ll_footer_plans);
        ll_footer_plans.setOnClickListener(this);
        ll_footer_profile=(LinearLayout)findViewById(R.id.ca_ll_footer_profile);
        ll_footer_profile.setOnClickListener(this);


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
                        Intent i = new Intent(CareerAssessment.this, MainActivity.class);
                        startActivity(i);

                        dl.closeDrawers();

                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(CareerAssessment.this, CareerAssessment.class);
                        startActivity(j);
                        dl.closeDrawers();
                        break;
                    case R.id.nv_item_counselling:
                        Intent k= new Intent(CareerAssessment.this, Career_counselling.class);
                        startActivity(k);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(CareerAssessment.this, Jobsandinternships.class);
                        startActivity(l);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent m = new Intent(CareerAssessment.this, ResumeService.class);
                        startActivity(m);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_learn_english:
                        Intent n = new Intent(CareerAssessment.this, LearnEnglishPage.class);
                        startActivity(n);
                        dl.closeDrawers();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.CA_btn_take_freetest:
                //sv.fullScroll(ScrollView.FOCUS_DOWN);
                sv.scrollTo(5, 380);
                break;

            case R.id.ca_ll_footer_home:
                Intent home = new Intent(CareerAssessment.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.ca_ll_footer_callnow:
                CallNowDialog callnow=new CallNowDialog();
                callnow.showDialog(CareerAssessment.this);

                break;

            case R.id.ca_ll_footer_contactus:
                ContactUsDialog contactUsDialog = new ContactUsDialog();
                contactUsDialog.showDialog(CareerAssessment.this);

                break;

            case R.id.ca_ll_footer_plans:
                Intent planpage = new Intent(CareerAssessment.this, MembershipPlanpage.class);
                startActivity(planpage);

                break;


            case R.id.ca_ll_footer_profile:
                Intent profilepage = new Intent(CareerAssessment.this, ProfilePage.class);
                startActivity(profilepage);

                break;

            case R.id.btn_report_pdf_a:
                Intent openpdfa = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdfa);
                Globalvariables.select_pdf="open_pdf_a";
                break;

            case R.id.btn_report_pdf_b:
                Intent openpdfb = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdfb);
                Globalvariables.select_pdf="open_pdf_b";
                break;

            case R.id.btn_report_pdf_c:
                Intent openpdfc = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdfc);
                Globalvariables.select_pdf="open_pdf_c";
                break;

            case R.id.btn_report_pdf_d:
                Intent openpdfd = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdfd);
                Globalvariables.select_pdf="open_pdf_d";
                break;

            case R.id.btn_report_pdf_e:
                Intent openpdfe = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdfe);
                Globalvariables.select_pdf="open_pdf_e";
                break;

            case R.id.btn_report_pdf_f:
                Intent openpdff = new Intent(CareerAssessment.this, Ca_Openreport_pdf.class);
                startActivity(openpdff);
                Globalvariables.select_pdf="open_pdf_f";
                break;

            case R.id.ca_start_assessment:
                if (rbclickedvalue != null) {
                    if(rbclickedvalue.equals("class_9_10")){
                        Globalvariables.Assessment_test_level="beginner";
                    }else if(rbclickedvalue.equals("class_11_12"))
                    {
                        Globalvariables.Assessment_test_level="intermediate";
                    }
                    else if(rbclickedvalue.equals("college_students"))
                    {
                        Globalvariables.Assessment_test_level="advanced";
                    }
                    else if(rbclickedvalue.equals("job_seekers")){
                        Globalvariables.Assessment_test_level="advanced";
                    }
                    Toast toast = Toast.makeText(getApplicationContext(), ""+Globalvariables.Assessment_test_level, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.BLACK);
                    v1.setTextSize(14);
                    toast.show();
                    Intent i = new Intent(CareerAssessment.this, AssessmentTestPage.class);
                    startActivity(i);
                 /* String URL="http://fitment.sahicareer.com/sa_test?level="+Globalvariables.Assessment_test_level;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(browserIntent);*/
                }

            else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select your group", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.BLACK);
                    v1.setTextSize(14);
                    toast.show();
                }
                break;

            default:
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radio_class9_10) {
                rb_class_11_12.setChecked(false);
                rb_college_students.setChecked(false);
                rb_job_seekeres.setChecked(false);
                rbclickedvalue="class_9_10";
            }
            if (buttonView.getId() == R.id.radio_class11_12) {
                rb_class_9_10.setChecked(false);
                rb_college_students.setChecked(false);
                rb_job_seekeres.setChecked(false);
                rbclickedvalue="class_11_12";
            }
            if (buttonView.getId() == R.id.radio_class_college) {
                rb_class_11_12.setChecked(false);
                rb_class_9_10.setChecked(false);
                rb_job_seekeres.setChecked(false);
                rbclickedvalue="college_students";
            }
            if (buttonView.getId() == R.id.radio_job_seeker) {
                rb_class_11_12.setChecked(false);
                rb_college_students.setChecked(false);
                rb_class_9_10.setChecked(false);
                rbclickedvalue="job_seekers";
            }
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


            adapterLanguage =    new ArrayAdapter<CharSequence>(CareerAssessment.this,R.layout.my_spinner_items,strarray_selectsubject);
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
                        /*TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_mobile.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Mobile No. is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                     /*   TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_mobile.length()<10){
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Mobile No.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                       /* TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_email.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Email is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        /*TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(!str_cnt_email.matches(emailPattern)){
                        Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                       /* TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);*/
                        toast.show();
                    }else if(str_cnt_message.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Message is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        /*TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
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
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/


}
