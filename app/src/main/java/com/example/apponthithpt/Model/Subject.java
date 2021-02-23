package com.example.apponthithpt.Model;

import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("name")
    public String mName;

    @SerializedName("icon")
    public String mIcon;

    @SerializedName("src_exam")
    public String mSrcExam;

    public Subject(String mName, String mIcon, String mSrcExam) {
        this.mName = mName;
        this.mIcon = mIcon;
        this.mSrcExam = mSrcExam;
    }
}
