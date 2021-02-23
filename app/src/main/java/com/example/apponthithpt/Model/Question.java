package com.example.apponthithpt.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Question implements Parcelable {
    @SerializedName("ques")
    public String mQues;

    @SerializedName("img")
    public String mImg;

    @SerializedName("ans_a")
    public String mAnsA;

    @SerializedName("ans_b")
    public String mAnsB;

    @SerializedName("ans_c")
    public String mAnsC;

    @SerializedName("ans_d")
    public String mAnsD;

    @SerializedName("ans_true")
    public int mAnsTrue;

    public int mChoice;

    public Question(String mQues, String mImg, String mAnsA, String mAnsB, String mAnsC, String mAnsD, int mAnsTrue, int mChoice) {
        this.mQues = mQues;
        this.mImg = mImg;
        this.mAnsA = mAnsA;
        this.mAnsB = mAnsB;
        this.mAnsC = mAnsC;
        this.mAnsD = mAnsD;
        this.mAnsTrue = mAnsTrue;
        this.mChoice = mChoice;
    }

    protected Question(Parcel in) {
        mQues = in.readString();
        mQues = in.readString();
        mImg = in.readString();
        mAnsA = in.readString();
        mAnsB = in.readString();
        mAnsC = in.readString();
        mAnsD = in.readString();
        mAnsTrue = in.readInt();
        mChoice = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQues);
        dest.writeString(mImg);
        dest.writeString(mAnsA);
        dest.writeString(mAnsB);
        dest.writeString(mAnsC);
        dest.writeString(mAnsD);
        dest.writeInt(mAnsTrue);
        dest.writeInt(mChoice);
    }
}
