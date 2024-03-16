package com.example.android_homeword_1;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder>{


    private List<information> informationList;

//    private View view_copy;
//    private ViewHolder viewHolder_copy;

    public InformationAdapter(List<information> informationList) {
        this.informationList=informationList;
    }

    @NonNull
    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_item, null, false);
        ViewHolder viewHolder = new ViewHolder(view);

//        view_copy=view;
//        viewHolder_copy=viewHolder;
        //处理最外层布局的点击事件
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = viewHolder.getAdapterPosition();
//                information ainfo = informationList.get(position);
//                Toast.makeText(parent.getContext(), "you clicked view "+ainfo.getTitle(), Toast.LENGTH_SHORT).show();
////                MemoUpdateActivity.actionStart(view.getContext(),aMemo.getId()); // 启动笔记编辑活动
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InformationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        information ainfo = informationList.get(position);
        holder.TitleTxt.setText(ainfo.getTitle());
        holder.DateTxt.setText(ainfo.getDate());
        holder.ContentTxt.setText(ainfo.getContent());

//接口有关函数

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });



//        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);
//        if (mOnItemClickListener != null) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    mOnItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });
//        }


    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }


    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView TitleTxt;
        private final TextView ContentTxt;
        private final TextView DateTxt;

        public ViewHolder(View view) {
            super(view);
            TitleTxt = (TextView) view.findViewById(R.id.textView_information_item_title);
            ContentTxt =(TextView) view.findViewById(R.id.textView_information_item_content);
            DateTxt = (TextView) view.findViewById(R.id.textView_information_item_date);
        }
    }



    public String findcopornamebyid(int id){
        String name="";
//        BaseDBHelper_userinfo dbHelper =new BaseDBHelper_userinfo(, "InfoData.db", 1);
//        SQLiteDatabase database  = dbHelper.getWritableDatabase(); //获得数据库对象
//        Cursor cursor = database.query("informationInfo", null, null, null, null, null, null); //从数据库表 t_memo 中读取所有数据
//        if (cursor.moveToFirst() == true) {
//            do {
//                // 遍历 Cursor 对象，取出数据
//                @SuppressLint("Range") int infoid = cursor.getInt(cursor.getColumnIndex("infoid"));
//                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
//                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
//                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
//                @SuppressLint("Range") int userid2 = cursor.getInt(cursor.getColumnIndex("userid"));
//                @SuppressLint("Range") int corporationid = cursor.getInt(cursor.getColumnIndex("corporationid"));
//
//                informationList.add(new information(infoid,title,content,date,userid2,corporationid));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
        return name;

    }
    public String findeditornamebyid(int id){
        String name="";

        return name;
    }


}
