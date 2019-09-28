package com.example.estore;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    ///////// Banner slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    ///////// Banner slider


    ///////// Strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    ///////// Strip Ad

    ///////// Horizontal Product Layout
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllButton;
    private RecyclerView horizontalLayoutRecyclerView;
    ///////// Horizontal Product Layout

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Fruits & Vegetables"));
        categoryModelList.add(new CategoryModel("link","Foodgrains, Oil & Masala"));
        categoryModelList.add(new CategoryModel("link","Bakery, Cakes & Dairy"));
        categoryModelList.add(new CategoryModel("link","Beauty & Hygiene"));
        categoryModelList.add(new CategoryModel("link","Cleaning & Household"));
        categoryModelList.add(new CategoryModel("link","Kitchen"));
        categoryModelList.add(new CategoryModel("link","Babycare"));
        categoryModelList.add(new CategoryModel("link","Snacks"));
        categoryModelList.add(new CategoryModel("link","Electronics"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        ///////// Banner slider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.logo1,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.banner2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner3,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.bell,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.estore,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.logo1,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner2,"#077AE4"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });
        ///////// Banner slider

        ///////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.banner2);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
        ///////// Strip Ad

        ///////// Horizontal Product Layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllButton = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalLayoutRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bhutta,"Bhutta","1 kg","Rs.5/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.banana,"Banana","12 pcs","Rs.50/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.papaya,"Papaya","1 kg","Rs.65/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.strawberrie,"Strawberry","1 kg","Rs.125/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.amrood,"Guava","1 kg","Rs.40/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.grapes,"Grapes","1 kg","Rs.50/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.orange,"Orange","1 kg","Rs.60/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.pomegranate,"Pomegranate","1 kg","Rs.100/-"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalLayoutRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalLayoutRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();
        ///////// Horizontal Product Layout

        return view;
    }

    ///////// Banner slider
    private void pageLooper(){
        if (currentPage == sliderModelList.size() - 2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
              bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new  Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }
    private void stopBannerSlideShow(){
        timer.cancel();
    }
    ///////// Banner slider

}
