package com.example.gotpttk.view.adminGui.sectionsFragments;

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
import com.example.gotpttk.model.dbModels.Section;


public class RemoveChosenSectionFragment extends Fragment
{
    Button btnRemoveSection;
    TextView tvRemoveSectionStart;
    TextView tvRemoveSectionEnd;
    TextView tvRemoveSectionLength;
    TextView tvRemoveSectionMountain;
    TextView tvRemoveSectionPointsTo;
    TextView tvRemoveSectionPointsFrom;
    TextView tvRemoveSectionDesc;
    long SectionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remove_chosen_section, container, false);
        SectionId = getArguments().getLong("id");
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        final Section current = databaseHelper.getSection(SectionId);

        btnRemoveSection = (Button)view.findViewById(R.id.buttonRemoveSection);
        tvRemoveSectionStart = view.findViewById(R.id.textViewStartingSpotValue);
        tvRemoveSectionStart.setText(databaseHelper.getSpot(current.getIdSpStart()).getName());
        tvRemoveSectionEnd = view.findViewById(R.id.textViewEndingSpotValue);
        tvRemoveSectionEnd.setText(databaseHelper.getSpot(current.getIdSpEnd()).getName());
        tvRemoveSectionLength = view.findViewById(R.id.textViewLengthValue);
        tvRemoveSectionLength.setText(Integer.toString(current.getLength()));
        tvRemoveSectionMountain = view.findViewById(R.id.textViewMountainRangeValue);
        tvRemoveSectionMountain.setText(current.getMountainRange());
        tvRemoveSectionPointsTo = view.findViewById(R.id.textViewPointsValue);
        tvRemoveSectionPointsTo.setText(Integer.toString(current.getPointsTo()));
        String pointsFrom = "";
        if(current.getPointsFrom() != null){
            pointsFrom = Integer.toString(current.getPointsFrom());
        }
        tvRemoveSectionPointsFrom = view.findViewById(R.id.textViewReturnPointsValue);
        tvRemoveSectionPointsFrom.setText(pointsFrom);
        tvRemoveSectionDesc = view.findViewById(R.id.textViewSectionDesc);
        tvRemoveSectionDesc.setText(current.getDesc());
        btnRemoveSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                databaseHelper.deleteSection(SectionId);
                Toast.makeText(view.getContext(), "Odcinek pomyślnie usunięty", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}