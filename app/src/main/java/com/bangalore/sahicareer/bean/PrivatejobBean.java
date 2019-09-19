package com.bangalore.sahicareer.bean;

public class PrivatejobBean {
    private String privatejob_id;
    private String privatejob_title;
    private String privatejob_salary;
    private String privatejob_url;
    private String privatejob_category;
    private String privatejob_location;
    private String privatejob_designation;


    public PrivatejobBean() {
    }

    public PrivatejobBean(String privatejob_id,String privatejob_title, String privatejob_salary, String privatejob_url,String privatejob_category,String privatejob_location,String privatejob_designation) {
        this.setPrivatejob_id(privatejob_id);
        this.privatejob_title=privatejob_title;
        this.privatejob_salary=privatejob_salary;
        this.privatejob_url=privatejob_url;
        this.setPrivatejob_category(privatejob_category);
        this.privatejob_location=privatejob_location;
        this.setPrivatejob_designation(privatejob_designation);
    }

    public String getPrivatejob_title() {
        return privatejob_title;
    }

    public void setPrivatejob_title(String privatejob_title) {
        this.privatejob_title = privatejob_title;
    }

    public String getPrivatejob_location() {
        return privatejob_location;
    }

    public void setPrivatejob_location(String privatejob_location) {
        this.privatejob_location = privatejob_location;
    }

    public String getPrivatejob_salary() {
        return privatejob_salary;
    }

    public void setPrivatejob_salary(String privatejob_salary) {
        this.privatejob_salary = privatejob_salary;
    }

    public String getPrivatejob_url() {
        return privatejob_url;
    }

    public void setPrivatejob_url(String privatejob_url) {
        this.privatejob_url = privatejob_url;
    }

    public String getPrivatejob_id() {
        return privatejob_id;
    }

    public void setPrivatejob_id(String privatejob_id) {
        this.privatejob_id = privatejob_id;
    }

    public String getPrivatejob_category() {
        return privatejob_category;
    }

    public void setPrivatejob_category(String privatejob_category) {
        this.privatejob_category = privatejob_category;
    }

    public String getPrivatejob_designation() {
        return privatejob_designation;
    }

    public void setPrivatejob_designation(String privatejob_designation) {
        this.privatejob_designation = privatejob_designation;
    }
}
