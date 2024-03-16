package com.example.android_homeword_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void click_register(View view){
        EditText editText_name=findViewById(R.id.editText_login_Name);
        EditText editText_password=findViewById(R.id.editText_login_Password);

        if(havesamenane(editText_name.getText().toString())){
            Toast.makeText(this, "用户名重复", Toast.LENGTH_SHORT).show();

            editText_name.setText("");
            editText_password.setText("");


        }
        else {

            BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
            SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
            ContentValues values = new ContentValues();
            // 组装数据
            values.put("name", editText_name.getText().toString());
            values.put("password", editText_password.getText().toString());
            database.insert("Info", null, values);
            Toast.makeText(this, "新增成功", Toast.LENGTH_SHORT).show();


        }




    }

    public void click_login(View view) {
        int index=0;
        int passuserid=-1;

        EditText editText_name=findViewById(R.id.editText_login_Name);
        EditText editText_password=findViewById(R.id.editText_login_Password);


        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("Info", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));

                if(name.equalsIgnoreCase(editText_name.getText().toString())){
                    if(password.equalsIgnoreCase(editText_password.getText().toString())) {
                        index=2;
                        passuserid=id;
                    }
                    else {
                        index=1;
                    }
                }


            } while (cursor.moveToNext());
        }
        cursor.close();

        if(index==2)
        {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            String username=editText_name.getText().toString();
            Intent intent=new Intent(this,InfomationShowActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("userid",passuserid);

            startActivity(intent);


        }else {
            if(index==1) {
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                editText_password.setText("");
            }else {
                Toast.makeText(this, "无该用户", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean havesamenane(String name){

        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("Info", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
if(name.equalsIgnoreCase(name1)){
    return true;
}
            } while (cursor.moveToNext());
        }
        cursor.close();


        return false;
    }


}
