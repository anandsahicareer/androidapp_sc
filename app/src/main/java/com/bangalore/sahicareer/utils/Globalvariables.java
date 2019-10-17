package com.bangalore.sahicareer.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import com.bangalore.sahicareer.InternshipCategory;
import com.bangalore.sahicareer.R;

import java.util.List;

public class Globalvariables {

    public static String apiserverUrl = "http://www.sahicareer.com/dashboard/scheduler/";

    public static String select_pdf;
    public static String mobileno;
    public static String OTP;
    public static ProgressDialog progressDialog;
    public static String macaddress;
    public static String login_status;
    public static String user_status;
    public static String current_page;
    public static String username;
    public static String email;
    public static String user_id;

    public static String Assessment_test_level;

    public static String internship_url;
    public static String privatejob_url;
    public static String govtjob_url;
    public static int clicked_resume_template_id;
    public static String selected_edit_section_field;

    public static String selected_tc_pp_status;
    public static String ccavenueURL;

   public static int rb_employment_details_count=0;
   public static String str_temp;


   public static String rb_summary;





    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }


}
