package com.example.gotpttk.model.sectionModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;

import java.util.List;

public class RouteListViewAdapter extends BaseAdapter {

    RouteItem routeItem;
    private Context mContext;
    List<SectionWithDirection> sections;

    public RouteListViewAdapter(Context c, List<SectionWithDirection> sections){
        mContext = c;
        this.sections = sections;
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return sections.get(position).getSection().getIdSe();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.route_row, parent, false);
            routeItem = new RouteItem();
            routeItem.position = (TextView) v.findViewById(R.id.position);
            routeItem.name = (TextView) v.findViewById(R.id.startPoint);
            routeItem.desc = (TextView) v.findViewById(R.id.descValue);
            v.setTag(routeItem);
        }
        else{
            routeItem = (RouteItem) v.getTag();
        }

        DatabaseHelper db = new DatabaseHelper(v.getContext());

        routeItem.position.setText(Integer.toString(position+1)+".");

        if(!sections.get(position).getReversed()){
            routeItem.name.setText(db.getSpot(sections.get(position).getSection().getIdSpStart()).getName());
        }
        else{
            routeItem.name.setText(db.getSpot(sections.get(position).getSection().getIdSpEnd()).getName());
        }

        routeItem.desc.setText(sections.get(position).getSection().getDesc());

        return v;
    }
}