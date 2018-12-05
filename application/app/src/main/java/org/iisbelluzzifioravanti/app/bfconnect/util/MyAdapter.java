package org.iisbelluzzifioravanti.app.bfconnect.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.iisbelluzzifioravanti.app.bfconnect.R;

public class MyAdapter extends ArrayAdapter {

    private String[] titles;

    public MyAdapter(Context context, String[] titles) {
        super(context, R.layout.row, R.id.textList, titles);
        this.titles = titles;
    }

    @NonNull

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row, parent, false);
        TextView txt = row.findViewById(R.id.textList);
        txt.setText(titles[position]);
        return row;
    }
}
