package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map2 extends AppCompatActivity implements OnMapReadyCallback {
    DBHelper db;
    int i=0;
    String Name;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this::onMapReady);

        Name = getIntent().getStringExtra("name");
    }
    double v=59.938955;
    double v1=30.315644;

    private LatLng spb= new LatLng(v, v1);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map!=null) {
            map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(spb, 6)
            );
            db = new DBHelper(this);
            int name=0;
            int date=3;
            int cord1 = 1;
            int cord2 = 2;
            Cursor cursor = db.getCords();
            //Intent intent = new Intent(this,OrderActivity.class);
            if (cursor.moveToFirst()) // если курсор не пустой
            {
                for (int j = 0; j < cursor.getCount(); j++)
                {
                    LatLng map1= new LatLng(cursor.getDouble(cord1),cursor.getDouble(cord2));
                    map.addMarker(new MarkerOptions().position(map1).title(cursor.getString(name)).snippet(cursor.getString(date)));
                    cursor.moveToNext();
                    i++;
                }
            }
            cursor.close();
        }
        /*
        LatLng opex = new LatLng(59.953992, 31.039790);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(opex,6));
        map.addMarker(new MarkerOptions().position(opex).title("Крепость орешик").snippet("Основана в 1323 году"));
        LatLng gatchina = new LatLng(59.563303, 30.108686);
        map.addMarker(new MarkerOptions().position(gatchina).title("Большой Гатчинский дворец").snippet("Построен в середине XVIII века"));
        LatLng church = new LatLng(61.029985, 30.122003);
        map.addMarker(new MarkerOptions().position(church).title("Крепость Корела").snippet("Построена на рубеже XIII-XIX веков"));
        LatLng tih = new LatLng(59.653162, 33.521102);
        map.addMarker(new MarkerOptions().position(tih).title("Тихвинской монастырь").snippet("Основан в 1560 году"));
        LatLng sub = new LatLng(59.670726, 30.779249);
        map.addMarker(new MarkerOptions().position(sub).title("Саблинский памятник природы").snippet("Основан в 1976 году"));
        LatLng str = new LatLng(60.001131, 32.292431);
        map.addMarker(new MarkerOptions().position(str).title("Староладожская крепость").snippet("Построена приблизительно в 862 году"));
        LatLng mon = new LatLng(60.728784, 28.720263);
        map.addMarker(new MarkerOptions().position(mon).title("Парк Монрепо").snippet("Находится на окраине Выборга на берегу бухты Защитной"));
        LatLng kop = new LatLng(59.709010, 29.032521);
        map.addMarker(new MarkerOptions().position(kop).title("Крепость Копорье").snippet("Заложена в 1237 году"));
        */
    }
}