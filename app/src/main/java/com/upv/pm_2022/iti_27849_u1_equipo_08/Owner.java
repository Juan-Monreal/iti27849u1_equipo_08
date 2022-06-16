package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;

public class Owner {

    private int id;
    private String name;
    private String last_name;
    private String email;

    public Owner() {
    }

    public Owner(int id, String name, String last_name, String email) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String toExport(){
        return "(" +
                id + "," +
                "'" + name +"'," +
                "'" + last_name +"'," +
                "'" + email +"'" +
                ")";
    }
}
