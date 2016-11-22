package com.example.manutd.soccersocialnetwork.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.fragment.LoginFragment;
import com.example.manutd.soccersocialnetwork.fragment.RegisterFragment;

/**
 * Created by manutd on 19/10/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fm;
    FragmentTransaction ft;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                btnLogin.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
                ft.replace(R.id.frContainer, new LoginFragment());
                ft.commit();
                break;
            case R.id.btnRegister:
                btnLogin.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
                ft.replace(R.id.frContainer, new RegisterFragment());
                ft.commit();
                break;
        }
    }
}
