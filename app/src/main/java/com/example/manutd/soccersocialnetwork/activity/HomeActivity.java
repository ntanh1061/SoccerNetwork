package com.example.manutd.soccersocialnetwork.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.fragment.ChangePasswordFragment;
import com.example.manutd.soccersocialnetwork.fragment.JoinMatchFragment;
import com.example.manutd.soccersocialnetwork.fragment.MatchListFragment;
import com.example.manutd.soccersocialnetwork.fragment.MyMatchFragment;
import com.example.manutd.soccersocialnetwork.fragment.ProfileInformationFragment;
import com.example.manutd.soccersocialnetwork.fragment.SetupMatchFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fm;
    FragmentTransaction ft, ft1;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ChangePasswordFragment changePasswordFragment;
    ProfileInformationFragment profileInformationFragment;
    MatchListFragment matchListFragment;
    MyMatchFragment myMatchFragment;
    JoinMatchFragment joinMatchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseMessaging.getInstance().subscribeToTopic("testfcm");
        String token= FirebaseInstanceId.getInstance().getToken();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileInformationFragment = new ProfileInformationFragment();
        changePasswordFragment = new ChangePasswordFragment();
        matchListFragment = new MatchListFragment();
        myMatchFragment = new MyMatchFragment();
        joinMatchFragment = new JoinMatchFragment();
        fm = getSupportFragmentManager();
        ft1 = fm.beginTransaction().replace(R.id.frContainer, matchListFragment);
        ft1.commit();

        Bundle bundle = getIntent().getExtras();
        changePasswordFragment.setArguments(bundle);
        profileInformationFragment.setArguments(bundle);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fm = getSupportFragmentManager();
        toolbar.setTitle("My match");
    }

    //
    @Override
    public void onBackPressed() {
        ShowDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_exit) {
            ShowDialog();
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            String date = df.format(Calendar.getInstance().getTime());
            Toast.makeText(HomeActivity.this, date, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_match_list:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, matchListFragment);
                ft.commit();
                toolbar.setTitle("Match List");
                break;
            case R.id.nav_my_match:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, myMatchFragment);
                ft.commit();
                toolbar.setTitle("My match");
                break;
            case R.id.nav_join_match:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, joinMatchFragment);
                ft.commit();
                toolbar.setTitle("Join match");
                break;
            case R.id.nav_setup_math:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, new SetupMatchFragment());
                ft.commit();
                toolbar.setTitle("Create match");
                break;

            case R.id.nav_profile_information:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, profileInformationFragment);
                ft.commit();
                toolbar.setTitle("Profile information");
                break;

            case R.id.nav_chang_password:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, changePasswordFragment);
                ft.commit();
                toolbar.setTitle("Change password");

                break;

            case R.id.nav_logout:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShowDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        ;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }

}
