package com.example.manutd.soccersocialnetwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by manutd on 19/10/2016.
 */

public class ProfileInformationFragment extends Fragment {
    ApiInterface apiInterface;
    List<String> fieldList;
    EditText edtUsername, edtPhoneNumber, edtLastLogin, edtUserType, edtEmail, edtField;
    CheckBox checkBox;
    UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_information, container, false);
        edtUsername = (EditText) view.findViewById(R.id.edUsername);
        edtPhoneNumber = (EditText) view.findViewById(R.id.edPhone);
        edtLastLogin = (EditText) view.findViewById(R.id.edLastLogin);
        edtUserType = (EditText) view.findViewById(R.id.edUserType);
        edtEmail = (EditText) view.findViewById(R.id.edEmail);
        edtField = (EditText) view.findViewById(R.id.edField);
        checkBox = (CheckBox) view.findViewById(R.id.checkboxVerified);

        fieldList = new ArrayList<>();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getArguments();
        userModel = (UserModel) bundle.getSerializable("user");
        Log.d(getClass().getSimpleName(), userModel.toString());
        final String districtName = userModel.getDistrictName();
        final String username = userModel.getUsername();
        final String phoneNumber = userModel.getPhoneNumber();
        final String joinDate = userModel.getLastLogin();
        final int userType = userModel.getUserType();
        final boolean verify = userModel.isVerified();
        final String email = userModel.getEmail();
        final String lastLogin = bundle.getString("lastLogin");

        edtUsername.setText(username);
        edtPhoneNumber.setText(phoneNumber);
        edtLastLogin.setText(lastLogin);
        edtUserType.setText(userType + "");
        edtEmail.setText(email);
        edtField.setText(districtName);
        checkBox.setChecked(verify);

        view.findViewById(R.id.btnUpdateProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    UserModel userModelUpdate = new UserModel(userModel.getDistrictId(), userModel.getUserId(), districtName, edtPhoneNumber.getText().toString(), userModel.getStatus(), username, userType, userModel.getVerificationCode(), verify, edtEmail.getText().toString(), lastLogin, userModel.getPassword());
                    Call<UserModel> call = apiInterface.updateUser(userModelUpdate);
                    call.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {

                        }
                    });
                    Toast.makeText(getContext(), "Cap nhat thanh cong!", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, new MyMatchFragment()).commit();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Cap nhat that bai!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
