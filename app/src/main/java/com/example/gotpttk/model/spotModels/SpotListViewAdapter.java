package com.example.gotpttk.model.spotModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbModels.Spot;
import com.example.gotpttk.model.spotModels.SpotItem;

import java.util.ArrayList;
import java.util.List;


public class SpotListViewAdapter extends BaseAdapter {

    SpotItem spot;
    private Context mContext;
    List<Spot> spots = new ArrayList<>();

    public SpotListViewAdapter(Context c, List<Spot> spots){
        mContext = c;
        this.spots = spots;
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
        return spots.get(position).getIdSp();
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
        String height = "";
        if(spots.get(position).getHeight() != null){
            height = Integer.toString(spots.get(position).getHeight());
        }
        spot.height.setText(height);
        spot.desc.setText(spots.get(position).getDesc());

        return v;
    }
}
