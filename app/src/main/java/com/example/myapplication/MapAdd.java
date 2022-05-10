package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class MapAdd extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerDragListener {

    private Button AddMarks;
    private GoogleMap mmap;
    private Marker marker;
    private Double map1,map2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_add);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapadd);
        supportMapFragment.getMapAsync(this::onMapReady);
        AddMarks = (Button) findViewById(R.id.marksadd);
    }

    public void onMapReady(GoogleMap googleMap) {
        mmap = googleMap;
        LatLng spb= new LatLng(59.938955, 30.315644);
        marker = mmap.addMarker(new MarkerOptions().position(spb).draggable(true).title("Add"));
        mmap.setOnMarkerDragListener(this);
        mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(spb, 6));
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        Toast.makeText(this,"Lat: "+ marker.getPosition().latitude +
                        "\nLng: " + marker.getPosition().longitude
                , Toast.LENGTH_SHORT).show();
        map1 = marker.getPosition().latitude;
        map2 = marker.getPosition().longitude;
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    public void addmarks(View v) {
        switch (v.getId()) {
            case R.id.marksadd:
                Intent intent = new Intent(this, Insert.class);
                intent.putExtra("map1", map1);
                intent.putExtra("map2", map2);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}