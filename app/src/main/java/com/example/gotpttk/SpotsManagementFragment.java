package com.example.gotpttk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SpotsManagementFragment extends Fragment
{
    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spots_management, container, false);
        addButton = (Button) view.findViewById(R.id.buttonAddPoints);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSpotFragment()).commit();
            }
        });
        return view;
    }
}