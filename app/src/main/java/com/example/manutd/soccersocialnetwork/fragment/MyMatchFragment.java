package com.example.manutd.soccersocialnetwork.fragment;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
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

        listView = (ListView) view.findViewById(R.id.lvMatchesList);
        Call<List<MatchsDetailModel>> call = apiInterface.getMatchs();
        call.enqueue(new Callback<List<MatchsDetailModel>>() {
            @Override
            public void onResponse(Call<List<MatchsDetailModel>> call, Response<List<MatchsDetailModel>> response) {
                matchsDetailList.addAll(response.body());
                for (int i = 0; i < matchsDetailList.size(); i++) {
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

        return view;
    }
}
