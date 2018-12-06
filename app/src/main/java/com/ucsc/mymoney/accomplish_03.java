package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_03 extends AppCompatActivity {

    public static Boolean acc_3_in_use = false;
    Button Btn_acc_3;
    WaveLoadingView waveLoadingView_3;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_03);
        waveLoadingView_3 = findViewById(R.id.waveLoadingView_3);
        Btn_acc_3 = findViewById(R.id.acc_3_btn);
        view= findViewById(R.id.layout_3);
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }

        if (MainActivity.acc_3 == true){
            waveLoadingView_3.setProgressValue(100);
            waveLoadingView_3.setBottomTitle("");
            waveLoadingView_3.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_3.setTopTitle("");
        }else{
            if(MainActivity.acc3_percent < 50){
                waveLoadingView_3.setBottomTitle(String.format("%d%%",MainActivity.acc3_percent));
                waveLoadingView_3.setProgressValue(MainActivity.acc3_percent);
                //System.out.print("new = "+ MainActivity.acc2_percent/500);
                waveLoadingView_3.setCenterTitle("");
                waveLoadingView_3.setTopTitle("");
            }
            else if(MainActivity.acc3_percent < 80){
                waveLoadingView_3.setProgressValue(MainActivity.acc3_percent);
                waveLoadingView_3.setBottomTitle("");
                waveLoadingView_3.setCenterTitle(String.format("%d%%",MainActivity.acc3_percent));
                waveLoadingView_3.setTopTitle("");
            }
            else{
                waveLoadingView_3.setProgressValue(MainActivity.acc3_percent);
                waveLoadingView_3.setBottomTitle("");
                waveLoadingView_3.setCenterTitle("");
                waveLoadingView_3.setTopTitle(String.format("%d%%",MainActivity.acc3_percent));
            }
        }
        Btn_acc_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.acc_3 == true){
                    changeBackground(R.drawable.gradient_blue);
                    acc_3_in_use = true;
                    accomplish_01.acc_1_in_use = false;
                    accomplish_02.acc_2_in_use = false;
                    accomplish_04.acc_4_in_use = false;
                    Toast.makeText(accomplish_03.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_03.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
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
