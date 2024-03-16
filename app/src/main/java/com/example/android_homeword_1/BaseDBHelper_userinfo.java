package com.example.android_homeword_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BaseDBHelper_userinfo extends SQLiteOpenHelper {
    private Context context;

    private String createInfo = "create table Info (" +
            "id integer primary key autoincrement," +
            "name text ," +
            "password text)";

    private String createcorporationInfo = "create table  corporationInfo  (" +
            "corporid  integer primary key autoincrement, "  +
            "corporname  text  )";


    private String createinformationInfo = "create table  informationInfo  (" +
            "infoid  integer primary key autoincrement, "  +
            "title text ," +
            "content text ," +
            "date text ," +
            "userid text ," +
            "corporationid  text  )";


        public BaseDBHelper_userinfo(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createInfo);
        db.execSQL(createcorporationInfo);
        db.execSQL(createinformationInfo);
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop TABLE  if  exists Info");
        db.execSQL("drop TABLE  if  exists  corporationInfo");
        onCreate(db);
    }

}
