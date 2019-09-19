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
import com.bangalore.sahicareer.adapter.LanguagesAdapter;
import com.bangalore.sahicareer.bean.AwardsCertificatesBean;
import com.bangalore.sahicareer.bean.LanguagesBean;

import java.util.ArrayList;

public class RbLanguagesDetails extends AppCompatActivity {
    private Button bt_languages_submit,bt_languages_reset,bt_languages_addmore,bt_languages_removeone;

    private RecyclerView rv_languages_recyclerView;

    private LanguagesAdapter languagesAdapter;
    public ArrayList<LanguagesBean> edit_languages_ArrayList;

    int languages_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_rb_languages_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Languages");

        rv_languages_recyclerView = (RecyclerView) findViewById(R.id.rv_languages);
        bt_languages_submit = (Button) findViewById(R.id.btn_languages_submit);
        bt_languages_reset = (Button) findViewById(R.id.btn_languages_clear);
        bt_languages_addmore=(Button) findViewById(R.id.btn_languages_addmore);
        bt_languages_removeone=(Button) findViewById(R.id.btn_languages_removeone);

        edit_languages_ArrayList = populateList();
        CallAwardcertificateAdapter();
        /*professionalSkillsAdapter = new ProfessionalSkillsAdapter(this,edit_prof_sills_ArrayList);
        prof_skills_recyclerView.setAdapter(professionalSkillsAdapter);
        prof_skills_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
        bt_languages_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < LanguagesAdapter.edit_languageDetails_ArrayList.size(); i++){

                    Toast toast = Toast.makeText(getApplicationContext(),  LanguagesAdapter.edit_languageDetails_ArrayList.get(i).getEt_language_ratings() , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();



                }
            }
        });

        bt_languages_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages_count=1;

                edit_languages_ArrayList.clear();
                String language="";
                LanguagesBean languagesBean = new LanguagesBean();
                languagesBean.setEditTextValue(language);
                edit_languages_ArrayList.add(languagesBean);
                languagesAdapter.notifyDataSetChanged();
            }
        });

        bt_languages_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (languages_count==10){
                    Toast toast = Toast.makeText(getApplicationContext(), "You Already Add 10 Languages" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }else{
                    languages_count=languages_count+1;
                    String language="";
                    LanguagesBean languagesBean = new LanguagesBean();
                    languagesBean.setEditTextValue(language);
                    int insertIndex = 2;
                    edit_languages_ArrayList.add(languagesBean);
                    rv_languages_recyclerView.setAdapter(null);
                    rv_languages_recyclerView.setAdapter(languagesAdapter);
                    /*rv_languages_recyclerView.setAdapter(new Allvehicleadapter(getActivity(), deviceList, ""));
                    adapter.notifyItemInserted(insertIndex);*/
                }

            }
        });

        bt_languages_removeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(languages_count!=1) {
                    languages_count = languages_count - 1;
                    int removeIndex = languagesAdapter.getItemCount()-1;
                    edit_languages_ArrayList.remove(removeIndex);
                    languagesAdapter.notifyItemRemoved(removeIndex);

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Add Languages" , Toast.LENGTH_SHORT);
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
        RbLanguagesDetails.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    private ArrayList<LanguagesBean> populateList(){

        ArrayList<LanguagesBean> list = new ArrayList<>();

        for(int i = 0; i < languages_count; i++){
            LanguagesBean languagesBean = new LanguagesBean();
            languagesBean.setEditTextValue(String.valueOf(i));
            list.add(languagesBean);
        }

        return list;
    }

    public void CallAwardcertificateAdapter(){
        languagesAdapter = new LanguagesAdapter(this,edit_languages_ArrayList);
        rv_languages_recyclerView.setAdapter(languagesAdapter);

        rv_languages_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_languages_recyclerView.setLayoutManager(linearLayoutManager);

    }
}
