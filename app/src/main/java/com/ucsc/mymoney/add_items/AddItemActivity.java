package com.ucsc.mymoney.add_items;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ucsc.mymoney.adapter_and_helper.GlobalVariables;
import com.ucsc.mymoney.MainActivity;
import com.ucsc.mymoney.R;
import com.ucsc.mymoney.accomplishment.accomplish_01;
import com.ucsc.mymoney.accomplishment.accomplish_02;
import com.ucsc.mymoney.accomplishment.accomplish_03;
import com.ucsc.mymoney.accomplishment.accomplish_04;
import com.ucsc.mymoney.model.BookItem;
import com.ucsc.mymoney.model.IOItem;

import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItemActivity";

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private Button addCostBtn;
    private Button addEarnBtn;
    private Button clearBtn;
    private ImageButton addFinishBtn;
    private ImageButton addDescription;


    private ImageView bannerImage;
    private TextView bannerText;

    private TextView moneyText;
    public double price1 = 500;
    public double price2 = 1000;
    public double price3 = 5000;

    private TextView words;
    private View view;

    private SimpleDateFormat formatItem = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private SimpleDateFormat sumFormat  = new SimpleDateFormat("yyyy-MM", Locale.US);
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        view = findViewById(R.id.activity_add_item);
        addCostBtn = findViewById(R.id.add_cost_button);
        addEarnBtn = findViewById(R.id.add_earn_button);
        addFinishBtn   = findViewById(R.id.add_finish);
        addDescription = findViewById(R.id.add_description);
        clearBtn = findViewById(R.id.clear);
        words = findViewById(R.id.anime_words);
        // set the color
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/chinese_character.ttf");
        clearBtn.setTypeface(typeface);
        words.setTypeface(typeface);
        // the listeners
        addCostBtn.setOnClickListener(new ButtonListener());
        addEarnBtn.setOnClickListener(new ButtonListener());
        addFinishBtn.setOnClickListener(new ButtonListener());
        addDescription.setOnClickListener(new ButtonListener());
        clearBtn.setOnClickListener(new ButtonListener());
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

        bannerText = findViewById(R.id.chosen_title);
        bannerImage = findViewById(R.id.chosen_image);

        moneyText = findViewById(R.id.input_money_text);
        // set the value to 0
        moneyText.setText("0.00");

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.item_fragment, new CostFragment());
        transaction.commit();

    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            transaction = manager.beginTransaction();

            switch (view.getId()) {
                case R.id.add_cost_button:
                    addCostBtn.setTextColor(0xffff8c00); // set Expense color
                    addEarnBtn.setTextColor(0xff908070); // set Income color
                    transaction.replace(R.id.item_fragment, new CostFragment());
                    Log.d(TAG, "onClick: add_cost_button");

                    break;
                case R.id.add_earn_button:
                    addEarnBtn.setTextColor(0xffff8c00); // set Income color
                    addCostBtn.setTextColor(0xff908070); // set Expense color
                    transaction.replace(R.id.item_fragment, new EarnFragment());
                    Log.d(TAG, "onClick: add_earn_button");

                    break;
                case R.id.add_finish:
                    String moneyString =  moneyText.getText().toString();
                    if (moneyString.equals("0.00") || GlobalVariables.getmInputMoney().equals(""))
                        Toast.makeText(getApplicationContext(),"Amount not entered",Toast.LENGTH_SHORT).show();
                    else {
                        putItemInData(Double.parseDouble(moneyText.getText().toString()));
                        calculatorClear();
                        BookItem tmp = DataSupport.find(BookItem.class, GlobalVariables.getmBookId());
                        //check and update the accomplishment condition
                        if(tmp.getSumAll() >= 5000){
                            Log.i(TAG, "ACCOMPLISHMENT 4 meets");
                            MainActivity.accountOne = MainActivity.accountTwo = MainActivity.accountThree = MainActivity.accountFour = true;
                        }else if (tmp.getSumAll() >= 1000){
                            Log.i(TAG, "ACCOMPLISHMENT 3 meets");
                            MainActivity.accountOne = MainActivity.accountTwo = MainActivity.accountThree = true; MainActivity.accountFour = false;
                        }else if(tmp.getSumAll() >= 500){
                            Log.i(TAG, "ACCOMPLISHMENT 2 meets");
                            MainActivity.accountOne = MainActivity.accountTwo = true; MainActivity.accountThree = MainActivity.accountFour = false;
                        }else if (tmp.getSumAll() >= 100){
                            Log.i(TAG, "ACCOMPLISHMENT 1 meets");
                            MainActivity.accountOne = true; MainActivity.accountTwo = MainActivity.accountThree = MainActivity.accountFour = false;
                        }else{
                            MainActivity.accountOne = MainActivity.accountTwo = MainActivity.accountThree = MainActivity.accountFour = false;
                        }
                        //update the percent value
                        MainActivity.accountTwoPercent = (int) ((tmp.getSumAll()/price1)*100);
                        MainActivity.accountThreePercent = (int) ((tmp.getSumAll()/price2)*100);
                        MainActivity.accountFourPercent = (int) ((tmp.getSumAll()/price3)*100);
                        finish();
                    }
                    break;
                case R.id.clear:
                    calculatorClear();
                    moneyText.setText("0.00");
                    break;
                case R.id.add_description:
                    Intent intent = new Intent(AddItemActivity.this, AddDescription.class);
                    startActivity(intent);
            }

            transaction.commit();
        }
    }

    public void putItemInData(double money) {
        IOItem ioItem = new IOItem();
        BookItem bookItem = DataSupport.find(BookItem.class, GlobalVariables.getmBookId());
        String tagName = (String) bannerText.getTag();
        int tagType = (int) bannerImage.getTag();

        if (tagType < 0) {
            ioItem.setType(ioItem.TYPE_COST);
        } else ioItem.setType(ioItem.TYPE_EARN);

        ioItem.setName(bannerText.getText().toString());
        ioItem.setSrcName(tagName);
        ioItem.setMoney(money);
        // record the time
        ioItem.setTimeStamp(formatItem.format(new Date()));
        ioItem.setDescription(GlobalVariables.getmDescription());
        ioItem.setBookId(GlobalVariables.getmBookId());
        ioItem.save();

        // store the data into current book
        bookItem.getioList().add(ioItem);
        bookItem.setSumAll(bookItem.getSumAll() + money*ioItem.getType());
        bookItem.save();

        calculateMonthlyMoney(bookItem, ioItem.getType(), ioItem);

        // clear the description
        GlobalVariables.setmDescription("");
    }

    // calculate the monthly money
    public void calculateMonthlyMoney(BookItem bookItem, int money_type, IOItem ioItem) {
        String sDate = sumFormat.format(new Date());

        // calculate the type
        if (bookItem.getDate().equals(ioItem.getTimeStamp().substring(0, 7))) {
            if (money_type == 1) {
                bookItem.setSumMonthlyEarn(bookItem.getSumMonthlyEarn() + ioItem.getMoney());
            } else {
                bookItem.setSumMonthlyCost(bookItem.getSumMonthlyCost() + ioItem.getMoney());
            }
        } else {
            if (money_type == 1) {
                bookItem.setSumMonthlyEarn(ioItem.getMoney());
                bookItem.setSumMonthlyCost(0.0);
            } else {
                bookItem.setSumMonthlyCost(ioItem.getMoney());
                bookItem.setSumMonthlyEarn(0.0);
            }
            bookItem.setDate(sDate);
        }

        bookItem.save();
    }

    public void calculatorNumOnclick(View v) {
        Button view = (Button) v;
        String digit = view.getText().toString();
        String money = GlobalVariables.getmInputMoney();
        if (GlobalVariables.getmHasDot() && GlobalVariables.getmInputMoney().length()>2) {
            String dot = money.substring(money.length() - 3, money.length() - 2);
            Log.d(TAG, "calculatorNumOnclick: " + dot);
            if (dot.equals(".")) {
                Toast.makeText(getApplicationContext(), "Two decimal places max", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        GlobalVariables.setmInputMoney(money+digit);
        moneyText.setText(decimalFormat.format(Double.valueOf(GlobalVariables.getmInputMoney())));
    }

    // clear
    public void calculatorClear() {
        GlobalVariables.setmInputMoney("");
        GlobalVariables.setHasDot(false);
    }

    public void calculatorPushDot(View view) {
        if (GlobalVariables.getmHasDot()) {
            Toast.makeText(getApplicationContext(), "Decimal dot already clicked", Toast.LENGTH_SHORT).show();
        } else {
            GlobalVariables.setmInputMoney(GlobalVariables.getmInputMoney()+".");
            GlobalVariables.setHasDot(true);
        }
    }
}
