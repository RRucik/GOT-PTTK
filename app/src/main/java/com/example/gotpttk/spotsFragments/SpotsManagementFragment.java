package com.example.gotpttk.spotsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;

public class SpotsManagementFragment extends Fragment
{
    private Button addButton;
    private Button editButton;
    private Button removeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spots_management, container, false);
        addButton = (Button) view.findViewById(R.id.buttonAddSpots);
        editButton = (Button) view.findViewById(R.id.buttonEditSpots);
        removeButton = (Button) view.findViewById(R.id.buttonRemoveSpots);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSpotFragment()).commit();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditSpotFragment()).commit();
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemoveSpotFragment()).commit();
            }
        });
        return view;
    }
}