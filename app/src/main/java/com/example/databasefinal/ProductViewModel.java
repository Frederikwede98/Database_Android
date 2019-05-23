package com.example.databasefinal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private ProductDao noteDao;
    private ProductRoomDatabase noteDB;
    private LiveData<List<Product>> mAllNotes;

    public ProductViewModel(Application application) {
        super(application);

        noteDB = ProductRoomDatabase.getDatabase(application);
        noteDao = noteDB.noteDao();
        mAllNotes = noteDao.getAllNotes();
    }

    public void insert(Product note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    LiveData<List<Product>> getAllNotes() {
        return mAllNotes;
    }

    public void update(Product note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Product note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Product, Void, Void> {

        ProductDao mAsyncTaskDao;

        OperationsAsyncTask(ProductDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(ProductDao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Product... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(ProductDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Product... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(ProductDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Product... notes) {
            mAsyncTaskDao.delete(notes[0]);
            return null;
        }
    }

}
