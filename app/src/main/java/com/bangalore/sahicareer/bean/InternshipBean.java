package com.bangalore.sahicareer.bean;

public class InternshipBean {
    private String internship_title;
    private String internship_city;
    private String internship_salary;
    private String internship_working_hours;
    private String internship_duration;
    private String internship_lastdatetoapply;
    private String internship_url;



    public InternshipBean() {
    }

    public InternshipBean(String internship_title, String internship_city, String internship_salary,String internship_working_hours,String internship_duration,String internship_lastdatetoapply,String internship_url) {

        this.internship_title = internship_title;
        this.internship_city=internship_city;
        this.internship_salary = internship_salary;
        this.internship_working_hours = internship_working_hours;
        this.internship_duration = internship_duration;
        this.internship_url=internship_url;

        this.internship_lastdatetoapply = internship_lastdatetoapply;
    }

    public String getInternship_title() {
        return internship_title;
    }

    public void setInternship_title(String internship_title) {
        this.internship_title = internship_title;
    }


    public String getInternship_lastdatetoapply() {
        return internship_lastdatetoapply;
    }

    public void setInternship_lastdatetoapply(String internship_lastdatetoapply) {
        this.internship_lastdatetoapply = internship_lastdatetoapply;
    }

    public String getInternship_salary() {
        return internship_salary;
    }

    public void setInternship_salary(String internship_salary) {
        this.internship_salary = internship_salary;
    }

    public String getInternship_duration() {
        return internship_duration;
    }

    public void setInternship_duration(String internship_duration) {
        this.internship_duration = internship_duration;
    }

    public String getInternship_working_hours() {
        return internship_working_hours;
    }

    public void setInternship_working_hours(String internship_working_hours) {
        this.internship_working_hours = internship_working_hours;
    }

    public String getInternship_city() {
        return internship_city;
    }

    public void setInternship_city(String internship_city) {
        this.internship_city = internship_city;
    }

    public String getInternship_url() {
        return internship_url;
    }

    public void setInternship_url(String internship_url) {
        this.internship_url = internship_url;
    }
}
