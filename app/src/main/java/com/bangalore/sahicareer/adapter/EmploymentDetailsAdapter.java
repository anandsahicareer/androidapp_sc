package com.bangalore.sahicareer.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.Rb_Edit_Resume_Details_Page;
import com.bangalore.sahicareer.bean.EmploymentDetailsBean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EmploymentDetailsAdapter extends RecyclerView.Adapter<EmploymentDetailsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<EmploymentDetailsBean> edit_empdetails_ArrayList;
    ArrayAdapter<String> arrayAdapter;
    Context context;
    private RadioGroup lastCheckedRadioGroup = null;

    private RadioButton lastCheckedRB = null;

    public int mSelectedItem = -1;

    private int lastSelectedPosition = -1;




    public EmploymentDetailsAdapter(Context ctx, ArrayList<EmploymentDetailsBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_empdetails_ArrayList = editModelArrayList;
        this.context=ctx;


    }

    @Override
    public EmploymentDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_employmentdetails_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final EmploymentDetailsAdapter.MyViewHolder holder, final int position) {

        //holder.mRadio.setChecked(position == mSelectedItem);
        holder.edit_empdetails_designation.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_designation());
        holder.edit_empdetails_organization.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_organization());
        holder.edit_empdetails_location.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_location());
        holder.edit_empdetails_start_workdate.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_work_startdate());
        holder.edit_empdetails_end_workdate.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_work_enddate());
        holder.edit_empdetails_describe_job.setText(edit_empdetails_ArrayList.get(position).getEt_empdetails_describe_job());
        holder.tv_experience_count.setText("Experience Details: "+edit_empdetails_ArrayList.get(position).getEt_empdetails_count());
        Log.d("print","yes");
        //holder.rb_currentcompany_yes.setChecked(lastSelectedPosition == position);
        holder.rg_empdetails_currentcompany.setChecked(lastSelectedPosition == position);



    }

    @Override
    public int getItemCount() {
        return edit_empdetails_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText edit_empdetails_designation,edit_empdetails_organization,edit_empdetails_location,edit_empdetails_start_workdate,edit_empdetails_end_workdate,
                            edit_empdetails_describe_job;
        protected TextView tv_experience_count,tv_empdetails_describejob_count;


        public RadioButton rg_empdetails_currentcompany;




        public MyViewHolder(View itemView) {
            super(itemView);

            edit_empdetails_designation = (EditText) itemView.findViewById(R.id.et_empdetails_designation);
            edit_empdetails_organization = (EditText) itemView.findViewById(R.id.et_empdetails_organization);
            edit_empdetails_location = (EditText) itemView.findViewById(R.id.et_empdetails_location);
            edit_empdetails_start_workdate = (EditText) itemView.findViewById(R.id.et_empdetails_work_startdate);
            edit_empdetails_end_workdate = (EditText) itemView.findViewById(R.id.et_empdetails_work_tilldate);
            edit_empdetails_describe_job = (EditText) itemView.findViewById(R.id.et_empdetails_describe_job);

            tv_experience_count=(TextView)itemView.findViewById(R.id.tv_empdetails_experience_count);
            tv_empdetails_describejob_count=(TextView)itemView.findViewById(R.id.tv_empdetails_describejob_count);


            rg_empdetails_currentcompany=(RadioButton) itemView.findViewById(R.id.rb_empdetails_yes);

            rg_empdetails_currentcompany.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Toast.makeText(context,
                            "selected offer is " + lastSelectedPosition,
                            Toast.LENGTH_LONG).show();
                }
            });

            //mRadio = (RadioButton) itemView.findViewById(R.id.rb_empdetails_yes);

            /*View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            };
*/
            //itemView.setOnClickListener(clickListener);
           // mRadio.setOnClickListener(clickListener);
           /* rb_currentcompany_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                   *//* Toast.makeText(EmploymentDetailsAdapter.this.context,
                            "selected offer is " + offerName.getText(),
                            Toast.LENGTH_LONG).show();*//*
                }
            });*/




            edit_empdetails_designation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_designation(edit_empdetails_designation.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            edit_empdetails_organization.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_organization(edit_empdetails_organization.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            edit_empdetails_location.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_location(edit_empdetails_location.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            edit_empdetails_start_workdate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    final Calendar currentDate = Calendar.getInstance();
                    //  edit_freetrail_date.setBackgroundResource(R.drawable.roundededittext);
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                            currentDate.set(year, monthOfYear, dayOfMonth);

                            //use this date as per your requirement

                            if (currentDate.get(Calendar.DAY_OF_WEEK) ==
                                    Calendar.SUNDAY){
                                Toast toast = Toast.makeText(context,"Please Select week day", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                tv.setTextColor(Color.RED);
                                tv.setTextSize(14);
                                toast.show();
                                edit_empdetails_start_workdate.setText("Select Date");

                                //ft_selecteddate = null;

                            }
                            else {
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                                String selecteddate = fmt.format(currentDate.getTime());
                                //ft_selecteddate = selecteddate;
                                edit_empdetails_start_workdate.setText("" + selecteddate);
                                edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_work_startdate(edit_empdetails_start_workdate.getText().toString());


                            }


                        }
                    };
                    DatePickerDialog datePickerDialog = new  DatePickerDialog(context, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.getDatePicker();

                    Calendar c = Calendar.getInstance();

                    c.set(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH),currentDate.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();

                }
            });

            edit_empdetails_end_workdate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    final Calendar currentDate = Calendar.getInstance();
                    //  edit_freetrail_date.setBackgroundResource(R.drawable.roundededittext);
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                            currentDate.set(year, monthOfYear, dayOfMonth);

                            //use this date as per your requirement

                            if (currentDate.get(Calendar.DAY_OF_WEEK) ==
                                    Calendar.SUNDAY){
                                Toast toast = Toast.makeText(context,"Please Select week day", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                                tv.setTextColor(Color.RED);
                                tv.setTextSize(14);
                                toast.show();
                                edit_empdetails_end_workdate.setText("Select Date");

                                //ft_selecteddate = null;

                            }
                            else {
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                                String selecteddate = fmt.format(currentDate.getTime());
                                //ft_selecteddate = selecteddate;
                                edit_empdetails_end_workdate.setText("" + selecteddate);
                                edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_work_enddate(edit_empdetails_end_workdate.getText().toString());
                            }
                        }
                    };
                    DatePickerDialog datePickerDialog = new  DatePickerDialog(context, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.getDatePicker();

                    Calendar c = Calendar.getInstance();

                    c.set(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH),currentDate.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();

                }
            });

            edit_empdetails_end_workdate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_work_enddate(edit_empdetails_end_workdate.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            edit_empdetails_describe_job.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    tv_empdetails_describejob_count.setText(String.valueOf(charSequence.length()+"/4000"));
                    edit_empdetails_ArrayList.get(getAdapterPosition()).setEt_empdetails_describe_job(edit_empdetails_describe_job.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }



    }

}