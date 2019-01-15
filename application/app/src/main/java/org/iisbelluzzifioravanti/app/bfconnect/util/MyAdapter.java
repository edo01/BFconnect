package org.iisbelluzzifioravanti.app.bfconnect.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.iisbelluzzifioravanti.app.bfconnect.R;

import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<Bitmap> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Bitmap, List<String>> _listDataChild;

    public MyAdapter(Context context, List<Bitmap> listImageHeader,
                                 HashMap<Bitmap, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listImageHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.cardview_template_son, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.cardViewText);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //GET TEAM ROW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Bitmap bm = (Bitmap) getGroup(groupPosition);

        //ONLY INFLATE XML TEAM ROW MODEL IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.cardview_template, null);        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.cardViewImage);
        imageView.setImageBitmap(bm);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return true;
    }

}