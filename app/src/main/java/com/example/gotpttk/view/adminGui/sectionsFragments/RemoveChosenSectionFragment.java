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


public class RemoveChosenSectionFragment extends Fragment
{
    Button btnRemoveSection;
    EditText tvRemoveSectionStart;
    EditText tvRemoveSectionEnd;
    EditText tvRemoveSectionLength;
    EditText tvRemoveSectionMountain;
    EditText tvRemoveSectionPointsTo;
    EditText tvRemoveSectionPointsFrom;
    EditText tvRemoveSectionDesc;
    long SectionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_chosen_section, container, false);
        SectionId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        //Uzupelnic danymi - zamiast new section dac getSection(id)
        Section current = new Section();
        btnRemoveSection = (Button)view.findViewById(R.id.buttonRemoveSection);
        tvRemoveSectionStart = view.findViewById(R.id.textViewStartingSpotValue);
        //set
        tvRemoveSectionEnd = view.findViewById(R.id.textViewEndingSpotValue);

        tvRemoveSectionLength = view.findViewById(R.id.textViewLengthValue);

        tvRemoveSectionMountain = view.findViewById(R.id.textViewMountainRangeValue);

        tvRemoveSectionPointsTo = view.findViewById(R.id.textViewPointsValue);

        tvRemoveSectionPointsFrom = view.findViewById(R.id.textViewReturnPointsValue);

        tvRemoveSectionDesc = view.findViewById(R.id.textViewSectionDesc);

        btnRemoveSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete odcinka!
            }
        });

        return view;
    }
}