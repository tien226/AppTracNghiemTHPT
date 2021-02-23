package com.example.apponthithpt.Adapter;

import android.content.Context;
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


public class SubjectAdapter extends ArrayAdapter {
    public SubjectAdapter(Context context, ArrayList<Subject> subjects) {
        super(context, 0, subjects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_adapter, parent, false);

            TextView tvName = convertView.findViewById(R.id.itemSjName);
            ImageView ivIcon = convertView.findViewById(R.id.itemSjIcon);

            Subject subject = (Subject) getItem(position);
            if (subject != null){
                tvName.setText(subject.mName);
                ivIcon.setImageResource(Common.getId(subject.mIcon, R.drawable.class));
            }

        }
        return convertView;
    }
}
