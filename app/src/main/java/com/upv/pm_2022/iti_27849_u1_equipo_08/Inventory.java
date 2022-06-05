package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;

public class Inventory {

    private int id;
    private String name;


    public Inventory(int id, String name) {
        this.id = id;
        this.name = name;
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

    @NonNull
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
