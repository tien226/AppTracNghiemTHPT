package com.example.apponthithpt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apponthithpt.Activity.ExamActivity;
import com.example.apponthithpt.Activity.QuizActivity;
import com.example.apponthithpt.Common.Common;
import com.example.apponthithpt.Model.Exam;
import com.example.apponthithpt.Model.HighScore;
import com.example.apponthithpt.R;

import java.util.ArrayList;
import java.util.Locale;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    public static final String Extra_Raw_File_Name = "extraRawFileName";
    public static final String Extra_Exam_Num = "extraExamNum";
    public static final String Extra_Exam_Time = "extraExamTime";

    private Context mcontext;
    private ArrayList<Exam> mExams;
    private ArrayList<ArrayList<HighScore>> mArrHighScores;

    public ExamAdapter(Context mcontext, ArrayList<Exam> mExams, ArrayList<ArrayList<HighScore>> arrHighScores) {
        this.mcontext = mcontext;
        this.mExams = mExams;
        this.mArrHighScores = arrHighScores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.exam_adapter, parent, false);

        Animation animation = AnimationUtils.loadAnimation(mcontext,R.anim.scale_animation);
        view.startAnimation(animation);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Exam exam = mExams.get(position);
        final String rawName = ExamActivity.rawName;
        final ArrayList<HighScore> mHightScores = mArrHighScores.get(position);

        holder.tvExamNumber.setText(String.format(Locale.getDefault(), "Đề số: %d", position + 1));

        if (mHightScores.get(0).mRawName != null) {
            holder.tvExamHighScore.setText(String.format(Locale.getDefault(),
                    "Điểm cao nhất: %.3s", mHightScores.get(mHightScores.size() - 1).mScore));
            holder.tvExamLastQuiz.setText(String.format(Locale.getDefault(), "Lần thi cuối: %s",
                    Common.unixTimeToDate(mHightScores.get(0).mDate)));
        }else {
            holder.tvExamHighScore.setText("Điểm thi: ");
            holder.tvExamLastQuiz.setText("chưa có dữ liệu!");
        }
        // click de thi
        holder.layout_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, QuizActivity.class);
                intent.putExtra(Extra_Raw_File_Name, rawName);
                intent.putExtra(Extra_Exam_Num, exam.mExamNum);
                intent.putExtra(Extra_Exam_Time, exam.mTime);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExams.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExamNumber, tvExamHighScore, tvExamLastQuiz;
        LinearLayout layout_exam;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExamNumber = itemView.findViewById(R.id.tvExamNumber);
            tvExamHighScore = itemView.findViewById(R.id.tvItemExHighScore);
            tvExamLastQuiz = itemView.findViewById(R.id.tvItemExLastQuiz);
            layout_exam = itemView.findViewById(R.id.layout_item_exam);
        }
    }
}
