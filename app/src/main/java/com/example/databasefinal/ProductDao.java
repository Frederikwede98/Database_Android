package com.example.databasefinal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product note);

    @Query("SELECT * FROM shopping")
    LiveData<List<Product>> getAllNotes();

    @Query("SELECT * FROM shopping WHERE id=:noteId")
    LiveData<Product> getNote(String noteId);

    @Update
    void update(Product note);

    @Delete
    int delete(Product note);
}
