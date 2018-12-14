package com.ucsc.mymoney.theGraph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Random;

import com.ucsc.mymoney.MainActivity;
import com.ucsc.mymoney.accomplishment.accomplish_01;
import com.ucsc.mymoney.accomplishment.accomplish_02;
import com.ucsc.mymoney.accomplishment.accomplish_03;
import com.ucsc.mymoney.accomplishment.accomplish_04;
import com.ucsc.mymoney.adapter_and_helper.GlobalVariables;
import com.ucsc.mymoney.R;
import com.ucsc.mymoney.model.IOItem;

import org.litepal.crud.DataSupport;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Graph extends AppCompatActivity{
    public Button Btn_next;
    public Button Btn_back;
    private View view;
    private List<IOItem> ioList = new ArrayList<>();
    private static final String TAG = "Graph";
    private TextView title;
    GlobalVariables callThis;
    private List<IOItem> mioList;
    private static double[] income = new double[13];
    private static String[] nameId = new String[13];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        inti();
        //check if the theme has been applied first
        if (MainActivity.accountOne && accomplish_01.accountOne_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.accountTwo && accomplish_02.accountTwo_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 2 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.accountThree && accomplish_03.accountThree_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 3 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.accountFour && accomplish_04.accountFour_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 4 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        title = (TextView) findViewById(R.id.txTitle);
        title.setTextSize(20);
        title.setTextColor(Color.BLACK);
        title.setText("Income Pie Chart");


        ioList =  DataSupport.where("bookId = ?", String.valueOf(GlobalVariables.getmBookId())).find(IOItem.class);
        mioList = ioList;

        Btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Graph.this, Graph2.class);
                startActivity(intent);
            }
        });

        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Graph.this, MainActivity.class);
                startActivity(intent);
            }
        });

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);


//        for(int i = 0; i != 13; i++){
//            boolean found = false;
//            int j = 0;
//            IOItem ioItem = mioList.get(i);
//            for (String element:nameId ) {
//                if ( element.equals(ioItem.getSrcName())) {
//                    Log.d(TAG, "The value is found!");
//                    found = true;
//                    income[j] += ioItem.getMoney();
//                }
//                j++;
//            }
//            if(!found) {
//                income[i] = ioItem.getMoney();
//                nameId[i] = ioItem.getSrcName();
//            }
//            Log.d(TAG, "this is the pos" + i + "this is the $" + income[i]);
//        }
        int pos = callThis.getmPos();

        for(int i = 0; i != pos; i++){
            IOItem ioItem = mioList.get(i);
            if(ioItem.getType() == 1) {
                int a = (int) ioItem.getMoney();
                mPieChart.addPieSlice(new PieModel(ioItem.getName(), a, getRandomColor()));
            }
        }
//        mPieChart.addPieSlice(new PieModel("Freetime", 12, Color.parseColor("#FE6DA8")));
//        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
//        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));

        mPieChart.startAnimation();
    }
    public void inti(){
        Btn_next = findViewById(R.id.nextGraph);
        Btn_back = findViewById(R.id.backToMain);
        view = findViewById(R.id.activity_graph);
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
