package com.example.manutd.soccersocialnetwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manutd.soccersocialnetwork.R;

/**
 * Created by manutd on 20/10/2016.
 */

public class ChangePasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password,container,false);
        return view;
    }
}
