package com.example.apponthithpt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.apponthithpt.Adapter.ExamAdapter;
import com.example.apponthithpt.Common.Common;
import com.example.apponthithpt.Fragment.HomeFragment;
import com.example.apponthithpt.Helper.IOHelper;
import com.example.apponthithpt.Model.Exam;
import com.example.apponthithpt.Model.HighScore;
import com.example.apponthithpt.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {
    public static String rawName;
    TextView tvName, tvtitle;
    private RecyclerView recyclerView;
    private IOHelper helper;
    private ArrayList<Exam> mExams = new ArrayList<>();
    private ArrayList<ArrayList<HighScore>> mArrHighScores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Ôn Tập Trắc Nghiệm THPT");
        }

        recyclerView = findViewById(R.id.recycler_exam);
        tvName = findViewById(R.id.tvExamName);
        tvtitle = findViewById(R.id.tvExamTitle);

//        Animation topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        tvtitle.setAnimation(topanimation);
//        tvName.setAnimation(topanimation);


        Intent getIntent = getIntent();
        rawName = getIntent.getStringExtra(HomeFragment.Extra_Raw_Name);

        helper = new IOHelper(this);
        try {
            mExams = helper.getExam(Common.getId(rawName, R.raw.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvName.setText(String.format("Môn: %s", mExams.get(0).mSubject));
        tvName.setPaintFlags(tvName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        LoadHighScore();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ExamAdapter adapter = new ExamAdapter(this, mExams, mArrHighScores);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void LoadHighScore(){
        SharedPreferences pref = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        for (int i = 0; i < mExams.size(); i++){
            String key_prefs = rawName + "_" + mExams.get(i).mExamNum;
            Gson gson = new Gson();
            String json = pref.getString(key_prefs, null);
            Type type = new TypeToken<ArrayList<HighScore>>(){}.getType();
            ArrayList<HighScore> mHighScores = gson.fromJson(json, type);
            if (mHighScores == null){
                HighScore highScore = new HighScore(null, 0, 0, 0, 0, 0, 0);
                mHighScores = new ArrayList<>();
                mHighScores.add(highScore);
            }
            mArrHighScores.add(mHighScores);
        }
    }
}