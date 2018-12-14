package com.ucsc.mymoney.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class BookItem extends DataSupport {
    private int id;
    private String name;
    private double sumAll = 0.0;
    private double sumMonthlyCost = 0.0;
    private double sumMonthlyEarn = 0.0;
    private String date;
    private List<IOItem> ioList = new ArrayList<IOItem>();      // a book with mutiple income

    public BookItem() {}
    public BookItem(String name) {
        this.name = name;
    }

    public int getId()                          { return id; }
    public String getName()                     { return name; }
    public List<IOItem> getioList()             { return ioList; }
    public double getSumAll()                   { return sumAll; }
    public double getSumMonthlyCost()           { return sumMonthlyCost; }
    public double getSumMonthlyEarn()           { return sumMonthlyEarn; }
    public String getDate()                     { return date; }

    public void setId(int id)                   { this.id = id; }
    public void setName(String name)            { this.name = name; }
    public void setioList(List<IOItem> ioList)  { this.ioList = ioList; }
    public void setSumAll(double all)           { this.sumAll = all; }
    public void setSumMonthlyCost(double cost)  { this.sumMonthlyCost = cost; }
    public void setSumMonthlyEarn(double earn)  { this.sumMonthlyEarn = earn; }
    public void setDate(String date)            { this.date = date; }

    public boolean isThereABook(int id) {
        if (DataSupport.find(BookItem.class, id) == null)
            return false;
        return true;
    }

    public void saveBook(BookItem bookItem, int id, String name) {
        bookItem.setId(id);
        bookItem.setName(name);
        bookItem.save();
    }
}
