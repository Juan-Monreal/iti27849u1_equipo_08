package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;

public class Inventory {

    private int id;
    private String name;
    private int owner_id;


    public Inventory(int id, String name, int owner_id) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
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

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner_id=" + owner_id +
                '}';
    }
}
