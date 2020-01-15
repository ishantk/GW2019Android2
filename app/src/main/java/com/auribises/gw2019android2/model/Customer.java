package com.auribises.gw2019android2.model;

public class Customer {

    public String name;
    public String phone;
    public String email;

    public Customer(){
        name = "NA";
        phone = "NA";
        email = "NA";
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
