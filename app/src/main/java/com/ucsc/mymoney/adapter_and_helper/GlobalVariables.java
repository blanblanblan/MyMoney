package com.ucsc.mymoney.adapter_and_helper;


import android.util.Log;
import java.lang.String;
import java.util.stream.IntStream;

public class GlobalVariables {
    private static final String TAG = "GlobalVariables";
    private static String mDate = "";
    private static boolean mHasDot = false;
    private static String mInputMoney = "";
    private static String mDescription = "";
    private static int mBookId = 1;                         // 初始状态选择第一本账本
    private static int mBookPos = 0;
    private static int mPos = 0;

    //for the graph



    public static void setmDate(String date)      { mDate = date;     }
    public static void setHasDot(boolean hasDot)  { mHasDot = hasDot; }
    public static void setmInputMoney(String a)   { mInputMoney = a;  }
    public static void setmDescription(String a ) { mDescription = a; }
    public static void setmBookId(int id)         { mBookId = id;     }
    public static void setmBookPos(int pos)       { mBookPos = pos;   }
    public static void setmPos(int pos)           {
        if (mPos < pos)
            mPos = pos + 1;
    }


    public static String getmDate()        { return mDate;        }
    public static boolean getmHasDot()     { return mHasDot;      }
    public static String getmInputMoney()  { return mInputMoney;  }
    public static String getmDescription() { return mDescription; }
    public static int getmBookId()         { return mBookId;      }
    public static int getmBookPos()        { return mBookPos;     }
    public static int getmPos()            { return mPos;     }
}
