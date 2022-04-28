package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Info extends AppCompatActivity {

    ArrayList<String> images = new ArrayList<String>();

    private TextView Name,alltext;
    private EditText test;
    private ImageView Foto,map;
    private Button back,in;
    DBHelper dbHelper;
    final String LOG_TAG ="myLogs";
    int s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        s=0;
        map = (ImageView) findViewById(R.id.map2);
        Name = (TextView) findViewById(R.id.Namedep);
        Foto = (ImageView) findViewById(R.id.Foto);
        back = (Button) findViewById(R.id.button);
        in = (Button) findViewById(R.id.insert);
        alltext = (TextView) findViewById(R.id.Textinfo);
        test = (EditText) findViewById(R.id.ink);
        dbHelper = new DBHelper(this);

        //images.add();
        String name = getIntent().getStringExtra("plase");
        String info = getIntent().getStringExtra("info");
        String image = getIntent().getStringExtra("img");
        Name.setText(name);
        alltext.setText(info);

        //Foto.setImageResource(image);

        Picasso.with(this)
                .load(image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(0, 227)
                .into(Foto);
    }
    public void goBack(View v){
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void map(View v){
        String namel = Name.getText().toString();
        switch (v.getId()) {
            case R.id.map2:
                Intent intent = new Intent(this, map2.class);
                intent.putExtra("name", namel);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void Klic(View v){
        String login =  getIntent().getStringExtra("login");
        ContentValues cv =new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db2 = dbHelper.getReadableDatabase();
        switch (v.getId()) {
            case R.id.insert:
                String comments = test.getText().toString();
                String name = Name.getText().toString();
                Log.d(LOG_TAG,"--- Insert in users: ---");
                cv.put("comment",comments);
                cv.put("place",name);
                cv.put("login",login);
                db.insert("comments",null,cv);
                //Log.d(LOG_TAG,"comment = "+comments+" place = "+name + " login = "+login);
                Cursor c = db2.query("comments", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("comment");
                    int plase1ColIndex = c.getColumnIndex("place");
                    int plase2ColIndex = c.getColumnIndex("login");
                    do {
                        Log.d(LOG_TAG, "comment = " + c.getString(idColIndex) + ", place = " + c.getString(plase1ColIndex) + ", login = " + c.getString(plase2ColIndex));
                    }
                    while (c.moveToNext());
                }
                    else
                    Log.d(LOG_TAG, "0 rows");
                    c.close();
                break;
            default:
                break;
        }
    }
}