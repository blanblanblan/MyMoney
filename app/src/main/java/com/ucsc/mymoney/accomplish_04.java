package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_04 extends AppCompatActivity {

    public static Boolean acc_4_in_use = false;
    Button Btn_acc_4;
    WaveLoadingView waveLoadingView_4;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_04);
        waveLoadingView_4 = findViewById(R.id.waveLoadingView_4);
        Btn_acc_4 = findViewById(R.id.acc_4_btn);
        view= findViewById(R.id.layout_4);
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }

        if (MainActivity.acc_4 == true){
            waveLoadingView_4.setProgressValue(100);
            waveLoadingView_4.setBottomTitle("");
            waveLoadingView_4.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_4.setTopTitle("");
        }else{
            if(MainActivity.acc4_percent < 50){
                waveLoadingView_4.setBottomTitle(String.format("%d%%",MainActivity.acc4_percent));
                waveLoadingView_4.setProgressValue(MainActivity.acc4_percent);
                //System.out.print("new = "+ MainActivity.acc2_percent/500);
                waveLoadingView_4.setCenterTitle("");
                waveLoadingView_4.setTopTitle("");
            }
            else if(MainActivity.acc4_percent < 80){
                waveLoadingView_4.setProgressValue(MainActivity.acc4_percent);
                waveLoadingView_4.setBottomTitle("");
                waveLoadingView_4.setCenterTitle(String.format("%d%%",MainActivity.acc4_percent));
                waveLoadingView_4.setTopTitle("");
            }
            else{
                waveLoadingView_4.setProgressValue(MainActivity.acc4_percent);
                waveLoadingView_4.setBottomTitle("");
                waveLoadingView_4.setCenterTitle("");
                waveLoadingView_4.setTopTitle(String.format("%d%%",MainActivity.acc4_percent));
            }
        }
        Btn_acc_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.acc_4 == true){
                    changeBackground(R.drawable.gradient_purple);
                    acc_4_in_use = true;
                    accomplish_01.acc_1_in_use = false;
                    accomplish_02.acc_2_in_use = false;
                    accomplish_03.acc_3_in_use = false;
                    Toast.makeText(accomplish_04.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_04.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                    accomplish_04.acc_4_in_use = false;
                }
            }
        });
    }
    public void changeBackground(int drawable){
        view.setBackground(getDrawable(drawable));
    }
}
