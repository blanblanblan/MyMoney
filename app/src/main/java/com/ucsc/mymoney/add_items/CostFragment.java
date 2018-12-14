package com.ucsc.mymoney.add_items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;
import com.ucsc.mymoney.adapter_and_helper.GridRecyclerAdapter;
import com.ucsc.mymoney.adapter_and_helper.MyGridLayoutManager;
import com.ucsc.mymoney.R;
import com.ucsc.mymoney.adapter_and_helper.ViewPagerAdapter;
import com.ucsc.mymoney.model.IOItem;

import java.util.ArrayList;
import java.util.List;



public class CostFragment extends Fragment {

    private String[] titles = {"Regular", "Food", "Snack", "Transport", "Recharge", "Shopping", "ENT.", "Housing", "Drink", "Online",
            "Shoes", "Skincare", "Makeup", "Movie", "Transfer", "Waste", "Gym", "Medical", "Travel", "Education", "Smoke", "Alcohol", "Digital", "Donation",
            "Family", "Pet", "Clothes", "Daily", "Fruit", "Baby", "CreditCard", "Finance", "Job", "Furniture", "COMM."};
    private ViewPager mPager;
    private List<View> mPagerList;
    private List<IOItem> mDatas;
    private LayoutInflater inflater;
    private ImageView itemImage;
    private TextView itemTitle;
    private RelativeLayout itemLayout;
    private ExtensiblePageIndicator extensiblePageIndicator;
    private int pageCount;
    private int pageSize = 18;
    private int curIndex = 0;

    private static final String TAG = "CostFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        getBannerId();
        View view = inflater.inflate(R.layout.cost_fragment, container, false);
        mPager = (ViewPager) view.findViewById(R.id.viewpager_1);
        extensiblePageIndicator = (ExtensiblePageIndicator) view.findViewById(R.id.ll_dot_1);

        int height = mPager.getHeight();
        int width = mPager.getWidth();

        // initialization
        initDatas();

        // initialize the banner
        changeBanner(mDatas.get(0));

        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_recycler_grid, mPager ,false);
            MyGridLayoutManager layoutManager = new MyGridLayoutManager(getContext(), 6);
            recyclerView.setLayoutManager(layoutManager);
            GridRecyclerAdapter adaper = new GridRecyclerAdapter(mDatas, i, pageSize);
            recyclerView.setAdapter(adaper);

            mPagerList.add(recyclerView);

            adaper.setOnItemClickListener(new GridRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    changeBanner(mDatas.get(position));
                }
            });
        }
        // set the adapter
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        extensiblePageIndicator.initViewPager(mPager);

        return view;
    }

    /**
     * initial the data
     */
    private void initDatas() {
        mDatas = new ArrayList<IOItem>();
        for (int i = 1; i <= titles.length; i++) {
            mDatas.add(new IOItem("type_big_" + i, titles[i-1]));
        }
    }

    public void getBannerId() {
        itemImage = getActivity().findViewById(R.id.chosen_image);
        itemTitle = getActivity().findViewById(R.id.chosen_title);
        itemLayout = getActivity().findViewById(R.id.have_chosen);
    }

    // change the banner status
    public void changeBanner(IOItem tmpItem) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), tmpItem.getSrcId());
        Palette.Builder pb = new Palette.Builder(bm);
        pb.maximumColorCount(1);


        itemImage.setImageResource(tmpItem.getSrcId());
        itemTitle.setText(tmpItem.getName());
        itemImage.setTag(-1);
        itemTitle.setTag(tmpItem.getSrcName());

        // change the color of the banner once different icon clicked
        pb.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getSwatches().get(0);
                if (swatch != null) {
                    itemLayout.setBackgroundColor(swatch.getRgb());
                } else {
                    Log.d(TAG, "changeBanner: ");
                }
            }
        });
    }
}