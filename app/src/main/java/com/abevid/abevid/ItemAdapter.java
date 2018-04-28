package com.abevid.abevid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/*
 * ItemAdapter logic downloaded from tutorial at
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

/**
 * Adapter class used to extend ArrayAdapter.  Used to build the ListView display in the Results Activity
 * @author John Moser
 * @see ArrayAdapter
 */
public class ItemAdapter extends ArrayAdapter<Items> {

    /**
     * Default constructor for the class
     * @param context The current context
     * @param items The resource used to instantiate the view by the adapter
     * @see Items
     * @see ArrayList
     */
    public ItemAdapter(Context context, ArrayList<Items> items) {
        super(context, 0, items);
    }

    /**
     * Gets the position of the view for the data
     * @param position Position within the dataset passed in
     * @param convertView The old view used
     * @param parent Where the view being returned will be attached
     * @return The converted view
     * @see ViewGroup
     */
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