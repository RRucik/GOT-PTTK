package com.example.gotpttk.view.adminGui.spotsFragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;

public class SpotsManagementFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spots_management, container, false);
        Button addButton = (Button) view.findViewById(R.id.buttonAddSpots);
        Button editButton = (Button) view.findViewById(R.id.buttonEditSpots);
        Button removeButton = (Button) view.findViewById(R.id.buttonRemoveSpots);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSpotFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Dodaj punkt");
            }
        });
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditSpotFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Edytuj punkt");
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemoveSpotFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Usuń punkt");
            }
        });
        return view;
    }
}