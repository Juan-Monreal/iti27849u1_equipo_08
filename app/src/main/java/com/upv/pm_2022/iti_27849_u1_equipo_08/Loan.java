package com.upv.pm_2022.iti_27849_u1_equipo_08;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RequiresApi(api = Build.VERSION_CODES.O)
public class Loan {

    private int id;
    private int inventory_id;
    private int customer_id;
    private boolean status;
    private LocalDateTime loan_datetime;
    private DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    //TODO: Add field to represent LocalDateTime
    //https://stackoverflow.com/questions/7363112/best-way-to-work-with-dates-in-android-sqlite

    public Loan(int id, int inventory_id, int customer_id, boolean status, LocalDateTime loan_datetime) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.status = status;
        this.loan_datetime = loan_datetime;
    }
    public Loan(int inventory_id, int customer_id, boolean status, LocalDateTime loan_datetime) {
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.status = status;
        this.loan_datetime = loan_datetime;
    }

    public Loan(int id, int inventory_id, int customer_id, int status, LocalDateTime loan_datetime) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        if (status == 1)
            this.status = true;
        else
            this.status = false;
        this.loan_datetime = loan_datetime;
    }
    public Loan(int inventory_id, int customer_id, int status, LocalDateTime loan_datetime) {
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        if (status == 1)
            this.status = true;
        else
            this.status = false;
        this.loan_datetime = loan_datetime;
    }

    public Loan(int id, int inventory_id, int customer_id, boolean status, String loan_datetime) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.status = status;
        this.loan_datetime = LocalDateTime.from(pattern.parse(loan_datetime));
    }
    public Loan(int inventory_id, int customer_id, boolean status, String loan_datetime) {
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.status = status;
        this.loan_datetime = LocalDateTime.from(pattern.parse(loan_datetime));
    }

    public Loan(int id, int inventory_id, int customer_id, int status, String loan_datetime) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        if (status == 1)
            this.status = true;
        else
            this.status = false;
        this.loan_datetime = LocalDateTime.from(pattern.parse(loan_datetime));
    }
    public Loan(int inventory_id, int customer_id, int status, String loan_datetime) {
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        if (status == 1)
            this.status = true;
        else
            this.status = false;
        this.loan_datetime = LocalDateTime.from(pattern.parse(loan_datetime));
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

    public int getStatus() {
        if (this.status)
            return 1;
        else
            return 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStatus(int status) {
        if (status == 1)
            this.status = true;
        else
            this.status = false;
    }

    public LocalDateTime getLoan_datetime() {
        return loan_datetime;
    }
    public String getLoan_datetimeAsString(){
        return loan_datetime.toString();
    }

    public void setLoan_datetime(LocalDateTime loan_datetime) {
        this.loan_datetime = loan_datetime;
    }

    public void SetLoan_datetime(String loan_datetime){
        this.loan_datetime = LocalDateTime.from(pattern.parse(loan_datetime));
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", inventory_id=" + inventory_id +
                ", customer_id=" + customer_id +
                ", status=" + status +
                ", loan_datetime=" + loan_datetime +
                '}';
    }

    @NonNull

    public String toExport(){
        return "(" +
                id + "," +
                inventory_id + "," +
                customer_id + "," +
                getStatus() + "," +
                 "'"+ loan_datetime.toString() + "'"+
                ")";
    }
}
