package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ThreeColumn_ListAdapter extends ArrayAdapter<registros> {

    private LayoutInflater mInflater;
    private ArrayList<registros> users;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<registros> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        registros user = users.get(position);

        if (user != null) {
            TextView firstName = (TextView) convertView.findViewById(R.id.col1);
            TextView lastName = (TextView) convertView.findViewById(R.id.col2);
            TextView favFood = (TextView) convertView.findViewById(R.id.col3);
            if (firstName != null) {
                firstName.setText(user.getuno());
            }
            if (lastName != null) {
                lastName.setText((user.getdos()));
            }
            if (favFood != null) {
                favFood.setText((user.gettres()));
            }
        }

        return convertView;
    }
}