package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private int Id;
    private String Name;
    private String date;
    private String info;
    private String photo;

    public Contact(int id,String name,String Date,String Info,String Photo) {
        Id = id;
        Name = name;
        date = Date;
        info = Info;
        photo = Photo;
    }
    public int getID(){return  Id;};
    public String getName() {return Name;}
    public String getdate() {return date;}
    public String getInfo() {return info;}
    public String getPhoto() {return photo;}

    public static ArrayList<Contact> createContactsList(int numContacts,int ID[],String[] myInts,String[] mydate,String[] inform,String photo[]) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        /*
        List<Integer> massive = new ArrayList<>();
        massive.add(R.drawable.krepostoreshek);
        massive.add(R.drawable.tikhvinskiymonastyr);
        massive.add(R.drawable.gatchinskiydvorets);
        massive.add(R.drawable.sablinskiypamyatnikprirody);
        massive.add(R.drawable.staroladozhskayakrepost);
        massive.add(R.drawable.parkmonrepo);
        massive.add(R.drawable.krepostkoporye);
        massive.add(R.drawable.krepostkorela);
        for (int i=0;i<=numContacts;i++)
        {
            if (massive.size()<numContacts){
                massive.add(R.drawable.non);
            }
        }
         изначальное
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact(myInts[i-1], massive.get(i - 1),mydate[i-1],inform[i-1]));
        }
         ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact(myInts[i-1],mydate[i-1],inform[i-1], photo[i-1]));
        }
        return contacts;
        */
        return contacts;
    }
}
