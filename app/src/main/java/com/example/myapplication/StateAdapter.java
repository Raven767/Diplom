package com.example.myapplication;

import android.content.Context;
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
        public android.widget.TextView nameTextView;
        public TextView textlogin;
        public TextView TextView;

        StateAdapter.OnSightListener onSightListener;

        public ViewHolder(View itemView, StateAdapter.OnSightListener onSightListener) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.textCommit);
            TextView = (TextView) itemView.findViewById(R.id.textLogin);
            textlogin = (TextView) itemView.findViewById(R.id.textNamePlace);
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
        TextView textView = holder.nameTextView;
        textView.setText(state.getLog());
        TextView textVieww = holder.textlogin;
        textVieww.setText(state.getCapital());
        TextView textView1 = holder.TextView;
        textView1.setText(state.getFlagResource());
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
