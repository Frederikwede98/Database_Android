package com.example.databasefinal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "shopping")
public class Product {

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }

    @NonNull
    public String getPrice(){
        return this.mPrice;
    }

    @NonNull
    public String getStore(){
        return this.mStore;
    }


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "product")
    private String mNote;

    @ColumnInfo(name = "price")
    private String mPrice;

    @ColumnInfo(name = "store")
    private String mStore;

    public Product(String note, String price, String store) {
        this.mNote = note;
        this.mPrice = price;
        this.mStore = store;
    }

    public void setId(int id) {
        this.id = id;
    }
}
