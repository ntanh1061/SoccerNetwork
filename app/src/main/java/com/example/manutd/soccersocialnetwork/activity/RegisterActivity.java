package com.example.manutd.soccersocialnetwork.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.manutd.soccersocialnetwork.R;

/**
 * Created by manutd on 20/10/2016.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{
    Spinner spinner;
    Button btnRegister;
    ArrayAdapter<String> listSpiner;
    final String [] DISTRICT = {"Lien Chieu","Hai Chau","Hoa Minh","Hoa Vang","Thanh Khe","Hoa Cam","Hoa Minh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_logout);

        spinner = (Spinner)findViewById(R.id.spiner);
        spinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this,R.layout.support_simple_spinner_dropdown_item,DISTRICT));

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
