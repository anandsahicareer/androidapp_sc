package com.bangalore.sahicareer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.LanguagesBean;
import com.bangalore.sahicareer.bean.SocialMediaBean;

import java.util.ArrayList;

public class SocialmediaAdapter extends RecyclerView.Adapter<SocialmediaAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<SocialMediaBean> edit_SocialmediaDetails_ArrayList;
    ArrayAdapter<String> arrayAdapter;
    Context context;
    ArrayAdapter<CharSequence> adapterSocialmedia;
    String[] strarray_socialmedia = {"Linkedin","FaceBook","Twitter","GitHub","Other"};
    String[] strarray_socialmedia1 = {"Linkedin","FaceBook","Twitter","GitHub","Other"};
    String[] strarray_socialmedia2 = {"FaceBook","Twitter","GitHub","Other","Linkedin"};
    String[] strarray_socialmedia3 = {"Twitter","GitHub","Other","Linkedin","FaceBook"};
    String[] strarray_socialmedia4 = {"GitHub","Other","Linkedin","FaceBook","Twitter"};
    String[] strarray_socialmedia5 = {"Other","Linkedin","FaceBook","Twitter","GitHub"};

    String sp_socialmedia_name;



    public SocialmediaAdapter(Context ctx, ArrayList<SocialMediaBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_SocialmediaDetails_ArrayList = editModelArrayList;
        this.context=ctx;

    }

    @Override
    public SocialmediaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_socialmedia_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SocialmediaAdapter.MyViewHolder holder, final int position) {


        holder.edit_socialmedia_link.setText(edit_SocialmediaDetails_ArrayList.get(position).getEt_socialmedia_links());
        sp_socialmedia_name=edit_SocialmediaDetails_ArrayList.get(position).getEt_socialmedia_name();
        String ss=sp_socialmedia_name;


        if(sp_socialmedia_name != null) {
          if(sp_socialmedia_name.equals("Linkedin"))
          {
              adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia1);
              adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_socialmedia.setAdapter(adapterSocialmedia);
          }
          else if(sp_socialmedia_name.equals("FaceBook")) {

              adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia2);
              adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_socialmedia.setAdapter(adapterSocialmedia);

          }else if(sp_socialmedia_name.equals("Twitter")){
              adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia3);
              adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_socialmedia.setAdapter(adapterSocialmedia);
          }
          else if(sp_socialmedia_name.equals("GitHub")){
              adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia4);
              adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_socialmedia.setAdapter(adapterSocialmedia);
          }
          else if(sp_socialmedia_name.equals("Other")){
              adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia5);
              adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              holder.sp_socialmedia.setAdapter(adapterSocialmedia);
          }


        }else{
            adapterSocialmedia = new ArrayAdapter<CharSequence>(context, R.layout.my_spinner_items, strarray_socialmedia1);
            adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.sp_socialmedia.setAdapter(adapterSocialmedia);
        }




        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return edit_SocialmediaDetails_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_socialmedia_link;

        protected Spinner  sp_socialmedia;



        public MyViewHolder(View itemView) {
            super(itemView);

            edit_socialmedia_link = (EditText) itemView.findViewById(R.id.et_socialmedia_link);
            sp_socialmedia    =(Spinner)itemView.findViewById(R.id.spinner_socialmedia_name);

            adapterSocialmedia=     new ArrayAdapter<CharSequence>(context,R.layout.my_spinner_items,strarray_socialmedia1);
            adapterSocialmedia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_socialmedia.setAdapter(adapterSocialmedia);
            //edit_awardcertificateDetails_ArrayList.get(getAdapterPosition()).setEt_language_ratings("Begginer");

            edit_socialmedia_link.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_SocialmediaDetails_ArrayList.get(getAdapterPosition()).setEt_socialmedia_links(edit_socialmedia_link.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            sp_socialmedia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    edit_SocialmediaDetails_ArrayList.get(getAdapterPosition()).setEt_socialmedia_name(sp_socialmedia.getSelectedItem().toString());

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
        edit_SocialmediaDetails_ArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, edit_SocialmediaDetails_ArrayList.size());
    }
}