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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.regex.Pattern;

public class Insert extends AppCompatActivity {
    private Button exit,see;
    private EditText name,date,info,link;
    private GoogleMap googleMap;
    private ImageView maps;

    Double mapmarks2,mapmarks1;
    Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])");
    final String LOG_TAG ="myLogs";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        exit = (Button) findViewById(R.id.ExitFromAdmin);
        see = (Button) findViewById(R.id.SeeAll);
        name = (EditText) findViewById(R.id.Dname);
        info = (EditText) findViewById(R.id.Dinfo);
        date = (EditText) findViewById(R.id.Ddate);
        maps = (ImageView) findViewById(R.id.InsertCor);
        link = (EditText)findViewById(R.id.Link);
        dbHelper = new DBHelper(this);

        mapmarks1 = getIntent().getDoubleExtra("map1",0);
        mapmarks2 = getIntent().getDoubleExtra("map2",0);
    }


    public void clik(View v) {
        switch (v.getId()) {
            case R.id.ExitFromAdmin:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void see(View v) {
        switch (v.getId()) {
            case R.id.SeeAll:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void mapadd(View v) {
        switch (v.getId()) {
            case R.id.InsertCor:
                Intent intent = new Intent(this, MapAdd.class);
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
            case R.id.InsertAll:
                String name1 = name.getText().toString();
                String date1 = date.getText().toString();
                String info1 = info.getText().toString();
                Log.d(LOG_TAG,"--- Insert in users: ---");
                cv.put("plece",name1);
                cv.put("map1",mapmarks1);
                cv.put("map2",mapmarks2);
                cv.put("date",date1);
                cv.put("info",info1);
                db.insert("plase",null,cv);
                Toast toast = Toast.makeText(this, "Достопримечательность успешно добавлена",Toast.LENGTH_LONG);
                toast.show();
                break;
            default:
                break;
        }
    }


}