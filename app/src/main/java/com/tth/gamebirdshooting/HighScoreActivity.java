package com.tth.gamebirdshooting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends Activity {
    private static final String APP_NAME = "Bird_Shooting";
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    private SharedPreferences pre;
    private int t1, t2, t3, t4, t5, t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        tv1 = findViewById(R.id.tv_top1);
        tv2 = findViewById(R.id.tv_top2);
        tv3 = findViewById(R.id.tv_top3);
        tv4 = findViewById(R.id.tv_top4);
        tv5 = findViewById(R.id.tv_top5);
        tv6 = findViewById(R.id.tv_top6);
        pre = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        t1 = pre.getInt("score1", 0);
        t2 = pre.getInt("score2", 0);
        t3 = pre.getInt("score3", 0);
        t4 = pre.getInt("score4", 0);
        t5 = pre.getInt("score5", 0);
        t6 = pre.getInt("score6", 0);
        tv1.setText(String.valueOf(t1));
        tv2.setText(String.valueOf(t2));
        tv3.setText(String.valueOf(t3));
        tv4.setText(String.valueOf(t4));
        tv5.setText(String.valueOf(t5));
        tv6.setText(String.valueOf(t6));
    }
}