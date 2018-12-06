package com.ucsc.mymoney;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class more_func_in_this_app extends AppCompatActivity {
    public ImageButton Btn_Acc,Btn_Gra,Btn_Wis,Btn_Cal;
    public View view;
    ImageView Img_Acc,Img_Gra,Img_Wis,Img_Cal;

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
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
    }

    public void inti(){
        Img_Acc = findViewById(R.id.imageB_Acc);
        //Btn_Acc = findViewById(R.id.imageB_Acc);
        Img_Gra = findViewById(R.id.imageB_Gra);
        Img_Wis = findViewById(R.id.imageB_Wis);
        Img_Cal = findViewById(R.id.imageB_Cal);
        //Btn_Gra = findViewById(R.id.imageB_Gra);
        //Btn_Wis = findViewById(R.id.imageB_Wis);
        //Btn_Cal = findViewById(R.id.imageB_Cal);
    }

    public void changeBackground(){
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
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
