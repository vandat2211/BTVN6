package com.example.btvn6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "folder.db";

    private static final String TABLE_NAME = "folder";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY," +
                TITLE + " TEXT, " +
                CONTENT + " TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("drop table if exists "+TABLE_NAME);
    onCreate(db);
    }
    public void addfolder(Folder folder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, folder.getTitle());
        contentValues.put(CONTENT, folder.getContent());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public List<Folder> getAll() {
        List<Folder> folderArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Folder folder = new Folder();
                folder.setId(cursor.getInt(0));
                folder.setTitle(cursor.getString(1));
                folder.setContent(cursor.getString(2));
                folderArrayList.add(folder);
            } while (cursor.moveToNext());
        }
        db.close();
        return folderArrayList;
    }
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
    public long updatedb(Folder folder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, folder.getId());
        values.put(TITLE, folder.getTitle());
        values.put(CONTENT, folder.getContent());


        String where = ID + " = " + folder.getId();
        return db.update(TABLE_NAME, values, where, null);

    }
}
