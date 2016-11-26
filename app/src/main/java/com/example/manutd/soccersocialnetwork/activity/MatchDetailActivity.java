package com.example.manutd.soccersocialnetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;

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

        Bundle bundle = getIntent().getExtras();
        matchsDetailModel = (MatchsDetailModel) bundle.getSerializable("match");

        String fieldName = matchsDetailModel.getFieldName();
        String address = matchsDetailModel.getFieldName();
        String host = matchsDetailModel.getHostName();
        int maxPlayer = matchsDetailModel.getMaxPlayers();
        int price = matchsDetailModel.getPrice();
        String startTime = matchsDetailModel.getStartTime();
        String endTime = matchsDetailModel.getEndTime();

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
    }
}
