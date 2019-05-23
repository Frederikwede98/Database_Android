package com.example.databasefinal;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.NoteViewHolder>{

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Product myNote);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Product> mNotes;
    private OnDeleteClickListener onDeleteClickListener;

    public ProductListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        if (mNotes != null) {
         //   Product note = mNotes.get(position);
            holder.productName.setText(mNotes.get(position).getNote());
            holder.price.setText(mNotes.get(position).getPrice());
            holder.productName.setText(mNotes.get(position).getStore());
           // holder.setData(note.getNote(), note.getPrice(), note.getStore(), position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.productName.setText("No Products");
            holder.price.setText("No Price");
            holder.store.setText("No Store");
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public void setNotes(List<Product> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView productName, price, store;
        private int mPosition;
        private ImageView imgDelete, imgEdit;

        public NoteViewHolder(View itemView) { //creates space for the text
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            store = itemView.findViewById(R.id.store);
            imgDelete = itemView.findViewById(R.id.ivRowDelete);
            imgEdit = itemView.findViewById(R.id.ivRowEdit);
        }

       /* public void setData(String note, String priceOfProduct, String storeName, int position) {
            productName.setText(note);
            price.setText(priceOfProduct);
            store.setText(storeName);
            mPosition = position;
        }*/

        public void setListeners() {
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UpdateProduct.class);
                    intent.putExtra("note_id", mNotes.get(mPosition).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));
                    }
                }
            });
        }
    }

}
