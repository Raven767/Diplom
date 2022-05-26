package com.example.myapplication;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>{
    private StateAdapter.OnSightListener mOnSightListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public android.widget.TextView commitTextView;
        public TextView textPlase;
        public TextView TextLOgin;

        StateAdapter.OnSightListener onSightListener;

        public ViewHolder(View itemView, StateAdapter.OnSightListener onSightListener) {
            super(itemView);
            commitTextView = (TextView) itemView.findViewById(R.id.textCommit);
            TextLOgin = (TextView) itemView.findViewById(R.id.textLogin);
            textPlase = (TextView) itemView.findViewById(R.id.textNamePlace);
            this.onSightListener = onSightListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onSightListener.onSightClick(getAdapterPosition());
        }
    }

    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context state = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(state);
        View contactView = inflater.inflate(R.layout.item_state, parent, false);
        StateAdapter.ViewHolder viewHolder = new StateAdapter.ViewHolder(contactView,mOnSightListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        State state = mContacts.get(position);
        // Set item views based on your views and data model
        TextView textView = holder.commitTextView;
        textView.setText(state.getLog());
        TextView textVieww = holder.textPlase;
        textVieww.setText(state.getPLACE());
        TextView textView1 = holder.TextLOgin;
        textView1.setText(state.getcom());
        holder.commitTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    public interface OnSightListener {
        void onSightClick(int position);

    }

    public StateAdapter(ArrayList<State> contacts, OnSightListener onSightListener) {
        mContacts = contacts;

        this.mOnSightListener = onSightListener;
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    private List<State> mContacts;
    // Pass in the contact array into the constructor
}
