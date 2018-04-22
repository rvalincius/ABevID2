package com.abevid.abevid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * ItemAdapter Code downloaded from https://stackoverflow.com/questions/19466757/hashmap-to-listview
 */

public class ItemAdapter extends ArrayAdapter<Items> {
    public ItemAdapter(Context context, ArrayList<Items> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Items item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }
        // Lookup view for data population
        TextView tCategory = (TextView) convertView.findViewById(R.id.category);
        TextView tContent = (TextView) convertView.findViewById(R.id.content);
        // Populate the data into the template view using the data object
        tCategory.setText(item.iIdentifier);
        tContent.setText(item.iContent);
        // Return the completed view to render on screen
        return convertView;
    }
}