package com.babymanager.babymanager.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.babymanager.babymanager.Models.Category;
import com.babymanager.babymanager.Models.DetailTips;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class TipsBabyDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "TipsBaby.db";
    private static final int DB_VER = 1;
    public TipsBabyDatabase(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }
    public List<Category> getAllCategory(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id","Name"};
        String table = "Category";
        qb.setTables(table);
        Cursor c  = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Category> categoryList = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex("Id")));
                category.setName(c.getString(c.getColumnIndex("Name")));
                categoryList.add(category);
            }while (c.moveToNext());
        }
        return categoryList;
    }

    public DetailTips getDetailTips(int categoryId) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Detail",
                new String[]{"Id","Content","Photo","CoverImage","CategoryId"},
                "CategoryId=?",
                new String[]{String.valueOf(categoryId)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        DetailTips detailTips = new DetailTips(
                cursor.getInt(cursor.getColumnIndex("Id")),
                cursor.getString(cursor.getColumnIndex("Content")),
                cursor.getBlob(cursor.getColumnIndex("Photo")),
                cursor.getString(cursor.getColumnIndex("CoverImage")),
                cursor.getInt(cursor.getColumnIndex("CategoryId")));
        // close the db connection
        cursor.close();
        return detailTips;
    }
}
