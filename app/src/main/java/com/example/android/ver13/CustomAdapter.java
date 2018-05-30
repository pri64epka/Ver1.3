package com.example.android.ver13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Admin on 05.02.2018.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] matchMode_array;
    String[] full_description;
    LayoutInflater inflater1;


    public CustomAdapter(Context applicationContext, String[] matchMode_array, String[] full_description) {
        this.context = applicationContext;
        this.matchMode_array = matchMode_array;
        this.full_description = full_description;
        inflater1 = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return matchMode_array.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater1.inflate(R.layout.aa_spinner_layout, null);
        TextView names1 = (TextView) view.findViewById(R.id.matchModeTV);
        TextView names2 = (TextView) view.findViewById(R.id.fullDescriptionTV);
        names1.setText(matchMode_array[i]);
        names2.setText(full_description[i]);
        return view;
    }




}
