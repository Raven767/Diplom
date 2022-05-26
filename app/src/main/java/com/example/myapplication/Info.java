package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Info extends AppCompatActivity {
    ArrayList<String> images = new ArrayList<String>();
    //переменные для работы
    private TextView Name,alltext;
    private EditText test;
    private ImageView Foto,map;
    private Button back,in;
    private GoogleMap mmap;
    private Marker marker;
    Double map1,map2;
    String name, info, image;

    DBHelper dbHelper;
    final String LOG_TAG ="myLogs";
    int s;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //присаивание переменным обьекты на форме
        s=0;
        map = (ImageView) findViewById(R.id.map2);
        Name = (TextView) findViewById(R.id.Nameattraction);
        Foto = (ImageView) findViewById(R.id.Foto);
        back = (Button) findViewById(R.id.ExitFromInformation);
        in = (Button) findViewById(R.id.insert);
        alltext = (TextView) findViewById(R.id.Textinfo);
        test = (EditText) findViewById(R.id.Comments);
        dbHelper = new DBHelper(this);
        alltext.setMovementMethod(new ScrollingMovementMethod());
        login = ((NewClass) this.getApplication()).getSomeVariable();
        //получаени данных с дургой формы
        name = getIntent().getStringExtra("plase");
        info = getIntent().getStringExtra("info");
        image = getIntent().getStringExtra("img");
        map1 = getIntent().getDoubleExtra("map1",0);
        map2 = getIntent().getDoubleExtra("map2",0);
        Name.setText(name);
        alltext.setText(info);

        Picasso.with(this)
                .load(image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(0, 227)
                .into(Foto);
    }
    //обработка нажания кнопки назад
    public void goBack(View v){
        switch (v.getId()) {
            case R.id.ExitFromInformation:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    //обработка нажатия кнопки карты
    public void map(View v){
        switch (v.getId()) {
            case R.id.map2:
                //Вывоз диалогового окна при нажатии на кнопку
                Dialog dialog = new Dialog(Info.this);//созданеи диалогового окна
                dialog.setContentView(R.layout.map_diolog);//путь к макету диалогового окна

                SupportMapFragment supportMapFragment =
                        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.diologmap);
                supportMapFragment.getMapAsync(this::onMapReady);

                dialog.show();//показать диалоговое окно
                break;
            default:
                break;
        }
    }
    //методдля работы с картой
    private void onMapReady(GoogleMap googleMap) {
        mmap = googleMap;
        LatLng spb= new LatLng(map1, map2);
        marker = mmap.addMarker(new MarkerOptions().position(spb).draggable(true).title(name));
        mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(spb, 6));
    }
    //обработка нажатия кнопки добавить
    public void Klic(View v){
        ContentValues cv =new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.insert:
                //добаление комментариев в БД
                String comments = test.getText().toString();
                String name = Name.getText().toString();
                Log.d(LOG_TAG,"--- Insert in users: ---");
                cv.put("comment",comments);
                cv.put("place",name);
                cv.put("login",login);
                db.insert("comments",null,cv);
                break;
            default:
                break;
        }
    }
}