package com.bangalore.sahicareer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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

import com.bangalore.sahicareer.Career_counselling;
import com.bangalore.sahicareer.R;

import com.bangalore.sahicareer.bean.LanguagesBean;

import java.util.ArrayList;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<LanguagesBean> edit_languageDetails_ArrayList;
    ArrayAdapter<String> arrayAdapter;
    Context context;
    ArrayAdapter<CharSequence> adapterLanguage;
    String[] strarray_language = {"Beginner", "Intermediate","Expert"};
    String[] strarray_language1 = {"Intermediate", "Beginner","Expert"};
    String[] strarray_language2 = {"Expert", "Beginner","Intermediate"};

    String sp_langage_rating;



    public LanguagesAdapter(Context ctx, ArrayList<LanguagesBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_languageDetails_ArrayList = editModelArrayList;
        this.context=ctx;

    }

    @Override
    public LanguagesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_languages_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final LanguagesAdapter.MyViewHolder holder, final int position) {


        holder.edit_language_name.setText(edit_languageDetails_ArrayList.get(position).getEt_language_name());
         sp_langage_rating=edit_languageDetails_ArrayList.get(position).getEt_language_ratings();
        String ss=sp_langage_rating;


        if(sp_langage_rating != null) {
          if(sp_langage_rating.equals("Beginner"))
          {
              adapterLanguage = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_language);
              adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_languages.setAdapter(adapterLanguage);
          }
          else if(sp_langage_rating.equals("Intermediate")) {

              adapterLanguage = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_language1);
              adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_languages.setAdapter(adapterLanguage);

          }else if(sp_langage_rating.equals("Expert")){
              adapterLanguage = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_language2);
              adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_languages.setAdapter(adapterLanguage);
          }


        }else{
            adapterLanguage = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_language);
            adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.sp_languages.setAdapter(adapterLanguage);
        }




        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return edit_languageDetails_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_language_name;

        protected Spinner  sp_languages;



        public MyViewHolder(View itemView) {
            super(itemView);

            edit_language_name = (EditText) itemView.findViewById(R.id.et_language_name);
            sp_languages    =(Spinner)itemView.findViewById(R.id.spinner_language);

            adapterLanguage=     new ArrayAdapter<CharSequence>(context,R.layout.my_spinner_items,strarray_language);
            adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_languages.setAdapter(adapterLanguage);
            //edit_awardcertificateDetails_ArrayList.get(getAdapterPosition()).setEt_language_ratings("Begginer");

            edit_language_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_languageDetails_ArrayList.get(getAdapterPosition()).setEt_language_name(edit_language_name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            sp_languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    edit_languageDetails_ArrayList.get(getAdapterPosition()).setEt_language_ratings(sp_languages.getSelectedItem().toString());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            /*sp_languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    edit_awardcertificateDetails_ArrayList.get(getAdapterPosition()).setEt_language_ratings(sp_languages.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });*/

        }

    }
    private void removeAt(int position) {
        edit_languageDetails_ArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, edit_languageDetails_ArrayList.size());
    }
}