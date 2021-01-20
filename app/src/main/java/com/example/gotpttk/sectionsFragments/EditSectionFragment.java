package com.example.gotpttk.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;
import com.example.gotpttk.spotsFragments.EditChosenSpotFragment;

public class EditSectionFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_edit_section, container, false);
        Button editSearch = (Button) view.findViewById(R.id.buttonEditSearchSection);
        editSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditChosenSectionFragment()).commit();
            }
        });
        return view;
    }
}