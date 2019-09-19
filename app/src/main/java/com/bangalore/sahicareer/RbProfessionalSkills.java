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

import com.bangalore.sahicareer.adapter.ProfessionalSkillsAdapter;
import com.bangalore.sahicareer.bean.ProfessionalSkillsBean;

import java.util.ArrayList;

public class RbProfessionalSkills extends AppCompatActivity {
    private Button bt_prof_skills_submit,bt_prof_skills_reset,bt_prof_skills_addmore,bt_prof_skills_removeone;

    private RecyclerView rv_prof_skills_recyclerView;

    private ProfessionalSkillsAdapter professionalSkillsAdapter;
    public ArrayList<ProfessionalSkillsBean> edit_prof_sills_ArrayList;

    int professionalskill_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_professional_skills);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Professional Skills");

        rv_prof_skills_recyclerView = (RecyclerView) findViewById(R.id.rv_profskills_skillsdetails);
        bt_prof_skills_submit = (Button) findViewById(R.id.btn_profskills_submit);
        bt_prof_skills_reset = (Button) findViewById(R.id.btn_profskills_clear);
        bt_prof_skills_addmore=(Button) findViewById(R.id.btn_profskills_addmore);
        bt_prof_skills_removeone=(Button) findViewById(R.id.btn_profskills_removeone);

        edit_prof_sills_ArrayList = populateList();
        CallProfessionalSkillsAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_prof_skills_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < ProfessionalSkillsAdapter.edit_prof_sills_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  ProfessionalSkillsAdapter.edit_prof_sills_ArrayList.get(i).getEt_professionalskills_count() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_prof_skills_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professionalskill_count=1;
                edit_prof_sills_ArrayList.clear();
                String language="";
                ProfessionalSkillsBean professionalSkillsBean = new ProfessionalSkillsBean();
                professionalSkillsBean.setEt_professionalskills_count(language);
                edit_prof_sills_ArrayList.add(professionalSkillsBean);
                professionalSkillsAdapter.notifyDataSetChanged();
            }
        });

        bt_prof_skills_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (professionalskill_count==10){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 10 Skills" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }else{
                    professionalskill_count=professionalskill_count+1;
                    String hobbies="";
                    ProfessionalSkillsBean professionalSkillsBean = new ProfessionalSkillsBean();
                    professionalSkillsBean.setEt_professionalskills_count(hobbies);
                    int insertIndex = 2;
                    edit_prof_sills_ArrayList.add(professionalSkillsBean);
                    rv_prof_skills_recyclerView.setAdapter(null);
                    rv_prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
                }

            }
        });

        bt_prof_skills_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(professionalskill_count!=1) {
                    professionalskill_count = professionalskill_count - 1;
                    int removeIndex = professionalSkillsAdapter.getItemCount()-1;
                    edit_prof_sills_ArrayList.remove(removeIndex);
                    professionalSkillsAdapter.notifyItemRemoved(removeIndex);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Skill Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }

            }
        });
    }
    private ArrayList<ProfessionalSkillsBean> populateList(){

        ArrayList<ProfessionalSkillsBean> list = new ArrayList<>();

        for(int i = 0; i < professionalskill_count; i++){
            ProfessionalSkillsBean professionalSkillsBean = new ProfessionalSkillsBean();
            professionalSkillsBean.setEt_professionalskills_count(String.valueOf(i));
            list.add(professionalSkillsBean);
        }

        return list;
    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        RbProfessionalSkills.this.overridePendingTransition(0,0);
        finish();
        return true;
    }
    public void CallProfessionalSkillsAdapter(){
        professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        rv_prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);

        rv_prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_prof_skills_recyclerView.setLayoutManager(linearLayoutManager);

    }
}
