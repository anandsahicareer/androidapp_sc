package com.bangalore.sahicareer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bangalore.sahicareer.utils.CallNowDialog;
import com.bangalore.sahicareer.utils.Globalvariables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class Career_counselling extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    LinearLayout ll_footer_home,ll_footer_callnow,ll_footer_contactus,ll_footer_plans,ll_footer_profile;
    Button bt_free_trail_a,bt_free_trail_b,bt_schedule_a,bt_schedule_b;
    TextView txt_readmore,txt_readless;
    ScrollView sv;
    String Counselling_type;

    final Calendar myCalendar = Calendar.getInstance();

    String selectedtime,OTP,OTPstatus,cnt_selectedsubject;
    String ft_name,ft_emailid,ft_phoneno,ft_selectedmode,ft_selecteddate,ft_selectedtime,ft_selectedlanguage,str_cnt_subject,
            str_cnt_name,str_cnt_mobile,str_cnt_email,str_cnt_message;
    int OTPEnteredCount=0;

    private String TAG = Career_counselling.class.getSimpleName();

    StringBuilder sb=new StringBuilder();

    LinearLayout ll_otp,ll_freetrail_proceed;
    Button free_trail_submit;

    Dialog freescheduledialog,contactus_dialog,progree_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_career_counselling);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        progree_dialog = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.remove_border_for_progress_dialog, null);
        progree_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progree_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progree_dialog.setContentView(view);

        sv=(ScrollView)findViewById(R.id.CC_main_scrollview);

        ll_footer_home=(LinearLayout)findViewById(R.id.cc_ll_footer_home);
        ll_footer_home.setOnClickListener(this);
        ll_footer_callnow=(LinearLayout)findViewById(R.id.cc_ll_footer_callnow);
        ll_footer_callnow.setOnClickListener(this);
        ll_footer_contactus=(LinearLayout)findViewById(R.id.cc_ll_footer_contactus);
        ll_footer_contactus.setOnClickListener(this);
        ll_footer_plans=(LinearLayout)findViewById(R.id.cc_ll_footer_plans);
        ll_footer_plans.setOnClickListener(this);
        ll_footer_profile=(LinearLayout)findViewById(R.id.cc_ll_footer_profile);
        ll_footer_profile.setOnClickListener(this);


        bt_free_trail_a=(Button)findViewById(R.id.btn_takefreetrail_a);
        //bt_free_trail_a.setOnClickListener(this);

        bt_free_trail_b=(Button)findViewById(R.id.btn_takefreetrail_b);

        bt_schedule_a=(Button)findViewById(R.id.btn_takeschedule_a);
        bt_schedule_b=(Button)findViewById(R.id.btn_takeschedule_b);




        bt_free_trail_a.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        FreeTrailDialog freetrail_a = new FreeTrailDialog();
                        freetrail_a.showDialog(Career_counselling.this);
                        Counselling_type="0";
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
        bt_free_trail_b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        FreeTrailDialog freetrail_a = new FreeTrailDialog();
                        freetrail_a.showDialog(Career_counselling.this);
                        Counselling_type="0";
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        bt_schedule_a.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        FreeTrailDialog freetrail_a = new FreeTrailDialog();
                        freetrail_a.showDialog(Career_counselling.this);
                        Counselling_type="1";
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
        bt_schedule_b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        FreeTrailDialog freetrail_a = new FreeTrailDialog();
                        freetrail_a.showDialog(Career_counselling.this);
                        Counselling_type="1";
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        //sv=(ScrollView)findViewById(R.id.CA_main_scrollview);
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
                        Intent i = new Intent(Career_counselling.this, MainActivity.class);
                        startActivity(i);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_assessment:
                        Intent j = new Intent(Career_counselling.this, CareerAssessment.class);
                        startActivity(j);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_counselling:
                        Intent k = new Intent(Career_counselling.this, Career_counselling.class);
                        startActivity(k);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_jobs_internships:
                        Intent l = new Intent(Career_counselling.this, Jobsandinternships.class);
                        startActivity(l);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_Resume_Service:
                        Intent resume_service = new Intent(Career_counselling.this, ResumeService.class);
                        startActivity(resume_service);
                        dl.closeDrawers();
                        break;

                    case R.id.nv_item_learn_english:
                        Intent n = new Intent(Career_counselling.this, LearnEnglishPage.class);
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
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.cc_ll_footer_home:
                Intent home = new Intent(Career_counselling.this, MainActivity.class);
                startActivity(home);
                //txt_readless.setVisibility(View.GONE);
                break;

            case R.id.cc_ll_footer_callnow:
               CallNowDialog callnow=new CallNowDialog();
                callnow.showDialog(Career_counselling.this);
                break;

            case R.id.cc_ll_footer_contactus:
                ContactUsDialog contactUsDialog = new ContactUsDialog();
                contactUsDialog.showDialog(Career_counselling.this);

                break;

            case R.id.cc_ll_footer_plans:
                Intent planpage = new Intent(Career_counselling.this, MembershipPlanpage.class);
                startActivity(planpage);
                break;

            case R.id.cc_ll_footer_profile:
                Intent profilepage = new Intent(Career_counselling.this, ProfilePage.class);
                startActivity(profilepage);
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


            adapterLanguage =    new ArrayAdapter<CharSequence>(Career_counselling.this,R.layout.my_spinner_items,strarray_selectsubject);
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
                       /* TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_mobile.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Mobile No. is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        /*TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_mobile.length()<10){
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Mobile No.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        /*TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(str_cnt_email.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Email is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                       /* TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                        v1.setTextColor(Color.RED);
                        v1.setTextSize(14);*/
                        toast.show();

                    }
                    else if(!str_cnt_email.matches(emailPattern)){
                        Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        /*TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);*/
                        toast.show();
                    }else if(str_cnt_message.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Message is required.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                       /* TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
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

    public class FreeTrailDialog {

        public void showDialog(Activity activity) {
            freescheduledialog = new Dialog(activity);
            freescheduledialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            freescheduledialog.setCancelable(false);
            freescheduledialog.setContentView(R.layout.cc_freetrail_customlayout);
            freescheduledialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            ft_selecteddate=null;
            free_trail_submit = freescheduledialog.findViewById(R.id.cntbtn_submit);
           // final EditText cnt_name=dialog.findViewById(R.id.et_cntname);
            ll_freetrail_proceed=freescheduledialog.findViewById(R.id.cc_freetrail_proceed);

            ArrayAdapter<CharSequence> adapterMode;
            ArrayAdapter<CharSequence> adapterLanguage;

            String[] strarray_modeofselection = {"None","Telephonic", "Video Call", "F2F"};
            String[] strarray_language = {"None","English", "Hindi", "Kannada","Bengali"};



            final Spinner sp_modeofselection =(Spinner)freescheduledialog.findViewById(R.id.spinner_modes);
            final Spinner sp_language    =(Spinner)freescheduledialog.findViewById(R.id.spinner_languages);

            adapterMode =    new ArrayAdapter<CharSequence>(Career_counselling.this,R.layout.my_spinner_items,strarray_modeofselection);
            adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_modeofselection.setAdapter(adapterMode);

            adapterLanguage=     new ArrayAdapter<CharSequence>(Career_counselling.this,R.layout.my_spinner_items,strarray_language);
            adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_language.setAdapter(adapterLanguage);

           final EditText edit_freetrail_date= (EditText) freescheduledialog.findViewById(R.id.et_cc_freetrial_date);
            final EditText edit_freetrail_time= (EditText) freescheduledialog.findViewById(R.id.et_cc_freetrial_time);
            final EditText edit_name= (EditText) freescheduledialog.findViewById(R.id.et_cc_freetrial_name);
            final EditText edit_emailid= (EditText) freescheduledialog.findViewById(R.id.et_cc_freetrial_email);
            final EditText edit_phone= (EditText) freescheduledialog.findViewById(R.id.et_cc_freetrial_mobile);

            edit_name.setText(Globalvariables.username, TextView.BufferType.EDITABLE);
            edit_phone.setText(Globalvariables.mobileno, TextView.BufferType.EDITABLE);
            edit_emailid.setText(Globalvariables.email, TextView.BufferType.EDITABLE);



            edit_phone.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub
                    if(edit_phone.length()==10)
                    {

                        /*Intent counselling = new Intent(Career_counselling.this, MembershipPlanpage.class);
                        startActivity(counselling);
*/
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edit_phone.getWindowToken(), 0);
                        //edit_freetrail_date.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {



                }

                public void afterTextChanged(Editable s) {


                }
            });


            edit_freetrail_date.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    final Calendar currentDate = Calendar.getInstance();
                  //  edit_freetrail_date.setBackgroundResource(R.drawable.roundededittext);
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                            currentDate.set(year, monthOfYear, dayOfMonth);

                            //use this date as per your requirement

                            if (currentDate.get(Calendar.DAY_OF_WEEK) ==
                                    Calendar.SUNDAY){
                                Toast toast = Toast.makeText(getApplicationContext(),"Please Select week day", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                tv.setTextColor(Color.RED);
                                tv.setTextSize(14);
                                toast.show();
                                edit_freetrail_date.setText("Select Date");

                                ft_selecteddate = null;

                            }
                            else {
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                                String selecteddate = fmt.format(currentDate.getTime());
                                ft_selecteddate = selecteddate;
                                edit_freetrail_date.setText("" + selecteddate);
                                //edit_freetrail_time.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);
                                //edit_freetrail_date.setBackgroundResource(R.drawable.roundededittext);
                            }


                        }
                    };
                    DatePickerDialog datePickerDialog = new  DatePickerDialog(Career_counselling.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                    Calendar c = Calendar.getInstance();

                    c.set(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+3,currentDate.get(Calendar.DAY_OF_MONTH));
                    //c.set(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+3,currentDate.get(Calendar.DAY_OF_MONTH));
                   // c.set(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - c.get(Calendar.DAY_OF_WEEK) + 7 ));
                    // saturday = Calendar.getInstance();
                    // saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) + i));
                    // weekends.add(saturday);
                    //weekends.add(sunday);//Year,Mounth -1,Day
                    datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                    if (c.get(Calendar.DAY_OF_WEEK) ==
                            Calendar.SUNDAY){
                        Color.parseColor("#FFFFF");

                    }

                    datePickerDialog.show();

                }
            });


            edit_freetrail_time.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    final Calendar currentDate = Calendar.getInstance();
                    //edit_freetrail_time.setBackgroundResource(R.drawable.roundededittext);
                    int date=mcurrentTime.get(Calendar.DATE);
                    final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;

                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh a");


                    final String todaydate = fmt.format(currentDate.getTime());
                    if(ft_selecteddate==null) {


                        Toast toast = Toast.makeText(getApplicationContext(), "Please Select Date First", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                        edit_freetrail_time.setText("Select Time");

                        ft_selectedtime = null;
                    }
                    else {
                        mTimePicker = new TimePickerDialog(Career_counselling.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                                selectedtime = String.valueOf(selectedHour) + ":" + String.valueOf(selectedMinute);
                                String timeSet = "";

                                if (selectedHour > 12) {
                                    selectedHour -= 12;
                                    timeSet = "PM";
                                } else if (selectedHour == 0) {
                                    selectedHour += 12;
                                    timeSet = "AM";
                                } else if (selectedHour == 12) {
                                    timeSet = "PM";
                                } else {
                                    timeSet = "AM";
                                }
                                /*if (ft_selecteddate.equals(todaydate)) {
                                    if (selectedHour >= hour) {
                                        int a = 1;
                                        int b = a;
                                    }
                                } else {*/
                                    if (selectedHour == 12) {
                                        if (timeSet.equals("PM")) {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Please Select Time Between 11AM to 6PM", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                            tv.setTextColor(Color.RED);
                                            tv.setTextSize(14);
                                            toast.show();
                                            edit_freetrail_time.setText("Select Time");

                                            ft_selectedtime = null;

                                        } else {
                                            edit_freetrail_time.setText("" + selectedHour + ":" + selectedMinute + timeSet);
                                            ft_selectedtime = "" + selectedHour + ":" + selectedMinute + "" + timeSet;
                                        }
                                    } else if (selectedHour == 11) {
                                        if (timeSet.equals("PM")) {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Please Select Time Between 11AM to 6PM", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                            tv.setTextColor(Color.RED);
                                            tv.setTextSize(14);
                                            toast.show();
                                            edit_freetrail_time.setText("Select Time");

                                            ft_selectedtime = null;

                                        } else {
                                            edit_freetrail_time.setText("" + selectedHour + ":" + selectedMinute + timeSet);
                                            ft_selectedtime = "" + selectedHour + ":" + selectedMinute + "" + timeSet;
                                        }
                                    } else if (selectedHour >= 1 && selectedHour <= 6) {
                                        if (timeSet.equals("AM")) {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Please Select Time Between 11AM to 6PM", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                            tv.setTextColor(Color.RED);
                                            tv.setTextSize(14);
                                            toast.show();
                                            edit_freetrail_time.setText("Select Time");

                                            ft_selectedtime = null;

                                        } else {
                                            edit_freetrail_time.setText("" + selectedHour + ":" + selectedMinute + timeSet);
                                            ft_selectedtime = "" + selectedHour + ":" + selectedMinute + "" + timeSet;
                                        }
                                    } else {

                                        Toast toast = Toast.makeText(getApplicationContext(), "Please Select Time Between 11AM to 6PM", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                        tv.setTextColor(Color.RED);
                                        tv.setTextSize(14);
                                        toast.show();
                                        edit_freetrail_time.setText("Select Time");

                                        ft_selectedtime = null;
                                    }


                            }

                        }, hour, minute, false);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();
                    }
                }
                //showDateTimePicker();




            });
            sp_language.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onGlobalLayout() {
                            // Ensure you call it only once works for JELLY_BEAN and later
                            sp_language.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            // add the listener
                            sp_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                    // check if pos has changed
                                    // then do your work

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

            sp_modeofselection.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onGlobalLayout() {
                            // Ensure you call it only once works for JELLY_BEAN and later
                            sp_modeofselection.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            // add the listener
                            sp_modeofselection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                    // check if pos has changed
                                    // then do your work
                                    //sp_modeofselection.setBackgroundResource(R.drawable.roundededittext);
                                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> arg0) {
                                   // sp_modeofselection.setBackgroundResource(R.drawable.roundededittext);
                                    //sp_modeofselection.setBackgroundResource(R.drawable.roundededittext);
                                }

                            });

                        }
                    });


            free_trail_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    /*AsyncT asyncT = new AsyncT();
                    asyncT.execute();*/

                     ft_name  = edit_name.getText().toString();
                    ft_emailid  = edit_emailid.getText().toString();
                    ft_phoneno  = edit_phone.getText().toString();
                    ft_selectedmode  = sp_modeofselection.getSelectedItem().toString();
                    ft_selectedlanguage  = sp_language.getSelectedItem().toString();

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                    if(ft_name.equals("")||ft_name.equals(null)){
                        Toast toast = Toast.makeText(getApplicationContext()," Name is Required", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_emailid.equals("")||ft_emailid.equals(null)){
                        Toast toast = Toast.makeText(getApplicationContext()," Email Id is Required", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(!ft_emailid.matches(emailPattern)){
                        Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }

                    else if(ft_phoneno.equals("")||ft_phoneno.equals(null)){
                        Toast toast = Toast.makeText(getApplicationContext()," Phone No is Required", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_phoneno.length()<10){
                        Toast toast = Toast.makeText(getApplicationContext()," Invalid Phone No.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_selecteddate==null){
                        Toast toast = Toast.makeText(getApplicationContext()," Date is Required", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_selectedtime==null){
                        Toast toast = Toast.makeText(getApplicationContext()," Time is Required", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_selectedlanguage.equals("None")){
                        Toast toast = Toast.makeText(getApplicationContext()," Please choose Language", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }
                    else if(ft_selectedmode.equals("None")){
                        Toast toast = Toast.makeText(getApplicationContext(),"Please choose mode of Session", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(14);
                        toast.show();
                    }

                    else{



                      /*  if (Globalvariables.progressDialog == null) {
                            Globalvariables.progressDialog = Globalvariables.createProgressDialog(Career_counselling.this);
                            Globalvariables.progressDialog.show();
                        } else {
                            Globalvariables.progressDialog.show();
                        }*/
                        new ScheduleCounsellingAPI().execute();

                    }

                }
            });

            Button mDialogOk = freescheduledialog.findViewById(R.id.cntbtn_cancel);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    freescheduledialog.cancel();
                }
            });
            freescheduledialog.show();

        }
    }

    public class ScheduleCounsellingAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/schedule-career-counselling"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_mobile", ft_phoneno);
                postDataParams.put("user_email", ft_emailid);
                postDataParams.put("user_id", Globalvariables.user_id);
                postDataParams.put("schedule_date", ft_selecteddate);
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");

                postDataParams.put("schedule_time", selectedtime);


                String Telephonic="Telephonic";
                String videocall="Video Call";
                String f2f="F2F";
                if(ft_selectedmode.equals(Telephonic)) {
                    postDataParams.put("counselling_mode", 1);

                }
                else if(ft_selectedmode.equals(videocall)){
                    postDataParams.put("counselling_mode", 2);

                }
                else if(ft_selectedmode.equals(f2f)){
                    postDataParams.put("counselling_mode", 3);

                }

                postDataParams.put("counselling_type",Counselling_type);
                String English="English";
                String Hindi="Hindi";
                String kananda="Kannada";
                String Bengali="Bengali";
                if(ft_selectedlanguage.equals(English)) {
                    postDataParams.put("counselling_language",7);

                }
                else if(ft_selectedlanguage.equals(Hindi)){
                    postDataParams.put("counselling_language",8);

                }
                else if(ft_selectedlanguage.equals(kananda)){
                    postDataParams.put("counselling_language",54);

                }
                else if(ft_selectedlanguage.equals(Bengali)){
                    postDataParams.put("counselling_language",53);

                }

                postDataParams.put("user_name",ft_name);


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
                String ack = jsonObject.getString("ack");
                String message = jsonObject.getString("msg");


                if(ack.equals("3")){
                    freescheduledialog.cancel();
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();

                }
                else if(ack.equals("1")){
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                    freescheduledialog.cancel();
                    Intent counselling = new Intent(Career_counselling.this, MembershipPlanpage.class);
                    startActivity(counselling);


                }
                else if(ack.equals("2")){
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                    /*freescheduledialog.cancel();
                    Intent counselling = new Intent(Career_counselling.this, MembershipPlanpage.class);
                    startActivity(counselling);*/


                }
                else if(ack.equals("0")){
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.RED);
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


}
