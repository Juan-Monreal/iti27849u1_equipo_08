package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;

public class Customer {
    private int id;
    private String name;
    private String last_name;

    public Customer() {
    }

    public Customer(int id, String name, String last_name) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
    }

    public Customer(String name, String last_name) {
        this.name = name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    public String toExport(){
        return "(" +
                id + "," +
                "'" + name +"'," +
                "'" + last_name +"'" +
                ")";
    }
}
