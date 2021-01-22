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
            public void onClick(View v)
            {
                Section section;
                String startPointName = etSpotName.getText().toString();
                String endPointName = etSpotHeight.getText().toString();
                String desc = etSpotDesc.getText().toString();
                Integer heightAsInt = null;

                try
                {
                    // Repairing strings
                    if (name.isEmpty())
                    {
                        name = null;
                    }
                    if (desc.isEmpty())
                    {
                        desc = null;
                    }
                    // Repairing integers
                    if (!height.isEmpty())
                    {
                        heightAsInt = Integer.parseInt(height);
                    }

                    spot = new Spot(name, heightAsInt, desc);
                    DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                    boolean success = databaseHelper.createSpot(spot);
                    if (success)
                    {
                        Toast.makeText(view.getContext(), "Punkt pomyślnie dodany", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(view.getContext(), "Nie można dodać punktu - niektóre wymagane pola nie wypełnione", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można dodać punktu - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}