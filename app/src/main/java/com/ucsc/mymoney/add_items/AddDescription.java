package com.ucsc.mymoney.add_items;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ucsc.mymoney.adapter_and_helper.GlobalVariables;
import com.ucsc.mymoney.MainActivity;
import com.ucsc.mymoney.R;
import com.ucsc.mymoney.accomplishment.accomplish_01;
import com.ucsc.mymoney.accomplishment.accomplish_02;
import com.ucsc.mymoney.accomplishment.accomplish_03;
import com.ucsc.mymoney.accomplishment.accomplish_04;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.markushi.ui.CircleButton;

public class AddDescription extends AppCompatActivity {
    private static final String TAG = "AddDescription";
    private EditText inputTxt;
    private TextView countTxt;
    private TextView dateTxt;
    private CircleButton doneBtn;
    private View view;

    private SimpleDateFormat formatItem = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descrpition);

        inputTxt = findViewById(R.id.page3_edit);
        countTxt = findViewById(R.id.page3_count);
        dateTxt = findViewById(R.id.page3_date);
        doneBtn = findViewById(R.id.page3_done);
        view = findViewById(R.id.activity_add_descripition);
        //check if the theme has been applied first
        if (MainActivity.accountOne && accomplish_01.accountOne_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 1 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.accountTwo && accomplish_02.accountTwo_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 2 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.accountThree && accomplish_03.accountThree_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 3 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.accountFour && accomplish_04.accountFour_in_use){
            Log.i(TAG, "ACCOMPLISHMENT 4 Theme Success");
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }

        // display the date
        dateTxt.setText(formatItem.format(new Date()));

        inputTxt.setFocusable(true);

        inputTxt.setText(GlobalVariables.getmDescription());
        inputTxt.setSelection(inputTxt.getText().length());
        countTxt.setText(String.valueOf(inputTxt.getText().length()) +"/30");

        // the real-time setting
        inputTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                countTxt.setText(String.valueOf(s.length())+"/30");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.setmDescription(inputTxt.getText().toString());
                finish();
            }
        });
    }
}
