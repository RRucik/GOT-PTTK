package com.example.gotpttk.view.guestGui.loginFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;
import com.example.gotpttk.presenter.AdminActivity;

public class LoginFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnLogin = view.findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}