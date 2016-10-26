package com.example.manutd.soccersocialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by manutd on 19/10/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin,btnRegister;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                break;

            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }

    }
}
