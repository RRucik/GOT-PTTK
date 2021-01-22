package com.example.gotpttk.model.sectionModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;

import java.util.List;

public class SectionListViewAdapter extends BaseAdapter {
    SectionItem section;
    private Context mContext;
    List<Section> sections;

    public SectionListViewAdapter(Context c, List<Section> sections){
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
        return sections.get(position).getIdSe();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.section_row, parent, false);
            section = new SectionItem();
            section.start = (TextView) v.findViewById(R.id.textViewSectionRowStartValue);
            section.end = (TextView) v.findViewById(R.id.textViewSectionRowEndValue);
            section.length = (TextView) v.findViewById(R.id.textViewSectionRowLengthValue);
            section.points = (TextView) v.findViewById(R.id.textViewSectionRowPointsValue);
            section.active = (TextView) v.findViewById(R.id.textViewSectionRowActiveSinceValue);
            section.mountain = (TextView) v.findViewById(R.id.textViewSectionRowMountainValue);
            section.desc = (TextView) v.findViewById(R.id.textViewSectionDescValue);
            v.setTag(section);
        }
        else{
            section = (SectionItem) v.getTag();
        }

        DatabaseHelper db = new DatabaseHelper(v.getContext());

        section.start.setText(db.getSpot(sections.get(position).getIdSpStart()).getName());
        section.end.setText(db.getSpot(sections.get(position).getIdSpEnd()).getName());

        section.length.setText(Integer.toString(sections.get(position).getLength()));
        section.points.setText(Integer.toString(sections.get(position).getPointsTo()));
        section.active.setText(sections.get(position).getActiveSince());
        section.mountain.setText(sections.get(position).getMountainRange());
        section.desc.setText(sections.get(position).getDesc());

        return v;
    }
}
