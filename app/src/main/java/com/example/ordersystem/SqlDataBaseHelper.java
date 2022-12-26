package com.example.ordersystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlDataBaseHelper extends SQLiteOpenHelper {
    // 以Singleton模式使用資料庫
    private static SqlDataBaseHelper mInstance = null;
    // 資料庫名稱
    private static final String DataBaseName = "History";
    // 資料庫版本，要更新資料庫時需增加版本號
    private static final int DataBaseVersion = 1;

    public static SqlDataBaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new SqlDataBaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }


    // 建立資料表寫在這裡
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SqlTable = "CREATE TABLE IF NOT EXISTS history (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Datetime TEXT not null," +
                "Tablenumber INTEGER not null," +
                "Orders TEXT not null," +
                "Number TEXT not null," +
                "Total INTEGER not null" +
                ")";
        sqLiteDatabase.execSQL(SqlTable);
    }

    // 當資料庫版本更新時會執行這邊，可在此修改資料表
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String SQL = "DROP TABLE history";
        sqLiteDatabase.execSQL(SQL);
    }
}