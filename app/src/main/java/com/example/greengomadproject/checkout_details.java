package com.example.greengomadproject;

public class checkout_details {
    private String enter_address;
    private String phone;
    public checkout_details(){}

    public checkout_details(String enter_address, String phone) {
        this.enter_address = enter_address;
        this.phone = phone;
    }

    public String getEnter_address() {
        return enter_address;
    }

    public void setEnter_address(String enter_address) {
        this.enter_address = enter_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
