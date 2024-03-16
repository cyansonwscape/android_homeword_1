package com.example.android_homeword_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateInformationActivity extends AppCompatActivity {
    int ifeditor;
    int informationid;

    information ainformation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateinformation_layout);
        init();
    }
    public void init(){
        ifeditor=getIntent().getIntExtra("ifeditor",-1);//取出传递的数据
        informationid=getIntent().getIntExtra("informationid",0);//取出传递的数据
        EditText editText_title=findViewById(R.id.textView_update_title);
        EditText editText_content=findViewById(R.id.textView_update_content);
        Button button_update=findViewById(R.id.button_update_information);
        Button button_del=findViewById(R.id.button_del_information);

        editText_title.setText("ifeditor"+ifeditor);
        editText_content.setText("informationid"+informationid);
//从数据库中获取数据
        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("informationInfo", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") int infoid = cursor.getInt(cursor.getColumnIndex("infoid"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") int userid2 = cursor.getInt(cursor.getColumnIndex("userid"));
                @SuppressLint("Range") int corporationid = cursor.getInt(cursor.getColumnIndex("corporationid"));

                if(infoid==informationid){
                    ainformation=new information(infoid,title,content,date,userid2,corporationid);
                }


            } while (cursor.moveToNext());
        }
        cursor.close();
        //初始化界面

    editText_title.setText(ainformation.getTitle());
    editText_content.setText(ainformation.getContent());
    if(ifeditor==0){
        button_update.setVisibility(View.GONE);
        button_del.setVisibility(View.GONE);
        editText_content.setFocusable(false);
        editText_title.setFocusable(false);

    }

    //初始化界面2
        TextView textView_corporname=findViewById(R.id.textView_update_show_corporation);
    textView_corporname.setText("发布的社团为："+getcorporationbyid(ainformation.getCorporation_id()));

        TextView textView_author=findViewById(R.id.textView_update_show_author);
        textView_author.setText("发布人为："+getauthorbyid(ainformation.getUer_id()));

        TextView textView_time=findViewById(R.id.textView_updat_show_time);
    textView_time.setText("发布时间为："+ainformation.getDate());



    }

    public void click_update_information(View view){
        Toast.makeText(this, "开始更行", Toast.LENGTH_SHORT).show();

        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        ContentValues values = new ContentValues();
        EditText editText_title=findViewById(R.id.textView_update_title);
        EditText editText_content=findViewById(R.id.textView_update_content);

        String title=editText_title.getText().toString();
        String content=editText_content.getText().toString();

        values.put("title", title);
        values.put("content", content);

        String id_string=""+informationid;

        database.update("informationInfo", values, "infoid = ? ", new String[]{id_string}); //修改指定编号的记录


       finish();

    }

    public void click_del_information(View view){
        Toast.makeText(this, "开始删除", Toast.LENGTH_SHORT).show();

        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        String id_string=""+informationid;
        database.delete("informationInfo","infoid=?",new String[]{id_string});

        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
       finish();

    }

    public String getauthorbyid(int authorid){
        String authorname="";

        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("Info", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));

//用Sting中的contain函数实现模糊查询
                if(id==authorid) {
                    authorname=name;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return authorname;

    }

    public String getcorporationbyid(int corporid){
        String corporname="";

        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("corporationInfo", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("corporid"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("corporname"));

//用Sting中的contain函数实现模糊查询
                if(id==corporid) {
                    corporname=name;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return corporname;

    }
}
