package com.example.databasefinal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Product.class, version = 1) 
public abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract ProductDao noteDao();

    private static volatile ProductRoomDatabase noteRoomInstance;

    static ProductRoomDatabase getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (ProductRoomDatabase.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "product_database")
                            .build();
                }
            }
        }
        return noteRoomInstance;
    }

}
