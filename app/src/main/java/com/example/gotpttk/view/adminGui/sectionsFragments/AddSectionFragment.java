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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    String startSpotName;
    String endSpotName;
    String length;
    String mountainRange;
    String pointsTo;
    String pointsFrom;
    String activeSince;
    String desc;
    Integer lengthAsInt;
    Integer pointsToAsInt;
    Integer pointsFromAsInt;

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
        etEditActiveSince = view.findViewById(R.id.editTextActiveSince);
        etEditSectionDesc = view.findViewById(R.id.editTextSectionDesc);
        btnSaveSectionChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Section section;

                try
                {
                    repairInput(view);
                    DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                    Spot spotStart = databaseHelper.getSpotWithName(startSpotName);
                    Spot spotEnd = databaseHelper.getSpotWithName(endSpotName);

                    if (spotStart == null || spotEnd == null)
                    {
                        Toast.makeText(view.getContext(), "Nie można dodać odcinka - punkty o podanych nazwach nie istnieją", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Integer heightDiff = null;
                        if(spotStart.getHeight() != null && spotEnd.getHeight() != null)
                        {
                            heightDiff = Math.abs(spotStart.getHeight() - spotEnd.getHeight());
                        }
                        section = new Section(spotStart.getIdSp(), spotEnd.getIdSp(), lengthAsInt, mountainRange, pointsToAsInt, pointsFromAsInt, activeSince, desc, heightDiff);
                        boolean success = databaseHelper.createSection(section);
                        if (success)
                        {
                            Toast.makeText(view.getContext(), "Odcinek pomyślnie dodany", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Nie można dodać odcinka - niektóre wymagane pola nie wypełnione", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można dodać odcinka - dane wprowadzone w złym formacie (data musi być wprowadzona w formacie DD/MM/YY)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void repairInput(View view) throws ParseException
    {
        startSpotName = etEditSectionStart.getText().toString();
        endSpotName = etEditSectionEnd.getText().toString();
        length = etEditSectionLength.getText().toString();
        mountainRange = etEditSectionMountain.getText().toString();
        pointsTo = etEditSectionPointsTo.getText().toString();
        pointsFrom = etEditSectionPointsFrom.getText().toString();
        activeSince = etEditActiveSince.getText().toString();
        desc = etEditSectionDesc.getText().toString();

        lengthAsInt = null;
        pointsToAsInt = null;
        pointsFromAsInt = null;

        // Repairing strings
        if (startSpotName.isEmpty())
            startSpotName = null;
        if (endSpotName.isEmpty())
            endSpotName = null;
        if (mountainRange.isEmpty())
            mountainRange = null;

        SimpleDateFormat sdformat = new SimpleDateFormat("DD/MM/YYYY");
        if (activeSince.isEmpty())
            activeSince = sdformat.format((new Date(System.currentTimeMillis())));
        else if(sdformat.parse(activeSince) == null){
            Toast.makeText(view.getContext(), "Data musi być podana w formacie DD/MM/YYYY", Toast.LENGTH_SHORT).show();
            return;
        }
        if (desc.isEmpty())
            desc = null;

        // Repairing integers
        if (!length.isEmpty())
            lengthAsInt = Integer.parseInt(length);
        if (!pointsTo.isEmpty())
            pointsToAsInt = Integer.parseInt(pointsTo);
        if (!pointsFrom.isEmpty())
            pointsFromAsInt = Integer.parseInt(pointsFrom);
    }
}