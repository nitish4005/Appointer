package com.example.android.appointer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.appointer.Common.DrawerRow;
import com.example.android.appointer.DrawerAdapter;
import com.example.android.appointer.LoginActivity;
import com.example.android.appointer.MainActivity;
import com.example.android.appointer.R;
import com.example.android.appointer.Service.ServiceFactory;
import com.example.android.appointer.Service.SharedPreferencesService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.android.appointer.Common.SharedPreferenceConstants.DISPLAY_NAME;
import static com.example.android.appointer.Common.SharedPreferenceConstants.LOGIN_MODE;
import static com.example.android.appointer.Common.SharedPreferenceConstants.PROFILE_PIC;

/**
 * Created by Prasad on 16-Apr-18.
 */

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggel;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private DrawerAdapter drawerAdapter;
    private ImageButton backButton;
    private CircleImageView profilePic;


    private SharedPreferencesService preferencesService;

    //For Login
    private TextView mUsername;
    public Context activityRef;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        Code to track Fragment
//        AnalyticsApplication.sendScreenAnalytics("Nav Fragment");


        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        backButton = (ImageButton) layout.findViewById(R.id.navDrawerBackButton);
        //     fb_text=layout.findViewById(R.id.fb_text)

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawers();
            }
        });

        mUsername = (TextView) layout.findViewById(R.id.userName);
        profilePic = (CircleImageView) layout.findViewById(R.id.profile_image);

        preferencesService = ServiceFactory.getSharedPreferencesService();
        activityRef = getActivity();

        LinearLayout profileContent= (LinearLayout) layout.findViewById(R.id.profile_content);

        profileContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(preferencesService.contains(LOGIN_MODE)) {
                    preferencesService.get(LOGIN_MODE);
                } else{
                    ((MainActivity) getActivity()).closeDrawer();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("Instruction", "fragment");
                    startActivity(intent);
                }

            }
        });

        return layout;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public List<DrawerRow> getRowData() {
        List<DrawerRow> drawerData = new ArrayList<>();
        int[] icons = {R.drawable.homeicon, R.drawable.profileicon,R.drawable.messageicon, R.drawable.sendfeedbackicon,
                R.drawable.privacypolicyicon, R.drawable.helpicon, R.drawable.loginicon};
        String[] titles = {activityRef.getString(R.string.home),activityRef.getString(R.string.profile) , activityRef.getString(R.string.message), activityRef.getString(R.string.send_feedback), activityRef.getString(R.string.privacy_policy),activityRef.getString(R.string.help),activityRef.getString(R.string.login)};


        for (int i = 0; i < titles.length; i++) {
            if(i==titles.length-1 && preferencesService.contains(LOGIN_MODE)){
                DrawerRow drawerRow = new DrawerRow(R.drawable.logouticon,activityRef.getString(R.string.login) );
                drawerData.add(drawerRow);
                break;
            }
            DrawerRow drawerRow = new DrawerRow(icons[i], titles[i]);
            drawerData.add(drawerRow);
        }

        return drawerData;

    }

    public void setDefaultImageandText()
    {
        mUsername.setText(activityRef.getString(R.string.login));
        profilePic.setImageResource(R.drawable.drawer_avatar_circular);
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {

        final Toolbar mToolbar = toolbar;
        mDrawerLayout = drawerLayout;

        initNavDrawer();


        mDrawerToggel = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

         /*   @Override
         Too make that Dim Effect but this mess up the animation
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6){
                mToolbar.setAlpha(1-slideOffset);
            }} */
        };
        mDrawerLayout.setDrawerListener(mDrawerToggel);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggel.syncState();
            }
        });

    }

    private void initNavDrawer() {
        drawerAdapter = new DrawerAdapter(mDrawerLayout, getActivity(), getRowData());
        recyclerView.setAdapter(drawerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));
    }


    private void displayUserProfileImage() {
        String url, user;

        if(preferencesService.contains(DISPLAY_NAME)) {
            user = preferencesService.get(DISPLAY_NAME);
        } else{
            user = "";
        }
        mUsername.setText(user);

        if(preferencesService.contains(PROFILE_PIC)) {
            url = preferencesService.get(PROFILE_PIC);
        } else {
            return;
        }

        Picasso.with(activityRef).load(url).noFade().into(profilePic);

    }

    public void loadPic() {
        if(preferencesService.contains(LOGIN_MODE)) {
            preferencesService.get(LOGIN_MODE);    //IS_USER_LOGGED_IN =YES/NO
        } else{
            mUsername.setText("Login");
            profilePic.setImageResource(R.drawable.drawer_avatar_circular);
            return;
        }
        displayUserProfileImage();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void refreshData()
    {
        initNavDrawer();
    }
}
