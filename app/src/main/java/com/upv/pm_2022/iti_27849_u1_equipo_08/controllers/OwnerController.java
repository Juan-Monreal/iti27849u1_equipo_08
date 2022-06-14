package com.upv.pm_2022.iti_27849_u1_equipo_08.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Owner;

public class OwnerController {

    private final String TABLE_NAME = "Owners";
    private SQLiteDatabase db;

    public OwnerController(SQLiteDatabase db){
        this.db = db;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public void addOwner(Owner owner){
        ContentValues values = new ContentValues();
        values.put("name", owner.getName());
        values.put("last_name", owner.getLast_name());
        values.put("email", owner.getEmail());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Owner getOwner(int id){
        Owner owner;
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { "id", "name", "last_name", "email"}, "id = ?",
                new String[] { String.valueOf(id)},
                null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            owner = new Owner(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
        }else
            owner = null;
        return owner;
    }
    public int updateOwner(Owner owner){
        ContentValues values = new ContentValues();
        values.put("name", owner.getName());
        values.put("last_name", owner.getLast_name());
        values.put("email", owner.getEmail());
        return db.update(TABLE_NAME, values, "id = ?",
                new String[] { String.valueOf(owner.getId())});
    }
}
