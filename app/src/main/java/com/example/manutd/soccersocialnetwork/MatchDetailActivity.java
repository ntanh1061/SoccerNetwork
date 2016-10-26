package com.example.manutd.soccersocialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by manutd on 20/10/2016.
 */

public class MatchDetailActivity extends AppCompatActivity {
        ImageButton imgMaps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail_layout);

                imgMaps = (ImageButton) findViewById(R.id.imgMaps);

        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchDetailActivity.this, MapsActivity.class));
            }
        });
    }
}
