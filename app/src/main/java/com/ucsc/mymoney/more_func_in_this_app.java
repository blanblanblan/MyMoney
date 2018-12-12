package com.ucsc.mymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ucsc.mymoney.accomplishment.AccomplishmentViewPage;
import com.ucsc.mymoney.accomplishment.accomplish_01;
import com.ucsc.mymoney.accomplishment.accomplish_02;
import com.ucsc.mymoney.accomplishment.accomplish_03;
import com.ucsc.mymoney.accomplishment.accomplish_04;
import com.ucsc.mymoney.wish_list.wishlist;
import com.ucsc.mymoney.theGraph.Graph;

public class more_func_in_this_app extends AppCompatActivity {
    private static final String TAG = "Function Page";
    private ImageButton Btn_Acc,Btn_Gra,Btn_Wis,Btn_Cal;
    private View view;
    private ImageView Img_Acc,Img_Gra,Img_Wis,Img_Cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_func_in_this_app);
        view= findViewById(R.id.layout_more_func);
        inti();

        Img_Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(more_func_in_this_app.this, AccomplishmentViewPage.class);
                startActivity(intent);
            }
        });

        Img_Gra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(more_func_in_this_app.this, Graph.class);
                startActivity(intent);
            }
        });

        Img_Wis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(more_func_in_this_app.this, wishlist.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(more_func_in_this_app.this, "AAAAAAAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
        // check the theme condition and update theme if needed
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
    }

    public void inti(){
        //Toast.makeText(more_func_in_this_app.this, "DDDD", Toast.LENGTH_SHORT).show();
        Img_Acc = findViewById(R.id.imageB_Acc);
        Img_Gra = findViewById(R.id.imageB_Gra);
        Img_Wis = findViewById(R.id.imageB_Wis);
        Img_Cal = findViewById(R.id.imageB_Cal);
    }

    public void changeBackground(){
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            //Toast.makeText(more_func_in_this_app.this, "JJJJJJJJ", Toast.LENGTH_SHORT).show();
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
    }
}
