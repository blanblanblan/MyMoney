package com.ucsc.mymoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccomplishmentViewPage extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplishment_view_page);
        ListView lv = (ListView) findViewById(R.id.listViewAccomplish);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.accomplishmentlist, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(AccomplishmentViewPage.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                if(position == 0){
                    Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_01.class);
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_02.class);
                    startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_03.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_04.class);
                    startActivity(intent);
                }
            }
        });
        view= findViewById(R.id.layout_accomplishment_view_page);
        Thread theThread = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()){
                    try {
                        Thread.sleep(100);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
                                    view.setBackground(getDrawable(R.drawable.gradient_green));
                                }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
                                    view.setBackground(getDrawable(R.drawable.gradient_yellow));
                                }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
                                    view.setBackground(getDrawable(R.drawable.gradient_blue));
                                }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
                                    view.setBackground(getDrawable(R.drawable.gradient_purple));
                                }
                            }
                        });
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        theThread.start();
/*
        view= findViewById(R.id.layout_accomplishment_view_page);
        if (MainActivity.acc_1 && accomplish_01.acc_1_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_green));
        }else if (MainActivity.acc_2 && accomplish_02.acc_2_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_yellow));
        }else if (MainActivity.acc_3 && accomplish_03.acc_3_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_blue));
        }else if (MainActivity.acc_4 && accomplish_04.acc_4_in_use){
            view.setBackground(getDrawable(R.drawable.gradient_purple));
        }
        */

    }

    private void generateListContent() {
        for(int i = 0; i < 4; i++) {
            data.add("Accomplish " + (i+1));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = convertView.findViewById(R.id.profile_image);
                if(position == 0){
                    viewHolder.thumbnail.setImageResource(R.drawable.number_1);
                }else if(position == 1){
                    viewHolder.thumbnail.setImageResource(R.drawable.number_2);
                }else if(position == 2){
                    viewHolder.thumbnail.setImageResource(R.drawable.number_3);
                }else if(position == 3){
                    viewHolder.thumbnail.setImageResource(R.drawable.number_4);
                }
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.btn_list_item);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                    if(position == 0){
                        Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_01.class);
                        startActivity(intent);
                    }else if(position == 1){
                        Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_02.class);
                        startActivity(intent);
                    }else if(position == 2){
                        Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_03.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(AccomplishmentViewPage.this, accomplish_04.class);
                        startActivity(intent);
                    }
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {
        CircleImageView thumbnail;
        //ImageView thumbnail;
        TextView title;
        Button button;
    }
}
