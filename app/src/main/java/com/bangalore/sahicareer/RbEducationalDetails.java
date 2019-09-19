package com.bangalore.sahicareer;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.adapter.EducationalDetailsAdapter;
import com.bangalore.sahicareer.adapter.ProfessionalSkillsAdapter;
import com.bangalore.sahicareer.bean.AwardsCertificatesBean;
import com.bangalore.sahicareer.bean.EducationalDetailsBean;
import com.bangalore.sahicareer.bean.ProfessionalSkillsBean;

import java.util.ArrayList;

public class RbEducationalDetails extends AppCompatActivity {
    private Button bt_educationdetails_submit,bt_educationdetails_reset,bt_educationdetails_addmore,bt_educationdetails_removeone;

    private RecyclerView rv_educationdetails_recyclerView;

    private EducationalDetailsAdapter EducationdetailsAdapter;
    public ArrayList<EducationalDetailsBean> edit_educationdetails_ArrayList;

    int educationdetails_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_educational_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Educational Skills");

        rv_educationdetails_recyclerView = (RecyclerView) findViewById(R.id.rv_educationdetails);
        bt_educationdetails_submit = (Button) findViewById(R.id.btn_educationdetails_submit);
        bt_educationdetails_reset = (Button) findViewById(R.id.btn_educationdetails_clear);
        bt_educationdetails_addmore=(Button) findViewById(R.id.btn_educationdetails_addmore);
        bt_educationdetails_removeone=(Button) findViewById(R.id.btn_educationdetails_removeone);

        edit_educationdetails_ArrayList = populateList();
        CallEducationDetailsAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_educationdetails_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < EducationalDetailsAdapter.edit_educationdetails_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  EducationalDetailsAdapter.edit_educationdetails_ArrayList.get(i).getEdit_educationdetails_graduate() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_educationdetails_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                educationdetails_count=1;
                edit_educationdetails_ArrayList.clear();
                String language="";
                EducationalDetailsBean awardsCertificatesBean = new EducationalDetailsBean();
                awardsCertificatesBean.setEt_educationdetails_count(language);
                edit_educationdetails_ArrayList.add(awardsCertificatesBean);
                EducationdetailsAdapter.notifyDataSetChanged();
            }
        });

        bt_educationdetails_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (educationdetails_count==3){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 3 Education Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    /*v1.setTextColor(Color.RED);
                    v1.setTextSize(14);*/
                    toast.show();
                }else{
                    educationdetails_count=educationdetails_count+1;
                    String hobbies="";
                    EducationalDetailsBean educationalDetailsBean = new EducationalDetailsBean();
                    educationalDetailsBean.setEt_educationdetails_count(hobbies);
                    int insertIndex = 2;
                    edit_educationdetails_ArrayList.add(educationalDetailsBean);
                    rv_educationdetails_recyclerView.setAdapter(null);
                    EducationdetailsAdapter.notifyDataSetChanged();
                    educationalDetailsBean.setEt_educationdetails_count(String.valueOf(educationdetails_count));
                    rv_educationdetails_recyclerView.setAdapter(EducationdetailsAdapter);
                }

            }
        });

        bt_educationdetails_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(educationdetails_count!=1) {
                    educationdetails_count = educationdetails_count - 1;
                    int removeIndex = EducationdetailsAdapter.getItemCount()-1;
                    edit_educationdetails_ArrayList.remove(removeIndex);
                    EducationdetailsAdapter.notifyItemRemoved(removeIndex);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Educaton Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                   /* v1.setTextColor(Color.RED);
                    v1.setTextSize(14);*/
                    toast.show();
                }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        RbEducationalDetails.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<EducationalDetailsBean> populateList(){

        ArrayList<EducationalDetailsBean> list = new ArrayList<>();

        for(int i = 0; i < educationdetails_count; i++){
            EducationalDetailsBean educationalDetailsBean = new EducationalDetailsBean();
            educationalDetailsBean.setEt_educationdetails_count(String.valueOf(educationdetails_count));
            list.add(educationalDetailsBean);
        }

        return list;
    }

    public void CallEducationDetailsAdapter(){
        EducationdetailsAdapter = new EducationalDetailsAdapter(this,edit_educationdetails_ArrayList);
        rv_educationdetails_recyclerView.setAdapter(EducationdetailsAdapter);

        rv_educationdetails_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_educationdetails_recyclerView.setLayoutManager(linearLayoutManager);

    }
}
