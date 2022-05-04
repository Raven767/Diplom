package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG="myLogs";
    ContentValues cv =new ContentValues();
    public DBHelper(@Nullable Context context) {
        super(context, "myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(LOG_TAG,"---onCreate database ---");
        db.execSQL(" create table IF NOT EXISTS users(login text primary key,email text,parol text);");
        db.execSQL("create table IF NOT EXISTS plase(id INTEGER primary key autoincrement,plece text ,map1 double,map2 double,date text,info text,photo text);");
        db.execSQL("create table IF NOT EXISTS comments(comment text, place text,login text,FOREIGN KEY(login) REFERENCES users(login), FOREIGN KEY(place) REFERENCES plase(plece));");

        Log.d(LOG_TAG,"---insert in database ---");
        cv.put("id","1");
        cv.put("plece","Крепость орешек");
        cv.put("map1","59.953992");
        cv.put("map2","31.039790");
        cv.put("date","Основана в 1323 году на Ореховом острове и занимает всю его территорию.");
        cv.put("info","За время своего существования успела побывать в собственности шведов на протяжении почти столетия, а позже утратило первоначальное военно-оборонительное значение и использовалась, как тюрьма. В период ВОВ сильно пострадала. Солдатам, защищавшим крепость, посвятили памятник, установленный здесь же.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/krepost-oreshek-700x464.jpg");
        db.insert("plase",null,cv);

        cv.put("id","2");
        cv.put("plece","Тихвинской монастырь");
        cv.put("map1","59.653162");
        cv.put("map2","33.521102");
        cv.put("date","Основан в 1560 году. Располагается на одноимённой реке в одноименном городе.");
        cv.put("info","В начале XVII находился в шведской осаде. В 20-х годах помещения мужского монастыря перестали выполнять прямые обязанности, спустя десятилетие его и вовсе закрыли. Самая важная реликвия – икона Божьей матери, считающаяся чудотворной. В 1995 году РПЦ ввернула контроль над обителью, чуть позже на место установили и икону.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/tikhvinskiy-bogorodichnyy-uspenskiy-muzhskoy-monastyr-700x472.jpg");
        db.insert("plase",null,cv);

        cv.put("id","3");
        cv.put("plece","Больщой Гатчинский дворец");
        cv.put("map1","59.5633032");
        cv.put("map2","30.108686");
        cv.put("date","Построен в середине XVIII века. Композиция является симбиозом замка эпохи средневековья и резиденции.");
        cv.put("info","Архитектурный стиль – классицизм. Он же присущ и интерьерам дворца. Неоднократно перестраивался, изменения касались отделки фасада, ремонта не которых помещений и парковой зоны. Был разграблен и сожжён фашистами. После войны велось активное восстановление. Реставрационные работы ведутся до сих пор.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/bolshoy-gatchinskiy-dvorets-700x470.jpg");
        db.insert("plase",null,cv);

        cv.put("id","4");
        cv.put("plece","Саблинский памятник природы");
        cv.put("map1","59.669881");
        cv.put("map2","30.786747");
        cv.put("info","Природный ансамбль раскинулся на территории, превышающей ста тридцати гектар. Пара водопадов, каньоны, пещеры, две реки – природные красоты на любой вкус. В прошлом тут добывали кварцевый песок, однако промысел уже давно свернули. У этого места богатая история, связанная с войнами и выживанием. На данную тему также можно найти объекты в заповеднике.");
        cv.put("date","Основан в 1976 году. Располагается в Тосненском районе.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/sablinskiy-pamyatnik-prirody-700x465.jpg");
        db.insert("plase",null,cv);

        cv.put("id","5");
        cv.put("plece","Староладожская крепость");
        cv.put("map1","60.001131");
        cv.put("map2","32.292431");
        cv.put("info","Восстановление требовалось башням и участкам стены. История крепости неразрывно связана с противостоянием со шведами. В более поздний период в окрестностях проводились раскопки, ставшие основой для многих исследовательских трудов. В 1971 году на территории комплекса открыт краеведческий музей.");
        cv.put("date","Построена приблизительно в 862 году. Хотя внешне крепость выглядит хорошо, реставрационный период затянулся.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/staroladozhskaya-krepost-700x467.jpg");
        db.insert("plase",null,cv);

        cv.put("id","6");
        cv.put("plece","Парк Монрепо");
        cv.put("map1","60.728784");
        cv.put("map2","28.7202632");
        cv.put("date","Находится на окраине Выборга на берегу бухты Защитной. Расцвет территории связан с усадьбой президента Санкт-Петербургской Академии наук.");
        cv.put("info","Он посвятил её развитию много времени, особенно после выхода на пенсию. Его наследники продолжили украшать парк, ставя скульптуры, вписывающиеся в пейзаж. Сейчас ведётся масштабная реконструкция, но территория открыта для посещения с некоторыми ограничениями.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/park-monrepo-700x471.jpg");
        db.insert("plase",null,cv);

        cv.put("id","7");
        cv.put("plece","Крепость Копорье");
        cv.put("map1","59.709010");
        cv.put("map2","29.032521");
        cv.put("date","Заложена в 1237 году. Находится на берегу Копорки.");
        cv.put("info","Будучи памятником средневекового оборонительного зодчества, никогда не реставрировалась. С одной стороны это позволило сохранить архитектуру без изменений, с другой – состояние крепости среднее. За свою историю несколько раз переходила под руководство шведов. Получила статус музея в начале 2000-х. Открыта для посещений с экскурсоводами и без.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/krepost-koporye-700x469.jpg");
        db.insert("plase",null,cv);

        cv.put("id","8");
        cv.put("plece","Крепость Корела");
        cv.put("map1","61.029985");
        cv.put("map2","30.122003");
        cv.put("date","Построена на рубеже XIII-XIX веков. Находится в районе Приозёрска.");
        cv.put("info","Архитектура не совсем типична для укреплений такого типа. Стены очень толстые, но ниже, чем обычно. Это свидетельствует об изменении в вооружении противника и адаптации строителей. Некоторый период принадлежала шведам, позже была отбита. Финны захватывали её в начале 40-х, хотя быстро потеряли.");
        cv.put("photo","https://must-see.top/wp-content/uploads/2018/06/krepost-korela-700x472.jpg");
        db.insert("plase",null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("drop table if exists "+ plase);
        //onCreate(db);
    }
    public Cursor  getTable(String plase) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "select * from " + plase + ";";
        return db.rawQuery(sqlQuery, null);
    }
    public Cursor  findplace(Integer plasename) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "select id,plece,date,info,photo from plase WHERE id="+plasename+" ; ";
        //String sqlQuery = "select * from plase WHERE plece like '%"+plasename+"%' ; ";
        return db.rawQuery(sqlQuery, null);
    }

    public Cursor  getCords() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "select * from plase";
        return db.rawQuery(sqlQuery, null);
    }

    public Boolean chekking(String login,String parol) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login=? and parol=?", new String[]{login, parol});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    public Cursor getEmailAddress(String emailNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery ="select * from plase WHERE plece = " + emailNo + ";";
        return db.rawQuery(sqlQuery, null);
    }
}
