package com.example.manutd.soccersocialnetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manutd.soccersocialnetwork.R;
import com.example.manutd.soccersocialnetwork.activity.HomeActivity;
import com.example.manutd.soccersocialnetwork.activity.LoginActivity;
import com.example.manutd.soccersocialnetwork.model.DistrictModel;
import com.example.manutd.soccersocialnetwork.model.UserModel;
import com.example.manutd.soccersocialnetwork.rest.ApiClient;
import com.example.manutd.soccersocialnetwork.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nta on 19/11/2016.
 */

public class RegisterFragment extends Fragment {
    final String[] DISTRICT = {"Lien Chieu", "Hai Chau", "Hoa Minh", "Hoa Vang", "Thanh Khe", "Hoa Cam", "Hoa Minh"};
    Spinner spinner;
    EditText edtUser, edtPassword, edtEmail, edtPhoneNumber;
    String user, password, email, phoneNumber, address;
    List<DistrictModel> districtList;
    ApiInterface apiInterface;
    List<UserModel> userModelList;
    int checkRegister = 0;
    int districtId;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        spinner = (Spinner) view.findViewById(R.id.spiner);
        userModelList = new ArrayList<UserModel>();
        edtUser = (EditText) view.findViewById(R.id.edUsername);
        edtEmail = (EditText) view.findViewById(R.id.edEmail);
        edtPassword = (EditText) view.findViewById(R.id.edPassword);
        edtPhoneNumber = (EditText) view.findViewById(R.id.edPhoneNumber);
        districtList = new ArrayList<>();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DistrictModel>> callDistricts = apiInterface.getDistricts();
        callDistricts.enqueue(new Callback<List<DistrictModel>>() {
            @Override
            public void onResponse(Call<List<DistrictModel>> call, Response<List<DistrictModel>> response) {
                districtList.addAll(response.body());
                String[] listDistricts = new String[districtList.size()];
                for (int i = 0; i < districtList.size(); i++) {
                    listDistricts[i] = districtList.get(i).getDistrictName();
                }
                spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, listDistricts));
            }

            @Override
            public void onFailure(Call<List<DistrictModel>> call, Throwable t) {

            }
        });

        final Call<List<UserModel>> userModelCall = apiInterface.getUser();
        userModelCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                userModelList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

        view.findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = edtUser.getText().toString();
                password = edtPassword.getText().toString();
                email = edtEmail.getText().toString();
                phoneNumber = edtPhoneNumber.getText().toString();
                address = spinner.getSelectedItem().toString();

                for (int i = 0; i < districtList.size(); i++) {
                    if (address.equals(districtList.get(i).getDistrictName())) {
                        districtId = districtList.get(i).getmDistrictId();
                        break;
                    }
                }

//                Toast.makeText(getContext(), districtId + ""+address, Toast.LENGTH_SHORT).show();
                UserModel userModel1 = new UserModel(districtId, address, phoneNumber, 0, user, 0, "", false, email, "", password);
                for (int i = 0; i < userModelList.size(); i++) {
                    if (user.equals(userModelList.get(i).getUsername())) {
                        Toast.makeText(getContext(), "Ten user da ton tai", Toast.LENGTH_SHORT).show();
                        checkRegister = 1;
                        break;
                    } else {
                    }
                }
                if (checkRegister == 0) {
                    Call<UserModel> userModelCall = apiInterface.createUser(userModel1);
                    userModelCall.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {

                        }
                    });
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

            }
        });
        return view;
    }
}
