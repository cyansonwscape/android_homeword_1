package com.example.android_homeword_1;

import java.util.Date;

public class information {
private int info_id;
private String title;
private String content;//内容
private String date;
private int uer_id;
private int corporation_id;

public information(int info_id,String title,String content,String date,int uer_id,int corporation_id){
    this.info_id=info_id;
    this.content=content;
    this.date=date;
    this.uer_id=uer_id;
    this.title=title;
    this.corporation_id=corporation_id;

}

    public int getCorporation_id() {
        return corporation_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public int getUer_id() {
        return uer_id;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCorporation_id(int corporation_id) {
        this.corporation_id = corporation_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUer_id(int uer_id) {
        this.uer_id = uer_id;
    }
}
