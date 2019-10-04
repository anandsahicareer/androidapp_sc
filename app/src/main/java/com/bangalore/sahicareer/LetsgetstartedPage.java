package com.bangalore.sahicareer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class LetsgetstartedPage extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    LinearLayout ll_iam_in_9th_or_10th,ll_iam_in_11th_or_12th,ll_iam_studying_in_college,ll_iam_lookin_for_job,ll_iam_working_professionals,ll_iam_parent,
            ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.6F);
    Dialog dialog;

    String str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;
    private String TAG = LetsgetstartedPage.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_letsgetstarted_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        //getSupportActionBar().setIcon(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ll_iam_in_9th_or_10th=(LinearLayout) findViewById(R.id.ma_ll_iam_in_9th_or_10th);
        ll_iam_in_9th_or_10th.setOnClickListener(this);

        ll_iam_in_11th_or_12th=(LinearLayout) findViewById(R.id.ma_ll_iam_in_11th_or_12th);
        ll_iam_in_11th_or_12th.setOnClickListener(this);

        ll_iam_studying_in_college=(LinearLayout) findViewById(R.id.ma_ll_iam_studying_in_college);
        ll_iam_studying_in_college.setOnClickListener(this);

        ll_iam_lookin_for_job=(LinearLayout) findViewById(R.id.ma_ll_iam_looking_for_job);
        ll_iam_lookin_for_job.setOnClickListener(this);

        ll_iam_working_professionals=(LinearLayout) findViewById(R.id.ma_ll_iam_working_professional);
        ll_iam_working_professionals.setOnClickListener(this);

        ll_footer_home=(LinearLayout)findViewById(R.id.lgs_ll_footer_home);
        ll_footer_home.setOnClickListener(this);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.lgs_ll_footer_callnow);
        ll_footer_callnow.setOnClickListener(this);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.lgs_ll_footer_contactus);
        ll_footer_contactus.setOnClickListener(this);
        ll_footer_plans=(LinearLayout)findViewById(R.id.lgs_ll_footer_plans);
        ll_footer_plans.setOnClickListener(this);
        ll_footer_profile=(LinearLayout)findViewById(R.id.lgs_ll_footer_profile);
        ll_footer_profile.setOnClickListener(this);

        ll_iam_parent=(LinearLayout) findViewById(R.id.ma_ll_iam_a_parent);
        ll_iam_parent.setOnClickListener(this);
        nv = (NavigationView)findViewById(R.id.nav_view);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nv_item_home:
                        Intent i = new Intent(LetsgetstartedPage.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(LetsgetstartedPage.this, CareerAssessment.class);
                        startActivity(j);
                        break;
                    case R.id.nv_item_counselling:
                        Intent k= new Intent(LetsgetstartedPage.this, Career_counselling.class);
                        startActivity(k);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(LetsgetstartedPage.this, Jobsandinternships.class);
                        startActivity(l);
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent resume_service = new Intent(LetsgetstartedPage.this, ResumeService.class);
                        startActivity(resume_service);
                        break;

                    case R.id.nv_item_learn_english:
                        Intent learn_english = new Intent(LetsgetstartedPage.this, LearnEnglishPage.class);
                        startActivity(learn_english);
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
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.ma_ll_iam_in_9th_or_10th:

                v.startAnimation(buttonClick);
                Intent iam_in_9th_or_10th = new Intent(LetsgetstartedPage.this, Land_10th_11th_class.class);
                startActivity(iam_in_9th_or_10th);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.ma_ll_iam_in_11th_or_12th:

                v.startAnimation(buttonClick);
                Intent iam_in_11th_or_12th = new Intent(LetsgetstartedPage.this, Land_10th_11th_class.class);
                startActivity(iam_in_11th_or_12th);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.ma_ll_iam_studying_in_college:

                v.startAnimation(buttonClick);
                Intent iam_studing_in_college = new Intent(LetsgetstartedPage.this, Land_iam_in_college.class);
                startActivity(iam_studing_in_college);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.ma_ll_iam_looking_for_job:

                v.startAnimation(buttonClick);
                Intent iam_looking_for_job = new Intent(LetsgetstartedPage.this, Land_looking_for_job.class);
                startActivity(iam_looking_for_job);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;


            case R.id.ma_ll_iam_working_professional:

                v.startAnimation(buttonClick);
                Intent iam_working_professionals = new Intent(LetsgetstartedPage.this, Land_iam_working_professional.class);
                startActivity(iam_working_professionals);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.ma_ll_iam_a_parent:

                v.startAnimation(buttonClick);
                Intent iam_a_parent = new Intent(LetsgetstartedPage.this, Land_Iam_parent.class);
                startActivity(iam_a_parent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            /*case R.id.ma_ll_represent_school_and_college:

                v.startAnimation(buttonClick);
                Intent represent_school_college = new Intent(LetsgetstartedPage.this, Land_Iam_parent.class);
                startActivity(represent_school_college);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;
*/
            /*case R.id.ma_ll_represent_institution:

                v.startAnimation(buttonClick);
                Intent represent_institution = new Intent(LetsgetstartedPage.this, Land_Iam_parent.class);
                startActivity(represent_institution);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;*/


            case R.id.lgs_ll_footer_home:
                Intent home = new Intent(LetsgetstartedPage.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.lgs_ll_footer_callnow:
                CallNowDialog callnowdialog = new CallNowDialog();
                callnowdialog.showDialog(LetsgetstartedPage.this);
                break;

            case R.id.lgs_ll_footer_contactus:
                ContactUsDialog contactUsDialog = new ContactUsDialog();
                contactUsDialog.showDialog(LetsgetstartedPage.this);

                break;

            case R.id.lgs_ll_footer_plans:
                Intent planpage = new Intent(LetsgetstartedPage.this, MembershipPlanpage.class);
                startActivity(planpage);

                break;


            case R.id.lgs_ll_footer_profile:
                Intent profilepage = new Intent(LetsgetstartedPage.this, ProfilePage.class);
                startActivity(profilepage);

                break;
            default:
                break;
        }
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


            adapterLanguage =    new ArrayAdapter<CharSequence>(LetsgetstartedPage.this,R.layout.my_spinner_items,strarray_selectsubject);
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
                        v1.setTextColor(Color.BLACK);
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
                    }
                    else if(str_cnt_message.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Message is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);
                        toast.show();
                    }
                    else{
                        /*Toast toast = Toast.makeText(getApplicationContext(), ""+str_cnt_name+":"+str_cnt_mobile+":"+str_cnt_email+":"+str_cnt_subject+":"+str_cnt_message, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.GREEN);
                        v1.setTextSize(14);
                        toast.show();*/
                        new CountactUsAPI().execute();
                        // do it here main function
                        //dialog.cancel();
                        if (Globalvariables.progressDialog == null) {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(LetsgetstartedPage.this);
                            Globalvariables.progressDialog.show();
                        } else {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(LetsgetstartedPage.this);
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
