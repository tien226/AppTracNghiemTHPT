package com.example.apponthithpt.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apponthithpt.Common.Common;
import com.example.apponthithpt.R;
import com.example.apponthithpt.Model.HighScore;


import java.util.ArrayList;


public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<HighScore> mHighScores;

    public ScoreAdapter(Context context, ArrayList<HighScore> highScores) {
        this.mContext = context;
        this.mHighScores = highScores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HighScore highScore = mHighScores.get(position);
        if (mHighScores.get(0).mRawName != null) {
            holder.tvItemSScore.setVisibility(View.VISIBLE);
            holder.tvItemSClassification.setVisibility(View.VISIBLE);
            holder.tvItemSDate.setText(String.format("%s\n%s",
                    Common.unixTimeToDate(highScore.mDate),
                    Common.unixTimeToTime(highScore.mDate)));
            holder.tvItemSScore.setText(String.format("%.3s", highScore.mScore));

            if (highScore.mScore >= 9){
                holder.tvItemSClassification.setText("Xuất xắc");
            }else if (highScore.mScore >= 8 && highScore.mScore < 9){
                holder.tvItemSClassification.setText("Giỏi");
            }else if (highScore.mScore >= 6.5 && highScore.mScore < 8){
                holder.tvItemSClassification.setText("Khá");
            }else if (highScore.mScore >= 5 && highScore.mScore < 6.5){
                holder.tvItemSClassification.setText("Trung bình");
            } else {
                holder.tvItemSClassification.setText("Không đạt \nCần cố gắng hơn!");
            }

            holder.layout_item_score.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.showDialogHighScore(mContext, mHighScores, position);
                }
            });
        } else {
            holder.tvItemSDate.setText("Chưa có dữ liệu!");
            holder.tvItemSScore.setVisibility(View.GONE);
            holder.tvItemSClassification.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mHighScores.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemSDate, tvItemSScore, tvItemSClassification;
        LinearLayout layout_item_score;

        ViewHolder(View itemView) {
            super(itemView);
            tvItemSDate = itemView.findViewById(R.id.tvItemSDate);
            tvItemSScore = itemView.findViewById(R.id.tvItemSScore);
            tvItemSScore.setTextColor(Color.RED);
            tvItemSClassification = itemView.findViewById(R.id.tvItemSClassification);
            layout_item_score = itemView.findViewById(R.id.layout_item_score);
        }
    }
}
