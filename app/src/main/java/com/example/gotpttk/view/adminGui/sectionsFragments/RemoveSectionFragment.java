package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.sectionModels.SectionListViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RemoveSectionFragment extends Fragment
{
    List<Section> sections = new ArrayList<>();
    EditText etSectionStart;
    EditText etSectionEnd;
    EditText etSectionLength;
    EditText etSectionMountain;
    EditText etSectionPoints;
    EditText etSectionActive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_remove_section, container, false);
        Button removeSearch = (Button) view.findViewById(R.id.buttonRemoveSearchSection);
        etSectionStart = view.findViewById(R.id.editTextStartingSpot);
        etSectionEnd = view.findViewById(R.id.editTextEndingSpot);
        etSectionLength = view.findViewById(R.id.editTextMinLength);
        etSectionMountain = view.findViewById(R.id.editTextMountainRange);
        etSectionPoints = view.findViewById(R.id.editTextMinPoints);
        etSectionActive = view.findViewById(R.id.editTextActiveSince);
        removeSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewInner)
            {
                String start = etSectionStart.getText().toString();
                String end = etSectionEnd.getText().toString();
                String length = etSectionLength.getText().toString();
                String mountain = etSectionMountain.getText().toString();
                String points = etSectionPoints.getText().toString();
                String active = etSectionActive.getText().toString();
                Integer lengthAsInt = null;
                Integer pointsAsInt = null;

                try{
                    if(!length.isEmpty()){
                        lengthAsInt = Integer.parseInt(length);
                    }
                    if(!points.isEmpty()){
                        pointsAsInt = Integer.parseInt(points);
                    }
                    if(active.isEmpty()){
                        active = null;
                    }
                    else{
                        SimpleDateFormat sdformat = new SimpleDateFormat("DD/MM/YYYY");
                        if(sdformat.parse(active) == null){
                            Toast.makeText(view.getContext(), "Data musi być podana w formacie DD/MM/YYYY", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    if(start.isEmpty()){
                        start = null;
                    }
                    if(end.isEmpty()){
                        end = null;
                    }
//                    if(mountain.isEmpty()){
//                        mountain = null;
//                    }
                }
                catch(Exception e){
                    Toast.makeText(view.getContext(), "Nie można wyszukać odcinków - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

                sections = databaseHelper.getFilteredSections(start, end, lengthAsInt, mountain, pointsAsInt, active);

                if(sections.isEmpty()){
                    Toast.makeText(getContext(), "Brak odcinków spełniających kryteria", Toast.LENGTH_SHORT).show();
                }
                else{
                    ListView listview = (ListView)view.findViewById(R.id.listViewRemoveSectionSearch);
                    listview.setAdapter(new SectionListViewAdapter(getActivity(), sections));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Bundle passId = new Bundle();
                            passId.putLong("id", id);
                            RemoveChosenSectionFragment fragment = new RemoveChosenSectionFragment();
                            fragment.setArguments(passId);
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }
                    });
                }
            }
        });
        return view;
    }
}