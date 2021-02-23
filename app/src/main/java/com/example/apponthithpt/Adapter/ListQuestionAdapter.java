package com.example.apponthithpt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.apponthithpt.Activity.QuizActivity;
import com.example.apponthithpt.Common.Common;
import com.example.apponthithpt.R;
import com.example.apponthithpt.Model.Question;

import java.util.ArrayList;

public class ListQuestionAdapter extends ArrayAdapter<Question> {

    public ListQuestionAdapter(Context context, ArrayList<Question> arrQuestion) {
        super(context, 0, arrQuestion);
    }

    @Override
    public View getView(final int position, @Nullable View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_question_adapter, parent, false);
        }

        TextView tvItemQuesNum = convertView.findViewById(R.id.tvItemQuesNum);
        TextView tvItemQuesChoice = convertView.findViewById(R.id.tvItemQuesChoice);
        TextView tvItemQuesTrue = convertView.findViewById(R.id.tvItemQuesTrue);

        final Question question = getItem(position);

        if (question != null) {

            tvItemQuesNum.setText(String.valueOf(position+1));
            tvItemQuesChoice.setText(Common.getChoiceText(question.mChoice));

            if (QuizActivity.checkResult){
                tvItemQuesTrue.setVisibility(View.VISIBLE);
                tvItemQuesTrue.setText(Common.getChoiceText(question.mAnsTrue));
                tvItemQuesChoice.setBackgroundResource(R.drawable.tv_circle_choice);
                tvItemQuesTrue.setBackgroundResource(R.drawable.tv_circle_true);
                if (question.mChoice == question.mAnsTrue){
                    tvItemQuesChoice.setBackgroundResource(R.drawable.tv_circle_true);
                }
            }
        }

        return convertView;
    }

}
