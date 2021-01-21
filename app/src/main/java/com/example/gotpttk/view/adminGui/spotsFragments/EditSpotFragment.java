package com.example.gotpttk.view.adminGui.spotsFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Spot;

import java.util.ArrayList;
import java.util.List;

public class EditSpotFragment extends Fragment
{
    List<Spot> spots = new ArrayList<Spot>();

    private class SpotItem{
        TextView name;
        TextView height;
        TextView desc;
    }

    public class SpotListAdapter extends BaseAdapter {

        SpotItem spot;
        private Context mContext;

        public SpotListAdapter(Context c){
            mContext = c;
        }

        @Override
        public int getCount() {
            return spots.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(R.layout.spot_row, parent, false);
                spot = new SpotItem();
                spot.name = (TextView) v.findViewById(R.id.textViewSpotRowNameValue);
                spot.height = (TextView) v.findViewById(R.id.textViewSpotRowHeightValue);
                spot.desc = (TextView) v.findViewById(R.id.textViewSpotRowDescValue);
                v.setTag(spot);
            }
            else{
                spot = (SpotItem) v.getTag();
            }

            spot.name.setText(spots.get(position).getName());
            spot.height.setText(Integer.toString(spots.get(position).getHeight()));
            spot.desc.setText(spots.get(position).getDesc());

            return v;
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState)
    {

        final View view = inflater.inflate(R.layout.fragment_edit_spot, container, false);
        Button editSearch = (Button) view.findViewById(R.id.buttonEditSearchSpot);
        editSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewInner)
            {
                // TU BEDZIE SEARCH PUNKTU
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditChosenSpotFragment()).commit();
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                spots = databaseHelper.getAllSpots();
                if(spots.isEmpty()){
                    Toast.makeText(getContext(), "Brak punktów spełniających kryteria", Toast.LENGTH_SHORT).show();
                }
                else{
                    ListView listview = (ListView)view.findViewById(R.id.listViewEditSpotSearch);
                    listview.setAdapter(new SpotListAdapter(getActivity()));
                }
            }
        });
        return view;
    }
}