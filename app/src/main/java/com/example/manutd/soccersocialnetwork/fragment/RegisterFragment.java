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

import com.example.manutd.soccersocialnetwork.activity.HomeActivity;
import com.example.manutd.soccersocialnetwork.R;

/**
 * Created by nta on 19/11/2016.
 */

public class RegisterFragment extends Fragment {
    ArrayAdapter<String> listSpiner;
    final String[] DISTRICT = {"Lien Chieu", "Hai Chau", "Hoa Minh", "Hoa Vang", "Thanh Khe", "Hoa Cam", "Hoa Minh"};
    Spinner spinner;
    EditText edtUser, edtPassword, edtEmail, edtPhoneNumber;
    String user, password, email, phoneNumber, address;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        spinner = (Spinner) view.findViewById(R.id.spiner);
        edtUser = (EditText) view.findViewById(R.id.edUsername);
        edtEmail = (EditText) view.findViewById(R.id.edEmail);
        edtPassword = (EditText) view.findViewById(R.id.edPassword);
        edtPhoneNumber = (EditText) view.findViewById(R.id.edPhoneNumber);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, DISTRICT));

        view.findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = edtUser.getText().toString();
                password = edtPassword.getText().toString();
                email = edtEmail.getText().toString();
                phoneNumber = edtPhoneNumber.getText().toString();
                address = spinner.getSelectedItem().toString();
                startActivity(new Intent(getContext(), HomeActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
