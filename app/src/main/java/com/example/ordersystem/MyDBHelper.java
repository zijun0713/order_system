package com.example.ordersystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    // 以Singleton模式使用資料庫
    private static MyDBHelper mInstance = null;
    // 資料庫名稱
    private final static String _DBName = "MyDB.db";
    // 資料庫版本，要更新資料庫時需增加版本號
    private final static int _DBVersion = 1;

    public static MyDBHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new MyDBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private MyDBHelper(Context context) {
        super(context, _DBName, null, _DBVersion);
    }
    // 建立資料表寫在這裡
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS history (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Datetime TEXT not null," +
                "Tablenumber INTEGER not null," +
                "Orders TEXT not null," +
                "Number TEXT not null," +
                "Total INTEGER not null" +
                ");");
    }

    // 當資料庫版本更新時會執行這邊，可在此修改資料表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
        db.execSQL( "DROP TABLE history");
    }
}