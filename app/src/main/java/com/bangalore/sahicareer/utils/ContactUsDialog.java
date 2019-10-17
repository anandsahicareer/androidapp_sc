package com.bangalore.sahicareer.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.CareerAssessment;
import com.bangalore.sahicareer.R;

import java.util.ArrayList;
import java.util.List;

public class ContactUsDialog {

    public void showDialog(final Activity activity) {
        final Dialog contactus_dialog = new Dialog(activity);
        final Context context = null;
        final Activity activityContext = null;
        final String[] cnt_selectedsubject = new String[1];
        final String rbclickedvalue;
        final String[] str_cnt_name = new String[1];
        final String[] str_cnt_mobile = new String[1];
        final String[] str_cnt_email = new String[1];
        final String[] str_cnt_subject = new String[1];
        final String[] str_cnt_message = new String[1];
        //contactus_dialog = new Dialog(activity);
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






        adapterLanguage =    new ArrayAdapter<CharSequence>(getActivity(),R.layout.my_spinner_items,strarray_selectsubject);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_subjects.setAdapter(adapterLanguage);

        mDialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_cnt_name[0] =cnt_name.getText().toString();
                str_cnt_mobile[0] =cnt_mobile.getText().toString();
                str_cnt_email[0] =cnt_email.getText().toString();
                str_cnt_message[0] =cnt_message.getText().toString();
                str_cnt_subject[0] = sp_subjects.getSelectedItem().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                cnt_selectedsubject[0] = sp_subjects.getSelectedItem().toString();
                if(str_cnt_name[0].equals("")){
                    Toast toast = Toast.makeText(context, "Name is Required.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();

                }
                else if(str_cnt_mobile[0].equals("")){
                    Toast toast = Toast.makeText(context, "Mobile No. is required.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();

                }
                else if(str_cnt_mobile[0].length()<10){
                    Toast toast = Toast.makeText(context, "Invalid Mobile No.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();

                }
                else if(str_cnt_email[0].equals("")){
                    Toast toast = Toast.makeText(context, "Email is required.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();

                }
                else if(!str_cnt_email[0].matches(emailPattern)){
                    Toast toast = Toast.makeText(context," Invalid Email Id", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.RED);
                    tv.setTextSize(14);
                    toast.show();
                }else if(str_cnt_message[0].equals("")){
                    Toast toast = Toast.makeText(context, "Message is required.", Toast.LENGTH_SHORT);
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
                    //new CareerAssessment.CountactUsAPI().execute();
                    //progree_dialog.show();

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

    private Context getActivity() {
        return null;
    }
}
