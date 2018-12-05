package com.ucsc.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_04 extends AppCompatActivity {

    Button Btn_acc_4;
    WaveLoadingView waveLoadingView_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_04);
        waveLoadingView_4 = findViewById(R.id.waveLoadingView_4);
        Btn_acc_4 = findViewById(R.id.acc_4_btn);

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
                    Toast.makeText(accomplish_04.this, "You have unlock the theme successful! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_04.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
