package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private FindAdapter.OnSightListener mOnSightListener;
    private Context context;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public android.widget.TextView nameTextView;
        public ImageView fotoView;
        public TextView TextView;

        FindAdapter.OnSightListener onSightListener;

        public ViewHolder(View itemView, FindAdapter.OnSightListener onSightListener) {
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
    public FindAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View findView = inflater.inflate(R.layout.item_contant, parent, false);
        FindAdapter.ViewHolder viewHolder = new FindAdapter.ViewHolder(findView,mOnSightListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ViewHolder holder, int position) {
        Find find = mContacts.get(position);
        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(find.getName());
        ImageView imageView = holder.fotoView;
        Picasso.with(context)
                .load(find.getPhoto())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(0, 227)
                .into(imageView);
        TextView textView1 = holder.TextView;
        textView1.setText(find.getdate());
    }

    public interface OnSightListener {
        void onSightClick(int position);
    }

    public void clearApplications() {
        int size = this.mContacts.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mContacts.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
    public FindAdapter(ArrayList<Find> contacts, FindAdapter.OnSightListener onSightListener) {
        mContacts = contacts;
        this.mOnSightListener = onSightListener;
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    private List<Find> mContacts;
    // Pass in the contact array into the constructor
}
