package com.example.manutd.soccersocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by manutd on 20/10/2016.
 */

public class ListViewAdapter extends BaseAdapter {
    List<MatchsDetailModel> list;
    LayoutInflater layoutInflater;
    Context context;
    TextView tvField, tvPrice, tvDateTime, tvSpace, tvStatus;
    String status = "";

    public ListViewAdapter(Context context, List<MatchsDetailModel> list) {
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_match_item, parent, false);

        tvField = (TextView) convertView.findViewById(R.id.tvField);
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvDateTime = (TextView) convertView.findViewById(R.id.tvDateTime);
        tvSpace = (TextView) convertView.findViewById(R.id.tvSpace);
        tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        String currentTime = dtf.format(calendar.getTime());
        String endTime = list.get(position).getEndTime();

        String[] currentDateTime = currentTime.split(" ");
        String[] timeEnd = endTime.split(" ");

        String[] date = currentDateTime[0].split("/");
        String[] dateEnd = timeEnd[0].split("/");

        String[] timeCurrent = currentDateTime[1].split(":");
        String[] timeEndcheck = timeEnd[1].split(":");

        if (list.get(position).getAvailableSlots() > 0) {
            status = "Con trong";
        } else {
            status = "Het cho";
        }
        tvField.setText(list.get(position).getFieldName());
        tvPrice.setText(list.get(position).getPrice() + " VND");
        tvStatus.setText(status);
        tvDateTime.setText(list.get(position).getStartTime());
        tvSpace.setText(list.get(position).getAvailableSlots() + "");

        return convertView;
    }
}
