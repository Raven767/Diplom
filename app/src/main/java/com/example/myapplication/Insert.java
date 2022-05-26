package com.example.myapplication;

import androidx.annotation.NonNull;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerDragListener {
    private Button exit,see;
    private ImageView foto;
    private EditText name,date,info,link;
    private ImageView maps;
    private GoogleMap mmap;
    private Marker marker;

    Double map1,map2;
    Pattern pattern = Pattern.compile("(http|https):\\/\\/(.+?)\\/(([a-zA-Z0-9_ \\-%\\.]*)\\.(jpg|png|jpeg|gif))");
    final String LOG_TAG ="myLogs";
    DBHelper dbHelper;
    String link1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        foto = (ImageView) findViewById(R.id.NewFoto);
        exit = (Button) findViewById(R.id.ExitFromAdmin);
        see = (Button) findViewById(R.id.SeeAll);
        name = (EditText) findViewById(R.id.Dname);
        info = (EditText) findViewById(R.id.Dinfo);
        date = (EditText) findViewById(R.id.Ddate);
        maps = (ImageView) findViewById(R.id.InsertCor);
        link = (EditText)findViewById(R.id.Link);
        dbHelper = new DBHelper(this);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapadd1);
        supportMapFragment.getMapAsync(this::onMapReady);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mmap = googleMap;
        LatLng spb= new LatLng(59.938955, 30.315644);
        marker = mmap.addMarker(new MarkerOptions().position(spb).draggable(true).title("Add"));
        mmap.setOnMarkerDragListener(this);
        mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(spb, 6));
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
                link1 = link.getText().toString();
                Picasso.with(this)
                        .load(link1)
                        .error(R.drawable.ic_launcher_background)
                        .placeholder(R.drawable.ic_launcher_background)
                        .resize(0, 227)
                        .into(foto);
                break;
            default:
                break;
        }
    }
    public void insert(View v) {
        Matcher matcher = pattern.matcher(link.getText().toString());
        ContentValues cv =new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.InsertAll:
                if((name.getText().toString().equals("")) || (date.getText().toString().equals("")) || (info.getText().toString().equals("")) || (link.getText().toString().equals(""))) {
                    Toast toast = Toast.makeText(this, "Все поля должны быть заполнены",Toast.LENGTH_LONG);
                    toast.show(); }
                else{
                    if ((map1 == null) || (map2 == null)){
                        Toast toast = Toast.makeText(this, "Нет координат достопримечательности",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else {
                        if(matcher.matches()){
                            String name1 = name.getText().toString();
                            String date1 = date.getText().toString();
                            String info1 = info.getText().toString();
                            link1 = link.getText().toString();
                            Log.d(LOG_TAG,"--- Insert in users: ---");
                            cv.put("plece",name1);
                            cv.put("map1",map1);
                            cv.put("map2",map2);
                            cv.put("date",date1);
                            cv.put("info",info1);
                            cv.put("photo",link1);
                            db.insert("plase",null,cv);
                            Toast toast = Toast.makeText(this, "Достопримечательность успешно добавлена",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else{
                            Toast toast = Toast.makeText(this, "Неверная ссылка",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
                break;
            default:
                break;
        }
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


}