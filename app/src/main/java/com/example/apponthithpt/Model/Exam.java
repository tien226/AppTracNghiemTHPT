package com.example.apponthithpt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Exam {

    @SerializedName("subject")
    public String mSubject;

    @SerializedName("exam_num")
    public int mExamNum;

    @SerializedName("time")
    public int mTime;

    @SerializedName("questions")
    public ArrayList<Question> mQuestions;

    public Exam(String mSubject, int mExamNum, int mTime, ArrayList<Question> mQuestions) {
        this.mSubject = mSubject;
        this.mExamNum = mExamNum;
        this.mTime = mTime;
        this.mQuestions = mQuestions;
    }
}
