package com.example.manutd.soccersocialnetwork.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.activity.HomeActivity;
import com.example.manutd.soccersocialnetwork.R;

/**
 * Created by manutd on 20/10/2016.
 */

public class LoginFragment extends Fragment {
    Button btnLogin, btnRegister;
    SharedPreferences settings;
    EditText edtUsername, edtPassword;
    String username, password;
    String checkUser, checkPassword;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtUsername = (EditText) view.findViewById(R.id.edtUsername);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();

        if (settings != null) {
            checkUser = settings.getString("username", "");
            checkPassword = settings.getString("password", "");
            edtUsername.setText(checkUser);
            edtPassword.setText(checkPassword);
        }

        Toast.makeText(getContext(), checkUser + " " + checkPassword, Toast.LENGTH_SHORT).show();

        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();

                if (username.equals(checkUser) && password.equals(checkPassword)) {
                    startActivity(new Intent(getContext(), HomeActivity.class));
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "User or Password is InCorect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
