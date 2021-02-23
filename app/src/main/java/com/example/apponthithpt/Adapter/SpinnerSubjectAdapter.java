package com.example.apponthithpt.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apponthithpt.Common.Common;
import com.example.apponthithpt.Model.Subject;
import com.example.apponthithpt.R;

import java.util.ArrayList;

public class SpinnerSubjectAdapter extends ArrayAdapter<Subject> {
    public SpinnerSubjectAdapter(Context context, ArrayList<Subject> subjects) {
        super(context, 0, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spiner_subject_adapter, parent, false
            );
        }

        ImageView ivSubjectSpinner = convertView.findViewById(R.id.ivSubjectSpinner);
        TextView tvSubjectSpinner = convertView.findViewById(R.id.tvSubjectSpinner);
        Subject currentSubject = getItem(position);
        if (currentSubject != null) {
            ivSubjectSpinner.setImageResource(Common.getId(currentSubject.mIcon, R.drawable.class));
            ivSubjectSpinner.setColorFilter(Color.BLUE);
            tvSubjectSpinner.setText(currentSubject.mName);
            tvSubjectSpinner.setTextColor(Color.BLUE);
        }
        return convertView;
    }
}
