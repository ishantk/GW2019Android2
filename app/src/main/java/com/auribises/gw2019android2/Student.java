package com.auribises.gw2019android2;

public class Student {

    // Attributes : Property of Object
    public boolean ccpp;
    public boolean java;
    public boolean python;

    public String gender;

    public String city;

    public float rating;

    public String name;

    public Student(){

    }

    public Student(boolean ccpp, boolean java, boolean python, String gender, String city, float rating, String name) {
        this.ccpp = ccpp;
        this.java = java;
        this.python = python;
        this.gender = gender;
        this.city = city;
        this.rating = rating;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ccpp=" + ccpp +
                ", java=" + java +
                ", python=" + python +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                '}';
    }

    boolean validate(){
        boolean flag = true;

        if( !(ccpp || java || python) ){
            flag = false;
        }

        if(gender == null){
            flag = false;
        }

        if(city==null){
            flag = false;
        }

        if(rating == 0.0){
            flag = false;
        }


        if(name == null){
            flag = false;
        }

        return flag;
    }
}
