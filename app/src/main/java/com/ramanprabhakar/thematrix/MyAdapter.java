package com.ramanprabhakar.thematrix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Raman on 5/20/2016.
 */
public class MyAdapter extends BaseAdapter {

    Context mContext;
    int[] mArray;

    public MyAdapter(Context context, int[] array) {
        mContext = context;
        mArray = array;
    }

    @Override
    public int getCount() {
        return mArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_grid_item, parent, false);
        TextView textView = (TextView) v.findViewById(R.id.tv_grid_item);
        textView.setText("" + mArray[position]);
        return v;
    }

}
