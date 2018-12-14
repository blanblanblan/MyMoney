package com.ucsc.mymoney;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ucsc.mymoney.adapter_and_helper.GlobalVariables;
import com.ucsc.mymoney.add_items.AddItemActivity;
import com.ucsc.mymoney.model.BookItem;
import com.ucsc.mymoney.model.BookItemAdapter;
import com.ucsc.mymoney.model.IOItem;
import com.ucsc.mymoney.model.IOItemAdapter;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {
    private List<IOItem> ioList = new ArrayList<>();
    private List<BookItem> bookList = new ArrayList<>();

    public static String myPackageName;
    public static Resources resources;
    public static Boolean accountOne = false;
    public static Boolean accountTwo = false;
    public static Boolean accountThree = false;
    public static Boolean accountFour = false;
    public static int accountOnePercent = 1;
    public static int accountTwoPercent = 1;
    public static int accountThreePercent = 1;
    public static int accountFourPercent = 1;
    //public static int temp2 = 1;
    public double price1 = 500;
    public double price2 = 1000;
    public double price3 = 5000;
    //public static int ceshi = 0;

    // parameter for drawer
    private DrawerLayout    drawerLayout;
    private LinearLayout    bookLinearLayout;
    private RecyclerView    itemRecyclerViewB;
    private BookItemAdapter bookAdapter;
    private ImageButton     addBook;
    private ImageView       myBanner;
    public  SparkButton     morefuncs;

    private RecyclerView    itemRecyclerView;
    private IOItemAdapter   ioAdapter;
    private Button          myBtn;
    private CircleButton    addBtn;
    private ImageView       topImg;
    private TextView        monthlyCost, monthlyEarn;

    public static final int pictureForMain = 1;
    public static final int pictureForDrawer = 2;
    public DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private static final String TAG = "MainActivity";
    private SimpleDateFormat sumFormat  = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
    String sDate = sumFormat.format(new Date());

    // ioitem recyclerView swip acction
    private ItemTouchHelper.Callback iCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            // get the position
            final int position = viewHolder.getAdapterPosition();

            if(direction != ItemTouchHelper.RIGHT){
                //we are going to do nothing here
            }
            else if (direction == ItemTouchHelper.RIGHT) {
                // Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Want to delete?");

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ioAdapter.removeItem(position);
                        // refresh
                        onResume();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinearLayout sonView = (LinearLayout) viewHolder.itemView;
                        TextView grandsonTextView = (TextView) sonView.findViewById(R.id.iotem_date);

                        if (sonView.findViewById(R.id.date_bar).getVisibility() != View.VISIBLE){
                            GlobalVariables.setmDate(ioAdapter.getItemDate(position));
                        }
                        else {
                            GlobalVariables.setmDate("");
                        }
                        ioAdapter.notifyItemChanged(position);
                    }
                }).show();  // show dialog
            }
            else{
                //we are going to do nothing here
            }
        }
    };

    private ItemTouchHelper TouchedHelper = new ItemTouchHelper(iCallback);


    // bookitem recyclerview
    private ItemTouchHelper.Callback bookCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            // if you dont want to drag up and down dragFlags = 0
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            // if you want to swip left and right swipeFlags = 0
            int swipeFlags = ItemTouchHelper.RIGHT;
            //final movemnet（flags）must be created from makeMovementFlags()
            int flags = makeMovementFlags(dragFlags, swipeFlags);
            return flags;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }


        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            // get the position
            final int position = viewHolder.getAdapterPosition();

            if (direction != ItemTouchHelper.RIGHT){
                //we do nothing
            }
            else {
                // the dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Want to delete?");

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bookAdapter.removeItem(position);
                        // refresh
                        onResume();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bookAdapter.notifyDataSetChanged();
                    }
                }).show();  // show the dialog
            }
        }
    };

    private ItemTouchHelper bookTouchHelper = new ItemTouchHelper(bookCallback);
    //=============================================================================================================//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // litepal
        Connector.getDatabase();

        // 获得包名和资源，方便后面的程序使用
        myPackageName = getApplicationContext().getPackageName();
        resources = getResources();

        myBtn = findViewById(R.id.show_money_button);
        addBtn = findViewById(R.id.add_button);
        itemRecyclerView = findViewById(R.id.in_and_out_items);
        topImg = findViewById(R.id.header_img);
        monthlyCost = findViewById(R.id.monthly_cost_money);
        monthlyEarn = findViewById(R.id.monthly_earn_money);
        // drawer
        drawerLayout = findViewById(R.id.drawer_of_books);
        itemRecyclerViewB = findViewById(R.id.book_list);
        addBook = findViewById(R.id.add_book_button);
        bookLinearLayout = findViewById(R.id.left_drawer) ;
        myBanner = findViewById(R.id.drawer_banner);
        morefuncs = findViewById(R.id.spark_button);

        // the listeners
        myBtn.setOnClickListener(new ButtonListener());
        addBtn.setOnClickListener(new ButtonListener());
        addBook.setOnClickListener(new ButtonListener());

        morefuncs.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                Intent intent = new Intent(MainActivity.this, more_func_in_this_app.class);
                startActivity(intent);
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });


        //long press to change the header image
        topImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectPictureFromGallery(1);
                return false;
            }
        });

        myBanner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectPictureFromGallery(2);
                return false;
            }
        });

        setImageForHeaderAndBanner();
    }


    @Override
    protected void onResume() {
        super.onResume();

        initbookList(this);
        initioList(this);

        myBtn.setText("SHOW BALANCE");

        BookItem tmp = DataSupport.find(BookItem.class, bookList.get(GlobalVariables.getmBookPos()).getId());
        monthlyCost.setText(decimalFormat.format(tmp.getSumMonthlyCost()));
        monthlyEarn.setText(decimalFormat.format(tmp.getSumMonthlyEarn()));
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);  // ACTION_MAIN
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);        // CATEGORY_HOME  the initial activity

        startActivity(intent);
    }


    // Buttons Listeners
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                // switch to AddItemActivity if button is clicked
                case R.id.add_button:
                    Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                    startActivity(intent);
                    break;
                case R.id.show_money_button:
                    if (myBtn.getText() == "SHOW BALANCE") {
                        BookItem tmp = DataSupport.find(BookItem.class, GlobalVariables.getmBookId());
                        if(tmp.getSumAll() >= 5000){
                            accountOne = accountTwo = accountThree = accountFour = true;
                        }else if (tmp.getSumAll() >= 1000){
                            accountOne = accountTwo = accountThree = true; accountFour = false;
                        }else if(tmp.getSumAll() >= 500){
                            accountOne = accountTwo = true; accountThree = accountFour = false;
                        }else if (tmp.getSumAll() >= 100){
                            accountOne = true; accountTwo = accountThree = accountFour = false;
                        }else{
                            accountOne = accountTwo = accountThree = accountFour = false;
                        }
                        //ceshi = (int)tmp.getSumAll();
                        //accountTwoPercent =Math.di;
                        //int temp3 = temp2/500;
                        Log.i(TAG, "ACCOMPLISHMENT 1: "+ accountOnePercent);
                        accountTwoPercent = (int) ((tmp.getSumAll()/price1)*100);
                        Log.i(TAG, "ACCOMPLISHMENT 2: "+ accountTwoPercent);
                        accountThreePercent = (int) ((tmp.getSumAll()/price2)*100);
                        Log.i(TAG, "ACCOMPLISHMENT 3: "+ accountThreePercent);
                        accountFourPercent = (int) ((tmp.getSumAll()/price3)*100);
                        Log.i(TAG, "ACCOMPLISHMENT 4: "+ accountFourPercent);

                        String sumString = decimalFormat.format( tmp.getSumAll() );
                        myBtn.setText(sumString);
                    } else myBtn.setText("SHOW BALANCE");
                    break;
                case R.id.add_book_button:
                    final BookItem bookItem = new BookItem();
                    final EditText book_title = new EditText(MainActivity.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Enter Account Name");

                    builder.setView(book_title);

                    builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!book_title.getText().toString().isEmpty()) {
                                bookItem.setName(book_title.getText().toString());
                                bookItem.setSumAll(0.0);
                                bookItem.setSumMonthlyCost(0.0);
                                bookItem.setSumMonthlyEarn(0.0);
                                bookItem.setDate(sDate);
                                bookItem.save();
                                onResume();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "No Name Entered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();  // show dialog
                    break;

                default:
                    break;
            }
        }
    }


    // initial the values
    public void initioList(final Context context) {

        ioList =  DataSupport.where("bookId = ?", String.valueOf(GlobalVariables.getmBookId())).find(IOItem.class);
        setitemRecyclerView(context);
    }


    public void initbookList(final Context context) {
        bookList = DataSupport.findAll(BookItem.class);

        if (bookList.isEmpty()) {
            BookItem bookItem = new BookItem();

            bookItem.saveBook(bookItem, 1, "Default Account");
            bookItem.setSumAll(0.0);
            bookItem.setSumMonthlyCost(0.0);
            bookItem.setSumMonthlyEarn(0.0);
            bookItem.setDate(sDate);
            bookItem.save();

            bookList = DataSupport.findAll(BookItem.class);
        }

        setitemRecyclerViewB(context);
    }

    public void selectPictureFromGallery(int id) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        // set the type to image
        intent.setType("image/*");
        // open the image selection
        if (id == 1)
            startActivityForResult(intent, pictureForMain);
        else
            startActivityForResult(intent, pictureForDrawer);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case pictureForMain:
                if (data == null) return;
                // user selected photo from our database will come back as a Uri
                Uri uri1 = data.getData();
                this.topImg.setImageURI(uri1);
                saveImageUri(pictureForMain, uri1);

                // get permission for uri access
                int takeFlags = data.getFlags();
                takeFlags &=(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                getContentResolver().takePersistableUriPermission(uri1, takeFlags);
                break;

            case pictureForDrawer:
                if (data == null) return;
                // user selected photo from our database will come back as a Uri
                Uri uri2 = data.getData();
                this.myBanner.setImageURI(uri2);
                saveImageUri(pictureForDrawer, uri2);

                // get permission for uri access
                int takeFlags2 = data.getFlags();
                takeFlags2 &=(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                getContentResolver().takePersistableUriPermission(uri2, takeFlags2);
                break;
        }
    }

    // use SharedPreferences to save uri
    public void saveImageUri(int id, Uri uri) {
        SharedPreferences pref = getSharedPreferences("image"+id, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString("uri", uri.toString());
        prefEditor.apply();
    }

    public void setImageForHeaderAndBanner() {
        SharedPreferences pref1 = getSharedPreferences("image"+pictureForMain, MODE_PRIVATE);
        String imageUri1 = pref1.getString("uri", "");

        if (!imageUri1.equals("")) {
            Uri contentUri = Uri.parse(imageUri1);
            this.topImg.setImageURI(contentUri);
        }

        SharedPreferences pref2 = getSharedPreferences("image"+pictureForDrawer, MODE_PRIVATE);
        String imageUri2 = pref2.getString("uri", "");

        if (!imageUri2.equals("")) {
            Uri contentUri = Uri.parse(imageUri2);
            this.myBanner.setImageURI(contentUri);
        }
    }

    public void setitemRecyclerView(Context context) {
        // save recview date
        GlobalVariables.setmDate("");

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setStackFromEnd(true);    // list from bottom up and end to front
        layoutManager.setReverseLayout(true);   // revers the list

        itemRecyclerView.setLayoutManager(layoutManager);
        ioAdapter = new IOItemAdapter(ioList);
        itemRecyclerView.setAdapter(ioAdapter);
        TouchedHelper.attachToRecyclerView(itemRecyclerView);
    }

    public void setitemRecyclerViewB(Context context) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        itemRecyclerViewB.setLayoutManager(layoutManager);
        itemRecyclerViewB.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        bookAdapter = new BookItemAdapter(bookList);

        bookAdapter.setOnItemClickListener(new BookItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 选中之后的操作
                GlobalVariables.setmBookPos(position);
                GlobalVariables.setmBookId(bookList.get(position).getId());
                onResume();
                drawerLayout.closeDrawer(bookLinearLayout);
            }
        });

        itemRecyclerViewB.setAdapter(bookAdapter);
        bookTouchHelper.attachToRecyclerView(itemRecyclerViewB);
    }
}
