package com.example.gotpttk.view.adminGui.spotsFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gotpttk.R;

public class RemoveSpotFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remove_spot, container, false);
        Button removeSearch = (Button) view.findViewById(R.id.buttonRemoveSearchSpot);
        removeSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // TU BEDZIE SEARCH PUNKTU
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemoveChosenSpotFragment()).commit();
            }
        });
        return view;
    }
}