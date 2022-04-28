package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    final String LOG_TAG ="myLogs";
    DBHelper dbHelper;

    public Button exit;
    public EditText log,mail;
    public TextInputLayout parol1,parol2;
    Pattern pattern = Pattern.compile("^([a-z0-9]+\\.)*[a-z0-9]+@[a-z0-9]*\\.[a-z]{2,3}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        log = (EditText) findViewById(R.id.mail);
        mail = (EditText) findViewById(R.id.name);
        parol1 = (TextInputLayout) findViewById(R.id.parol);
        parol2 = (TextInputLayout) findViewById(R.id.parolagain);
        exit = (Button) findViewById(R.id.create);
        dbHelper = new DBHelper(this);
    }
    public void chek1(View v){
        Matcher matcher = pattern.matcher(mail.getText().toString());
        ContentValues cv =new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch ( v.getId()) {
            case R.id.create:
                if((log.getText().toString().equals("")) || (mail.getText().toString().equals("")) || (parol2.getEditText().getText().toString().trim().isEmpty() == true) || (parol1.getEditText().getText().toString().trim().isEmpty() == true)) {
                    Toast toast = Toast.makeText(this, "Все поля должны быть заполнены",Toast.LENGTH_LONG);
                    toast.show(); }
                else {
                    if(matcher.matches()){
                        if(parol1.getEditText().getText().toString().trim().equals(parol2.getEditText().getText().toString().trim())) {
                            Toast toast = Toast.makeText(this, "Вы зарегестрировались",Toast.LENGTH_LONG);
                            Intent intent =new Intent(this, MainActivity.class);
                            String login = log.getText().toString();
                            String email = mail.getText().toString();
                            String parol = parol1.getEditText().getText().toString();
                            Log.d(LOG_TAG,"--- Insert in users: ---");
                            cv.put("login",login);
                            cv.put("email",email);
                            cv.put("parol",parol);
                            db.insert("users",null,cv);
                            Log.d(LOG_TAG,"login = "+login+", email= "+email+", parol= "+parol);

                            startActivity(intent);
                            toast.show(); }
                        else {
                            Toast toast = Toast.makeText(this, "Пароли должны совпадать",Toast.LENGTH_LONG);
                            toast.show(); }
                    }
                    else{
                        Toast toast = Toast.makeText(this, "Не правельная почта",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                break;
            default:
                break;
        }
    }
}