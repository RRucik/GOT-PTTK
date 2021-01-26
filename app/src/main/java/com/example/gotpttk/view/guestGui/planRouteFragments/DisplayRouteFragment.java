package com.example.gotpttk.view.guestGui.planRouteFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.sectionModels.RouteListViewAdapter;
import com.example.gotpttk.model.sectionModels.SectionWithDirection;
import com.example.gotpttk.model.sectionModels.SectionWithDirectionListViewAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayRouteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayRouteFragment extends Fragment {

    List<SectionWithDirection> sections;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayRouteFragment() {
        // Required empty public constructor
    }

    public DisplayRouteFragment(List<SectionWithDirection> sections) {
        // Required empty public constructor
        this.sections = sections;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayRouteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayRouteFragment newInstance(String param1, String param2) {
        DisplayRouteFragment fragment = new DisplayRouteFragment();
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
        View view = inflater.inflate(R.layout.fragment_display_route, container, false);

        ListView listview = (ListView)view.findViewById(R.id.listViewRouteDisplay);
        listview.setAdapter(new RouteListViewAdapter(getActivity(), sections));

        DatabaseHelper db = new DatabaseHelper(view.getContext());
        TextView endPoint = view.findViewById(R.id.end_in_value);
        if(!sections.get(sections.size()-1).getReversed()){
            endPoint.setText(db.getSpot(sections.get(sections.size()-1).getSection().getIdSpEnd()).getName());
        }
        else{
            endPoint.setText(db.getSpot(sections.get(sections.size()-1).getSection().getIdSpStart()).getName());
        }

        return view;
    }
}