package com.example.manutd.soccersocialnetwork.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.fragment.ChangePasswordFragment;
import com.example.manutd.soccersocialnetwork.fragment.MatchListFragment;
import com.example.manutd.soccersocialnetwork.fragment.ProfileInformationFragment;
import com.example.manutd.soccersocialnetwork.fragment.SetupMatchFragment;
import com.example.manutd.soccersocialnetwork.model.MatchDetailModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fm;
    FragmentTransaction ft;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    SearchView searchView;
    ListView listView;
    List<MatchDetailModel> list;
    MatchDetailModel matchDetailModel;
    ListViewAdapter listViewAdapter;
    ChangePasswordFragment changePasswordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.lvHome);
        list = new ArrayList<>();
        matchDetailModel = new MatchDetailModel("Nguyen Chanh", "255000 VND", "20/10/2016 07:33:00", 11, "Con trong");
        list.add(matchDetailModel);

        changePasswordFragment = new ChangePasswordFragment();
        Bundle bundle = getIntent().getExtras();
        changePasswordFragment.setArguments(bundle);

        listViewAdapter = new ListViewAdapter(this, list);
        listView.setAdapter(listViewAdapter);

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
//        searchView = (SearchView) findViewById(R.id.search_view);
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////        });

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
                ft.replace(R.id.frContainer, new MatchListFragment());
                ft.commit();
                toolbar.setTitle("Match List");
                break;
            case R.id.nav_my_match:
                ft = fm.beginTransaction();
                ft.replace(R.id.frContainer, new MatchListFragment());
                ft.commit();
                toolbar.setTitle("My match");
                break;
            case R.id.nav_join_match:
                ft = fm.beginTransaction();
                ft.replace(R.id.content_main, new MatchListFragment());
                ft.commit();
                toolbar.setTitle("Join match");
                break;
            case R.id.nav_setup_math:
                ft = fm.beginTransaction();
                ft.replace(R.id.content_main, new SetupMatchFragment());
                ft.commit();
                toolbar.setTitle("Create match");
                break;

            case R.id.nav_profile_information:
                ft = fm.beginTransaction();
                ft.replace(R.id.content_main, new ProfileInformationFragment());
                ft.commit();
                toolbar.setTitle("Profile information");
                break;

            case R.id.nav_chang_password:
                ft = fm.beginTransaction();
                ft.replace(R.id.content_main, changePasswordFragment);
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
