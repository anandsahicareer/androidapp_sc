package com.bangalore.sahicareer.bean;

public class GovtjobBean {
    private String govt_id;
    private String govt_title;
    private String govt_url;
    private String govt_lastdatetoapply;
    private String govt_location;

    public GovtjobBean() {
    }
    public GovtjobBean(String govt_id,String govt_title,String govt_url,String govt_lastdatetoapply,String govt_location){
        this.setGovt_id(govt_id);
        this.setGovt_title(govt_title);
        this.setGovt_url(govt_url);
        this.setGovt_lastdatetoapply(govt_lastdatetoapply);
        this.setGovt_location(govt_location);

    }


    public String getGovt_id() {
        return govt_id;
    }

    public void setGovt_id(String govt_id) {
        this.govt_id = govt_id;
    }

    public String getGovt_title() {
        return govt_title;
    }

    public void setGovt_title(String govt_title) {
        this.govt_title = govt_title;
    }

    public String getGovt_url() {
        return govt_url;
    }

    public void setGovt_url(String govt_url) {
        this.govt_url = govt_url;
    }

    public String getGovt_lastdatetoapply() {
        return govt_lastdatetoapply;
    }

    public void setGovt_lastdatetoapply(String govt_lastdatetoapply) {
        this.govt_lastdatetoapply = govt_lastdatetoapply;
    }

    public String getGovt_location() {
        return govt_location;
    }

    public void setGovt_location(String govt_location) {
        this.govt_location = govt_location;
    }
}
