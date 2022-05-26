package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements ContactAdapter.OnSightListener{
    ArrayList<Contact> contacts;
    private ContactAdapter adapter;
    private ArrayList<Integer> idList = new ArrayList<Integer>();
    private ImageView maps;
    private Button back,comm;
    private EditText found;
    private RecyclerView rvContacts;
    private RecyclerView.LayoutManager mLayoutManager;
    final String LOG_TAG ="myLogs";
    DBHelper db;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        found = (EditText) findViewById(R.id.SearchText);
        back = (Button) findViewById(R.id.ExitFromList);
        comm = (Button) findViewById(R.id.reviews);
        maps = (ImageView) findViewById(R.id.Map);
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        createExampleList();
        buildRecyclerView();

        found.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                if (found.length() == 0) {
                }
            }
        });
    }

    private void filter(String text) {
        ArrayList<Contact> filteredList = new ArrayList<>();
        idList.clear();
        for (Contact item : contacts) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                idList.add(item.getID());
            }
        }

        adapter.filterList(filteredList);
    }
    private void createExampleList() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM plase;", null);
        while (query.moveToNext()) {
            count = query.getInt(0);
        }

        contacts = new ArrayList<>();

        for(int i = 0; i <= count; i++)
        {
            Cursor query2 = db.rawQuery("SELECT * FROM plase WHERE id = " + i + " ;", null);
            while (query2.moveToNext()) {
                contacts.add(new Contact(i, query2.getString(1), query2.getString(4), query2.getString(5), query2.getString(6), query2.getDouble(2), query2.getDouble(3)));
            }
        }
    }
    private void buildRecyclerView() {
        rvContacts.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new ContactAdapter(contacts, this);
        rvContacts.setLayoutManager(mLayoutManager);
        rvContacts.setAdapter(adapter);
    }

    @Override
    public void onSightClick(int position) {
        Intent intent = new Intent(this, Info.class);
        intent.putExtra("name", contacts.get(position).getdate());
        intent.putExtra("plase", contacts.get(position).getName());
        intent.putExtra("info", contacts.get(position).getInfo());
        intent.putExtra("img", contacts.get(position).getPhoto());
        intent.putExtra("map1", contacts.get(position).getMap1());
        intent.putExtra("map2", contacts.get(position).getMap2());
        startActivity(intent);
        finish();
    }

    public void map(View v){
        switch (v.getId()){
            case R.id.Map:
                Intent intent =new Intent(this, Map.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void gotoin(View v){
        switch (v.getId()){
            case R.id.ExitFromList:
                Intent intent =new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void gotocomm(View v){
        switch (v.getId()){
            case R.id.reviews:
                Intent intent =new Intent(this, Comments.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}