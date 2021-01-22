package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;


public class
EditChosenSectionFragment extends Fragment
{
    Button btnSaveSectionChanges;
    EditText etEditSectionStart;
    EditText etEditSectionEnd;
    EditText etEditSectionLength;
    EditText etEditSectionMountain;
    EditText etEditSectionPointsTo;
    EditText etEditSectionPointsFrom;
    EditText etEditSectionDesc;
    long SectionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_chosen_section, container, false);
        SectionId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        // POD CURRENT PODPIAC SECTION Z HELPERA O DANYM ID
        Section current = new Section();
        //

        btnSaveSectionChanges = (Button)view.findViewById(R.id.buttonEditSection);
        etEditSectionStart = view.findViewById(R.id.editTextStartingSpot);
        //SET TEXT JAK W SPOT CHOSEN FRAGMENT - ANALOGICZNIE DLA RESZTY NIZEJ
        etEditSectionEnd = view.findViewById(R.id.editTextEndingSpot);

        etEditSectionLength = view.findViewById(R.id.editTextLength);

        etEditSectionMountain = view.findViewById(R.id.editTextMountainRange);

        etEditSectionPointsTo = view.findViewById(R.id.editTextPoints);

        etEditSectionPointsFrom = view.findViewById(R.id.editTextReturnPoints);

        etEditSectionDesc = view.findViewById(R.id.editTextSectionDesc);

        btnSaveSectionChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update odcinka!
            }
        });

        return view;
    }
}