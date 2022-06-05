package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;

public class Loan {

    private int id;
    private int inventory_id;
    private int customer_id;
    private boolean status;

    //TODO: Add fields to represent Date

    public Loan(int id, int inventory_id, int customer_id, boolean status) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", inventory_id=" + inventory_id +
                ", customer_id=" + customer_id +
                ", status=" + status +
                '}';
    }
}
