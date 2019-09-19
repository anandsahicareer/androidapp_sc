package com.bangalore.sahicareer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener{
    EditText et_mobileno;
    CheckBox cb_termscondition;
    Button bt_continue,bt_decline;

    String str_chechbox_sts="";
    String str_mobileno;
    private Uri uriContact;
    private String TAG = RegistrationPage.class.getSimpleName();
    boolean doubleBackToExitPressedOnce = false;
    TextView txt_terms_condition,txt_privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //enable full screen
        setContentView(R.layout.activity_registration_page);

        et_mobileno=(EditText)findViewById(R.id.edit_reg_mobileno);

        bt_continue=(Button)findViewById(R.id.bt_reg_continue);
        bt_continue.setOnClickListener(this);

        bt_decline=(Button)findViewById(R.id.bt_reg_decline);
        bt_decline.setOnClickListener(this);

        txt_terms_condition=(TextView) findViewById(R.id.txt_terms_conditions);
        txt_terms_condition.setOnClickListener(this);

        txt_privacy_policy=(TextView) findViewById(R.id.txt_privacy_policy);
        txt_privacy_policy.setOnClickListener(this);

        cb_termscondition=(CheckBox)findViewById(R.id.cb_termscondition);





        cb_termscondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    str_chechbox_sts="checked";

                }else{
                    str_chechbox_sts="";
                }
            }
        });
        et_mobileno.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if(et_mobileno.length()==10)
                {

                        /*Intent counselling = new Intent(Career_counselling.this, MembershipPlanpage.class);
                        startActivity(counselling);
*/
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_mobileno.getWindowToken(), 0);
                   // bt_continue.setBackgroundColor(bt_continue.getContext().getResources().getColor(R.color.blue));
                    bt_continue.setBackgroundResource(R.drawable.roundedbutton_otpblue);
                }
                if(et_mobileno.length()<10)
                {

                    bt_continue.setBackgroundResource(R.drawable.roundedbutton_otp);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {



            }

            public void afterTextChanged(Editable s) {


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bt_reg_continue:

                str_mobileno=et_mobileno.getText().toString();
                if(str_mobileno.equals("")){
                    //Toast.makeText(this,"Please enter a valid number", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else if(str_mobileno.length()<10){
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Mobile No.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    //v1.setTextColor(Color.RED);
                    //v1.setTextSize(14);
                    toast.show();
                }else if(str_chechbox_sts.equals("")){
                     cb_termscondition.startAnimation(AnimationUtils.loadAnimation(RegistrationPage.this, R.anim.shakenew));
                }
                else{
                    Globalvariables.mobileno=str_mobileno;

                   new SendPostRequestForOTPAPI().execute();


                }
                break;

            case R.id.txt_terms_conditions:
                Globalvariables.selected_tc_pp_status="terms_conditions";
                Intent terms_codition = new Intent(RegistrationPage.this, PrivacyPolicyPage.class);
                startActivity(terms_codition);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.txt_privacy_policy:
                Globalvariables.selected_tc_pp_status="privacy_policy";
                Intent privacy_policy = new Intent(RegistrationPage.this, PrivacyPolicyPage.class);
                startActivity(privacy_policy);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.bt_reg_decline:

                //Checking for fragment count on backstack
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else if (!doubleBackToExitPressedOnce) {
                    this.doubleBackToExitPressedOnce = true;
                    //Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

                    Toast toast = Toast.makeText(getApplicationContext(), "Please click BACK again to exit.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    //v1.setTextColor(Color.RED);
                    //v1.setTextSize(14);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                } else {
                    finishAffinity(); // Close all activites
                    System.exit(0);  // closing files, releasing resources
                }

                break;
        }
    }


    public class SendPostRequestForOTPAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/send-otp"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("mobile_number", str_mobileno);
                postDataParams.put("resend_otp", "0");

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
                String status = jsonObject.getString("msg");

                if(status.equals("OTP has been sent successfully to given number"))
                {
                    Intent aboutus = new Intent(RegistrationPage.this, OtpVerification.class);
                    startActivity(aboutus);
                    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                    Toast toast = Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                else
                    {
                    Toast toast = Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBackPressed() {

       /* //Checking for fragment count on backstack
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
            finishAffinity(); // Close all activites
            System.exit(0);  // closing files, releasing resources
        }

    }

}
