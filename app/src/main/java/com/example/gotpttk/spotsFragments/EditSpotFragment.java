package com.example.gotpttk.spotsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;

public class EditSpotFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_edit_spot, container, false);
        Button editSearch = (Button) view.findViewById(R.id.buttonEditSearchSpot);
        editSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // TU BEDZIE SEARCH PUNKTU
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditChosenSpotFragment()).commit();
            }
        });
        return view;
    }
}