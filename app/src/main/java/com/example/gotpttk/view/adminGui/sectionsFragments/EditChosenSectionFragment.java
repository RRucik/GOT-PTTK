package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.dbModels.Spot;


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
//        SectionId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        // POD CURRENT PODPIAC SECTION Z HELPERA O DANYM ID
        Section current = new Section();
        //

        btnSaveSectionChanges = (Button)view.findViewById(R.id.buttonEditSection);
        etEditSectionStart = view.findViewById(R.id.editTextStartingSpot);
        etEditSectionEnd = view.findViewById(R.id.editTextEndingSpot);
        etEditSectionLength = view.findViewById(R.id.editTextLength);
        etEditSectionMountain = view.findViewById(R.id.editTextMountainRange);
        etEditSectionPointsTo = view.findViewById(R.id.editTextPoints);
        etEditSectionPointsFrom = view.findViewById(R.id.editTextReturnPoints);
        etEditSectionDesc = view.findViewById(R.id.editTextSectionDesc);
        btnSaveSectionChanges.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Section section;
                String startSpotName = etEditSectionStart.getText().toString();
                String endSpotName = etEditSectionEnd.getText().toString();
                String length = etEditSectionLength.getText().toString();
                String mountainRange = etEditSectionMountain.getText().toString();
                String pointsTo = etEditSectionPointsTo.getText().toString();
                String pointsFrom = etEditSectionPointsFrom.getText().toString();
                // Trzeba dorobic pole active w edicie albo przekazywac wartosc currenta
//                String activeSince = etEditActiveSince.getText().toString();
                String desc = etEditSectionDesc.getText().toString();

                Integer lengthAsInt = null;
                Integer pointsToAsInt = null;
                Integer pointsFromAsInt = null;

                try
                {
                    // Repairing strings
                    if (startSpotName.isEmpty())
                        startSpotName = null;
                    if (endSpotName.isEmpty())
                        endSpotName = null;
                    if (mountainRange.isEmpty())
                        mountainRange = null;
//                    if (activeSince.isEmpty())
//                        activeSince = null;
                    if (desc.isEmpty())
                        desc = null;

                    // Repairing integers
                    if (!length.isEmpty())
                        lengthAsInt = Integer.parseInt(length);
                    if (!pointsTo.isEmpty())
                        pointsToAsInt = Integer.parseInt(pointsTo);
                    if (!pointsFrom.isEmpty())
                        pointsFromAsInt = Integer.parseInt(pointsFrom);

                    DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                    Spot spotStart = databaseHelper.getSpotWithName(startSpotName);
                    Spot spotEnd = databaseHelper.getSpotWithName(endSpotName);

                    if (spotStart == null || spotEnd == null)
                    {
                        Toast.makeText(view.getContext(), "Nie można edytować odcinka - punkty o podanych nazwach nie istnieją", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Integer heightDiff = null;
                        if(spotStart.getHeight() != null && spotEnd.getHeight() != null)
                        {
                            heightDiff = Math.abs(spotStart.getHeight() - spotEnd.getHeight());
                        }
                        section = new Section(1, spotStart.getIdSp(), spotEnd.getIdSp(), lengthAsInt, mountainRange, pointsToAsInt, pointsFromAsInt, "23/11/2020", desc, heightDiff);
                        boolean success = databaseHelper.updateSection(section);
                        if (success)
                        {
                            Toast.makeText(view.getContext(), "Odcinek pomyślnie edytowany", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Nie można edytować odcinka - niektóre wymagane pola nie wypełnione", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można edytować odcinka - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}