package com.bangalore.sahicareer;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class InternshipUrlPage extends AppCompatActivity implements View.OnClickListener{
    private WebView webView;
    String URL;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    LinearLayout ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;
    Dialog dialog;
    String str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;

    private String TAG = InternshipUrlPage.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_internship_url_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ll_footer_home=(LinearLayout)findViewById(R.id.intern_url_ll_footer_home);
        ll_footer_home.setOnClickListener(this);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.intern_url_ll_footer_callnow);
        ll_footer_callnow.setOnClickListener(this);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.intern_url_ll_footer_contactus);
        ll_footer_contactus.setOnClickListener(this);
        ll_footer_plans=(LinearLayout)findViewById(R.id.intern_url_ll_footer_plans);
        ll_footer_plans.setOnClickListener(this);
        ll_footer_profile=(LinearLayout)findViewById(R.id.intern_url_ll_footer_profile);
        ll_footer_profile.setOnClickListener(this);

        URL= Globalvariables.internship_url;
        webView = (WebView) findViewById(R.id.wv_internship_url);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView .getSettings().setDomStorageEnabled(true);
        // webView.loadUrl("https://stackoverflow.com/questions/4331094/add-a-progress-bar-in-webview");

        // Makes Progress bar Visible
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        //webView = (WebView) findViewById(R.id.webView1);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        webView.setWebViewClient(new InternshipUrlPage.HelloWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }

        haveStoragePermission();

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
                        Intent i = new Intent(InternshipUrlPage.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(InternshipUrlPage.this, CareerAssessment.class);
                        startActivity(j);
                        break;
                    case R.id.nv_item_counselling:
                        Intent k= new Intent(InternshipUrlPage.this, Career_counselling.class);
                        startActivity(k);
                        break;

                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(InternshipUrlPage.this, Jobsandinternships.class);
                        startActivity(l);
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent resume_service = new Intent(InternshipUrlPage.this, ResumeService.class);
                        startActivity(resume_service);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_learn_english:
                        Intent learn_english = new Intent(InternshipUrlPage.this, LearnEnglishPage.class);
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
    //this is required for Navigation
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.intern_url_ll_footer_home:
                Intent home = new Intent(InternshipUrlPage.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.intern_url_ll_footer_callnow:
                CallNowDialog callnowdialog = new CallNowDialog();
                callnowdialog.showDialog(InternshipUrlPage.this);
                break;

            case R.id.intern_url_ll_footer_contactus:
                ContactUsDialog contactUsDialog = new ContactUsDialog();
                contactUsDialog.showDialog(InternshipUrlPage.this);

                break;

            case R.id.intern_url_ll_footer_plans:
                Intent planpage = new Intent(InternshipUrlPage.this, MembershipPlanpage.class);
                startActivity(planpage);

                break;
            case R.id.intern_url_ll_footer_profile:
                Intent profile = new Intent(InternshipUrlPage.this, ProfilePage.class);
                startActivity(profile);

                break;
                default:
                    break;
        }

        }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }

    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                webView.loadUrl(URL );
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");

            webView.loadUrl(URL);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //you have the permission now.

            webView.loadUrl(URL);
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


            adapterLanguage =    new ArrayAdapter<CharSequence>(InternshipUrlPage.this,R.layout.my_spinner_items,strarray_selectsubject);
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
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(InternshipUrlPage.this);
                            Globalvariables.progressDialog.show();
                        } else {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(InternshipUrlPage.this);
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

                java.net.URL url = new URL("https://www.sahicareer.com/contact-team"); // here is your URL path



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
                                "One of our colleagues will get back to you shortly.\n", Toast.LENGTH_LONG);
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
