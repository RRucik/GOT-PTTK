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

public class EditChosenSpotFragment extends Fragment
{
    Button btnSaveSpotChanges;
    EditText etEditSpotName;
    EditText etEditSpotHeight;
    EditText etEditSpotDesc;
    long SpotId;
    String name;
    String height;
    String desc;
    Integer heightAsInt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_chosen_spot, container, false);
        SpotId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        Spot current = databaseHelper.getSpot(SpotId);
        btnSaveSpotChanges = (Button) view.findViewById(R.id.buttonEditSpot);
        etEditSpotName = view.findViewById(R.id.editTextEditSpotName);
        etEditSpotName.setText(current.getName());
        etEditSpotHeight = view.findViewById(R.id.editTextEditSpotHeight);
        String height = "";
        if(current.getHeight() != null){
            height = Integer.toString(current.getHeight());
        }
        etEditSpotHeight.setText(height);
        etEditSpotDesc = view.findViewById(R.id.editTextEditSpotDesc);
        etEditSpotDesc.setText(current.getDesc());
        btnSaveSpotChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    repairInput();
                    Spot spot = new Spot((int)SpotId, name, heightAsInt, desc);
                    DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                    boolean success = databaseHelper.updateSpot(spot);
                    if (success)
                    {
                        Toast.makeText(view.getContext(), "Punkt pomyślnie edytowany", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(view.getContext(), "Nie można edytować punktu - niektóre wymagane pola nie wypełnione lub istenie punkt o podanej nazwie", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można edytować punktu - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void repairInput()
    {
        name = etEditSpotName.getText().toString();
        height = etEditSpotHeight.getText().toString();
        desc = etEditSpotDesc.getText().toString();
        heightAsInt = null;
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
    }
}