package com.example.manutd.soccersocialnetwork.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manutd on 20/10/2016.
 */

public class SetupMatchFragment extends Fragment implements View.OnClickListener {
    Spinner spinner;
    ArrayAdapter<String> listSpiner;
    final String[] DISTRICT = {"Nam cao", "Xuan thieu", "Duy tan", "Trang hoang", "Viet hoa", "Ngoc thach", "MU", "Nguyen Chanh"};
    TextView tvTimeStart, tvDate;
    Calendar calendar;
    SimpleDateFormat dft;
    ApiInterface apiInterface;
    List<FieldModel> fieldModelList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_match, container, false);

        spinner = (Spinner) view.findViewById(R.id.spiner);
        fieldModelList = new ArrayList<>();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FieldModel>> call = apiInterface.getField();
        call.enqueue(new Callback<List<FieldModel>>() {
            @Override
            public void onResponse(Call<List<FieldModel>> call, Response<List<FieldModel>> response) {
                fieldModelList.addAll(response.body());
                String[] fielddArr = new String[fieldModelList.size()];
                for (int i = 0; i < fieldModelList.size(); i++) {
                    fielddArr[i] = fieldModelList.get(i).getFieldName();
                }
                listSpiner = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, fielddArr);
                spinner.setAdapter(listSpiner);
            }

            @Override
            public void onFailure(Call<List<FieldModel>> call, Throwable t) {

            }
        });

        tvDate = (TextView) view.findViewById(R.id.tvDateTime);
        tvTimeStart = (TextView) view.findViewById(R.id.tvTimeStart);
        calendar = Calendar.getInstance();
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(calendar.getTime());
        tvDate.setText(strDate);

        dft = new SimpleDateFormat("hh:mm", Locale.getDefault());
        String strTime = dft.format(calendar.getTime());

        tvTimeStart.setText(strTime);
        view.findViewById(R.id.btnDateSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        view.findViewById(R.id.btnTimeStartSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
        return view;
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tvTimeStart.setText(hour + (hour < 10 ? "0" : "") + ":" + minute + (minute < 10 ? "0" : ""));
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }
        };
        String s = tvTimeStart.getText().toString();
        String[] timeArr = s.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), callback, hour, minute, true);
        timePickerDialog.setTitle("Chọn giờ bắt đầu!");
        timePickerDialog.show();
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvDate.setText(day + "/" + (month + 1) + "/" + year);
                calendar.set(year, month, day);
            }
        };

        String s = tvDate.getText().toString();
        String[] dateArr = s.split("/");
        int day = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]) - 1;
        int year = Integer.parseInt(dateArr[2]);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), callback, year, month, day);
        datePickerDialog.setTitle("Chọn ngày diễn ra trận đấu!");
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDateSet:
                break;
            case R.id.btnTimeStartSet:
                break;
        }
    }
}
