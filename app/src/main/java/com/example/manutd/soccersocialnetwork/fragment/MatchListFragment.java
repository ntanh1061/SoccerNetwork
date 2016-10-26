package com.example.manutd.soccersocialnetwork.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.MatchDetailActivity;
import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.model.MatchDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manutd on 20/10/2016.
 */

public class MatchListFragment extends Fragment{

    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    ListViewAdapter listViewAdapter;
    List<MatchDetailModel> list;
    MatchDetailModel matchDetailModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_list_layout, container, false);

        listView = (ListView) view.findViewById(R.id.lvMatchesList);
        list = new ArrayList<>();
        matchDetailModel = new MatchDetailModel("Nguyen Chanh","255000 VND","20/10/2016 07:33:00",11,"Con trong");
        list.add(matchDetailModel);

        listViewAdapter = new ListViewAdapter(getContext(),list);
        listView.setAdapter(listViewAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setEnabled(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setEnabled(false);
                    }
                },3000);
            }
        });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               startActivity(new Intent(getContext(), MatchDetailActivity.class));
           }
       });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to delete?");
                builder.setCancelable(true);
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"No",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Yes",Toast.LENGTH_SHORT).show();

                    }
                }).create().show();
                return true;
            }
        });


        return view;
    }
}
