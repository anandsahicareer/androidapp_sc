package com.bangalore.sahicareer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.ProfessionalSkillsBean;

import java.util.ArrayList;

public class ProfessionalSkillsAdapter extends RecyclerView.Adapter<ProfessionalSkillsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<ProfessionalSkillsBean> edit_prof_sills_ArrayList;
    ArrayAdapter<String> arrayAdapter;
    Context context;

    ArrayAdapter<CharSequence> adapterSkillsRating;

    String[] strarray_skills_rating = {"Beginner", "Intermediate","Expert"};
    String[] strarray_skills_rating1 = {"Intermediate", "Beginner","Expert"};
    String[] strarray_skills_rating2 = {"Expert", "Beginner","Intermediate"};

    String sp_skills_rating;

    public ProfessionalSkillsAdapter(Context ctx, ArrayList<ProfessionalSkillsBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_prof_sills_ArrayList = editModelArrayList;
        this.context=ctx;



    }

    @Override
    public ProfessionalSkillsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_professionalskills_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final ProfessionalSkillsAdapter.MyViewHolder holder, final int position) {


        holder.edit_prof_skills.setText(edit_prof_sills_ArrayList.get(position).getEt_skills_name());

        sp_skills_rating=edit_prof_sills_ArrayList.get(position).getSp_skills_ratings();
        String ss=sp_skills_rating;


        if(sp_skills_rating != null) {
            if(sp_skills_rating.equals("Beginner"))
            {
                adapterSkillsRating = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_skills_rating);
                adapterSkillsRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.sp_skills_ratings.setAdapter(adapterSkillsRating);
            }
            else if(sp_skills_rating.equals("Intermediate")) {

                adapterSkillsRating = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_skills_rating1);
                adapterSkillsRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.sp_skills_ratings.setAdapter(adapterSkillsRating);

            }else if(sp_skills_rating.equals("Expert")){
                adapterSkillsRating = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_skills_rating2);
                adapterSkillsRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.sp_skills_ratings.setAdapter(adapterSkillsRating);
            }


        }else{
            adapterSkillsRating = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_skills_rating);
            adapterSkillsRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.sp_skills_ratings.setAdapter(adapterSkillsRating);
        }

        Log.d("print","yes");



    }

    @Override
    public int getItemCount() {
        return edit_prof_sills_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_prof_skills;
        protected Spinner  sp_skills_ratings;



        public MyViewHolder(View itemView) {
            super(itemView);

            edit_prof_skills = (EditText) itemView.findViewById(R.id.et_pro_skills);
            sp_skills_ratings    =(Spinner)itemView.findViewById(R.id.spinner_skills_rating);

            adapterSkillsRating=     new ArrayAdapter<CharSequence>(context,R.layout.my_spinner_items,strarray_skills_rating);
            adapterSkillsRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_skills_ratings.setAdapter(adapterSkillsRating);



            edit_prof_skills.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_prof_sills_ArrayList.get(getAdapterPosition()).setEt_skills_name(edit_prof_skills.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            sp_skills_ratings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    edit_prof_sills_ArrayList.get(getAdapterPosition()).setSp_skills_ratings(sp_skills_ratings.getSelectedItem().toString());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


        }

    }

}