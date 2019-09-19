package com.bangalore.sahicareer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.AwardsCertificatesBean;
import com.bangalore.sahicareer.bean.HobbiesInterestBean;

import java.util.ArrayList;

public class HobbiesInterestAdapter extends RecyclerView.Adapter<HobbiesInterestAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<HobbiesInterestBean> edit_hobbiesinterest_ArrayList;
    ArrayAdapter<String> arrayAdapter;




    public HobbiesInterestAdapter(Context ctx, ArrayList<HobbiesInterestBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_hobbiesinterest_ArrayList = editModelArrayList;


    }

    @Override
    public HobbiesInterestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_hobbiesinterests_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final HobbiesInterestAdapter.MyViewHolder holder, final int position) {


        holder.edit_hobbiesinterest_name.setText(edit_hobbiesinterest_ArrayList.get(position).getEdit_hobbies_name());
        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return edit_hobbiesinterest_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_hobbiesinterest_name;




        public MyViewHolder(View itemView) {
            super(itemView);

            edit_hobbiesinterest_name = (EditText) itemView.findViewById(R.id.et_hobbies_name);


            edit_hobbiesinterest_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_hobbiesinterest_ArrayList.get(getAdapterPosition()).setEdit_hobbies_name(edit_hobbiesinterest_name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



        }

    }
    private void removeAt(int position) {
        edit_hobbiesinterest_ArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, edit_hobbiesinterest_ArrayList.size());
    }
}