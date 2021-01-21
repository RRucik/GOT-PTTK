package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;


public class RemoveSectionFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remove_section, container, false);
        Button removeSearch = (Button) view.findViewById(R.id.buttonRemoveSearchSection);
        removeSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TU BEDZIE SEARCH ODCINKA
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemoveChosenSectionFragment()).commit();
            }
        });
        return view;
    }
}