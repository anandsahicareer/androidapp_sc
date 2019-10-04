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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class Resume_EditTemplateSections extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    LinearLayout ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;
    Dialog progree_dialog,dialog,callnow_dialog,contactus_dialog,rsume_template_dialog;
    private String TAG = ResumeBuilder.class.getSimpleName();
    String cnt_selectedsubject,str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_subject,str_cnt_message;

    Button bt_contact_details_arrow;
    int cnt_details_arrow_sts=0;

    LinearLayout ll_contact_details,ll_summary,ll_employment_details,ll_professional_details,ll_educational_details,
            ll_awards_certificates,ll_hobbies_interests,ll_languages,ll_socialmedias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_resume_edit_template_sections);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progree_dialog = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.remove_border_for_progress_dialog, null);
        progree_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progree_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progree_dialog.setContentView(view);

        ll_footer_home=(LinearLayout)findViewById(R.id.ts_ll_footer_home);
        ll_footer_home.setOnClickListener(this);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.ts_ll_footer_callnow);
        ll_footer_callnow.setOnClickListener(this);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.ts_ll_footer_contactus);
        ll_footer_contactus.setOnClickListener(this);
        ll_footer_plans=(LinearLayout)findViewById(R.id.ts_ll_footer_plans);
        ll_footer_plans.setOnClickListener(this);
        ll_footer_profile=(LinearLayout)findViewById(R.id.ts_ll_footer_profile);
        ll_footer_profile.setOnClickListener(this);

        ll_contact_details=(LinearLayout)findViewById(R.id.rb_ll_contact_details);
        ll_contact_details.setOnClickListener(this);

        ll_summary=(LinearLayout)findViewById(R.id.rb_ll_summary);
        ll_summary.setOnClickListener(this);

        ll_employment_details=(LinearLayout)findViewById(R.id.rb_ll_employment_details);
        ll_employment_details.setOnClickListener(this);

        ll_professional_details=(LinearLayout)findViewById(R.id.rb_ll_professional_skills);
        ll_professional_details.setOnClickListener(this);

        ll_educational_details=(LinearLayout)findViewById(R.id.rb_ll_educational_details);
        ll_educational_details.setOnClickListener(this);

        ll_awards_certificates=(LinearLayout)findViewById(R.id.rb_ll_awards_certifcate);
        ll_awards_certificates.setOnClickListener(this);

        ll_hobbies_interests=(LinearLayout)findViewById(R.id.rb_ll_hobbies_interest);
        ll_hobbies_interests.setOnClickListener(this);

        ll_languages=(LinearLayout)findViewById(R.id.rb_ll_languages);
        ll_languages.setOnClickListener(this);

        ll_socialmedias=(LinearLayout)findViewById(R.id.rb_ll_socialmedias);
        ll_socialmedias.setOnClickListener(this);

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
                        Intent i = new Intent(Resume_EditTemplateSections.this, MainActivity.class);
                        startActivity(i);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(Resume_EditTemplateSections.this, CareerAssessment.class);
                        startActivity(j);
                        dl.closeDrawers();
                        break;
                    case R.id.nv_item_counselling:
                        Intent k= new Intent(Resume_EditTemplateSections.this, Career_counselling.class);
                        startActivity(k);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(Resume_EditTemplateSections.this, Jobsandinternships.class);
                        startActivity(l);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent m = new Intent(Resume_EditTemplateSections.this, ResumeService.class);
                        startActivity(m);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_learn_english:
                        Intent learn_english = new Intent(Resume_EditTemplateSections.this, LearnEnglishPage.class);
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
            case R.id.ts_ll_footer_home:
                Intent home = new Intent(Resume_EditTemplateSections.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.ts_ll_footer_callnow:
                CallNowDialog callnow = new CallNowDialog();
                callnow.showDialog(Resume_EditTemplateSections.this);

                break;

            case R.id.ts_ll_footer_contactus:
                ContactUsDialog contactUsDialog = new ContactUsDialog();
                contactUsDialog.showDialog(Resume_EditTemplateSections.this);
                break;

            case R.id.ts_ll_footer_plans:
                Intent planpage = new Intent(Resume_EditTemplateSections.this, MembershipPlanpage.class);
                startActivity(planpage);
                break;


            case R.id.ts_ll_footer_profile:
                Intent profilepage = new Intent(Resume_EditTemplateSections.this, ProfilePage.class);
                startActivity(profilepage);
                break;

            case R.id.rb_ll_contact_details:
                Globalvariables.selected_edit_section_field="contact_details";
                Intent contact_details = new Intent(Resume_EditTemplateSections.this, Rb_Edit_Resume_Details_Page.class);
                startActivity(contact_details);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;
            case R.id.rb_ll_summary:
                Globalvariables.selected_edit_section_field="summary";
                Intent summary = new Intent(Resume_EditTemplateSections.this, Rb_Edit_Resume_Details_Page.class);
                startActivity(summary);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;
            case R.id.rb_ll_employment_details:
                //Globalvariables.selected_edit_section_field="employee_details";
                Intent employee_details = new Intent(Resume_EditTemplateSections.this, RbEmployementDetails.class);
                startActivity(employee_details);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_professional_skills:
                //Globalvariables.selected_edit_section_field="professional_details";
                Intent professional_details = new Intent(Resume_EditTemplateSections.this, RbProfessionalSkills.class);
                startActivity(professional_details);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_educational_details:
                //Globalvariables.selected_edit_section_field="educational_details";
                Intent educational_details = new Intent(Resume_EditTemplateSections.this, RbEducationalDetails.class);
                startActivity(educational_details);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_awards_certifcate:
                //Globalvariables.selected_edit_section_field="awards";
                Intent awards_certificate = new Intent(Resume_EditTemplateSections.this, Rb_awardsCertificateDetails.class);
                startActivity(awards_certificate);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_hobbies_interest:
                //Globalvariables.selected_edit_section_field="hobbies";
                Intent hobbies_interest = new Intent(Resume_EditTemplateSections.this, RbHobbiesInterestDetails.class);
                startActivity(hobbies_interest);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_languages:
                //Globalvariables.selected_edit_section_field="languages";
                Intent languages = new Intent(Resume_EditTemplateSections.this, RbLanguagesDetails.class);
                startActivity(languages);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            case R.id.rb_ll_socialmedias:
                //Globalvariables.selected_edit_section_field="languages";
                Intent socialmedias = new Intent(Resume_EditTemplateSections.this, RbSocialMediaLinks.class);
                startActivity(socialmedias);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

            default:
                break;
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


            adapterLanguage =    new ArrayAdapter<CharSequence>(Resume_EditTemplateSections.this,R.layout.my_spinner_items,strarray_selectsubject);
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
