package com.example.lastexam_letienloi.model;

public class ListNote {
    private String title, des, dateTime, ussername;
    private Float progress;
    private int id;

    public ListNote(int id, String title, String des, String dateTime, String ussername) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.dateTime = dateTime;
        this.ussername = ussername;
    }

    public ListNote(String title, String des, String dateTime, String ussername) {
        this.title = title;
        this.des = des;
        this.dateTime = dateTime;
        this.ussername = ussername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUssername() {
        return ussername;
    }

    public void setUssername(String ussername) {
        this.ussername = ussername;
    }
}

