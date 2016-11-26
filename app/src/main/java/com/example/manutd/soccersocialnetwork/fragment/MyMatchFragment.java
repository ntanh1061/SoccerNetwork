package com.example.manutd.soccersocialnetwork.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.activity.MatchDetailActivity;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;
import com.example.manutd.soccersocialnetwork.until.DateUtils;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manutd on 20/10/2016.
 */

public class MyMatchFragment extends Fragment {
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String hostName;
    ListView listView;
    ApiInterface apiInterface;
    List<MatchsDetailModel> matchsDetailList;
    List<MatchsDetailModel> joinMatchList;
    ListViewAdapter adapter;
    Calendar calendar;
    Date currentDate;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_list_layout, container, false);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        hostName = settings.getString("hostname", "");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        matchsDetailList = new ArrayList<>();
        joinMatchList = new ArrayList<>();

        calendar = Calendar.getInstance();
        currentDate = calendar.getTime();

        listView = (ListView) view.findViewById(R.id.lvMatchesList);
        Call<List<MatchsDetailModel>> call = apiInterface.getMatchs();
        call.enqueue(new Callback<List<MatchsDetailModel>>() {
            @Override
            public void onResponse(Call<List<MatchsDetailModel>> call, Response<List<MatchsDetailModel>> response) {
                matchsDetailList.addAll(response.body());
                for (int i = 0; i < matchsDetailList.size(); i++) {
                    String timeEnd = matchsDetailList.get(i).getEndTime();
                    Date dateEnd = DateUtils.convertToUDatetime(timeEnd);
                    if (matchsDetailList.get(i).getHostName().equals(hostName)) {
                        joinMatchList.add(matchsDetailList.get(i));
                        adapter = new ListViewAdapter(getContext(), joinMatchList);
                        listView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MatchsDetailModel>> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MatchDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("match", joinMatchList.get(position));
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
                        Call<List<MatchsDetailModel>> call1 = apiInterface.deleteMatch(joinMatchList.get(position).getMatchId());
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
