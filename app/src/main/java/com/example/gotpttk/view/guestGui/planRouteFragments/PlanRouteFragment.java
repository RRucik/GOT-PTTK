package com.example.gotpttk.view.guestGui.planRouteFragments;

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
import com.example.gotpttk.model.dbModels.Spot;
import com.example.gotpttk.model.sectionModels.SectionWithDirection;
import com.example.gotpttk.model.spotModels.SpotListViewAdapter;
import com.example.gotpttk.view.adminGui.spotsFragments.EditChosenSpotFragment;

import java.util.ArrayList;
import java.util.List;


public class PlanRouteFragment extends Fragment
{
    List<Spot> spots = new ArrayList<Spot>();
    Button routeSearch;
    EditText etSpotNameFilter;
    EditText etSpotHeightFilter;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_filter_spot, container, false);
        routeSearch = (Button) view.findViewById(R.id.buttonSearchSpot);
        etSpotNameFilter = view.findViewById(R.id.editTextSearchSpotName);
        etSpotHeightFilter = view.findViewById(R.id.editTextSearchSpotHeight);
        routeSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewInner)
            {
                String name = etSpotNameFilter.getText().toString();
                String height = etSpotHeightFilter.getText().toString();
                Integer heightAsInt = null;
                try
                {
                    // Repairing strings
                    /*
                    if (name.isEmpty())
                    {
                        name = null;
                    }
                    */

                    // Repairing integers
                    if (!height.isEmpty())
                    {
                        heightAsInt = Integer.parseInt(height);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można wyszukać punktów - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                spots = databaseHelper.getFilteredSpots(name, heightAsInt);
                listView = (ListView)view.findViewById(R.id.listViewSpotSearch);
                listView.setAdapter(new SpotListViewAdapter(getActivity(), spots));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle passId = new Bundle();
                        List<SectionWithDirection> sections = databaseHelper.getSectionsFromPoint(id);
                        if(sections.isEmpty()){
                            Toast.makeText(view.getContext(), "Brak odcinków z tego punktu", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            AddSectionToRouteFragment fragment = new AddSectionToRouteFragment(id, sections);
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag2").commit();
                        }
                    }
                });
                if(spots.isEmpty()){
                    Toast.makeText(getContext(), "Brak punktów spełniających kryteria", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setRetainInstance(true);
        return view;
    }
}