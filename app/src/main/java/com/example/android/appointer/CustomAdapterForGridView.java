package com.example.android.appointer;

/**
 * Created by Prasad on 20-Apr-18.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterForGridView extends BaseAdapter {
    Context context;
    int logos[];
    String[] categoryTitles;
    LayoutInflater inflter;
    public CustomAdapterForGridView(Context applicationContext, int[] logos, String[] categoryTitles) {
        this.context = applicationContext;
        this.logos = logos;
        this.categoryTitles=categoryTitles;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
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
        view = inflter.inflate(R.layout.grid_view_item, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.gridItemIcon);
        TextView titles =(TextView) view.findViewById(R.id.gridItemName);
        titles.setText(categoryTitles[i]);
        icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}