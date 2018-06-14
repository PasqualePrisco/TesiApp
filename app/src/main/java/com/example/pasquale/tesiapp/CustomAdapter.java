package com.example.pasquale.tesiapp;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Album> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, List<Album> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.list_layout, null);
        }

        Album a = getItem(position);

        Log.d("DEBUG","contact c="+a);

        TextView txt;

        txt = (TextView) v.findViewById(R.id.titleText);


        txt.setText(a.getTitle());

        txt.setTag(position);

        return v;
    }
}


