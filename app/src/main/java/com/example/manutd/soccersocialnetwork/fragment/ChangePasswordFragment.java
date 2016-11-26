package com.example.manutd.soccersocialnetwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.model.UserModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manutd on 20/10/2016.
 */

public class ChangePasswordFragment extends Fragment {
    String password;
    EditText edtCurentPassword, edtNewPassword, edtConfirmPasword;
    String currentPass, newPass, confirmPass;
    UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        edtCurentPassword = (EditText) view.findViewById(R.id.edtCurentPassword);
        edtNewPassword = (EditText) view.findViewById(R.id.edtNewPassword);
        edtConfirmPasword = (EditText) view.findViewById(R.id.edtConfirmPassword);

        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Bundle bundle = getArguments();
        userModel = (UserModel) bundle.getSerializable("user");

        password = userModel.getPassword();
        view.findViewById(R.id.btnSavePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPass = edtCurentPassword.getText().toString();
                newPass = edtNewPassword.getText().toString();
                confirmPass = edtConfirmPasword.getText().toString();

                if (currentPass.equals(password) && newPass.equals(confirmPass)) {
                    UserModel userModel1 = new UserModel(userModel.getDistrictId(), userModel.getUserId(), userModel.getDistrictName(), userModel.getPhoneNumber(), userModel.getStatus(), userModel.getUsername(), userModel.getUserType(), userModel.getVerificationCode(), userModel.isVerified(), userModel.getEmail(), userModel.getLastLogin(), confirmPass);
                    Log.d(getClass().getSimpleName(), userModel1.toString());

                    Call<UserModel> call = apiInterface.updateUser(userModel1);
                    call.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {

                        }
                    });
                    Toast.makeText(getContext(), "Thay doi mat khau thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Moi ban nhap lai mat khau cho dung!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
