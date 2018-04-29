package com.example.android.appointer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.example.android.appointer.Fragments.NavigationDrawerFragment;
import com.example.android.appointer.Service.ServiceFactory;
import com.example.android.appointer.Service.SharedPreferencesService;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.android.appointer.Common.SharedPreferenceConstants.LOGIN_MODE;

public class MainActivity extends AppCompatActivity {
    private RecyclerView servicesRecyclerView;
    private ServicesRecyclerViewAdapter servicesRecyclerViewAdapter;
    public static Context context;
    private ViewPager bannnerViewPager;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationDrawerFragment drawerFragment;
    private Timer bannnerTimerTask;
    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private int[] mbanners={R.drawable.service_image,R.drawable.service_image,R.drawable.service_image};
    private GridView simpleGrid;
    private int logos[] = {R.drawable.plumer, R.drawable.electrician, R.drawable.painter,
            R.drawable.grocery,R.drawable.developer, R.drawable.tutors};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        String[] categoryTitles ={getString(R.string.plumber),getString(R.string.electrician),getString(R.string.painter),
                getString(R.string.grocery_shops),getString(R.string.developer),getString(R.string.tutor),};
        CustomAdapterForGridView customAdapterForGridView = new CustomAdapterForGridView(getApplicationContext(), logos,categoryTitles);
        simpleGrid.setAdapter(customAdapterForGridView);
//        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // set an Intent to Another Activity
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("image", logos[position]); // put image data in Intent
//                startActivity(intent); // start Intent
//            }
//        });
        bannnerViewPager = findViewById(R.id.bannerViewPager);
        bannerViewPagerAdapter = new BannerViewPagerAdapter(context, mbanners);
        bannnerViewPager.setAdapter(bannerViewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(bannnerViewPager, true);

        bannnerTimerTask = new Timer();
        bannnerTimerTask.scheduleAtFixedRate(new MyTimerTask(),5000,4000);

        servicesRecyclerView = (RecyclerView) findViewById(R.id.servicesRecyclerView);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        servicesRecyclerView.setNestedScrollingEnabled(false);
        servicesRecyclerViewAdapter = new ServicesRecyclerViewAdapter(this, categoryTitles);
        servicesRecyclerView.setAdapter(servicesRecyclerViewAdapter);
        initLayout();
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    private void initLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       initToolbar();
        mDrawerLayout.setDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        SharedPreferencesService sharedPreferencesService = ServiceFactory.getSharedPreferencesService();
                        if(sharedPreferencesService.contains(LOGIN_MODE)) {
                            sharedPreferencesService.get(LOGIN_MODE);
                            if (drawerFragment != null)
                                drawerFragment.loadPic();
                        } else{
                            drawerFragment.setDefaultImageandText();
                        }
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );
    }
    private void initToolbar() {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse);
        collapsingToolbar.setTitleEnabled(false);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bannnerViewPager.isEnabled()){
                        if(bannnerViewPager.getCurrentItem()>=0 && bannnerViewPager.getCurrentItem()<2){
                            bannnerViewPager.setCurrentItem(bannnerViewPager.getCurrentItem()+1);
                        }else {
                            bannnerViewPager.setCurrentItem(0);
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bannnerTimerTask != null) {
            bannnerTimerTask.cancel();
        }
    }
}

