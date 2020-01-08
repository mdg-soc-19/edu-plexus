package com.example.mdg_soc_eduplexus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapterForScore extends ArrayAdapter<Score> {

        private Activity context;
        private List<Score> ScoreList;

    public CustomAdapterForScore(Activity context, List<Score> scoreList) {
        super(context, R.layout.list_item, scoreList);
        this.context = context;
        ScoreList = scoreList;
    }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item,null,true);
        Score score = ScoreList.get(position);

        TextView t1 = view.findViewById(R.id.message_text);
        TextView t2 = view.findViewById(R.id.message_time);
        TextView t3 = view.findViewById(R.id.message_user);


            String s2 = String.valueOf(score.getScore());
            t3.setText(score.getUser());
            t2.setText(score.getTestNo());
            t1.setText(s2);
        return view;
    }
}
