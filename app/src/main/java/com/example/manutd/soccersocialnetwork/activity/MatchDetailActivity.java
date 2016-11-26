package com.example.manutd.soccersocialnetwork.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.model.SlotsModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manutd on 20/10/2016.
 */

public class MatchDetailActivity extends AppCompatActivity {
    ImageButton imgMaps;
    MatchsDetailModel matchsDetailModel;
    TextView tvField, tvAddress, tvHost, tvMaxPlayer, tvPrice, tvStart, tvEnd;
    ApiInterface apiInterface;
    EditText edtQuantity;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    List<SlotsModel> slotsModelList;
    boolean checkslots = true;
    int matchId;
    int quantity;
    int userId;
    String username;
    String verificationCode;
    int availableSlot;
    boolean verified;
    Button btnJoin, btnEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail_layout);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        tvField = (TextView) findViewById(R.id.tvFieldMatch);
        tvAddress = (TextView) findViewById(R.id.tvAdress);
        tvHost = (TextView) findViewById(R.id.tvHost);
        tvMaxPlayer = (TextView) findViewById(R.id.tvMaxPlayers);
        tvPrice = (TextView) findViewById(R.id.tvPriceMatch);
        tvStart = (TextView) findViewById(R.id.tvStartTime);
        tvEnd = (TextView) findViewById(R.id.tvEndTime);
        edtQuantity = (EditText) findViewById(R.id.edtQuantity);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        slotsModelList = new ArrayList<>();
        settings = PreferenceManager.getDefaultSharedPreferences(MatchDetailActivity.this);
        editor = settings.edit();
        Bundle bundle = getIntent().getExtras();
        matchsDetailModel = (MatchsDetailModel) bundle.getSerializable("match");

        Call<List<SlotsModel>> call1 = apiInterface.getSlots();
        call1.enqueue(new Callback<List<SlotsModel>>() {
            @Override
            public void onResponse(Call<List<SlotsModel>> call, Response<List<SlotsModel>> response) {
                slotsModelList.addAll(response.body());
                for (int i = 0; i < slotsModelList.size(); i++) {
                    if (matchId == slotsModelList.get(i).getMatchId() && userId == slotsModelList.get(i).getUserId()) {
                        checkslots = false;
                        btnJoin.setVisibility(View.GONE);
                        btnEdit.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SlotsModel>> call, Throwable t) {

            }
        });

        String fieldName = matchsDetailModel.getFieldName();
        String address = matchsDetailModel.getFieldName();
        String host = matchsDetailModel.getHostName();
        int maxPlayer = matchsDetailModel.getMaxPlayers();
        int price = matchsDetailModel.getPrice();
        String startTime = matchsDetailModel.getStartTime();
        String endTime = matchsDetailModel.getEndTime();

        matchId = matchsDetailModel.getMatchId();
        userId = settings.getInt("hostid", 0);
        username = settings.getString("hostname", "");
        verificationCode = matchsDetailModel.getVerificationCode();
        availableSlot = matchsDetailModel.getAvailableSlots();
        verified = matchsDetailModel.isVerified();
        tvField.setText(fieldName);
        tvAddress.setText(address);
        tvHost.setText(host);
        tvMaxPlayer.setText(maxPlayer + "");
        tvPrice.setText(price + "");
        tvStart.setText(startTime);
        tvEnd.setText(endTime);

        imgMaps = (ImageButton) findViewById(R.id.imgMaps);
        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fieldId = matchsDetailModel.getFieldId();
                final MatchsDetailModel matchsDetailModel1;
                Call<FieldModel> call = apiInterface.getFieldByID(fieldId);
                call.enqueue(new Callback<FieldModel>() {
                    @Override
                    public void onResponse(Call<FieldModel> call, Response<FieldModel> response) {
                        double latitude = response.body().getLatitude();
                        double longitude = response.body().getLongitude();

                        Intent intent = new Intent(MatchDetailActivity.this, MapsActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putDouble("latitude", latitude);
                        bundle1.putDouble("longitude", longitude);
                        intent.putExtras(bundle1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<FieldModel> call, Throwable t) {

                    }
                });

            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    quantity = Integer.parseInt(edtQuantity.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MatchDetailActivity.this, "Vui long nhap vao so luong player", Toast.LENGTH_SHORT).show();
                }
                if (checkslots && availableSlot >= quantity) {
                    SlotsModel slotsModel = new SlotsModel(matchId, quantity, userId, username, verificationCode, verified);
                    Call<List<SlotsModel>> listCall = apiInterface.createSlots(slotsModel);
                    listCall.enqueue(new Callback<List<SlotsModel>>() {
                        @Override
                        public void onResponse(Call<List<SlotsModel>> call, Response<List<SlotsModel>> response) {
                            Toast.makeText(MatchDetailActivity.this, "Them slot thanh cong!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<List<SlotsModel>> call, Throwable t) {

                        }
                    });
                    startActivity(new Intent(MatchDetailActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(MatchDetailActivity.this, "Them slot khong thanh cong!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    quantity = Integer.parseInt(edtQuantity.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MatchDetailActivity.this, "Vui long nhap vao so luong player", Toast.LENGTH_SHORT).show();
                }
                if (availableSlot >= quantity) {
                    SlotsModel slotsModel1 = new SlotsModel(matchId, quantity, userId, username, verificationCode, verified);
                    Call<List<SlotsModel>> call1 = apiInterface.editSlot(slotsModel1);
                    call1.enqueue(new Callback<List<SlotsModel>>() {
                        @Override
                        public void onResponse(Call<List<SlotsModel>> call, Response<List<SlotsModel>> response) {

                        }

                        @Override
                        public void onFailure(Call<List<SlotsModel>> call, Throwable t) {

                        }
                    });
                    Toast.makeText(MatchDetailActivity.this, "Chinh sua slot thanh cong!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MatchDetailActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(MatchDetailActivity.this, "Chinh sua slot khong thanh cong!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
