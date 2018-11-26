package com.example.grift.flaxgt_individual_project_4.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.grift.flaxgt_individual_project_4.R;

import java.util.HashMap;

public class LinkedHashMapAdapter extends BaseAdapter {
    //declare variables for our custom adapter
    private HashMap<String, Long> mData;
    private String[] mKeys;
    private Long[] mValues;
    private Context context;

    public LinkedHashMapAdapter(Context context, HashMap<String, Long> data){
        //instantiate the variables when the adapter is instantiated
        this.context = context;
        mData  = data;
        mValues = mData.values().toArray(new Long[data.size()]);
        mKeys = mData.keySet().toArray(new String[data.size()]);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        //if the view is null, which it should be
        if(view==null) {
            //inflate the leaderboard item view using the username and attempts value for the view text
            view = ((Activity)context).getLayoutInflater().inflate(
                    R.layout.leaderboard_item_layout,viewGroup,false);
        }

        TextView leaderboardTV = (TextView)view.findViewById(R.id.leaderboard_item_tv);

        String key = mKeys[pos];
        long value = mValues[pos];

        leaderboardTV.setText(String.format("%s. %s: %s", pos+1, key, value));

        return view;
    }
}
