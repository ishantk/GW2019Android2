package com.auribises.gw2019android2;

public class Contact {

    public int image;
    public String name;
    public String phone;

    public Contact(){

    }

    public Contact(int image, String name, String phone) {
        this.image = image;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
