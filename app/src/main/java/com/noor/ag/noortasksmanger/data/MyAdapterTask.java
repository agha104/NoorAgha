package com.noor.ag.noortasksmanger.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.noor.ag.noortasksmanger.R;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterTask extends ArrayAdapter<MyTask> {

    public MyAdapterTask(Context context, int resource) {
        super(context, resource);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.my_a_doupter,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        EditText etPhone = (EditText) convertView.findViewById(R.id.etPhone);
        ImageButton btnPhone = (ImageButton) convertView.findViewById(R.id.btnPhone);
        EditText etLocation = (EditText) convertView.findViewById(R.id.etLocation);
        RatingBar rtBarPriority = (RatingBar) convertView.findViewById(R.id.rtBarPriority);
        ImageButton btnLocation = (ImageButton) convertView.findViewById(R.id.btnLocation);
        final MyTask myTask = getItem(position);
        tvName.setText(myTask.getTitle());
        etPhone.setText(myTask.getPhone());
        etLocation.setText(myTask.getAdress());
        rtBarPriority.setRating(myTask.getPriority());
        btnLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {

            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

            }
        });
        return convertView;

    }
    
}
