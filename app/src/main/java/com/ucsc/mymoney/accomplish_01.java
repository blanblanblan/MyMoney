package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_01 extends AppCompatActivity {

    Button Btn_acc_1;
    WaveLoadingView waveLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_01);
        waveLoadingView = findViewById(R.id.waveLoadingView_1);

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
                    Toast.makeText(accomplish_01.this, "You have unlock the theme successful! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_01.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
