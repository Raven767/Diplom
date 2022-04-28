package com.example.myapplication;

import android.widget.Toast;

import java.util.ArrayList;

public class Find {

    private String Name;
    private String date;
    private String info;
    private String photo;

    public Find(String name,String Date,String Info,String Photo) {
        Name = name;
        date = Date;
        info = Info;
        photo = Photo;

    }
    public String getName() {return Name;}
    public String getdate() {return date;}
    public String getInfo() {return info;}
    public String getPhoto() {return photo;}

    public static ArrayList<Find> createContactsList(int numContacts, String[] myInts, String[] mydate, String[] inform, String photo[]) {
        ArrayList<Find> contacts = new ArrayList<Find>();
        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Find(myInts[i-1],mydate[i-1],inform[i-1], photo[i-1]));
        }
        return contacts;
    }
}
