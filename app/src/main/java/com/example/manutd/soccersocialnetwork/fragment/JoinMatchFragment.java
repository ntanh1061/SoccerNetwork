package com.example.manutd.soccersocialnetwork.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.activity.MatchDetailActivity;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.model.SlotsModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manutd on 20/10/2016.
 */

public class JoinMatchFragment extends Fragment {
    ApiInterface apiInterface;
    List<SlotsModel> slotsModelList;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    int userid;
    List<MatchsDetailModel> matchsDetailModelList;
    ListView listView;
    MatchsDetailModel model;
    Integer[] listId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_list_layout, container, false);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        slotsModelList = new ArrayList<>();
        matchsDetailModelList = new ArrayList<>();
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        userid = settings.getInt("hostid", 0);
        listView = (ListView) view.findViewById(R.id.lvMatchesList);

        Call<List<SlotsModel>> call = apiInterface.getSlots();
        call.enqueue(new Callback<List<SlotsModel>>() {
            @Override
            public void onResponse(Call<List<SlotsModel>> call, Response<List<SlotsModel>> response) {
                slotsModelList.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<SlotsModel>> call, Throwable t) {

            }
        });
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < slotsModelList.size(); i++) {
                    if (userid == slotsModelList.get(i).getUserId()) {
                        Call<MatchsDetailModel> call1 = apiInterface.getMatchById(slotsModelList.get(i).getMatchId());
                        call1.enqueue(new Callback<MatchsDetailModel>() {
                            @Override
                            public void onResponse(Call<MatchsDetailModel> call, Response<MatchsDetailModel> response) {
                                model = response.body();
                                matchsDetailModelList.add(model);

                                ListViewAdapter adapter = new ListViewAdapter(getContext(), matchsDetailModelList);
                                listView.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<MatchsDetailModel> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        }, 2000);
        Toast.makeText(getContext(), "Vui long cho", Toast.LENGTH_SHORT).show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MatchDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("match", matchsDetailModelList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to delete?");
                builder.setCancelable(true);
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<List<MatchsDetailModel>> call1 = apiInterface.deleteMatch(matchsDetailModelList.get(position).getMatchId());
                        call1.enqueue(new Callback<List<MatchsDetailModel>>() {
                            @Override
                            public void onResponse(Call<List<MatchsDetailModel>> call, Response<List<MatchsDetailModel>> response) {
                            }

                            @Override
                            public void onFailure(Call<List<MatchsDetailModel>> call, Throwable t) {

                            }
                        });
                    }
                }).create().show();
                return true;
            }
        });
        return view;
    }

}
