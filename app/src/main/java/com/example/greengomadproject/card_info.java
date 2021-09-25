package com.example.greengomadproject;

public class card_info {

    private String card_number;
    private String expiry_date;
    private String cvv;
    public card_info(){}

    public card_info(String card_number, String expiry_date, String cvv) {
        this.card_number = card_number;
        this.expiry_date = expiry_date;
        this.cvv = cvv;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}