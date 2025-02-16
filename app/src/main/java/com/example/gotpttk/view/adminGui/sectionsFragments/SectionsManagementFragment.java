package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;

public class SectionsManagementFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections_management, container, false);
        Button addButton = (Button) view.findViewById(R.id.buttonAddSections);
        Button editButton = (Button) view.findViewById(R.id.buttonEditSections);
        Button removeButton = (Button) view.findViewById(R.id.buttonRemoveSections);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSectionFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Dodaj odcinek");
            }
        });
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditSectionFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Edytuj odcinek");
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemoveSectionFragment()).addToBackStack("tag2").commit();
                ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Usuń odcinek");
            }
        });
        return view;
    }
}