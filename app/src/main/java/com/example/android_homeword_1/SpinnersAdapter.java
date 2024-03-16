package com.example.android_homeword_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnersAdapter extends BaseAdapter {


    private Context context;
    private List<corporation> corporationList;

    public SpinnersAdapter(List<corporation> corporationList, Context context) {
        this.context=context;
        this.corporationList=corporationList;
    }


    @Override
    public int getCount() {
        return corporationList.size();
    }

    @Override
    public Object getItem(int position) {
        return corporationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.spinner_item_publish_info,null);
        if(convertView!=null) {
            TextView textView_id=(TextView) convertView.findViewById(R.id.textView_spinner_id);
            textView_id.setText(corporationList.get(position).getId()+":");
            TextView textView_name=(TextView) convertView.findViewById(R.id.textView_spinner_name);
            textView_name.setText(corporationList.get(position).getName());
        }

        return convertView;
    }
}
