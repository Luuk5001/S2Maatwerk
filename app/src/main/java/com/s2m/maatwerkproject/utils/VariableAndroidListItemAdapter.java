package com.s2m.maatwerkproject.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.s2m.maatwerkproject.models.Setting;

public class VariableAndroidListItemAdapter extends BaseAdapter {

    public interface VariableListItem{
        String getTextView1Text();
        String getTextView2Text();
        int getItemId();
    }

    private Context context;
    private Setting[] data;

    public VariableAndroidListItemAdapter(Context context, Setting[] settings) {
        this.context = context;
        this.data = settings;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return data[position].getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (data[position].getTextView1Text() != null && data[position].getTextView2Text() != null) {
            TwoLineListItem twoLineListItem;
            if (convertView == null || !(convertView instanceof TwoLineListItem)) {
                twoLineListItem = (TwoLineListItem) inflater.inflate(
                        android.R.layout.simple_list_item_2, null);
            }
            else {
                twoLineListItem = (TwoLineListItem) convertView;
            }

            TextView text1 = twoLineListItem.getText1();
            TextView text2 = twoLineListItem.getText2();

            text1.setText(data[position].getTextView1Text());
            text2.setText(data[position].getTextView2Text());

            return twoLineListItem;
        }
        else if(data[position].getTextView1Text() != null && data[position].getTextView2Text() == null){
            TextView textView;
            if (convertView == null || !(convertView instanceof TextView)) {
                textView = (TextView) inflater.inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            else {
                textView = (TextView) convertView;
            }

           textView.setText(data[position].getTextView1Text());
           return textView;
        }
        else{
            return null;
            //TODO throw unsupported exception
        }
    }
}
