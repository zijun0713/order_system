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
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.google.android.material.datepicker.MaterialDatePicker;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;
    /*
    //手工起司蛋糕
    Button plus1_1;
    Button minus1_1;
    TextView num1_1;
    //水果蛋糕
    Button plus1_2;
    Button minus1_2;
    TextView num1_2;
    //布朗尼
    Button plus1_3;
    Button minus1_3;
    TextView num1_3;

    //卡布奇諾
    Button plus2_1;
    Button minus2_1;
    TextView num2_1;
    //拿鐵咖啡
    Button plus2_2;
    Button minus2_2;
    TextView num2_2;
    //招牌花式咖啡
    Button plus2_3;
    Button minus2_3;
    TextView num2_3;

    //藍莓果粒茶
    Button plus3_1;
    Button minus3_1;
    TextView num3_1;
    //印度大吉嶺紅茶
    Button plus3_2;
    Button minus3_2;
    TextView num3_2;
    //英國伯爵奶茶
    Button plus3_3;
    Button minus3_3;
    TextView num3_3;
    */
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //手工起司蛋糕
        plus1_1 = (Button) findViewById(R.id.plus1_1);
        minus1_1 = (Button) findViewById(R.id.minus1_1);
        num1_1 = (TextView) findViewById(R.id.num1_1);
        plus1_1.setOnClickListener(this::onClick);
        minus1_1.setOnClickListener(this::onClick);
        //水果蛋糕
        plus1_2 = (Button) findViewById(R.id.plus1_2);
        minus1_2 = (Button) findViewById(R.id.minus1_2);
        num1_2 = (TextView) findViewById(R.id.num1_2);
        plus1_2.setOnClickListener(this::onClick);
        minus1_2.setOnClickListener(this::onClick);
        //布朗尼
        plus1_3 = (Button) findViewById(R.id.plus1_3);
        minus1_3 = (Button) findViewById(R.id.minus1_3);
        num1_3 = (TextView) findViewById(R.id.num1_3);
        plus1_3.setOnClickListener(this::onClick);
        minus1_3.setOnClickListener(this::onClick);
        */
        sp =  findViewById(R.id.sp);
        sp.setSelection(0, false);

        // 設定 sp 元件 ItemSelected 事件的 listener
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

        //screen_switch
        LayoutInflater inflater =  getLayoutInflater();
        final View activity_main = inflater.inflate(R.layout.activity_main, null);//展開主視窗
        final LinearLayout masterView = (LinearLayout) activity_main.findViewById(R.id.mView); //取得主視窗中下方的空白LinearLayout

        final View cake = inflater.inflate(R.layout.cake, null);//展開糕點輕食子畫面視窗
        final View coffee = inflater.inflate(R.layout.coffee, null);//展開義式咖啡子畫面視窗
        final View tea = inflater.inflate(R.layout.tea, null);//展開精選茶類子畫面視窗

        final LinearLayout View1 = (LinearLayout) cake.findViewById(R.id.view1);//找出糕點輕食視窗中的內容版面
        final LinearLayout View2 = (LinearLayout) coffee.findViewById(R.id.view2);//找出義式咖啡視窗中的內容版面
        final LinearLayout View3 = (LinearLayout) tea.findViewById(R.id.view3);//找出精選茶類視窗中的內容版面

        setContentView(activity_main);
        final LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        button1 = (Button) activity_main.findViewById(R.id.button1); //找出主視窗中第1個及設定按鈕動作
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                masterView.removeAllViews();//要加入內容版面前前移除掉所有已存在的版面
                masterView.addView(View1,p);//加入糕點輕食視窗中的內容版面
            }
        });

        button2 = (Button) activity_main.findViewById(R.id.button2); //找出主視窗中第2個及設定按鈕動作
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                masterView.removeAllViews();
                masterView.addView(View2);//加入義式咖啡視窗中的內容版面
            }
        });

        button3 = (Button) activity_main.findViewById(R.id.button3); //找出主視窗中第3個及設定按鈕動作
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                masterView.removeAllViews();
                masterView.addView(View3);//加入精選茶類視窗中的內容版面
            }
        });
    }
    private void onClick(View view){
        /*
        String s;
        int tot ;
        switch(view.getId()){
            case R.id.plus1_1:
                s = num1_1.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num1_1.setText(String.valueOf(tot));
                break;
            case R.id.minus1_1:
                s = num1_1.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num1_1.setText(String.valueOf(tot));
                break;
            case R.id.plus1_2:
                s = num1_2.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num1_2.setText(String.valueOf(tot));
                break;
            case R.id.minus1_2:
                s = num1_2.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num1_2.setText(String.valueOf(tot));
                break;
            case R.id.plus1_3:
                s = num1_3.getText().toString();
                tot = Integer.parseInt(s)+1;
                tot = check(tot);
                num1_3.setText(String.valueOf(tot));
                break;
            case R.id.minus1_3:
                s = num1_3.getText().toString();
                tot = Integer.parseInt(s)-1;
                tot = check(tot);
                num1_3.setText(String.valueOf(tot));
                break;
        }
        */
    }

    private int check(int number){
        if(number<=0) number = 0;
        else if(number>=10) number = 10;
        return number;
    }

}