package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Comments extends AppCompatActivity {
    ArrayList<State> states;
    private Button back;
    private ListView list;
    DBHelper db;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        back = (Button) findViewById(R.id.button7);
        db = new DBHelper(this);

        //String login = ((MyApplication) this.getApplication()).getSomeVariable();

        int comments = 0;
        int place = 1;
        int person = 2;
        Cursor cursor = db.getTable("comments"); // в классе DB должен быть метод
        String[] mycomments = new String[cursor.getCount()]; // объявляем массив
        String[] myplace = new String[cursor.getCount()]; // объявляем массив
        String[] myperson = new String[cursor.getCount()];
        if (cursor.moveToFirst()) // если курсор не пустой
        {
            for (int j = 0; j < cursor.getCount(); j++)
            {
                mycomments[j] = String.valueOf(cursor.getString(comments));
                myplace[j] = String.valueOf(cursor.getString(place));// заполняем
                myperson[j] = String.valueOf(cursor.getString(person));// заполняем
                cursor.moveToNext();
                i++;
            }
        }
        cursor.close();

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.newrv);
        states = State.createContactsList(i,mycomments,myplace,myperson);
        StateAdapter adapter = new StateAdapter(states,this::onSightClick);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
    public void onSightClick(int position) {

    }
    public void lio(View v){
        switch (v.getId()){
            case R.id.button7:
                Intent intent =new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}