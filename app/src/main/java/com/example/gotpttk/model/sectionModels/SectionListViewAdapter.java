package com.example.gotpttk.model.sectionModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gotpttk.R;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.dbModels.Spot;
import com.example.gotpttk.model.spotModels.SpotItem;

import java.util.ArrayList;
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
            v.setTag(section);
        }
        else{
            section = (SectionItem) v.getTag();
        }

        section.start.setText(sections.get(position).getIdSpStart());
        section.end.setText(sections.get(position).getIdSpEnd());
        section.length.setText(sections.get(position).getLength());
        section.points.setText(sections.get(position).getPointsTo());
        section.active.setText(sections.get(position).getActiveFrom());
        section.mountain.setText("Pasmo gór");

        return v;
    }
}
