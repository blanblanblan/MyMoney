package com.ucsc.mymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class more_func_in_this_app extends AppCompatActivity {
    public ImageButton Btn_Acc,Btn_Gra,Btn_Wis,Btn_Cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_func_in_this_app);
        inti();

        Btn_Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(more_func_in_this_app.this, AccomplishmentViewPage.class);
                startActivity(intent);
            }
        });
    }

    public void inti(){
        Btn_Acc = findViewById(R.id.imageB_Acc);
        Btn_Gra = findViewById(R.id.imageB_Gra);
        Btn_Wis = findViewById(R.id.imageB_Wis);
        Btn_Cal = findViewById(R.id.imageB_Cal);
    }
}
