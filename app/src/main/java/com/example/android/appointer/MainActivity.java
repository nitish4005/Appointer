package com.example.android.appointer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.appointer.Fragments.NavigationDrawerFragment;
import com.example.android.appointer.Service.ServiceFactory;
import com.example.android.appointer.Service.SharedPreferencesService;

import static com.example.android.appointer.Common.SharedPreferenceConstants.LOGIN_MODE;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationDrawerFragment drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
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
}

