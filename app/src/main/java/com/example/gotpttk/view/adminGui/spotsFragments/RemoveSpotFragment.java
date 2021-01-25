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
import com.example.gotpttk.model.spotModels.SpotListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RemoveSpotFragment extends Fragment
{
    List<Spot> spots = new ArrayList<Spot>();
    Button removeSearch;
    EditText etSpotNameFilter;
    EditText etSpotHeightFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_filter_spot, container, false);
        removeSearch = (Button) view.findViewById(R.id.buttonSearchSpot);
        etSpotNameFilter = view.findViewById(R.id.editTextSearchSpotName);
        etSpotHeightFilter = view.findViewById(R.id.editTextSearchSpotHeight);
        removeSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewInner)
            {
                String name = etSpotNameFilter.getText().toString();
                String height = etSpotHeightFilter.getText().toString();
                Integer heightAsInt = null;
                try
                {
                    // Repairing string

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
                    ListView listview = (ListView)view.findViewById(R.id.listViewSpotSearch);
                    listview.setAdapter(new SpotListViewAdapter(getActivity(), spots));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Bundle passId = new Bundle();
                            passId.putLong("id", (int)id);
                            RemoveChosenSpotFragment fragment = new RemoveChosenSpotFragment();
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