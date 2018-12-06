package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_02 extends AppCompatActivity {

    public static Boolean acc_2_in_use = false;
    Button Btn_acc_2;
    WaveLoadingView waveLoadingView_2;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_02);
        waveLoadingView_2 = findViewById(R.id.waveLoadingView_2);
        Btn_acc_2 = findViewById(R.id.acc_2_btn);
        view= findViewById(R.id.layout_2);
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }

        if (MainActivity.acc_2 == true){
            waveLoadingView_2.setProgressValue(100);
            waveLoadingView_2.setBottomTitle("");
            waveLoadingView_2.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_2.setTopTitle("");
        }else{
            if(MainActivity.acc2_percent < 50){
                waveLoadingView_2.setBottomTitle(String.format("%d%%",MainActivity.acc2_percent));
                waveLoadingView_2.setProgressValue(MainActivity.acc2_percent);
                //System.out.print("new = "+ MainActivity.acc2_percent/500);
                waveLoadingView_2.setCenterTitle("");
                waveLoadingView_2.setTopTitle("");
            }
            else if(MainActivity.acc2_percent < 80){
                waveLoadingView_2.setProgressValue(MainActivity.acc2_percent);
                waveLoadingView_2.setBottomTitle("");
                waveLoadingView_2.setCenterTitle(String.format("%d%%",MainActivity.acc2_percent));
                waveLoadingView_2.setTopTitle("");
            }
            else{
                waveLoadingView_2.setProgressValue(MainActivity.acc2_percent);
                waveLoadingView_2.setBottomTitle("");
                waveLoadingView_2.setCenterTitle("");
                waveLoadingView_2.setTopTitle(String.format("%d%%",MainActivity.acc2_percent));
            }
        }
        Btn_acc_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww = "+ MainActivity.ceshi/500);
                if (MainActivity.acc_2 == true){
                    changeBackground(R.drawable.gradient_yellow);
                    acc_2_in_use = true;
                    accomplish_01.acc_1_in_use = false;
                    accomplish_03.acc_3_in_use = false;
                    accomplish_04.acc_4_in_use = false;
                    Toast.makeText(accomplish_02.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(accomplish_02.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
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
