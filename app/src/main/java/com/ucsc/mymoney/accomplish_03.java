package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_03 extends AppCompatActivity {

    Button Btn_acc_3;
    WaveLoadingView waveLoadingView_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_03);
        waveLoadingView_3 = findViewById(R.id.waveLoadingView_3);
        Btn_acc_3 = findViewById(R.id.acc_3_btn);

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
                    Toast.makeText(accomplish_03.this, "You have unlock the theme successful! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_03.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
