package com.upv.pm_2022.iti_27849_u1_equipo_08.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Loan;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle the Database Operations of the Loans
 */
public class LoanController {

    private final String TABLE_NAME = "Loans";
    private SQLiteDatabase db;

    /**
     * Constructor
     *
     * @param db Current Database
     */
    public LoanController(SQLiteDatabase db) {
        this.db = db;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * Obtain all the resources of Loans
     *
     * @return List of Loans of the current loans in the db
     */
    public List<Loan> getAll(){
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try(Cursor cursor = db.rawQuery(query, null)){
            if (cursor.moveToFirst()) {
                do {
                    Loan loan = new Loan(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                    );
                    loans.add(loan);
                }while (cursor.moveToNext());
            }
        }
        return loans;
    }

    /**
     * Stores a new loan in the db
     *
     * @param loan new Object to be stored
     */
    public void addLoan(Loan loan){
        ContentValues values = new ContentValues();
        values.put("inventory_id", loan.getInventory_id());
        values.put("customer_id", loan.getCustomer_id());
        values.put("status", loan.getStatus());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Obtain a new Loan object from the id provided
     * Then, do a simple SQL Where using the id
     * @param id of the loan be searched
     * @return null if empty | Loan Object
     */
    public Loan getLoan(int id){
        Loan loan;
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { "id", "name", "last_name"},
                "id = ?",
                new String[] { String.valueOf(id)},
                null, null, null, null
        );
        if (cursor != null){
            cursor.moveToFirst();
            loan = new Loan(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3))
            );
        } else
            loan = null;
        return loan;
    }

    /**
     * Update the selected loan with new data
     * @param loan instance to be updated
     * @return the update result
     */
    public int updateLoan(Loan loan){
        ContentValues values = new ContentValues();
        values.put("inventory_id", loan.getInventory_id());
        values.put("customer_id", loan.getCustomer_id());
        values.put("status", loan.getStatus());
        return db.update(TABLE_NAME, values, "id = ?",
                new String[] { String.valueOf(loan.getId())}
        );
    }

    /**
     * Delete the selected loan instance using the id
     * @param loan instace to be deleted from the resources
     */
    public void deleteLoan(Loan loan) {
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(loan.getId())}
        );
        db.close();
    }

    /**
     * Uses the where clause with like clause with the columns name and last_name
     * to find all the similar who contains the string provided
     *
     * @param like String to be used and obtain similar
     * @return a new List of Loans who matches the provided string
     */
    public List<Loan> findLoanByNameOrLastName(String like){
        //TODO: Change query to use names and obtain products
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " +
                "name LIKE " + "%" + like + "%" +
                " OR " + "last_name LIKE " + "%" + like + "%";
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()){
                do {
                    Loan loan = new Loan(
                            Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            Integer.parseInt(cursor.getString(2)),
                            Integer.parseInt(cursor.getString(3))
                    );
                    loans.add(loan);
                }while (cursor.moveToNext());
            }
        }
        return loans;
    }

}
