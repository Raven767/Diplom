package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity {
    private Button exit,see,inn;
    private EditText name,map1,map2,date,info;

    final String LOG_TAG ="myLogs";
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        exit = (Button) findViewById(R.id.button2);
        see = (Button) findViewById(R.id.button3);
        name = (EditText) findViewById(R.id.Dname);
        map1 = (EditText) findViewById(R.id.Dmap1);
        map2 = (EditText) findViewById(R.id.Dmap2);
        info = (EditText) findViewById(R.id.Dinfo);
        date = (EditText) findViewById(R.id.Ddate);
        dbHelper = new DBHelper(this);
    }
    public void clik(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void see(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void insert(View v) {
        ContentValues cv =new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.button4:
               if (map1.getText().toString().equals("") || map2.getText().toString().equals(""))
               {
                   Toast toast = Toast.makeText(this, "Вы не ввели координаты ",Toast.LENGTH_LONG);
                   toast.show();
               }
               else {
                   String name1 = name.getText().toString();
                   String map11 = map1.getText().toString();
                   String map22 = map2.getText().toString();
                   String date1 = date.getText().toString();
                   String info1 = info.getText().toString();
                   Log.d(LOG_TAG,"--- Insert in users: ---");
                   cv.put("plece",name1);
                   cv.put("map1",map11);
                   cv.put("map2",map22);
                   cv.put("date",date1);
                   cv.put("info",info1);
                   db.insert("plase",null,cv);
                   Log.d(LOG_TAG,"plece = "+name1+", map1 = "+map11+", map2 = "+ map22+", date = "+date1+", info = "+info1);
               }
                break;
            default:
                break;
        }
    }
}