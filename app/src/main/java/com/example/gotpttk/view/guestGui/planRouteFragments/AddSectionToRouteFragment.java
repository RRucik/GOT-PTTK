package com.example.gotpttk.view.guestGui.planRouteFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.sectionModels.SectionListViewAdapter;
import com.example.gotpttk.model.sectionModels.SectionWithDirection;
import com.example.gotpttk.model.sectionModels.SectionWithDirectionListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddSectionToRouteFragment extends Fragment {

    List<SectionWithDirection> sections;
    List<SectionWithDirection> route;
    long spotId;
    ListView listview;
    Fragment lastFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSectionToRouteFragment() {
    }

    public AddSectionToRouteFragment(long id, List<SectionWithDirection> sections){
        this.spotId = id;
        this.sections = sections;
        this.route = new ArrayList<>();
    }

    public static AddSectionToRouteFragment newInstance(String param1, String param2) {
        AddSectionToRouteFragment fragment = new AddSectionToRouteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_section_to_route, container, false);

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        listview = (ListView)view.findViewById(R.id.listViewRouteSections);

        if(!route.isEmpty()){
            lastFragment = new LastSectionFragment(route.get(route.size()-1));
            getFragmentManager().beginTransaction().replace(R.id.single_section_container, lastFragment).commit();
        }

        Button finish = (Button)view.findViewById(R.id.buttonFinish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayRouteFragment fragment = new DisplayRouteFragment(route);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag3").commit();
            }
        });

        Button goBack = (Button)view.findViewById(R.id.buttonGoBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(route.size() > 0){
                    route.remove(route.size()-1);

                    if(route.size() != 0){
                        lastFragment = new LastSectionFragment(route.get(route.size()-1));
                        getFragmentManager().beginTransaction().replace(R.id.single_section_container, lastFragment).commit();

                        if(!route.get(route.size()-1).getReversed()){
                            sections = databaseHelper.getSectionsFromPoint(route.get(route.size()-1).getSection().getIdSpEnd());
                        }
                        else{
                            sections = databaseHelper.getSectionsFromPoint(route.get(route.size()-1).getSection().getIdSpStart());
                        }
                        listview.setAdapter(new SectionWithDirectionListViewAdapter(getActivity(), sections));

                    }
                    else{
                        getFragmentManager().beginTransaction().remove(lastFragment).commit();
                        sections = databaseHelper.getSectionsFromPoint(spotId);
                        listview.setAdapter(new SectionWithDirectionListViewAdapter(getActivity(), sections));
                    }
                }
                else{
                    getActivity().onBackPressed();
                }
            }
        });

        listview.setAdapter(new SectionWithDirectionListViewAdapter(getActivity(), sections));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                route.add(sections.get(position));
                lastFragment = new LastSectionFragment(sections.get(position));
                getFragmentManager().beginTransaction().replace(R.id.single_section_container, lastFragment).commit();
                if(!route.get(route.size()-1).getReversed()){
                    sections = databaseHelper.getSectionsFromPoint(route.get(route.size()-1).getSection().getIdSpEnd());
                }
                else{
                    sections = databaseHelper.getSectionsFromPoint(route.get(route.size()-1).getSection().getIdSpStart());
                }
                listview.setAdapter(new SectionWithDirectionListViewAdapter(getActivity(), sections));
            }
        });
        setRetainInstance(false);
        return view;
    }
}