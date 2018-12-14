package com.ucsc.mymoney.model;

import android.content.res.Resources;

import com.ucsc.mymoney.MainActivity;

import org.litepal.crud.DataSupport;


public class IOItem extends DataSupport {
    public final int TYPE_COST = -1;
    public final int TYPE_EARN =  1;

    private int id;
    private int type;
    private int bookId;
    private double money;
    private String name;
    private String description;
    private String timeStamp;
    private String srcName;                 // source of the item name

    public IOItem () {}

    public IOItem(String srcName, String name) {
        this.srcName = srcName;
        this.name = name;
    }

    // create var
    public IOItem(String srcName, int type, double money, String name) {
        this.srcName = srcName;
        this.money = money;
        this.type = type;
        this.name = name;
    }

    // creat var
    public IOItem(String srcName, int type, double money, String name, String description) {
        this.money = money;
        this.type = type;
        this.srcName = srcName;
        this.name = name;
        this.description = description;
    }

    public double getMoney()                       { return money; }
    public int getType()                           { return type; }
    public String getName()                        { return name; }
    public String getDescription()                 { return description; }
    public String getTimeStamp()                   { return timeStamp; }
    public int getBookId()                         { return bookId; }
    public String getSrcName()                     { return srcName; }
    public int getId()                             { return id; }

    // data type
    public void setMoney(double money)             { this.money = money; }
    public void setType(int type)                  { this.type = type; }
    public void setName(String name)               { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTimeStamp(String timeStamp)     { this.timeStamp = timeStamp; }
    public void setBookId(int mId)                 { this.bookId = mId; }
    public void setSrcName(String srcName)         { this.srcName = srcName; }

    // return the id of the source
    public int getSrcId() {
        Resources resources = MainActivity.resources;
        return resources.getIdentifier(srcName, "drawable", MainActivity.myPackageName);
    }
}
