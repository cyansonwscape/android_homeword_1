package com.example.android_homeword_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class InfomationShowActivity extends AppCompatActivity {
    int userid;
    String username;


    private List<information> informationList=null; //笔记信息
//    private SQLiteDatabase database; //数据库对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informationshow_layout);
        Toolbar memoToolbar = (Toolbar) findViewById(R.id.toolbar_informationshow);
        setSupportActionBar(memoToolbar);
        init();
       memoToolbar.setTitle("欢迎用户："+username +"用户编号为"+userid);

    }

    public void init() {
        username=getIntent().getStringExtra("username");//取出传递的数据
        userid=getIntent().getIntExtra("userid",0);//取出传递的数据

//向recycleview中写入数据
        informationList= new ArrayList<>();
        informationList.clear();
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

                informationList.add(new information(infoid,title,content,date,userid2,corporationid));
            } while (cursor.moveToNext());
        }
        cursor.close();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview_information_published);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        InformationAdapter adapter=new InformationAdapter(informationList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                information ainfo = informationList.get(position);
                Toast.makeText(InfomationShowActivity.this, "you clicked view "+ainfo.getTitle(), Toast.LENGTH_SHORT).show();
                int infoediterid=ainfo.getUer_id();
                int ifeditor=0;
                if(userid==infoediterid){ifeditor=1;}else{ifeditor=0;}
                int informationid=ainfo.getInfo_id();
                Intent intent=new Intent(InfomationShowActivity.this,UpdateInformationActivity.class);
                intent.putExtra("ifeditor", ifeditor);
                intent.putExtra("informationid",informationid);
                startActivity(intent);



            }
        });
        recyclerView.setAdapter(adapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.information_menu, menu);
        return true;
    }

public boolean click_menu_add_corporation(MenuItem menuItem) {

    Intent intent=new Intent(this,AddCoporationActivity.class);

    startActivity(intent);

    Toast.makeText(this, "新增社团", Toast.LENGTH_SHORT).show();


    return true;
}

public boolean click_menu_publish(MenuItem menuItem) {
        Intent intent=new Intent(this,PublishInformationActivity.class);

        intent.putExtra("userid",userid);
        startActivity(intent);
        Toast.makeText(this, "新增消息", Toast.LENGTH_SHORT).show();
        return true;
    }

    protected void onRestart() {
        super.onRestart();
        init();
    }

  public void  click_to_search(View view){
      EditText editText_search=findViewById(R.id.edittextview_search);

        String keyword=editText_search.getText().toString();
//向recycleview中写入数据
      informationList= new ArrayList<>();
      informationList.clear();
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
//用Sting中的contain函数实现模糊查询
              if(title.contains(keyword)) {
                  informationList.add(new information(infoid, title, content, date, userid2, corporationid));
              }
          } while (cursor.moveToNext());
      }
      cursor.close();

      RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview_information_published);
      RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      recyclerView.setLayoutManager(layoutManager);
      InformationAdapter adapter=new InformationAdapter(informationList);
      adapter.setOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
              information ainfo = informationList.get(position);
              Toast.makeText(InfomationShowActivity.this, "you clicked view "+ainfo.getTitle(), Toast.LENGTH_SHORT).show();
              int infoediterid=ainfo.getUer_id();
              int ifeditor=0;
              if(userid==infoediterid){ifeditor=1;}else{ifeditor=0;}
              int informationid=ainfo.getInfo_id();
              Intent intent=new Intent(InfomationShowActivity.this,UpdateInformationActivity.class);
              intent.putExtra("ifeditor", ifeditor);
              intent.putExtra("informationid",informationid);
              startActivity(intent);



          }
      });
      recyclerView.setAdapter(adapter);




  }





}
