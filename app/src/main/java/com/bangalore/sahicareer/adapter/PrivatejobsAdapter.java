package com.bangalore.sahicareer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.InternshipBean;
import com.bangalore.sahicareer.bean.PrivatejobBean;

import java.util.List;

public class PrivatejobsAdapter extends RecyclerView.Adapter<PrivatejobsAdapter.MyViewHolder> {

    private List<PrivatejobBean> privatejobList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_privatejob_title,tv_privatejob_designation,tv_privatejob_location,tv_privatejob_salary,tv_privatejob_duration;

        public MyViewHolder(View view) {
            super(view);
            tv_privatejob_title = (TextView) view.findViewById(R.id.privatejob_title);
            tv_privatejob_designation = (TextView) view.findViewById(R.id.privatejob_designation);
            tv_privatejob_location = (TextView) view.findViewById(R.id.privatejob_location);
            tv_privatejob_salary = (TextView) view.findViewById(R.id.privatejob_salary);
            tv_privatejob_duration = (TextView) view.findViewById(R.id.privatejob_duration);
        }
    }


    public PrivatejobsAdapter(List<PrivatejobBean> privatejobList) {
        this.privatejobList = privatejobList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.privatejob_lists, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PrivatejobBean privatejoblist = privatejobList.get(position);
        holder.tv_privatejob_title.setText(privatejoblist.getPrivatejob_title());
        //holder.tv_privatejob_desc.setText(privatejoblist.getPrivatejob_desc());
        holder.tv_privatejob_location.setText(privatejoblist.getPrivatejob_location());
        holder.tv_privatejob_salary.setText(privatejoblist.getPrivatejob_salary());
        holder.tv_privatejob_designation.setText(privatejoblist.getPrivatejob_designation());
    }

    @Override
    public int getItemCount() {
        return privatejobList.size();
    }
}