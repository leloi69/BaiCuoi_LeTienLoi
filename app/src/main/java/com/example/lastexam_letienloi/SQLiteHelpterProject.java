package com.example.lastexam_letienloi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lastexam_letienloi.model.ListNote;
import com.example.lastexam_letienloi.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SQLiteHelpterProject extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Project.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHelpterProject(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="CREATE TABLE User(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "pass TEXT)";
        String sql2="CREATE TABLE Listnote(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,"+
                "des TEXT,"+
                "dateTime TIME,"+
                "ussername TEXT)";
        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
    public void addListnote(ListNote listNote){
        String sql="INSERT INTO Listnote(title,des,dateTime,ussername) VALUES (?,?,?,?)";
        String[] args={listNote.getTitle(),listNote.getDes(),listNote.getDateTime(), listNote.getUssername()};
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    public List<ListNote> getAllListnote(){
        List<ListNote> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor resultset=statement.query("Listnote",null,null,null,null,null,null);
        while(resultset!=null && resultset.moveToNext()){
            int id=resultset.getInt(0);
            String title=resultset.getString(1);
            String des=resultset.getString(2);
            String dateTime=resultset.getString(3);
            String ussername=resultset.getString(4);
            list.add(new ListNote(id,title,des,dateTime,ussername));
        }
        return list;
    }
    public List<ListNote> getAllListnoteByUserName(String name){
        List<ListNote> list=new ArrayList<>();
        String whereClause = "ussername=?";
        String[] whereArgs = {name};
        SQLiteDatabase statement=getReadableDatabase();
        Cursor resultset=statement.query("Listnote",null,whereClause,whereArgs,null,null,null,null);
        while(resultset!=null && resultset.moveToNext()){
            int id=resultset.getInt(0);
            String title=resultset.getString(1);
            String des=resultset.getString(2);
            String dateTime=resultset.getString(3);
            String ussername=resultset.getString(4);
            list.add(new ListNote(id,title,des,dateTime,ussername));
        }
        resultset.close();
        return list;
    }
    public int updateListnote(ListNote ListNote){
        ContentValues v=new ContentValues();
        v.put("title",ListNote.getTitle());
        v.put("des",ListNote.getDes());
        v.put("dateTime",ListNote.getDateTime());
        v.put("ussername",ListNote.getUssername());
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(ListNote.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("Listnote",v,whereClause,whereArgs);
    }

    public int deleteListnote(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("Listnote",whereClause,whereArgs);
    }
    public List<ListNote> getByTitle(String name) {
        List<ListNote> list=new ArrayList<>();
        String whereClause = "title LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("Listnote", null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String des = cursor.getString(cursor.getColumnIndex("des"));
            String dateTime=cursor.getString(cursor.getColumnIndex("dateTime"));
            String ussername=cursor.getString(cursor.getColumnIndex("ussername"));
            list.add(new ListNote(id, title, des,dateTime,ussername));
        }
        cursor.close();
        return list;
    }
    public void addUser(User user){
        String sql="INSERT INTO User(name,pass) VALUES (?,?)";
        String[] args={user.getName(),user.getPass()};
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    public int updateUser(User user){
        ContentValues v=new ContentValues();
        v.put("name",user.getName());
        v.put("pass",user.getPass());
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(user.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("User",v,whereClause,whereArgs);
    }
    public Boolean login(String name,String pass) {
        String whereClause = "name=? and pass=?";
        String[] whereArgs = {name,pass};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("User", null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            return  true;
        }
        cursor.close();
        return false;
    }
}
