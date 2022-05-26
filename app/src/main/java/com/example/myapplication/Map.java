package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    DBHelper db;
    int i=0;
    String Name;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
            int name=1;
            int cord1 = 2;
            int cord2 = 3;
            Cursor cursor = db.getCords();
            if (cursor.moveToFirst()) // если курсор не пустой
            {
                for (int j = 0; j < cursor.getCount(); j++)
                {
                    LatLng map1= new LatLng(cursor.getDouble(cord1),cursor.getDouble(cord2));
                    map.addMarker(new MarkerOptions().position(map1).title(cursor.getString(name)));
                    cursor.moveToNext();
                    i++;
                }
            }
            cursor.close();
        }
    }
}