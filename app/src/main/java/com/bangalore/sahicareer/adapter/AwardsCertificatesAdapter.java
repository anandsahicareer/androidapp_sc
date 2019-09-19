package com.bangalore.sahicareer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.AwardsCertificatesBean;
import com.bangalore.sahicareer.bean.HobbiesInterestBean;
import com.bangalore.sahicareer.utils.Globalvariables;


import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class AwardsCertificatesAdapter extends RecyclerView.Adapter<AwardsCertificatesAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<AwardsCertificatesBean> edit_awardcertificate_ArrayList;
    ArrayAdapter<String> arrayAdapter;




    public AwardsCertificatesAdapter(Context ctx, ArrayList<AwardsCertificatesBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_awardcertificate_ArrayList = editModelArrayList;


    }

    @Override
    public AwardsCertificatesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_awardcertificate_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AwardsCertificatesAdapter.MyViewHolder holder, final int position) {


        holder.edit_awardcertificate_name.setText(edit_awardcertificate_ArrayList.get(position).getEdit_award_certificate_name());
        holder.edit_awardcertificate_desc.setText(edit_awardcertificate_ArrayList.get(position).getEdit_award_desc());
        //holder.tv_awards_count.setText(edit_awardcertificate_ArrayList.get(position).getEditTextValue());
        holder.tv_awards_count.setText("Award and Certificate:"+edit_awardcertificate_ArrayList.get(position).getEt_awardscertificate_count());
        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return edit_awardcertificate_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_awardcertificate_name,edit_awardcertificate_desc;
        protected TextView tv_awards_count;




        public MyViewHolder(View itemView) {
            super(itemView);

            edit_awardcertificate_name = (EditText) itemView.findViewById(R.id.et_awardcertificate_name);
            edit_awardcertificate_desc = (EditText) itemView.findViewById(R.id.et_award_description);
            tv_awards_count= (TextView) itemView.findViewById(R.id.tv_award_certificate_count);

            edit_awardcertificate_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_awardcertificate_ArrayList.get(getAdapterPosition()).setEdit_award_certificate_name(edit_awardcertificate_name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            edit_awardcertificate_desc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_awardcertificate_ArrayList.get(getAdapterPosition()).setEdit_award_desc(edit_awardcertificate_desc.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }

    }
    private void removeAt(int position) {
        edit_awardcertificate_ArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, edit_awardcertificate_ArrayList.size());
    }
}