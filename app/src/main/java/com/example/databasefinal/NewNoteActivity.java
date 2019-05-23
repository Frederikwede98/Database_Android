package com.example.databasefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_product";
    public static final String PRICE_ADDED = "new_price";
    public static final String STORE_ADDED = "new_store";

    private EditText etNewProduct;
    private EditText etNewPrice;
    private EditText etNewStore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        etNewProduct = findViewById(R.id.etNewNote);
        etNewPrice = findViewById(R.id.etNewPrice);
        etNewStore = findViewById(R.id.etNewStore);

        Button button = findViewById(R.id.bAdd);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(etNewProduct.getText()) || TextUtils.isEmpty(etNewPrice.getText()) || TextUtils.isEmpty(etNewStore.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String product = etNewProduct.getText().toString();
                    String price = etNewPrice.getText().toString();
                    String store = etNewStore.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED, product);
                    resultIntent.putExtra(PRICE_ADDED, price);
                    resultIntent.putExtra(STORE_ADDED, store);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });
    }

}
