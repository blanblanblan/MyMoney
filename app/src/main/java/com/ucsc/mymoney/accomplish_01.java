package com.ucsc.mymoney;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_01 extends AppCompatActivity {

    public static Boolean acc_1_in_use = false;
    Button Btn_acc_1;
    WaveLoadingView waveLoadingView;
    View view;
    //more_func_in_this_app more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_01);
        waveLoadingView = findViewById(R.id.waveLoadingView_1);
        view= findViewById(R.id.layout_1);

        if (MainActivity.acc_1 && acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        //more.changeBackground();
        Btn_acc_1 = findViewById(R.id.acc_1_btn);
        int percent = 0;
        if (MainActivity.acc_1 == true){
            waveLoadingView.setProgressValue(100);
            waveLoadingView.setBottomTitle("");
            waveLoadingView.setCenterTitle(String.format("%d%%",100));
            waveLoadingView.setTopTitle("");
        }else{
            if(MainActivity.acc1_percent < 50){
                waveLoadingView.setProgressValue(MainActivity.acc1_percent);
                waveLoadingView.setBottomTitle(String.format("%d%%",MainActivity.acc1_percent));
                waveLoadingView.setCenterTitle("");
                waveLoadingView.setTopTitle("");
            }
            else if(MainActivity.acc1_percent < 80){
                waveLoadingView.setProgressValue(MainActivity.acc1_percent);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle(String.format("%d%%",MainActivity.acc1_percent));
                waveLoadingView.setTopTitle("");
            }
            else{
                waveLoadingView.setProgressValue(MainActivity.acc1_percent);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle("");
                waveLoadingView.setTopTitle(String.format("%d%%",MainActivity.acc1_percent));
            }
        }
        Btn_acc_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.acc_1 == true){
                    changeBackground(R.drawable.gradient_green);
                    acc_1_in_use = true;
                    accomplish_02.acc_2_in_use = false;
                    accomplish_03.acc_3_in_use = false;
                    accomplish_04.acc_4_in_use = false;
                    Toast.makeText(accomplish_01.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_01.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                    accomplish_01.acc_1_in_use = false;
                    accomplish_02.acc_2_in_use = false;
                    accomplish_03.acc_3_in_use = false;
                    accomplish_04.acc_4_in_use = false;
                }
            }
        });
    }

    public void changeBackground(int drawable){
        view.setBackground(getDrawable(drawable));
    }

}
