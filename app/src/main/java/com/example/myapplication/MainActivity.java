package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button sigin,go;
    private TextView reg;
    private EditText login;
    private TextInputLayout parol;

    final String LOG_TAG ="myLogs";
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sigin = (Button) findViewById(R.id.Sing_in);
        go = (Button) findViewById(R.id.Go);
        reg = (TextView) findViewById(R.id.Regist);
        login = (EditText) findViewById(R.id.Login);
        parol = (TextInputLayout) findViewById(R.id.Password);
        db = new DBHelper(this);

        ContentValues cv =new ContentValues();
        SQLiteDatabase dbHelper = db.getReadableDatabase();
        Cursor c = dbHelper.query("plase", null, null, null, null, null, null);
        if (c.moveToFirst()){
            int id = c.getColumnIndex("id");
            int idColIndex = c.getColumnIndex("plece");
            int plase1ColIndex = c.getColumnIndex("map1");
            int plase2ColIndex = c.getColumnIndex("map2");
            int date = c.getColumnIndex("date");
            int emailColIndex = c.getColumnIndex("info");
            do {
                Log.d(LOG_TAG,"id = " + c.getString(id)+"plece = " + c.getString(idColIndex) + ", map1 = "+ c.getString(plase1ColIndex) + ", map2 = "+ c.getString(plase2ColIndex)+ ", date = "+ c.getString(date)+", info = "+ c.getString(emailColIndex));
            }
            while (c.moveToNext());
        }
        else
            Log.d(LOG_TAG, "0 rows");
        c.close();
    }


    public void letsgo(View v){
        switch (v.getId()){
            case R.id.Sing_in:
                if (login.getText().toString().equals("") || parol.getEditText().getText().toString().trim().isEmpty() == true)
                {
                    Toast toast = Toast.makeText(this, "Все поля должны быть заполнены",Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    String log = login.getText().toString();
                    String par = parol.getEditText().getText().toString();
                    Boolean chek = db.chekking(log,par);
                    if (chek == true)
                    {
                        if (login.getText().toString().equals("admin"))
                        {
                            Toast toast = Toast.makeText(this, "Вы зашли как администратор",Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(this, Map.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast toast = Toast.makeText(this, "Добро пожаловать",Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(this, MainActivity2.class);
                            intent.putExtra("login", login.getText().toString());
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast toast = Toast.makeText(this, "Логин или пароль неверны",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                break;
            default:
                break;
        }
    }
    public void Go(View v){
        switch (v.getId()){
            case R.id.Go:
                Toast toast = Toast.makeText(this, "Вы зашли как гость",Toast.LENGTH_LONG);
                toast.show();

                Intent intent =new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void GoToReg(View v){
        switch (v.getId()){
            case R.id.Regist:
                Intent intent =new Intent(this, Registration.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}