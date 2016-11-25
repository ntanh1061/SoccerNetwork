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
import com.example.manutd.soccersocialnetwork.model.UserModel;
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

public class LoginFragment extends Fragment {
    Button btnLogin, btnRegister;
    SharedPreferences settings;
    EditText edtUsername, edtPassword;
    String username, password;
    String checkUser, checkPassword;
    SharedPreferences.Editor editor;
    List<UserModel> userList;
    String fullName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtUsername = (EditText) view.findViewById(R.id.edtUsername);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        userList = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<UserModel>> call = apiInterface.getUser();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                userList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();

        if (settings != null) {
            checkUser = settings.getString("username", "");
            checkPassword = settings.getString("password", "");
            edtUsername.setText(checkUser);
            edtPassword.setText(checkPassword);
        }

        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                for (int i = 0; i < userList.size(); i++) {
                    String usernameValid = userList.get(i).getmUserName();
                    String passwordValid = userList.get(i).getmPassword();
                    if (username.equals(usernameValid) && password.equals(passwordValid)) {
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        startActivity(new Intent(getContext(), HomeActivity.class));
                        getActivity().finish();
                        break;
                    } else {
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        Toast.makeText(getContext(), "User or Password is InCorect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}
