package com.bangalore.sahicareer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangalore.sahicareer.R;
import com.bangalore.sahicareer.bean.GovtjobBean;
import com.bangalore.sahicareer.bean.PrivatejobBean;

import java.util.List;


public class GovtjobsAdapter extends RecyclerView.Adapter<GovtjobsAdapter.MyViewHolder> {

    private List<GovtjobBean> govtjobList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_govtjob_title,tv_govtjob_lastdatetoapply,tv_govtjob_location;

        public MyViewHolder(View view) {
            super(view);
            tv_govtjob_title = (TextView) view.findViewById(R.id.tv_govtjobs_title);
            tv_govtjob_lastdatetoapply = (TextView) view.findViewById(R.id.tv_govtjob_lastdatetoapply);
            tv_govtjob_location = (TextView) view.findViewById(R.id.tv_govtjob_location);
        }
    }


    public GovtjobsAdapter(List<GovtjobBean> govtjobList) {
        this.govtjobList = govtjobList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.govtjob_lists, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GovtjobBean govtjoblist = govtjobList.get(position);
        holder.tv_govtjob_title.setText(govtjoblist.getGovt_title());
        holder.tv_govtjob_lastdatetoapply.setText(govtjoblist.getGovt_lastdatetoapply());
        holder.tv_govtjob_location.setText(govtjoblist.getGovt_location());
    }

    @Override
    public int getItemCount() {
        return govtjobList.size();
    }
}