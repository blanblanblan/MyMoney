package com.ucsc.mymoney.accomplishment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ucsc.mymoney.MainActivity;
import com.ucsc.mymoney.R;

import me.itangqi.waveloadingview.WaveLoadingView;

public class accomplish_03 extends AppCompatActivity {
    private static final String TAG = "accountThree";
    //the variable that check the current theme
    public static Boolean accountThree_in_use = false;
    private Button Btn_accountThree;
    private WaveLoadingView waveLoadingView_3;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_03);
        waveLoadingView_3 = findViewById(R.id.waveLoadingView_3);
        Btn_accountThree = findViewById(R.id.accountThree_btn);
        view= findViewById(R.id.layout_3);
        // if both the theme is able to unlock and the user is switching to this theme, then change theme
        if (MainActivity.accountOne && accomplish_01.accountOne_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.accountTwo && accomplish_02.accountTwo_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.accountThree && accomplish_03.accountThree_in_use){
            Log.d(TAG, "ACCOMPLISHMENT 3 It's me");
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.accountFour && accomplish_04.accountFour_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        // check if the user is able to achieve the accomplishment
        if (MainActivity.accountThree == true){
            waveLoadingView_3.setProgressValue(100);
            waveLoadingView_3.setBottomTitle("");
            waveLoadingView_3.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_3.setTopTitle("");
        }else{
            //set the percent value and its position
            if(MainActivity.accountThreePercent < 50){
                waveLoadingView_3.setBottomTitle(String.format("%d%%",MainActivity.accountThreePercent));
                waveLoadingView_3.setProgressValue(MainActivity.accountThreePercent);
                //Log.d(TAG, "the ACCOMPLISHMENT 3: "+ MainActivity.ceshi/1000);
                Log.d(TAG, "the ACCOMPLISHMENT 3: "+ MainActivity.accountThreePercent/1000);
                waveLoadingView_3.setCenterTitle("");
                waveLoadingView_3.setTopTitle("");
            }
            else if(MainActivity.accountThreePercent < 80){
                waveLoadingView_3.setProgressValue(MainActivity.accountThreePercent);
                waveLoadingView_3.setBottomTitle("");
                waveLoadingView_3.setCenterTitle(String.format("%d%%",MainActivity.accountThreePercent));
                waveLoadingView_3.setTopTitle("");
            }
            else{
                waveLoadingView_3.setProgressValue(MainActivity.accountThreePercent);
                waveLoadingView_3.setBottomTitle("");
                waveLoadingView_3.setCenterTitle("");
                waveLoadingView_3.setTopTitle(String.format("%d%%",MainActivity.accountThreePercent));
            }
        }
        Btn_accountThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.accountThree == true){
                    //Toast.makeText(accomplish_03.this, "HEYYYYYYY ", Toast.LENGTH_SHORT).show();
                    changeBackground(R.drawable.gradient_blue);
                    accountThree_in_use = true;
                    //change all other theme status to false in order to avoid conflict
                    accomplish_01.accountOne_in_use = false;
                    accomplish_02.accountTwo_in_use = false;
                    accomplish_04.accountFour_in_use = false;
                    Toast.makeText(accomplish_03.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_03.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                    accomplish_03.accountThree_in_use = false;
                    accomplish_04.accountFour_in_use = false;
                }
            }
        });
    }
    public void changeBackground(int drawable){
        view.setBackground(getDrawable(drawable));
    }
}
