package com.example.android_homeword_1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddCoporationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_corporation_layout);
    }

    public void click_add_corporation(View view){
        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);

        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        ContentValues values = new ContentValues();
        // 组装数据
        EditText editText_name=findViewById(R.id.editText_add_corporation_name);

        values.put("corporname", editText_name.getText().toString());

        database.insert("corporationInfo", null, values);



        Toast.makeText(this, "新增社团成功", Toast.LENGTH_SHORT).show();




    }
}
