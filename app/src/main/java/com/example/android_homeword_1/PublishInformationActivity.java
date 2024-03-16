package com.example.android_homeword_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PublishInformationActivity extends AppCompatActivity {
    int userid;

    private ArrayList<corporation> corporationList=null;
    private int id_copy=-1;//用来保存当前选择id的备份

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_information_layout);
        init();
    }

    public void init(){
        //获取传递的id
        userid=getIntent().getIntExtra("userid",0);//取出传递的数据


        Spinner spinner= findViewById(R.id.spinner_choose_corporation);

        corporationList= new ArrayList<>();

        corporationList.clear();

        //从数据库中获取数据
        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);
        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        Cursor cursor = database.query("corporationInfo", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
        if (cursor.moveToFirst() == true) {
            do {
                // 遍历 Cursor 对象，取出数据
                @SuppressLint("Range") int corporid = cursor.getInt(cursor.getColumnIndex("corporid"));
                @SuppressLint("Range") String corporname = cursor.getString(cursor.getColumnIndex("corporname"));

                corporationList.add(new corporation(corporid,corporname));
           } while (cursor.moveToNext());
        }
        cursor.close();

        SpinnersAdapter adapter=new SpinnersAdapter(corporationList,this);
        spinner.setAdapter(adapter);


    }

    public void click_publish_information_submit(View view){
        Spinner corporspinner=(Spinner) findViewById(R.id.spinner_choose_corporation);
        int spinnerid=(int)corporspinner.getSelectedItemId();
        String getcorporname=corporationList.get(spinnerid).getName();
        int corporationid=corporationList.get(spinnerid).getId();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String datestring=simpleDateFormat.format(date);

        EditText editText_title=findViewById(R.id.editTextText_publish_title);
        EditText editText_content=findViewById(R.id.editTextText_publish_content);


        String title=editText_title.getText().toString();
        String content=editText_content.getText().toString();
        //以下开始向数据库中传递数据
        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(this, "InfoData.db", 1);

        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
        ContentValues values = new ContentValues();
        // 组装数据
        EditText editText_name=findViewById(R.id.editText_add_corporation_name);

        values.put("title", title);
        values.put("content", content);
        values.put("date",datestring);
        values.put("userid", userid);
        values.put("corporationid", corporationid);

        database.insert("informationInfo", null, values);


        Toast.makeText(this, title+"\n"+userid+"\n"+content+"\n"+"spinner:"+spinnerid+getcorporname+"\n"+datestring, Toast.LENGTH_SHORT).show();

    }
}
