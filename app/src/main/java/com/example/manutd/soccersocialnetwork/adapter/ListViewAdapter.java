package com.example.manutd.soccersocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.MatchDetailModel;

import java.util.List;

/**
 * Created by manutd on 20/10/2016.
 */

public class ListViewAdapter extends BaseAdapter {
    List<MatchDetailModel> list;
    LayoutInflater layoutInflater;
    Context context;
    TextView tvField,tvPrice,tvDateTime,tvSpace,tvStatus;


    public ListViewAdapter(Context context,List<MatchDetailModel> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_match_item,parent,false);

        tvField = (TextView) convertView.findViewById(R.id.tvField);
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvDateTime = (TextView) convertView.findViewById(R.id.tvDateTime);
        tvSpace = (TextView) convertView.findViewById(R.id.tvSpace);
        tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);

        tvField.setText(list.get(position).getField());
        tvPrice.setText(list.get(position).getPrice());
        tvStatus.setText(list.get(position).getStatus());
        tvDateTime.setText(list.get(position).getDate());
        tvSpace.setText(list.get(position).getSpace()+"");

        return convertView;
    }
}
