package com.example.ordersystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //手工起司蛋糕
    Button plus1;
    Button minus1;
    TextView num1;
    //水果蛋糕
    Button plus2;
    Button minus2;
    TextView num2;
    //布朗尼
    Button plus3;
    Button minus3;
    TextView num3;

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //手工起司蛋糕
        plus1 = (Button) findViewById(R.id.plus1);
        minus1 = (Button) findViewById(R.id.minus1);
        num1 = (TextView) findViewById(R.id.num1);
        plus1.setOnClickListener(this::onClick);
        minus1.setOnClickListener(this::onClick);
        //水果蛋糕
        plus2 = (Button) findViewById(R.id.plus2);
        minus2 = (Button) findViewById(R.id.minus2);
        num2 = (TextView) findViewById(R.id.num2);
        plus2.setOnClickListener(this::onClick);
        minus2.setOnClickListener(this::onClick);
        //布朗尼
        plus3 = (Button) findViewById(R.id.plus3);
        minus3 = (Button) findViewById(R.id.minus3);
        num3 = (TextView) findViewById(R.id.num3);
        plus3.setOnClickListener(this::onClick);
        minus3.setOnClickListener(this::onClick);

        sp =  findViewById(R.id.sp);

        // 設定 sp 元件 ItemSelected 事件的 listener
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });
    }
    private void onClick(View view){
        String s;
        int tot ;
        switch(view.getId()){
            case R.id.plus1:
                s = num1.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num1.setText(String.valueOf(tot));
                break;
            case R.id.minus1:
                s = num1.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num1.setText(String.valueOf(tot));
                break;
            case R.id.plus2:
                s = num2.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num2.setText(String.valueOf(tot));
                break;
            case R.id.minus2:
                s = num2.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num2.setText(String.valueOf(tot));
                break;
            case R.id.plus3:
                s = num3.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num3.setText(String.valueOf(tot));
                break;
            case R.id.minus3:
                s = num3.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num3.setText(String.valueOf(tot));
                break;
        }

    }
    private int check(int number){
        if(number<=0) number = 0;
        else if(number>=10) number = 10;
        return number;
    }
}