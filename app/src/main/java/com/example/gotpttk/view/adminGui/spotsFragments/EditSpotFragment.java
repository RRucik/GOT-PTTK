package com.example.gotpttk.view.adminGui.spotsFragments;

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

import java.util.ArrayList;
import java.util.List;
import com.example.gotpttk.model.spotModels.SpotListViewAdapter;

public class EditSpotFragment extends Fragment
{
    List<Spot> spots = new ArrayList<Spot>();
    Button editSearch;
    EditText etSpotNameFilter;
    EditText etSpotHeightFilter;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_edit_spot, container, false);
        editSearch = (Button) view.findViewById(R.id.buttonEditSearchSpot);
        etSpotNameFilter = view.findViewById(R.id.editTextEditSearchSpotName);
        etSpotHeightFilter = view.findViewById(R.id.editTextEditSearchSpotHeight);
        editSearch.setOnClickListener(new View.OnClickListener()
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
                    if (name.isEmpty())
                    {
                        name = null;
                    }

                    // Repairing integers
                    if (!height.isEmpty())
                    {
                        heightAsInt = Integer.parseInt(height);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Nie można dodać punktu - dane wprowadzone w złym formacie", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                spots = databaseHelper.getFilteredSpots(name, heightAsInt);
                if(spots.isEmpty()){
                    Toast.makeText(getContext(), "Brak punktów spełniających kryteria", Toast.LENGTH_SHORT).show();
                }
                else{
                    ListView listview = (ListView)view.findViewById(R.id.listViewEditSpotSearch);
                    listview.setAdapter(new SpotListViewAdapter(getActivity(), spots));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Bundle passId = new Bundle();
                            passId.putLong("id", id);
                            EditChosenSpotFragment fragment = new EditChosenSpotFragment();
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