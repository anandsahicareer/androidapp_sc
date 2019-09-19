package com.bangalore.sahicareer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.adapter.EmploymentDetailsAdapter;
import com.bangalore.sahicareer.adapter.ProfessionalSkillsAdapter;
import com.bangalore.sahicareer.bean.EmploymentDetailsBean;
import com.bangalore.sahicareer.bean.ProfessionalSkillsBean;
import com.bangalore.sahicareer.utils.Globalvariables;

import java.util.ArrayList;

public class RbEmployementDetails extends AppCompatActivity {
    private Button bt_empdetails_submit,bt_empdetails_reset,bt_empdetails_addmore,bt_empdetails_removeone;

    private RecyclerView rv_empdetails_recyclerView;

    private EmploymentDetailsAdapter EmploymentDetailsAdapter;
    public ArrayList<EmploymentDetailsBean> edit_empdetails_ArrayList;

    int employmentdetails_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_employement_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Employment Details");


        rv_empdetails_recyclerView = (RecyclerView) findViewById(R.id.rv_empdetails);
        bt_empdetails_submit = (Button) findViewById(R.id.btn_empdetails_submit);
        bt_empdetails_reset = (Button) findViewById(R.id.btn_empdetails_clear);
        bt_empdetails_addmore=(Button) findViewById(R.id.btn_empdetails_addmore);
        bt_empdetails_removeone=(Button) findViewById(R.id.btn_empdetails_removeone);

        edit_empdetails_ArrayList = populateList();
        CallEmploymentDetailsAdapter();

        bt_empdetails_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < EmploymentDetailsAdapter.edit_empdetails_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_designation()+
                            EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_organization()+
                            EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_location()+
                            EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_work_startdate()+
                            EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_work_enddate()+
                            EmploymentDetailsAdapter.edit_empdetails_ArrayList.get(i).getEt_empdetails_describe_job(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                   /* v1.setTextColor(Color.RED);
                    v1.setTextSize(14);*/
                    toast.show();
                }
            }
        });

        bt_empdetails_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employmentdetails_count=1;
                edit_empdetails_ArrayList.clear();
                String language="";
                EmploymentDetailsBean employmentDetailsBean = new EmploymentDetailsBean();
                employmentDetailsBean.setEt_empdetails_count(language);
                edit_empdetails_ArrayList.add(employmentDetailsBean);
                EmploymentDetailsAdapter.notifyDataSetChanged();
            }
        });

        bt_empdetails_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (employmentdetails_count==6){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 6 Employment Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                   /* v1.setTextColor(Color.RED);
                    v1.setTextSize(14);*/
                    toast.show();

                }else{
                    employmentdetails_count=employmentdetails_count+1;

                    String employment_details="";
                    EmploymentDetailsBean employmentDetailsBean = new EmploymentDetailsBean();
                    employmentDetailsBean.setEt_empdetails_count(employment_details);
                    int insertIndex = 2;
                    edit_empdetails_ArrayList.add(employmentDetailsBean);
                    rv_empdetails_recyclerView.setAdapter(null);
                    employmentDetailsBean.setEt_empdetails_count(String.valueOf(employmentdetails_count));
                    rv_empdetails_recyclerView.setAdapter(EmploymentDetailsAdapter);
                }


            }
        });

        bt_empdetails_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(employmentdetails_count!=1) {
                    employmentdetails_count = employmentdetails_count - 1;
                    int removeIndex = EmploymentDetailsAdapter.getItemCount()-1;
                    edit_empdetails_ArrayList.remove(removeIndex);
                    EmploymentDetailsAdapter.notifyItemRemoved(removeIndex);
                }else
                    {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Employment Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    /*v1.setTextColor(Color.RED);
                    v1.setTextSize(14);*/
                    toast.show();
                }


            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        RbEmployementDetails.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<EmploymentDetailsBean> populateList(){

        ArrayList<EmploymentDetailsBean> list = new ArrayList<>();

        for(int i = 0; i < employmentdetails_count; i++){
            EmploymentDetailsBean employmentDetailsBean = new EmploymentDetailsBean();
            employmentDetailsBean.setEt_empdetails_count(String.valueOf(employmentdetails_count));
            list.add(employmentDetailsBean);
        }

        return list;
    }

    public void CallEmploymentDetailsAdapter()
    {

        EmploymentDetailsAdapter = new EmploymentDetailsAdapter(this,edit_empdetails_ArrayList);
        rv_empdetails_recyclerView.setAdapter(EmploymentDetailsAdapter);

        rv_empdetails_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_empdetails_recyclerView.setLayoutManager(linearLayoutManager);

    }
}
