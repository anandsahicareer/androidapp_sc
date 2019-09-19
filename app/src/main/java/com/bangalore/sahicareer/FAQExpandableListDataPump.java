package com.bangalore.sahicareer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> individuals = new ArrayList<String>();
        individuals.add("How SahiCareer can help you in your Career");
        individuals.add("SahiCareer's Career Assessment Test");
        individuals.add("SahiCareer's Career Guidance");
        individuals.add("SahiCareer mentorship");
        individuals.add("What is the best part in career explorer from SahiCareer?");
        individuals.add("SahiCareer Resume Builder");


        List<String> schoolsandcollege = new ArrayList<String>();
        schoolsandcollege.add("Why does a School need SahiCareer?");
        schoolsandcollege.add("Why does a College need SahiCareer?");
        schoolsandcollege.add("How is SahiCareer better over competition?");
        schoolsandcollege.add("Other questions");


        expandableListDetail.put("For Individuals", individuals);
        expandableListDetail.put("For schools and colleges", schoolsandcollege);
        return expandableListDetail;
    }
}