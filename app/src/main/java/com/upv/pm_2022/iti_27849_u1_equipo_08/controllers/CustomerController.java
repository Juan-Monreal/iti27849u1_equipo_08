package com.upv.pm_2022.iti_27849_u1_equipo_08.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle the Database Operations of the Customers
 */
public class CustomerController {

    private final String TABLE_NAME = "Customers";
    private SQLiteDatabase db;

    /**
     * Constructor
     *
     * @param db Current Database
     */
    public CustomerController(SQLiteDatabase db) {
        this.db = db;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * Obtain all the resources of Customers
     *
     * @return List of Customers of the current customers in the db
     */
    public List<Customer> getAll(){
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try(Cursor cursor = db.rawQuery(query, null)){
            if (cursor.moveToFirst()) {
                do {
                    Customer customer = new Customer(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                    );
                    customers.add(customer);
                }while (cursor.moveToNext());
            }
        }
        return customers;
    }

    /**
     * Stores a new customer in the db
     *
     * @param customer new Object to be stored
     */
    public void addCustomer(Customer customer){
        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("last_name", customer.getLast_name());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Obtain a new Customer object from the id provided
     * Then, do a simple SQL Where using the id
     * @param id of the customer be searched
     * @return null if empty | Customer Object
     */
    public Customer getCustomer(int id){
        Customer customer;
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { "id", "name", "last_name"},
                "id = ?",
                new String[] { String.valueOf(id)},
                null, null, null, null
        );
        if (cursor != null){
            cursor.moveToFirst();
            customer = new Customer(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        } else
            customer = null;
        return customer;
    }

    /**
     * Update the selected customer with new data
     * @param customer instance to be updated
     * @return the update result
     */
    public int updateCustomer(Customer customer){
        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("last_name", customer.getLast_name());
        return db.update(TABLE_NAME, values, "id = ?",
                new String[] { String.valueOf(customer.getId())}
        );
    }

    /**
     * Delete the selected customer instance using the id
     * @param customer instace to be deleted from the resources
     */
    public void deleteCustomer(Customer customer) {
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(customer.getId())}
        );
        db.close();
    }

    /**
     * Uses the where clause with like clause with the columns name and last_name
     * to find all the similar who contains the string provided
     *
     * @param like String to be used and obtain similar
     * @return a new List of Customers who matches the provided string
     */
    public List<Customer> findCustomerByNameOrLastName(String like){
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " +
                "name LIKE " + "%" + like + "%" +
                " OR " + "last_name LIKE " + "%" + like + "%";
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()){
                do {
                    Customer customer = new Customer(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                    );
                    customers.add(customer);
                }while (cursor.moveToNext());
            }
        }
        return customers;
    }
}
