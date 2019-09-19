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
import com.bangalore.sahicareer.bean.LanguagesBean;

import java.util.ArrayList;

public class RbHobbiesInterestDetails extends AppCompatActivity {
    private Button bt_hobbiesinterest_submit,bt_hobbiesinterest_reset,bt_hobbiesinterest_addmore,bt_hobbiesinterest_removeone;

    private RecyclerView rv_hobbiesinterest_recyclerView;

    private HobbiesInterestAdapter hobbiesinterestAdapter;
    public ArrayList<HobbiesInterestBean> edit_hobbiesinterest_ArrayList;

    int hobbiesinterest_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_hobbies_interest_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Hobbies and Interests");

        rv_hobbiesinterest_recyclerView = (RecyclerView) findViewById(R.id.rv_hobbiesinterest);
        bt_hobbiesinterest_submit = (Button) findViewById(R.id.btn_hobbiesinterest_submit);
        bt_hobbiesinterest_reset = (Button) findViewById(R.id.btn_hobbiesinterest_clear);
        bt_hobbiesinterest_addmore=(Button) findViewById(R.id.btn_hobbiesinterest_addmore);
        bt_hobbiesinterest_removeone=(Button) findViewById(R.id.btn_hobbiesinterest_removeone);

        edit_hobbiesinterest_ArrayList = populateList();
        CallAwardcertificateAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_hobbiesinterest_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < HobbiesInterestAdapter.edit_hobbiesinterest_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  HobbiesInterestAdapter.edit_hobbiesinterest_ArrayList.get(i).getEdit_hobbies_name() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_hobbiesinterest_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hobbiesinterest_count=1;
                edit_hobbiesinterest_ArrayList.clear();
                String language="";
                HobbiesInterestBean hobbiesInterestBean = new HobbiesInterestBean();
                hobbiesInterestBean.setEditTextValue(language);
                edit_hobbiesinterest_ArrayList.add(hobbiesInterestBean);
                hobbiesinterestAdapter.notifyDataSetChanged();
            }
        });

        bt_hobbiesinterest_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hobbiesinterest_count==5){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 5 Hobbies" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }else{
                    hobbiesinterest_count=hobbiesinterest_count+1;
                    String hobbies="";
                    HobbiesInterestBean hobbiesInterestBean = new HobbiesInterestBean();
                    hobbiesInterestBean.setEditTextValue(hobbies);
                    int insertIndex = 2;
                    edit_hobbiesinterest_ArrayList.add(hobbiesInterestBean);
                    rv_hobbiesinterest_recyclerView.setAdapter(null);
                    rv_hobbiesinterest_recyclerView.setAdapter(hobbiesinterestAdapter);
                }

            }
        });



        bt_hobbiesinterest_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hobbiesinterest_count!=1) {
                    hobbiesinterest_count = hobbiesinterest_count - 1;
                    int removeIndex = hobbiesinterestAdapter.getItemCount()-1;
                    edit_hobbiesinterest_ArrayList.remove(removeIndex);
                    hobbiesinterestAdapter.notifyItemRemoved(removeIndex);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Hobbies Details" , Toast.LENGTH_SHORT);
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
        RbHobbiesInterestDetails.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<HobbiesInterestBean> populateList(){

        ArrayList<HobbiesInterestBean> list = new ArrayList<>();

        for(int i = 0; i < hobbiesinterest_count; i++){
            HobbiesInterestBean hobbiesInterestBean = new HobbiesInterestBean();
            //hobbiesInterestBean.setEditTextValue(String.valueOf(i));
            list.add(hobbiesInterestBean);
        }

        return list;
    }

    public void CallAwardcertificateAdapter(){
        hobbiesinterestAdapter = new HobbiesInterestAdapter(this,edit_hobbiesinterest_ArrayList);
        rv_hobbiesinterest_recyclerView.setAdapter(hobbiesinterestAdapter);

        rv_hobbiesinterest_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_hobbiesinterest_recyclerView.setLayoutManager(linearLayoutManager);

    }




}
