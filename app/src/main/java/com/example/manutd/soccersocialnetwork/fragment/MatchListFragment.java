package com.example.manutd.soccersocialnetwork.fragment;

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

import com.example.manutd.soccersocialnetwork.activity.MatchDetailActivity;
import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.adapter.ListViewAdapter;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;
import com.example.manutd.soccersocialnetwork.until.DateUtils;

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

public class MatchListFragment extends Fragment {
    ListView listView;
    ListViewAdapter listViewAdapter;
    List<MatchsDetailModel> list;
    ApiInterface apiInterface;
    Date currentDate;
    List<MatchsDetailModel> matchsDetailModelList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_list_layout, container, false);

        list = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.lvMatchesList);
        matchsDetailModelList = new ArrayList<>();
        currentDate = Calendar.getInstance().getTime();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        final Call<List<MatchsDetailModel>> call = apiInterface.getMatchs();
        call.enqueue(new Callback<List<MatchsDetailModel>>() {
            @Override
            public void onResponse(Call<List<MatchsDetailModel>> call, Response<List<MatchsDetailModel>> response) {
                list.addAll(response.body());
                for (int i = 0; i < list.size(); i++) {
                    String endDate = list.get(i).getEndTime();
                    Date dateEnd = DateUtils.convertToUDatetime(endDate);
                    if (currentDate.before(dateEnd)) {
                        matchsDetailModelList.add(list.get(i));
                        listViewAdapter = new ListViewAdapter(getContext(), matchsDetailModelList);
                        listView.setAdapter(listViewAdapter);
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
                listViewAdapter.notifyDataSetChanged();
                return true;
            }
        });


        return view;
    }
}
