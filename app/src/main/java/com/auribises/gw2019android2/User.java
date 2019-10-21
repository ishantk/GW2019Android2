package com.auribises.gw2019android2;

import java.io.Serializable;

public class User implements Serializable { // Marker Interface

    public String name;
    public int age;
    public int salary;

    public User(){

    }

    public User(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
