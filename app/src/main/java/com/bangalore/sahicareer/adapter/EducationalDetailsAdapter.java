package com.bangalore.sahicareer.adapter;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.EducationalDetailsBean;
import com.bangalore.sahicareer.bean.ProfessionalSkillsBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EducationalDetailsAdapter extends RecyclerView.Adapter<EducationalDetailsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<EducationalDetailsBean> edit_educationdetails_ArrayList;
    ArrayAdapter<String> arrayAdapter;
    Context context;




    public EducationalDetailsAdapter(Context ctx, ArrayList<EducationalDetailsBean> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.edit_educationdetails_ArrayList = editModelArrayList;
        this.context=ctx;


    }

    @Override
    public EducationalDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_educationdetails_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final EducationalDetailsAdapter.MyViewHolder holder, final int position) {


        holder.et_edudetails_graduate.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_graduate());
        holder.et_edudetails_university.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_university());
        holder.et_edudetails_college_name.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_college_name());
        holder.et_edudetails_location.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_location());
        holder.et_edudetails_specialization.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_specialization());
        holder.et_edudetails_starting_year.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_starting_year());
        holder.et_edudetails_passoutyear.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_passoutyear());
        holder.et_edudetails_percentage.setText(edit_educationdetails_ArrayList.get(position).getEdit_educationdetails_percentage());
        holder.tv_educationDetails_count.setText("Education Details: "+edit_educationdetails_ArrayList.get(position).getEt_educationdetails_count());
        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return edit_educationdetails_ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText et_edudetails_graduate,et_edudetails_university,et_edudetails_college_name,et_edudetails_location,et_edudetails_specialization,
                et_edudetails_starting_year,et_edudetails_passoutyear,et_edudetails_percentage;
        protected AutoCompleteTextView act_prof_skills_experience;
        protected TextView tv_educationDetails_count;



        public MyViewHolder(View itemView) {
            super(itemView);

            et_edudetails_graduate = (EditText) itemView.findViewById(R.id.et_educationdetails_gaduate);
            et_edudetails_university = (EditText) itemView.findViewById(R.id.et_educationdetails_univrsity);
            et_edudetails_college_name = (EditText) itemView.findViewById(R.id.et_educationdetails_college_name);
            et_edudetails_location = (EditText) itemView.findViewById(R.id.et_educationdetails_location);
            et_edudetails_specialization = (EditText) itemView.findViewById(R.id.et_educationdetails_specialization);
            et_edudetails_starting_year = (EditText) itemView.findViewById(R.id.et_educationdetails_starting_year);
            et_edudetails_passoutyear = (EditText) itemView.findViewById(R.id.et_educationdetails_passout_year);
            et_edudetails_percentage = (EditText) itemView.findViewById(R.id.et_educationdetails_percentage);
            //act_prof_skills_experience=(AutoCompleteTextView)itemView.findViewById(R.id.act_pro_skill_experiance);
            tv_educationDetails_count=(TextView)itemView.findViewById(R.id.tv_education_details_count);

            et_edudetails_graduate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_graduate(et_edudetails_graduate.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_edudetails_university.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_university(et_edudetails_university.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_edudetails_college_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_college_name(et_edudetails_college_name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_edudetails_location.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_location(et_edudetails_location.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_edudetails_specialization.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_specialization(et_edudetails_specialization.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_edudetails_starting_year.setOnClickListener(new View.OnClickListener() {

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
                                et_edudetails_starting_year.setText("Select Date");

                                //ft_selecteddate = null;

                            }
                            else {
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                                String selecteddate = fmt.format(currentDate.getTime());
                                //ft_selecteddate = selecteddate;
                                et_edudetails_starting_year.setText("" + selecteddate);
                                edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_starting_year(et_edudetails_starting_year.getText().toString());


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

            et_edudetails_passoutyear.setOnClickListener(new View.OnClickListener() {

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
                                et_edudetails_passoutyear.setText("Select Date");

                                //ft_selecteddate = null;

                            }
                            else {
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                                String selecteddate = fmt.format(currentDate.getTime());
                                //ft_selecteddate = selecteddate;
                                et_edudetails_passoutyear.setText("" + selecteddate);
                                edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_passoutyear(et_edudetails_passoutyear.getText().toString());


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

            et_edudetails_percentage.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    edit_educationdetails_ArrayList.get(getAdapterPosition()).setEdit_educationdetails_percentage(et_edudetails_percentage.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }

    }
    private void removeAt(int position) {
        edit_educationdetails_ArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, edit_educationdetails_ArrayList.size());
    }
}