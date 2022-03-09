package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements ContactAdapter.OnSightListener{
    ArrayList<Contact> contacts;
    private ArrayList<Integer> idList = new ArrayList<Integer>();
    private ImageView maps,diog;
    private Button back,comm;
    private boolean isFiltred = false;
    final String LOG_TAG ="myLogs";
    DBHelper db;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DBHelper(this);

        int columnIndex = 0;
        int date = 3;
        int info =4;
        Cursor cursor = db.getTable("plase"); // в классе DB должен быть метод
        String[] myInts = new String[cursor.getCount()]; // объявляем массив
        String[] mydate = new String[cursor.getCount()]; // объявляем массив
        String[] myinfo = new String[cursor.getCount()];
        if (cursor.moveToFirst()) // если курсор не пустой
        {
            for (int j = 0; j < cursor.getCount(); j++)
            {
                myInts[j] = String.valueOf(cursor.getString(columnIndex));
                mydate[j] = String.valueOf(cursor.getString(date));// заполняем
                myinfo[j] = String.valueOf(cursor.getString(info));// заполняем
                cursor.moveToNext();
                i++;
            }
        }
        cursor.close();
        back = (Button) findViewById(R.id.button5);
        comm = (Button) findViewById(R.id.button6);
        maps =(ImageView) findViewById(R.id.Map);
        diog =(ImageView) findViewById(R.id.Diogramma);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        contacts = Contact.createContactsList(i,myInts,mydate,myinfo);
        ContactAdapter adapter = new ContactAdapter(contacts,this::onSightClick);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSightClick(int position) {
        Intent intent = new Intent(this, Info.class);
        String login =  getIntent().getStringExtra("login");
        if(isFiltred == false)
        {
            intent.putExtra("name", contacts.get(position).getlastName());
            intent.putExtra("plase", contacts.get(position).getName());
            intent.putExtra("info", contacts.get(position).getInfo());
            intent.putExtra("img", contacts.get(position).getIMg());
            intent.putExtra("login", login);
        }

        if(isFiltred == true)
        {
            intent.putExtra("name", contacts.get(idList.get(position)-1).getlastName());
            intent.putExtra("plase", contacts.get(idList.get(position)-1).getName());
            intent.putExtra("info", contacts.get(idList.get(position)-1).getInfo());
            intent.putExtra("img", contacts.get(idList.get(position)-1).getIMg());
            intent.putExtra("login", login);
        }

        startActivity(intent);
        finish();
    }

    public void Dior(View v){
        switch (v.getId()){
            case R.id.Diogramma:
                Intent intent =new Intent(this, Diogramma.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void map(View v){
        switch (v.getId()){
            case R.id.Map:
                Intent intent =new Intent(this, map2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void gotoin(View v){
        switch (v.getId()){
            case R.id.button5:
                Intent intent =new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void gotocomm(View v){
        switch (v.getId()){
            case R.id.button6:
                Intent intent =new Intent(this, Comments.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}