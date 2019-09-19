package com.bangalore.sahicareer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.InternshipBean;

import java.util.List;

public class InternshipAdapter extends RecyclerView.Adapter<InternshipAdapter.MyViewHolder> {

    private List<InternshipBean> internshipsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_internship_title,tv_internship_city,tv_internship_salary,tv_internship_workinghours,tv_internship_duration,tv_internship_lastdate;

        public MyViewHolder(View view) {
            super(view);
            tv_internship_title = (TextView) view.findViewById(R.id.intern_title);
            tv_internship_salary = (TextView) view.findViewById(R.id.intern_salary);
            tv_internship_workinghours = (TextView) view.findViewById(R.id.intern_working_hours);
            tv_internship_duration = (TextView) view.findViewById(R.id.intern_duration);
            tv_internship_lastdate = (TextView) view.findViewById(R.id.intern_date);



        }
    }


    public InternshipAdapter(List<InternshipBean> internshipsList) {
        this.internshipsList = internshipsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.internships_lists, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InternshipBean internshiplist = internshipsList.get(position);
        holder.tv_internship_title.setText(internshiplist.getInternship_title());
        holder.tv_internship_salary.setText(internshiplist.getInternship_salary());
        holder.tv_internship_workinghours.setText(internshiplist.getInternship_working_hours());
        holder.tv_internship_duration.setText(internshiplist.getInternship_duration());
        holder.tv_internship_lastdate.setText(internshiplist.getInternship_lastdatetoapply());


    }

    @Override
    public int getItemCount() {
        return internshipsList.size();
    }

}