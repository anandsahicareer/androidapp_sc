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

import com.bangalore.sahicareer.adapter.AwardsCertificatesAdapter;
import com.bangalore.sahicareer.adapter.HobbiesInterestAdapter;
import com.bangalore.sahicareer.bean.AwardsCertificatesBean;
import com.bangalore.sahicareer.bean.HobbiesInterestBean;

import java.util.ArrayList;

public class Rb_awardsCertificateDetails extends AppCompatActivity {
    private Button bt_awardcertificate_submit,bt_awardcertificate_reset,bt_awardcertificate_addmore,bt_awardcertificate_removeone;

    private RecyclerView rv_awardcertificate_recyclerView;

    private AwardsCertificatesAdapter awardcertificateAdapter;
    public ArrayList<AwardsCertificatesBean> edit_awardcertificate_ArrayList;

    int awardscertificates_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_awards_certificate_details);



        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Awards and Certificates");

        rv_awardcertificate_recyclerView = (RecyclerView) findViewById(R.id.rv_awardcertificate);
        bt_awardcertificate_submit = (Button) findViewById(R.id.btn_awardcertificate_submit);
        bt_awardcertificate_reset = (Button) findViewById(R.id.btn_awardcertificate_clear);
        bt_awardcertificate_addmore=(Button) findViewById(R.id.btn_awardcertificate_addmore);
        bt_awardcertificate_removeone=(Button) findViewById(R.id.btn_awardcertificate_removeone);

        edit_awardcertificate_ArrayList = populateList();
        CallAwardcertificateAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_awardcertificate_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < AwardsCertificatesAdapter.edit_awardcertificate_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  AwardsCertificatesAdapter.edit_awardcertificate_ArrayList.get(i).getEdit_award_certificate_name()+""+AwardsCertificatesAdapter.edit_awardcertificate_ArrayList.get(i).getEdit_award_desc() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_awardcertificate_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awardscertificates_count=1;
                edit_awardcertificate_ArrayList.clear();
                String language="";
                AwardsCertificatesBean awardsCertificatesBean = new AwardsCertificatesBean();
                awardsCertificatesBean.setEt_awardscertificate_count(String.valueOf(awardscertificates_count));
                edit_awardcertificate_ArrayList.add(awardsCertificatesBean);
                awardcertificateAdapter.notifyDataSetChanged();
            }
        });

        bt_awardcertificate_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awardscertificates_count==3){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 3 Awards" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }else{
                    awardscertificates_count=awardscertificates_count+1;
                    String hobbies="";
                    AwardsCertificatesBean awardsCertificatesBean = new AwardsCertificatesBean();
                    awardsCertificatesBean.setEt_awardscertificate_count(hobbies);
                    int insertIndex = 2;
                    edit_awardcertificate_ArrayList.add(awardsCertificatesBean);
                    rv_awardcertificate_recyclerView.setAdapter(null);
                    awardsCertificatesBean.setEt_awardscertificate_count(String.valueOf(awardscertificates_count));
                    rv_awardcertificate_recyclerView.setAdapter(awardcertificateAdapter);
                }

            }
        });

        bt_awardcertificate_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awardscertificates_count!=1) {
                    awardscertificates_count = awardscertificates_count - 1;
                    int removeIndex = awardcertificateAdapter.getItemCount()-1;
                    edit_awardcertificate_ArrayList.remove(removeIndex);
                    awardcertificateAdapter.notifyItemRemoved(removeIndex);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Award Details" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        Rb_awardsCertificateDetails.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<AwardsCertificatesBean> populateList(){

        ArrayList<AwardsCertificatesBean> list = new ArrayList<>();

        for(int i = 0; i < awardscertificates_count; i++){
            AwardsCertificatesBean awardsCertificatesBean = new AwardsCertificatesBean();
            awardsCertificatesBean.setEt_awardscertificate_count(String.valueOf(awardscertificates_count));
            list.add(awardsCertificatesBean);
        }

        return list;
    }

    public void CallAwardcertificateAdapter(){
        awardcertificateAdapter = new AwardsCertificatesAdapter(this,edit_awardcertificate_ArrayList);
        rv_awardcertificate_recyclerView.setAdapter(awardcertificateAdapter);

        rv_awardcertificate_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_awardcertificate_recyclerView.setLayoutManager(linearLayoutManager);

    }
}