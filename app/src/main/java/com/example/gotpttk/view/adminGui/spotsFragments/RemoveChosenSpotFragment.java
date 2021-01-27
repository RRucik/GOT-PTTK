package com.example.gotpttk.view.adminGui.spotsFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Spot;

public class RemoveChosenSpotFragment extends Fragment
{
    Button btnRemoveSpot;
    TextView tvRemoveSpotName;
    TextView tvRemoveSpotHeight;
    TextView tvRemoveSpotDesc;
    long SpotId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remove_chosen_spot, container, false);
        SpotId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        Spot current = databaseHelper.getSpot(SpotId);
        btnRemoveSpot = (Button) view.findViewById(R.id.buttonRemoveSpot);
        tvRemoveSpotName = view.findViewById(R.id.textViewRemoveSpotNameValue);
        tvRemoveSpotName.setText(current.getName());
        tvRemoveSpotHeight = view.findViewById(R.id.textViewRemoveSpotHeightValue);
        String height = "";
        if(current.getHeight() != null){
            height = Integer.toString(current.getHeight());
        }
        tvRemoveSpotHeight.setText(height);
        tvRemoveSpotDesc = view.findViewById(R.id.textViewRemoveSpotDescValue);
        tvRemoveSpotDesc.setText(current.getDesc());
        btnRemoveSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                boolean success = databaseHelper.deleteSpot(SpotId);
                if (success)
                {
                    Toast.makeText(view.getContext(), "Punkt pomyślnie usunięty", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }
                else
                {
                    Toast.makeText(view.getContext(), "Punkt nie może zostać usunięty - jest częścią odcinka", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}