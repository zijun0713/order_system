package com.example.ordersystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.facebook.stetho.Stetho;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity<sqlDataBaseHelper> extends AppCompatActivity {

    private static final int ItemCake = 3;
    private static final int ItemCoffee = 3;
    private static final int ItemTea = 3;
    Button ButtonUp[]       = new Button[ItemCake+ItemCoffee+ItemTea];
    Button ButtonDown[]     = new Button[ItemCake+ItemCoffee+ItemTea];
    TextView ItemNumber[]   = new TextView[ItemCake+ItemCoffee+ItemTea];
    TextView ItemName[]   = new TextView[ItemCake+ItemCoffee+ItemTea];

    int IdButtonUp[]={
            R.id.plus1_1,R.id.plus1_2,R.id.plus1_3,
            R.id.plus2_1,R.id.plus2_2,R.id.plus2_3,
            R.id.plus3_1,R.id.plus3_2,R.id.plus3_3
    };
    int IdButtonDown[]={
            R.id.minus1_1,R.id.minus1_2,R.id.minus1_3,
            R.id.minus2_1,R.id.minus2_2,R.id.minus2_3,
            R.id.minus3_1,R.id.minus3_2,R.id.minus3_3
    };
    int IdTextNumber[]={
            R.id.num1_1,R.id.num1_2,R.id.num1_3,
            R.id.num2_1,R.id.num2_2,R.id.num2_3,
            R.id.num3_1,R.id.num3_2,R.id.num3_3
    };
    int IdItemName[]={
            R.id.item1_1,R.id.item1_2,R.id.item1_3,
            R.id.item2_1,R.id.item2_2,R.id.item2_3,
            R.id.item3_1,R.id.item3_2,R.id.item3_3
    };
    int ItemPrice[]={
            125,200,180,
            160,150,200,
            180,130,140
    };

    Button button1, button2, button3;
    TextView sum;
    String string = "";
    public Spinner sp;
    public SQLiteDatabase db;
    public int sppp;
    int TableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d("taggg", "b桌號:" + sppp);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        sp = findViewById(R.id.sp);
        sp.setSelection(0, false);

        // 設定 sp 元件 ItemSelected 事件的 listener
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
               //  sppp = Activity.this.getStringArray(R.array.num_table)[position];
               // parent.setVisibility(view.VISIBLE);
                sppp = position;
                Log.d("taggg", "桌號:" + sppp);
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

         */



        //Stetho.initializeWithDefaults(this);

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
        Spinner spinner = (Spinner)findViewById(R.id.sp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.num_table, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TableNumber = position;
                //Toast .makeText(MainActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        button1 = (Button) activity_main.findViewById(R.id.button1); //找出主視窗中第1個及設定按鈕動作
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                masterView.removeAllViews();//要加入內容版面前前移除掉所有已存在的版面
                masterView.addView(View1,p);//加入糕點輕食視窗中的內容版面
                Setup("cake");
                for(int i=0;i<ItemCake;i++){
                    ButtonUp[i].setOnClickListener(this::Click);
                    ButtonDown[i].setOnClickListener(this::Click);
                }
            }
            int tot = 0;
            int tot_original = 0;
            int id;
            int flag = 1;
            String s = "";
            private void Click(View view) {
                id = view.getId();
                for(int i=0;i<ItemCake;i++){
                    if(id == IdButtonUp[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original+1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    else if(id == IdButtonDown[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original-1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    if(flag == 0) {
                        ItemNumber[i].setText(String.valueOf(tot));
                        flag = 1;
                        break;
                    }
                }
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
                Setup("coffee");
                for(int i=ItemCake;i<ItemCake+ItemCoffee;i++){
                    ButtonUp[i].setOnClickListener(this::Click);
                    ButtonDown[i].setOnClickListener(this::Click);
                }
            }
            int tot = 0;
            int tot_original = 0;
            int id;
            int flag = 1;
            String s = "";
            private void Click(View view) {
                id = view.getId();
                for(int i=ItemCake;i<ItemCake+ItemCoffee;i++){
                    if(id == IdButtonUp[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original+1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    else if(id == IdButtonDown[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original-1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    if(flag == 0) {
                        ItemNumber[i].setText(String.valueOf(tot));
                        flag = 1;
                        break;
                    }
                }
            }
        });

        button3 = (Button) activity_main.findViewById(R.id.button3); //找出主視窗中第3個及設定按鈕動作
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                masterView.removeAllViews();
                masterView.addView(View3);//加入義式咖啡視窗中的內容版面
                Setup("tea");
                for(int i=ItemCake+ItemCoffee;i<ItemCake+ItemCoffee+ItemTea;i++){
                    ButtonUp[i].setOnClickListener(this::Click);
                    ButtonDown[i].setOnClickListener(this::Click);
                }
            }

            int tot = 0;
            int tot_original = 0;
            int id;
            int flag = 1;
            String s = "";
            private void Click(View view) {
                id = view.getId();
                for(int i=ItemCake+ItemCoffee;i<ItemCake+ItemCoffee+ItemTea;i++){
                    if(id == IdButtonUp[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original+1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    else if(id == IdButtonDown[i]){
                        s = ItemNumber[i].getText().toString();
                        tot_original =Integer.parseInt(s);
                        tot = tot_original-1;
                        tot = check(tot);
                        Calculate(ItemPrice[i]*(tot-tot_original));
                        flag = 0;
                    }
                    if(flag == 0) {
                        ItemNumber[i].setText(String.valueOf(tot));
                        flag = 1;
                        break;
                    }
                }
            }
        });


        // 呼叫MyDBHelper
        MyDBHelper DH = MyDBHelper.getInstance(this);
        // 取得可讀寫的資料庫
        db = DH.getWritableDatabase();
        //db.delete("history","*",null);


    }
    public class SpinnerActivity extends Activity implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
    private void Setup(String page){
        int i = 0;
        int first = 0,last = 0,flag = 1;
        if(page == "cake"){
            first = 0;
            last = ItemCake;
        }
        else if(page == "coffee"){
            first = ItemCake;
            last = ItemCake+ItemCoffee;
        }
        else if(page == "tea"){
            first = ItemCake+ItemCoffee;
            last = ItemCake+ItemCoffee+ItemTea;
        }
        else {
            flag = 0;
            Toast .makeText(MainActivity.this,"SetUp Error",Toast.LENGTH_SHORT).show();
        }
        if(flag == 1){
            for(i=first;i<last;i++){
                ButtonUp[i] = (Button) findViewById(IdButtonUp[i]);
                ButtonDown[i] = (Button) findViewById(IdButtonDown[i]);
                ItemNumber[i] = (TextView) findViewById(IdTextNumber[i]);
                ItemName[i] = (TextView) findViewById(IdItemName[i]);
            }
        }
    }

    private int check(int number){
        if(number<=0) number = 0;
        else if(number>=10) number = 10;
        return number;
    }
    int Sum =0;

    private  void Calculate(int value){
        //Toast .makeText(MainActivity.this,String.valueOf(value),Toast.LENGTH_SHORT).show();
        sum = (TextView) findViewById(R.id.sum);
        Sum = Integer.valueOf(sum.getText().toString());
        Sum +=value;
        //Toast .makeText(MainActivity.this,String.valueOf(Sum),Toast.LENGTH_SHORT).show();
        sum.setText(String.valueOf(Sum));
    }
    ArrayList<CharSequence> Final_Name = new ArrayList<CharSequence>();
    ArrayList<Integer> Final_Number = new ArrayList<Integer>();
    public void check_meal(View view) {

        //Intent intent = new Intent(this,CheckActivity.class);
        //startActivity(intent);
        CharSequence Name;
        int Number;

        String DATANAME="";
        String DATANumber="";
        string += "桌號："+String.valueOf(TableNumber+1)+"\n";
        for(int i=0;i<ItemCake+ItemCoffee+ItemTea;i++){
            try {
                Number = Integer.parseInt(ItemNumber[i].getText().toString());
                if(Number!=0){
                    Name = ItemName[i].getText();

                    string += String.valueOf(Name)+"\t\t\t\t"+Number+"\n";
                    DATANAME+=String.valueOf(Name)+"\t"+Number+"\n";
                   // DATANumber+=Number+"\n";

                    Final_Name.add(Name);
                    Final_Number.add(Number);
                }
            }
            catch (Exception e){

            }
        }
        string = string+"Total   "+sum.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("確認餐點");
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setMessage(string);
        /*-------------------------------抓取時間-------------------------------------*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        /*-------------------------------抓取時間-------------------------------------*/
        String finalDATANAME = DATANAME;
        String finalDATANumber = DATANumber;

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CharSequence Name;
                int number;
                //String string = "";
                String DATANAME="";
                String DATANumber="";
                // sp.getSelectedItem().toString();
                //時間
                //桌號
                //Final_Name        品項
                //Final_Number      數量
                //finish()
                // 將要新增的資料放到ContentValues
                ContentValues values = new ContentValues();
                values.put("Datetime", str);
                //values.put("Tablenumber", Integer.valueOf(sp.toString()));
                values.put("Tablenumber", TableNumber+1);
                values.put("Orders", finalDATANAME);
                values.put("Number", finalDATANumber);
                values.put("Total",Integer.valueOf(sum.getText().toString()) );
                // 新增資料到history欄位
                db.insert("history", null, values);
                //Log.d("taggg", "f桌號:" + sppp);

                for(i=0;i<ItemCake+ItemCoffee+ItemTea;i++){
                    try {
                        ItemNumber[i].setText(String.valueOf(0));
                        sum.setText(String.valueOf(0));
                    }
                    catch (Exception e){

                    }
                }
                string = "";
            }
        }); builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)  {
                string = "";
                Final_Name.removeAll(Final_Name);
                Final_Number.removeAll(Final_Number);
            }
        });
        builder.show();
    }
    public void history(View view) {
        Intent intent = new Intent(this,CheckActivity.class);
        startActivity(intent);
    }



}