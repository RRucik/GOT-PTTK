package com.example.gotpttk.view.adminGui.spotsFragments;

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
import com.example.gotpttk.model.dbModels.Spot;
import com.example.gotpttk.view.adminGui.sectionsFragments.EditChosenSectionFragment;

public class AddSpotFragment extends Fragment
{
    Button btnAddSpot;
    EditText etSpotName;
    EditText etSpotHeight;
    EditText etSpotDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_spot, container, false);
        btnAddSpot = (Button) view.findViewById(R.id.buttonAddSpot);
        etSpotName = view.findViewById(R.id.editTextSpotName);
        etSpotHeight = view.findViewById(R.id.editTextSpotHeight);
        etSpotDesc = view.findViewById(R.id.editTextSpotDesc);
        btnAddSpot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Spot spot;
                String name = etSpotName.getText().toString();
                String height = etSpotHeight.getText().toString();
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