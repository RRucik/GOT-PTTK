package com.example.gotpttk.view.adminGui.sectionsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.sectionModels.SectionListViewAdapter;
import com.example.gotpttk.model.spotModels.SpotListViewAdapter;
import com.example.gotpttk.view.adminGui.spotsFragments.EditChosenSpotFragment;

import java.util.ArrayList;
import java.util.List;

public class EditSectionFragment extends Fragment
{
    List<Section> sections = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_edit_section, container, false);
        Button editSearch = (Button) view.findViewById(R.id.buttonEditSearchSection);
        editSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewInner)
            {
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

                // UZUPELNIENIE LISTY SECTIONS METODA Z HELPERA ORAZ ODCZYTANIE Z POL DANYCH DO FILTRU
                //sections = databaseHelper.getAllSections();

                if(sections.isEmpty()){
                    Toast.makeText(getContext(), "Brak odcinków spełniających kryteria", Toast.LENGTH_SHORT).show();
                }
                else{
                    ListView listview = (ListView)view.findViewById(R.id.listViewEditSectionSearch);
                    listview.setAdapter(new SectionListViewAdapter(getActivity(), sections));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Bundle passId = new Bundle();
                            passId.putLong("id", id);
                            EditChosenSectionFragment fragment = new EditChosenSectionFragment();
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