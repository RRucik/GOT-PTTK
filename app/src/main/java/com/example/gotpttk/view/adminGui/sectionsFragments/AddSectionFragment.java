package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gotpttk.R;


public class AddSectionFragment extends Fragment
{
    Button btnSaveSectionChanges;
    EditText etEditSectionStart;
    EditText etEditSectionEnd;
    EditText etEditSectionLength;
    EditText etEditSectionMountain;
    EditText etEditSectionPointsTo;
    EditText etEditSectionPointsFrom;
    EditText etEditSectionDesc;
    EditText etEditActiveSince;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_section, container, false);
        btnSaveSectionChanges = (Button)view.findViewById(R.id.buttonAddSection);
        etEditSectionStart = view.findViewById(R.id.editTextStartingSpot);
        etEditSectionEnd = view.findViewById(R.id.editTextEndingSpot);
        etEditSectionLength = view.findViewById(R.id.editTextLength);
        etEditSectionMountain = view.findViewById(R.id.editTextMountainRange);
        etEditSectionPointsTo = view.findViewById(R.id.editTextPoints);
        etEditSectionPointsFrom = view.findViewById(R.id.editTextReturnPoints);
        etEditSectionDesc = view.findViewById(R.id.editTextSectionDesc);
        etEditActiveSince = view.findViewById(R.id.editTextActiveSince);
        btnSaveSectionChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add odcinka!
            }
        });

        return view;
    }
}