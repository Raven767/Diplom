package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
//класс для заполнения списка
public class Contact {

    private int Id;
    private String Name;
    private String date;
    private String info;
    private String photo;
    private Double Map1;
    private Double Map2;

    public Contact(int id,String name,String Date,String Info,String Photo,Double map1,Double map2) {
        Id = id;
        Name = name;
        date = Date;
        info = Info;
        photo = Photo;
        Map1 = map1;
        Map2 = map2;
    }
    //возвращение переменных
    public int getID(){return  Id;};
    public String getName() {return Name;}
    public String getdate() {return date;}
    public String getInfo() {return info;}
    public String getPhoto() {return photo;}
    public Double getMap1() {return Map1;}
    public Double getMap2() {return Map2;}


    public static ArrayList<Contact> createContactsList(int numContacts,int ID[],String[] myInts,String[] mydate,String[] inform,String photo[]) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        return contacts;
    }
}
