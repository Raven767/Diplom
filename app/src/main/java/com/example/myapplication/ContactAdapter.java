package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private OnSightListener mOnSightListener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public ImageView fotoView;
        public TextView TextView;

        OnSightListener onSightListener;

        public ViewHolder(View itemView, OnSightListener onSightListener) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.textView_surname);
            TextView = (TextView) itemView.findViewById(R.id.textall);
            fotoView = (ImageView) itemView.findViewById(R.id.imageView_back);
            this.onSightListener = onSightListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSightListener.onSightClick(getAdapterPosition());
        }
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contant, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView,mOnSightListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());
        ImageView imageView = holder.fotoView;
        Picasso.with(context)
                .load(contact.getPhoto())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(0, 227)
                .into(imageView);
        TextView textView1 = holder.TextView;
        textView1.setText(contact.getdate());
    }

    public interface OnSightListener {
        void onSightClick(int position);
    }
    public ContactAdapter(ArrayList<Contact> contacts, OnSightListener onSightListener) {
        mContacts = contacts;
        notifyDataSetChanged();
        this.mOnSightListener = onSightListener;
    }
    public void filterList(ArrayList<Contact> filteredList) {
        mContacts = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
    public void remove() {
        int size = this.mContacts.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mContacts.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }
    private List<Contact> mContacts;
    // Pass in the contact array into the constructor
}