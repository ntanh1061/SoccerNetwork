package com.example.manutd.soccersocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.FeedbackModel;

import java.util.List;

/**
 * Created by nta on 27/11/2016.
 */

public class ListViewFeedbackAdapter extends BaseAdapter {
    List<FeedbackModel> feedbackModelList;
    Context context;
    LayoutInflater inflater;

    public ListViewFeedbackAdapter(Context context, List<FeedbackModel> feedbackModelList) {
        this.context = context;
        this.feedbackModelList = feedbackModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return feedbackModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return feedbackModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.feedback_list_item, viewGroup, false);
        TextView tvFeedbackUser = (TextView) v.findViewById(R.id.tvFeedbackUser);
        TextView tvFeedback = (TextView) v.findViewById(R.id.tvFeedbackContent);
        TextView tvPosted = (TextView) v.findViewById(R.id.tvdatepost);

        tvFeedbackUser.setText(feedbackModelList.get(i).getUsername().toString());
        tvFeedback.setText(feedbackModelList.get(i).getFeedback());
        tvPosted.setText("(Posted: " + feedbackModelList.get(i).getCreateDate() + " )");
        return v;
    }
}
