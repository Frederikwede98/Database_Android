package com.example.databasefinal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

public class UpdateProductViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private ProductDao noteDao;
    private ProductRoomDatabase db;

    public UpdateProductViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Update ViewModel");
        db = ProductRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
    }

    public LiveData<Product> getNote(String noteId) {
        return noteDao.getNote(noteId);
    }

}
