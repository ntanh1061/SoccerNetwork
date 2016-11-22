package com.example.manutd.soccersocialnetwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.manutd.soccersocialnetwork.R;

/**
 * Created by manutd on 19/10/2016.
 */

public class ProfileInformationFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<String> listSpiner;
    final String [] DISTRICT = {"Lien Chieu","Hai Chau","Hoa Minh","Hoa Vang","Thanh Khe","Hoa Cam","Hoa Minh"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.Fragment_profile_information,container,false);
        spinner = (Spinner) view.findViewById(R.id.spiner);
        listSpiner = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,DISTRICT);
        spinner.setAdapter(listSpiner);

        return view;
    }
}
