package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String Name;
    private int foto;
    private String text;
    private String info;
    private String img;
    public Contact(String name,int Foto, String Text,String Info) {
        Name = name;
        foto = Foto;
        text = Text;
        info = Info;
    }
    public String getName() {return Name;}
    public int getlastName() {return foto;}
    public String getText() {return text;}
    public String getInfo() {return info;}
    public String getIMg() {return img;}

    public static ArrayList<Contact> createContactsList(int numContacts,String[] myInts,String[] mydate,String[] inform) {
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
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact(myInts[i-1], massive.get(i - 1),mydate[i-1],inform[i-1]));
        }
        return contacts;
    }
}
