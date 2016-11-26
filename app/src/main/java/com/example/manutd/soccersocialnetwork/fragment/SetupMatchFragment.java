package com.example.manutd.soccersocialnetwork.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;
import com.example.manutd.soccersocialnetwork.until.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.manutd.soccersocialnetwork.R.id.edPrice;

/**
 * Created by manutd on 20/10/2016.
 */

public class SetupMatchFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<String> listSpiner;
    TextView tvTimeStart, tvDate, tvTimeEnd;
    Calendar calendar;
    SimpleDateFormat dft;
    List<FieldModel> fieldModelList;
    ApiInterface apiInterface;
    EditText edtMaxPalayer, edtPrice;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    int fieldId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_match, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
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

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        edtMaxPalayer = (EditText) view.findViewById(R.id.edMaxPlayer);
        edtPrice = (EditText) view.findViewById(R.id.edPrice);
        tvDate = (TextView) view.findViewById(R.id.tvDateTime);
        tvTimeStart = (TextView) view.findViewById(R.id.tvSetupTimeStart);
        tvTimeEnd = (TextView) view.findViewById(R.id.tvSetupTimeEnd);
        calendar = Calendar.getInstance();
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(calendar.getTime());
        tvDate.setText(strDate);

        dft = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        final String strTime = dft.format(calendar.getTime());

        tvTimeStart.setText(strTime);
        tvTimeEnd.setText(strTime);
        view.findViewById(R.id.btnDateSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        view.findViewById(R.id.btnTimeEndSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
        view.findViewById(R.id.btnTimeStartSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeEndPickerDialog();
            }
        });

        view.findViewById(R.id.btnCreateMatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fieldName = spinner.getSelectedItem().toString();
                for (int i = 0; i < fieldModelList.size(); i++) {
                    if (fieldName.equals(fieldModelList.get(i).getFieldName())) {
                        fieldId = fieldModelList.get(i).getFieldId();
                        break;
                    }
                }
                int hostId = settings.getInt("hostid", 0);
                String hostName = settings.getString("hostname", "");
                try {
                    int maxPlayer = Integer.parseInt(edtMaxPalayer.getText().toString());
                    int price = Integer.parseInt(edtPrice.getText().toString());
                    String timeStart = tvDate.getText().toString() + " " + tvTimeStart.getText().toString();
                    String timeEnd = tvDate.getText().toString() + " " + tvTimeEnd.getText().toString();

                    Date checkTimeStart = DateUtils.convertToUDatetime(timeStart);
                    Date checkTimeEnd = DateUtils.convertToUDatetime(timeEnd);
                    if (checkTimeEnd.after(checkTimeStart)) {
                        MatchsDetailModel matchsDetailModel = new MatchsDetailModel(fieldId, hostId, maxPlayer, price, timeStart, timeEnd, "Big", true);
                        Call<List<MatchsDetailModel>> listCall = apiInterface.createMatch(matchsDetailModel);
                        listCall.enqueue(new Callback<List<MatchsDetailModel>>() {
                            @Override
                            public void onResponse(Call<List<MatchsDetailModel>> call, Response<List<MatchsDetailModel>> response) {

                            }

                            @Override
                            public void onFailure(Call<List<MatchsDetailModel>> call, Throwable t) {

                            }
                        });
                        Toast.makeText(getContext(), "Tao tran dau thanh cong!", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, new MyMatchFragment()).commit();
                    } else {
                        Toast.makeText(getContext(), "Vui long nhap nay thang chinh xac!", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    Toast.makeText(getContext(), "Vui long dien day du thong tin!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tvTimeStart.setText((hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute + ":00");
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

    public void showTimeEndPickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tvTimeEnd.setText((hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute + ":00");
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }
        };
        String s = tvTimeEnd.getText().toString();
        String[] timeArr = s.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), callback, hour, minute, true);
        timePickerDialog.setTitle("Chon gio ket thuc!");
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

}
