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

public class accomplish_02 extends AppCompatActivity {
    private static final String TAG = "accountTwo";
    //the variable that check the current theme
    public static Boolean accountTwo_in_use = false;
    private Button Btn_accountTwo;
    private WaveLoadingView waveLoadingView_2;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_02);
        waveLoadingView_2 = findViewById(R.id.waveLoadingView_2);
        Btn_accountTwo = findViewById(R.id.accountTwo_btn);
        view= findViewById(R.id.layout_2);
        // if both the theme is able to unlock and the user is switching to this theme, then change theme
        if (MainActivity.accountOne && accomplish_01.accountOne_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.accountTwo && accomplish_02.accountTwo_in_use){
            Log.d(TAG, "ACCOMPLISHMENT 2 HEY ___ ");
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.accountThree && accomplish_03.accountThree_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.accountFour && accomplish_04.accountFour_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        // check if the user is able to achieve the accomplishment
        if (MainActivity.accountTwo == true){
            waveLoadingView_2.setProgressValue(100);
            waveLoadingView_2.setBottomTitle("");
            waveLoadingView_2.setCenterTitle(String.format("%d%%",100));
            waveLoadingView_2.setTopTitle("");
        }else{
            //set the percent value and its position
            if(MainActivity.accountTwoPercent < 50){
                waveLoadingView_2.setBottomTitle(String.format("%d%%",MainActivity.accountTwoPercent));
                waveLoadingView_2.setProgressValue(MainActivity.accountTwoPercent);
                //System.out.print("new = "+ MainActivity.accountTwoPercent/500);
                waveLoadingView_2.setCenterTitle("");
                waveLoadingView_2.setTopTitle("");
            }
            else if(MainActivity.accountTwoPercent < 80){
                waveLoadingView_2.setProgressValue(MainActivity.accountTwoPercent);
                waveLoadingView_2.setBottomTitle("");
                waveLoadingView_2.setCenterTitle(String.format("%d%%",MainActivity.accountTwoPercent));
                waveLoadingView_2.setTopTitle("");
            }
            else{
                waveLoadingView_2.setProgressValue(MainActivity.accountTwoPercent);
                waveLoadingView_2.setBottomTitle("");
                waveLoadingView_2.setCenterTitle("");
                waveLoadingView_2.setTopTitle(String.format("%d%%",MainActivity.accountTwoPercent));
            }
        }
        Btn_accountTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(accomplish_02.this, "I'm here ", Toast.LENGTH_SHORT).show();
                //System.out.println("neww = "+ MainActivity.ceshi/500);
                //Log.d(TAG, "the ACCOMPLISHMENT 2: "+ MainActivity.ceshi/500);
                Log.d(TAG, "the ACCOMPLISHMENT 2: "+ MainActivity.accountTwoPercent/500);
                if (MainActivity.accountTwo == true){
                    changeBackground(R.drawable.gradient_yellow);
                    accountTwo_in_use = true;
                    //change all other theme status to false in order to avoid conflict
                    accomplish_01.accountOne_in_use = false;
                    accomplish_03.accountThree_in_use = false;
                    accomplish_04.accountFour_in_use = false;
                    Toast.makeText(accomplish_02.this, "You have unlock the theme successfully! ", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(accomplish_02.this, "Your balance did not meet the requirement   :(", Toast.LENGTH_SHORT).show();
                    accomplish_02.accountTwo_in_use = false;
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
