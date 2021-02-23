package com.example.apponthithpt.Helper;

import android.content.Context;

import com.example.apponthithpt.Model.Exam;
import com.example.apponthithpt.Model.Question;
import com.example.apponthithpt.Model.Subject;
import com.example.apponthithpt.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class IOHelper {
    private Context mContext;
    private ArrayList<Subject> mSubjects = new ArrayList<>();
    private ArrayList<Exam> mExams = new ArrayList<>();
    private ArrayList<Question> mQuestions = new ArrayList<>();

    public IOHelper(Context context){
        this.mContext = context;
    }

    private String stringFromRaw(Context context, int rawID) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(rawID); // Tạo luồng đọc dữ liệu
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // Tạo bộ đệm để đọc dòng dữ liệu
        StringBuilder stringBuilder = new StringBuilder(); // Đối tượng xây dựng chuỗi từ những dữ liệu đã được đọc
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuilder.append(str).append("\n"); // đọc và thêm các dữ liệu đã đọc được từ luồng vào chuỗi
        }
        return stringBuilder.toString();
    }

    public ArrayList<Subject> getSubject() throws IOException{
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Subject>>(){}.getType();
        String json = stringFromRaw(mContext, R.raw.subject);
        ArrayList<Subject> data = gson.fromJson(json, type);
        for (int i = 0; i < data.size(); i++) {
            Subject subject = new Subject(
                    data.get(i).mName,
                    data.get(i).mIcon,
                    data.get(i).mSrcExam
            );
            mSubjects.add(subject);
        }
        return mSubjects;
    }

    public ArrayList<Exam> getExam(int rawID) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Exam>>(){}.getType();
        String json = stringFromRaw(mContext, rawID);
        ArrayList<Exam> data = gson.fromJson(json, type);
        for (int i = 0; i < data.size(); i++){
            Exam exam = new Exam(
                    data.get(i).mSubject,
                    data.get(i).mExamNum,
                    data.get(i).mTime,
                    data.get(i).mQuestions
            );
            mExams.add(exam);
        }
        return mExams;
    }

    public ArrayList<Question> getQuestion(int rawID, int examNum) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Exam>>(){}.getType();
        String json =stringFromRaw(mContext, rawID);
        ArrayList<Exam> data = gson.fromJson(json, type);
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).mExamNum == examNum){
                for (int j = 0; j < data.get(i).mQuestions.size(); j++){
                    Question question = new Question(
                            data.get(i).mQuestions.get(j).mQues,
                            data.get(i).mQuestions.get(j).mImg,
                            data.get(i).mQuestions.get(j).mAnsA,
                            data.get(i).mQuestions.get(j).mAnsB,
                            data.get(i).mQuestions.get(j).mAnsC,
                            data.get(i).mQuestions.get(j).mAnsD,
                            data.get(i).mQuestions.get(j).mAnsTrue,
                            data.get(i).mQuestions.get(j).mChoice
                    );
                    mQuestions.add(question);
                }
            }
        }
        return mQuestions;
    }
}
