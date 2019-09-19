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

import com.bangalore.sahicareer.adapter.SocialmediaAdapter;
import com.bangalore.sahicareer.bean.LanguagesBean;
import com.bangalore.sahicareer.bean.SocialMediaBean;

import java.util.ArrayList;

public class RbSocialMediaLinks extends AppCompatActivity {
    private Button bt_socialmedia_submit,bt_socialmedia_reset,bt_socialmedia_addmore,bt_socialmedia_removeone;

    private RecyclerView rv_socialmedia_recyclerView;

    private SocialmediaAdapter socialmediaAdapter;
    public ArrayList<SocialMediaBean> edit_socialmedia_ArrayList;

    int socialmedia_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_social_media_links);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Social Media");
        rv_socialmedia_recyclerView = (RecyclerView) findViewById(R.id.rv_socialmedia);
        bt_socialmedia_submit = (Button) findViewById(R.id.btn_socialmedia_submit);
        bt_socialmedia_reset = (Button) findViewById(R.id.btn_socialmedia_clear);
        bt_socialmedia_addmore=(Button) findViewById(R.id.btn_socialmedia_addmore);
        bt_socialmedia_removeone=(Button) findViewById(R.id.btn_socialmedia_removeone);

        edit_socialmedia_ArrayList = populateList();
        CallAwardcertificateAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_socialmedia_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < SocialmediaAdapter.edit_SocialmediaDetails_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  SocialmediaAdapter.edit_SocialmediaDetails_ArrayList.get(i).getEt_socialmedia_name() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_socialmedia_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialmedia_count=1;

                edit_socialmedia_ArrayList.clear();
                String language="";
                SocialMediaBean socialMediaBean = new SocialMediaBean();
                socialMediaBean.setEditTextValue(language);
                edit_socialmedia_ArrayList.add(socialMediaBean);
                socialmediaAdapter.notifyDataSetChanged();
            }
        });

        bt_socialmedia_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (socialmedia_count==5){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 5 SocialMedias" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }else{
                    socialmedia_count=socialmedia_count+1;
                    String language="";
                    SocialMediaBean socialMediaBean = new SocialMediaBean();
                    socialMediaBean.setEditTextValue(language);
                    int insertIndex = 2;
                    edit_socialmedia_ArrayList.add(socialMediaBean);
                    rv_socialmedia_recyclerView.setAdapter(null);
                    rv_socialmedia_recyclerView.setAdapter(socialmediaAdapter);
                    /*rv_languages_recyclerView.setAdapter(new Allvehicleadapter(getActivity(), deviceList, ""));
                    adapter.notifyItemInserted(insertIndex);*/
                }

            }
        });

        bt_socialmedia_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(socialmedia_count!=1) {
                    socialmedia_count = socialmedia_count - 1;
                    int removeIndex = socialmediaAdapter.getItemCount()-1;
                    edit_socialmedia_ArrayList.remove(removeIndex);
                    socialmediaAdapter.notifyItemRemoved(removeIndex);

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add SocialMedia" , Toast.LENGTH_SHORT);
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
        RbSocialMediaLinks.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<SocialMediaBean> populateList(){

        ArrayList<SocialMediaBean> list = new ArrayList<>();

        for(int i = 0; i < socialmedia_count; i++){
            SocialMediaBean socialMediaBean = new SocialMediaBean();
            socialMediaBean.setEditTextValue(String.valueOf(i));
            list.add(socialMediaBean);
        }

        return list;
    }

    public void CallAwardcertificateAdapter(){
        socialmediaAdapter = new SocialmediaAdapter(this,edit_socialmedia_ArrayList);
        rv_socialmedia_recyclerView.setAdapter(socialmediaAdapter);

        rv_socialmedia_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_socialmedia_recyclerView.setLayoutManager(linearLayoutManager);

    }
}
