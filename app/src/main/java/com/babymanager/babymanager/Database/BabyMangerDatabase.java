package com.babymanager.babymanager.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;

import com.babymanager.babymanager.Models.Diaper;
import com.babymanager.babymanager.Models.Feed;
import com.babymanager.babymanager.Models.Measure;
import com.babymanager.babymanager.Models.Sleep;
import com.babymanager.babymanager.Models.Timer;
import com.babymanager.babymanager.Models.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class BabyMangerDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "BabyManager.db";
    private static final int DB_VER = 2;
    public BabyMangerDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    public void addBaby(String name,String sex,String birthday,byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO User VALUES(NULL,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindString(2,sex);
        statement.bindString(3,birthday);
        statement.bindBlob(4,image);

        statement.executeInsert();
    }
    public List<Feed> getAllFeed(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","NameFood","Content","Date","UserId","Note","ImageFood"};
        String table = "Feed";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,"Id DESC");
        List<Feed> feedList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Feed feed = new Feed();
                feed.setId(c.getInt(c.getColumnIndex("Id")));
                feed.setNameFood(c.getString(c.getColumnIndex("NameFood")));
                feed.setContent(c.getString(c.getColumnIndex("Content")));
                feed.setDate(c.getString(c.getColumnIndex("Date")));
                feed.setUserId(c.getInt(c.getColumnIndex("UserId")));
                feed.setNote(c.getString(c.getColumnIndex("Note")));
                feed.setImageFood(c.getBlob(c.getColumnIndex("ImageFood")));
                feedList.add(feed);
            }while (c.moveToNext());
        }
        return feedList;
    }
    public void addFood(String nameFood,String content,String date,int userId,String note,byte[] imageFood){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Feed VALUES(NULL,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,nameFood);
        statement.bindString(2,content);
        statement.bindString(3,date);
        statement.bindLong(4,userId);
        statement.bindString(5,note);
        statement.bindBlob(6,imageFood);
        statement.executeInsert();
    }

    public void updateBaby(String weight,String height,int id){
        SQLiteDatabase db = getWritableDatabase();

        SQLiteStatement statement = db.compileStatement("UPDATE User SET Weight = ?,Height = ? WHERE Id = ?");
        statement.clearBindings();
        statement.bindString(1,weight);
        statement.bindString(2,height);
        statement.bindDouble(3,id);

        statement.execute();
        db.close();
    }
    public void addWeightAndHeightBaby(String weight,String height,String date,int month,int userId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Measure VALUES(NULL,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,weight);
        statement.bindString(2,height);
        statement.bindString(3,date);
        statement.bindLong(4,month);
        statement.bindLong(5,userId);
        statement.executeInsert();
    }
    public List<Measure> getWeightAndHeightBaby(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","Weight","Height","Date","Month","UserId"};
        String table = "Measure";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,"Id DESC");
        List<Measure> measureList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Measure measure = new Measure();
                measure.setId(c.getInt(c.getColumnIndex("Id")));
                measure.setWeight(c.getFloat(c.getColumnIndex("Weight")));
                measure.setHeight(c.getInt(c.getColumnIndex("Height")));
                measure.setDate(c.getString(c.getColumnIndex("Date")));
                measure.setMonth(c.getInt(c.getColumnIndex("Month")));
                measure.setUserId(c.getInt(c.getColumnIndex("UserId")));
                measureList.add(measure);
            }while (c.moveToNext());
        }
        return measureList;
    }
    public void addDiaper(String status,String timestamp,int userId,byte[] imageDiaper){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Diaper VALUES(NULL,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,status);
        statement.bindString(2,timestamp);
        statement.bindLong(3,userId);
        statement.bindBlob(4,imageDiaper);
        statement.executeInsert();
    }
    public List<Diaper> getAllDiaper(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","Status","Timestamp","UserId","ImageDiaper"};
        String table = "Diaper";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,"Id DESC");
        List<Diaper> diaperList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Diaper diaper = new Diaper();
                diaper.setId(c.getInt(c.getColumnIndex("Id")));
                diaper.setStatus(c.getString(c.getColumnIndex("Status")));
                diaper.setTimeStamp(c.getString(c.getColumnIndex("Timestamp")));
                diaper.setUserId(c.getInt(c.getColumnIndex("UserId")));
                diaper.setImageDiaper(c.getBlob(c.getColumnIndex("ImageDiaper")));
                diaperList.add(diaper);
            }while (c.moveToNext());
        }
        return diaperList;
    }
    public void addSleep(String timestamp,int userId,String startSleep,String endSleep){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Sleep VALUES(NULL,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,timestamp);
        statement.bindLong(2,userId);
        statement.bindString(3,startSleep);
        statement.bindString(4,endSleep);
        statement.executeInsert();
    }
    public List<Sleep> getAllSleep(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","Timestamp","UserId","StartSleep","EndSleep"};
        String table = "Sleep";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,"Id DESC");
        List<Sleep> sleepList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Sleep sleep = new Sleep();
                sleep.setId(c.getInt(c.getColumnIndex("Id")));
                sleep.setTimeStamp(c.getString(c.getColumnIndex("Timestamp")));
                sleep.setUserId(c.getInt(c.getColumnIndex("UserId")));
                sleep.setStartDate(c.getString(c.getColumnIndex("StartSleep")));
                sleep.setEndDate(c.getString(c.getColumnIndex("EndSleep")));
                sleepList.add(sleep);
            }while (c.moveToNext());
        }
        return sleepList;
    }

    public void addTimer(String title,String body,String timestamp,String date,int userId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Timer VALUES(NULL,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,title);
        statement.bindString(2,body);
        statement.bindString(3,timestamp);
        statement.bindString(4,date);
        statement.bindLong(5,userId);
        statement.executeInsert();
    }
    public List<Timer> getAllTimer(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","Title","Body","Timestamp","Date","UserId"};
        String table = "Timer";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,"Id DESC");
        List<Timer> timerList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Timer timer = new Timer();
                timer.setId(c.getInt(c.getColumnIndex("Id")));
                timer.setTitle(c.getString(c.getColumnIndex("Title")));
                timer.setBody(c.getString(c.getColumnIndex("Body")));
                timer.setTimeStamp(c.getString(c.getColumnIndex("Timestamp")));
                timer.setDate(c.getString(c.getColumnIndex("Date")));
                timer.setUserId(c.getInt(c.getColumnIndex("UserId")));
                timerList.add(timer);
            }while (c.moveToNext());
        }
        return timerList;
    }

    public User getUser(int id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("User",
                new String[]{"Name", "Sex", "Birthday","Photo"},
                "Id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        User user = new User(
                cursor.getString(cursor.getColumnIndex("Name")),
                cursor.getString(cursor.getColumnIndex("Sex")),
                cursor.getString(cursor.getColumnIndex("Birthday")),
                cursor.getBlob(cursor.getColumnIndex("Photo")));
        // close the db connection
        cursor.close();
        return user;
    }

    public User getUserHeight(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("User",
                new String[]{"Height", "Weight"},
                "Id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        User user = new User(
                cursor.getString(cursor.getColumnIndex("Height")),
                cursor.getString(cursor.getColumnIndex("Weight")));
        // close the db connection
        cursor.close();
        return user;
    }

    public float getAvgHeight(int month){
        SQLiteDatabase db = getWritableDatabase();
        String query= "SELECT AVG(Height) From Measure WHERE Month = ?";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(month)});
        c.moveToFirst();
        float AverageValue=c.getFloat(0);
        return AverageValue;
    }
    public float getAvgWeight(int month){
        SQLiteDatabase db = getWritableDatabase();
        String query= "SELECT AVG(Weight) From Measure WHERE Month = ?";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(month)});
        c.moveToFirst();
        float AverageValue=c.getFloat(0);
        return AverageValue;
    }

    public void  removeFromDiaper(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Diaper WHERE Id = '%s';",id);
        db.execSQL(query);
    }
    public void  removeFromFeed(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Feed WHERE Id = '%s';",id);
        db.execSQL(query);
    }
    public void  removeFromSleep(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Sleep WHERE Id = '%s';",id);
        db.execSQL(query);
    }
    public void  removeFromMeasure(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Measure WHERE Id = '%s';",id);
        db.execSQL(query);
    }
    public void  removeFromTimer(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Timer WHERE Id = '%s';",id);
        db.execSQL(query);
    }
    public void updatePhoto(byte[] photo,int id){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("UPDATE User SET Photo = ? WHERE Id = ?");
        statement.clearBindings();
        statement.bindBlob(1,photo);
        statement.bindDouble(2,id);

        statement.execute();
        db.close();
    }
    public User getPhoto(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("User",
                new String[]{"Photo"},
                "Id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        User user = new User(
                cursor.getBlob(cursor.getColumnIndex("Photo")));
        cursor.close();
        return user;
    }

    public void updateName(String name,String birthday,String sex,int id){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("UPDATE User SET Name = ?,Birthday =?,Sex = ? WHERE Id = ?");
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,birthday);
        statement.bindString(3,sex);
        statement.bindDouble(4,id);

        statement.execute();
        db.close();
    }




}
