package com.example.apponthithpt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apponthithpt.Activity.ExamActivity;
import com.example.apponthithpt.Adapter.SubjectAdapter;
import com.example.apponthithpt.Helper.IOHelper;
import com.example.apponthithpt.Model.Subject;
import com.example.apponthithpt.R;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static final String Extra_Raw_Name = "extraRawID";
    public static final String Extra_String_Icon = "extraStringIcon";
    private ArrayList<Subject> mSubjects = new ArrayList<>();
    private GridView gridSubject;
    private IOHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            gridSubject = getActivity().findViewById(R.id.gridSubject);
        }
        helper = new IOHelper(getActivity());
        try {
            mSubjects = helper.getSubject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SubjectAdapter adapter = new SubjectAdapter(getActivity(), mSubjects);
        gridSubject.setAdapter(adapter);

        gridSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSubjects.get(position).mSrcExam.equals("")){
                    Toast.makeText(getActivity(), "Không có dữ liệu!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), ExamActivity.class);
                    intent.putExtra(Extra_Raw_Name, mSubjects.get(position).mSrcExam);
                    intent.putExtra(Extra_String_Icon, mSubjects.get(position).mIcon);
                    startActivity(intent);
                }
            }
        });

    }
}
