package com.ucsc.mymoney.add_items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
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


public class EarnFragment extends Fragment {

    private String[] titles = {"Regular", "Salary", "Cash", "PartTime", "Prize", "Pocket", "Insurance", "Invests", "Estate", "Living",
            "Bonus", "Profit", "Business"};
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
    // current page
    private int curIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getBannerId();

        View view = inflater.inflate(R.layout.earn_fragment, container, false);

        mPager = (ViewPager) view.findViewById(R.id.viewpager_2);
        extensiblePageIndicator = (ExtensiblePageIndicator) view.findViewById(R.id.ll_dot_2);
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
            mDatas.add(new IOItem("type_big_n" + i, titles[i-1]));
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
        itemImage.setTag(1);
        itemTitle.setTag(tmpItem.getSrcName());

        // change the color of the banner once different icon clicked
        pb.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getSwatches().get(0);
                if (swatch != null) {
                    itemLayout.setBackgroundColor(swatch.getRgb());
                } else {

                }
            }
        });

    }
}