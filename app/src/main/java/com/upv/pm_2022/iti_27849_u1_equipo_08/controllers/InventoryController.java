package com.upv.pm_2022.iti_27849_u1_equipo_08.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {

     private final String TABLE_NAME = "Inventory";
     private SQLiteDatabase db;

    public InventoryController(SQLiteDatabase db) {
        this.db = db;
    }

    public List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Inventory inventory = new Inventory(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            Integer.parseInt(cursor.getString(2))
                    );
                    items.add(inventory);
                } while (cursor.moveToNext());
            }
        }
        return items;
    }

    public void addItem(Inventory inventory){
        ContentValues values = new ContentValues();
        values.put("name", inventory.getName());
        values.put("owner_id", inventory.getOwner_id());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Inventory getItem(int id){
        Inventory inventory;
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { "id", "name", "last_name"},
                "id=?",
                new String[] { String.valueOf(id)},
                null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            inventory = new Inventory(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)));
        }else
            inventory = null;
        return inventory;
    }

    public int updateItem(Inventory inventory){
        ContentValues values = new ContentValues();
        values.put("name", inventory.getName());
        return db.update(TABLE_NAME, values, "id = ?",
                new String[] { String.valueOf(inventory.getId())});
    }

    public void deleteItem(Inventory inventory){
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(inventory.getId())});
        db.close();
    }
}
