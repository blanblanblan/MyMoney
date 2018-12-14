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

public class accomplish_04 extends AppCompatActivity {
    private static final String TAG = "accountFour";
    //the variable that check the current theme
    public static Boolean accountFour_in_use = false;
    private Button Btn_accountFour;
    private WaveLoadingView waveLoadingView_4;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_04);
        waveLoadingView_4 = findViewById(R.id.waveLoadingView_4);
        Btn_accountFour = findViewById(R.id.accountFour_btn);
        view= findViewById(R.id.layout_4);
        // if both the theme is able to unlock and the user is switching to this theme, then change theme
        if (MainActivity.accountOne && accomplish_01.accountOne_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.accountTwo && accomplish_02.accountTwo_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.accountThree && accomplish_03.accountThree_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.accountFour && accomplish_04.accountFour_in_use){
            Log.d(TAG, "ACCOMPLISHMENT 4 What's up ");
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        // check if the user is able to achieve the accomplishment
        if (MainActivity.accountFour == true){
            waveLoadingView_4.setProgressValue(100);
            waveLoadingView_4.setBottomTitle("");
            waveLoadingView_4.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_4.setTopTitle("");
        }else{
            //set the percent value and its position
            if(MainActivity.accountFourPercent < 50){
                waveLoadingView_4.setBottomTitle(String.format("%d%%",MainActivity.accountFourPercent));
                waveLoadingView_4.setProgressValue(MainActivity.accountFourPercent);
                Log.d(TAG, "the ACCOMPLISHMENT 4: "+ MainActivity.accountFourPercent/5000);
                //Log.d(TAG, "the ACCOMPLISHMENT 4: "+ MainActivity.ceshi/500);
                waveLoadingView_4.setCenterTitle("");
                waveLoadingView_4.setTopTitle("");
            }
            else if(MainActivity.accountFourPercent < 80){
                waveLoadingView_4.setProgressValue(MainActivity.accountFourPercent);
                waveLoadingView_4.setBottomTitle("");
                waveLoadingView_4.setCenterTitle(String.format("%d%%",MainActivity.accountFourPercent));
                waveLoadingView_4.setTopTitle("");
            }
            else{
                waveLoadingView_4.setProgressValue(MainActivity.accountFourPercent);
                waveLoadingView_4.setBottomTitle("");
                waveLoadingView_4.setCenterTitle("");
                waveLoadingView_4.setTopTitle(String.format("%d%%",MainActivity.accountFourPercent));
            }
        }
        Btn_accountFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.accountFour == true){
                    changeBackground(R.drawable.gradient_purple);
                    accountFour_in_use = true;
                    //change all other theme status to false in order to avoid conflict
                    accomplish_01.accountOne_in_use = false;
                    accomplish_02.accountTwo_in_use = false;
                    accomplish_03.accountThree_in_use = false;
                    Toast.makeText(accomplish_04.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(accomplish_04.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                    accomplish_04.accountFour_in_use = false;
                }
            }
        });
    }
    public void changeBackground(int drawable){
        view.setBackground(getDrawable(drawable));
    }
}
