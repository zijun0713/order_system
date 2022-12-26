package com.example.ordersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckActivity extends MainActivity {
    TextView textView; // 把視圖的元件宣告成全域變數
    Button button;
    String result; // 儲存資料用的字串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 按下之後會執行的程式碼
                Cursor c = db.rawQuery("SELECT * FROM history " , null);
                while (c.moveToNext()) {
                    String datetime = c.getString(1);
                    int tablenumber = c.getInt(2);
                    String orders = c.getString(3);
                    String number = c.getString(4);
                    int total = c.getInt(5);
                    textView.setText("日期:"+datetime+"桌號:"+tablenumber+"餐點:"+orders+"數量:"+number+"總價:"+total);
                 /*   Log.d("點餐資訊", "日期:" + datetime);
                    Log.d("點餐資訊", "桌號:" + tablenumber);
                    Log.d("點餐資訊", "餐點:" + orders);
                    Log.d("點餐資訊", "數量:" + number);
                    Log.d("點餐資訊", "總價:" + total);*/
                }
                c.close();
            }
        });
    }

}