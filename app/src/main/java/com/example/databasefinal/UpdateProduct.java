package com.example.databasefinal;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class UpdateProduct extends AppCompatActivity {

    public static final String PRODUCT_ID = "product_id";
    static final String UPDATED_NOTE = "note_text";
    static final String UPDATED_PRICE = "price_text";
    static final String UPDATED_STORE = "store_text";
    private EditText etNote;
    private EditText etPrice;
    private EditText etStore;
    private Bundle bundle;
    private String noteId;
    private LiveData<Product> note;

    UpdateProductViewModel noteModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etNote = findViewById(R.id.etNote);
        etPrice = findViewById(R.id.etPrice);
        etStore = findViewById(R.id.etStore);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            noteId = bundle.getString("note_id");
        }

        noteModel = ViewModelProviders.of(this).get(UpdateProductViewModel.class);
        note = noteModel.getNote(noteId);
        note.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product note) {
                etNote.setText(note.getNote());
                etPrice.setText(note.getPrice());
                etStore.setText(note.getStore());
            }
        });
    }

    public void updateNote(View view) {
        String updatedNote = etNote.getText().toString();
        String updatedPrice = etPrice.getText().toString();
        String updatedStore = etStore.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PRODUCT_ID, noteId);
        resultIntent.putExtra(UPDATED_NOTE, updatedNote);
        resultIntent.putExtra(UPDATED_PRICE, updatedPrice);
        resultIntent.putExtra(UPDATED_STORE, updatedStore);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }
}
