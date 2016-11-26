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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.adapter.ListViewFeedbackAdapter;
import com.example.manutd.soccersocialnetwork.model.FeedbackModel;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.model.SlotsModel;
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
    ListView lvFeedback;
    EditText edtFeedback;
    Button btnSendFeedback;
    String feedback;
    List<FeedbackModel> feedbackList;
    String host;
    Date currentTime;
    Date endTimeUntil;

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
        lvFeedback = (ListView) findViewById(R.id.lvFeedback);
        edtFeedback = (EditText) findViewById(R.id.edtFeedback);
        btnSendFeedback = (Button) findViewById(R.id.btnSendFeedback);


        slotsModelList = new ArrayList<>();
        settings = PreferenceManager.getDefaultSharedPreferences(MatchDetailActivity.this);
        editor = settings.edit();
        Bundle bundle = getIntent().getExtras();
        matchsDetailModel = (MatchsDetailModel) bundle.getSerializable("match");
        host = matchsDetailModel.getHostName();
        String fieldName = matchsDetailModel.getFieldName();
        String address = matchsDetailModel.getFieldName();

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

        endTimeUntil = DateUtils.convertToUDatetime(endTime.toString());
        Calendar calendar = Calendar.getInstance();
        currentTime = calendar.getTime();
        feedbackList = new ArrayList<>();
        if (host.equals(username) && currentTime.after(endTimeUntil)) {
            lvFeedback.setVisibility(View.VISIBLE);
            btnJoin.setVisibility(View.GONE);
            edtQuantity.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            edtFeedback.setVisibility(View.GONE);
            btnSendFeedback.setVisibility(View.GONE);
        } else if (currentTime.after(endTimeUntil)) {
            edtFeedback.setVisibility(View.VISIBLE);
            btnSendFeedback.setVisibility(View.VISIBLE);
            btnJoin.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            edtQuantity.setVisibility(View.GONE);
        } else {
//            btnJoin.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
        }
        Call<List<SlotsModel>> call1 = apiInterface.getSlots();
        call1.enqueue(new Callback<List<SlotsModel>>() {
            @Override
            public void onResponse(Call<List<SlotsModel>> call, Response<List<SlotsModel>> response) {
                slotsModelList.addAll(response.body());
                for (int i = 0; i < slotsModelList.size(); i++) {
                    if (currentTime.after(endTimeUntil) && matchId == slotsModelList.get(i).getMatchId() && userId == slotsModelList.get(i).getUserId() && !host.equals(username)) {
                        checkslots = false;
//                        btnJoin.setVisibility(View.GONE);
                        btnEdit.setVisibility(View.GONE);
//                        edtQuantity.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SlotsModel>> call, Throwable t) {

            }
        });

        Call<List<FeedbackModel>> callFeedback = apiInterface.getFeedback();
        callFeedback.enqueue(new Callback<List<FeedbackModel>>() {
            @Override
            public void onResponse(Call<List<FeedbackModel>> call, Response<List<FeedbackModel>> response) {
                feedbackList.addAll(response.body());
                ListViewFeedbackAdapter listViewFeedbackAdapter = new ListViewFeedbackAdapter(MatchDetailActivity.this, feedbackList);
                lvFeedback.setAdapter(listViewFeedbackAdapter);
            }

            @Override
            public void onFailure(Call<List<FeedbackModel>> call, Throwable t) {

            }
        });

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
        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    feedback = edtFeedback.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FeedbackModel feedbackModel = new FeedbackModel(matchId, userId, feedback);
                Call<FeedbackModel> call2 = apiInterface.createFeedback(feedbackModel);
                call2.enqueue(new Callback<FeedbackModel>() {
                    @Override
                    public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {

                    }

                    @Override
                    public void onFailure(Call<FeedbackModel> call, Throwable t) {

                    }
                });
                Toast.makeText(MatchDetailActivity.this, "Gui feedback thanh cong!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
