package com.bangalore.sahicareer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentLevelAdapter extends BaseExpandableListAdapter {
    private final Context mContext;
    private final List<String> mListDataHeader;
    private final Map<String, List<String>> mListData_SecondLevel_Map;
    private final Map<String, List<String>> mListData_ThirdLevel_Map;

    public ParentLevelAdapter(Context mContext, List<String> mListDataHeader) {
        this.mContext = mContext;
        this.mListDataHeader = new ArrayList<>();
        this.mListDataHeader.addAll(mListDataHeader);


        // SECOND LEVEL
        String[] mItemHeaders;
        mListData_SecondLevel_Map = new HashMap<>();
        int parentCount = mListDataHeader.size();
        for (int i = 0; i < parentCount; i++) {
            String content = mListDataHeader.get(i);
            switch (content) {
                case "How SahiCareer can help you in your Career":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_a);
                    break;
                case "SahiCareer's Career Assessment Test":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_b);
                    break;
                case "SahiCareer's Career Guidance":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_c);
                    break;
                case "SahiCareer mentorship":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_d);
                    break;
                case "What is the best part in career explorer from SahiCareer?":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_e);
                    break;
                case "SahiCareer Resume Builder":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_f);
                    break;
                case "Why does a School need SahiCareer?":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_g);
                    break;
                case "Why does a College need SahiCareer?":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_h);
                    break;
                case "How is SahiCareer better over competition?":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_i);
                    break;
                case "Other questions":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_array_j);
                    break;
                default:
                    mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_arrray_default);
            }
            mListData_SecondLevel_Map.put(mListDataHeader.get(i), Arrays.asList(mItemHeaders));
        }


        // THIRD LEVEL
        String[] mItemChildOfChild;
        List<String> listChild;
        mListData_ThirdLevel_Map = new HashMap<>();
        for (Object o : mListData_SecondLevel_Map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object object = entry.getValue();
            if (object instanceof List) {
                List<String> stringList = new ArrayList<>();

                Collections.addAll(stringList, (String[]) ((List) object).toArray());
                for (int i = 0; i < stringList.size(); i++) {
                    String content = stringList.get(i);
                    switch (content) {
                        case "How can SahiCareer help me plan my career?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_a_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                           /* Intent myIntent = new Intent(mContext, FAQquestions.class);
                            mContext.startActivity(myIntent);*/
                            break;
                        case "How is SahiCareer different from others?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_a_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What makes you different from other career \nassessment tests?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_b_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How reliable is the career assessment test?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_b_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What if I start the assessment test but can\'t \ncontinue.Can I complete the test some other time?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_b_three);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What is the difference between the Free \nassessment report and Paid assessment report?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_b_four);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What is the background of the SahiCareer career \ncounsellors?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_c_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Why do I need a career counsellor?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_c_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How can a career counsellor guide me to choose \nthe right job?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_c_three);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "My family and friends have advised me a career \ndifferent from that in my report. What should I do?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_c_four);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How do you ensure that I find a mentor matching \nmy profile?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_d_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What is the background of the SahiCareer career \nmentors?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_d_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What is SahiCareer\'s career explorer?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_e_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Why career exploration is important?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_e_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Does SahiCareer\'s career explorer tell me my \nperfect career?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_e_three);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How does your Resume Generator work?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_f_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How does resume play a role in getting a job?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_f_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What is the fees for building my resume?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_f_three);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "I am not happy with my resume. Can I get some \nhelp?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_f_four);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How will our school benefit from your Career \nGuidance System?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_g_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "We already have a Career Counsellor in our school.\nWhy do we need SahiCareer?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_g_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "How will our college benefit from the SahiCareer \nCareer Guidance?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_h_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "We already have a Career Counsellor in our college.\nWhy do we need SahiCareer?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_h_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "What different services does SahiCareer offer?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_i_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Does SahiCareer guarantee Data Confidentiality?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_j_one);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Are parents involved in this Guidance Process?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_j_two);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Do you have any commercial partner?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_j_three);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        case "Are the tests online or paper-based?":
                            mItemChildOfChild = mContext.getResources().getStringArray(R.array.desc_j_four);
                            listChild = Arrays.asList(mItemChildOfChild);
                            mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                            break;

                        default:
                            mItemHeaders = mContext.getResources().getStringArray(R.array.sub_one_items_arrray_default);


                    }
                }
            }
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CustomExpListView secondLevelExpListView = new CustomExpListView(this.mContext);
        String parentNode = (String) getGroup(groupPosition);
        secondLevelExpListView.setAdapter(new SecondLevelAdapter(this.mContext, mListData_SecondLevel_Map.get(parentNode), mListData_ThirdLevel_Map));
        secondLevelExpListView.setGroupIndicator(null);
       secondLevelExpListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    secondLevelExpListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        return secondLevelExpListView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drawer_list_group, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.NORMAL);
        lblListHeader.setTextColor(Color.BLACK);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
}