package com.tth.gamebirdshooting.Game;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tth.gamebirdshooting.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Score implements GameInterface {
    public static final String SCORE_PRE = "score_pre";
    private Bitmap one, two, three, four, five, six, seven, eight, nine, zero, bmpScore;
    private int screenHeight, screenWidth;
    private boolean collision = false;
    private int scores[] = new int[6];
    private int score = 0;
    private HashMap<Integer, Bitmap> map = new HashMap<>();

    public Score(Resources resources, int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        one = BitmapFactory.decodeResource(resources, R.drawable.one);
        two = BitmapFactory.decodeResource(resources, R.drawable.two);
        three = BitmapFactory.decodeResource(resources, R.drawable.three);
        four = BitmapFactory.decodeResource(resources, R.drawable.four);
        five = BitmapFactory.decodeResource(resources, R.drawable.five);
        six = BitmapFactory.decodeResource(resources, R.drawable.six);
        seven = BitmapFactory.decodeResource(resources, R.drawable.seven);
        eight = BitmapFactory.decodeResource(resources, R.drawable.eight);
        nine = BitmapFactory.decodeResource(resources, R.drawable.nine);
        zero = BitmapFactory.decodeResource(resources, R.drawable.zero);
        bmpScore = BitmapFactory.decodeResource(resources, R.drawable.score);
        map.put(0, zero);
        map.put(1, one);
        map.put(2, two);
        map.put(3, three);
        map.put(4, four);
        map.put(5, five);
        map.put(6, six);
        map.put(7, seven);
        map.put(8, eight);
        map.put(9, nine);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if (!collision) {
            ArrayList<Bitmap> digits = convertToBitmaps(score);
            for (int i = 0; i < digits.size(); i++) {
                int x = screenWidth / 2 - digits.size() * zero.getWidth() / 2 + zero.getWidth() * i;
                canvas.drawBitmap(digits.get(i), x, screenHeight / 4, null);
            }
        } else {
            ArrayList<Bitmap> currentDigits = convertToBitmaps(score);
            canvas.drawBitmap(bmpScore, screenWidth / 4 - bmpScore.getWidth() / 2, 3 * screenHeight / 4 - zero.getHeight() - bmpScore.getHeight(), null);
            for (int i = 0; i < currentDigits.size(); i++) {
                int x = screenWidth / 4 - currentDigits.size() * zero.getWidth() + zero.getWidth() * i;
                canvas.drawBitmap(currentDigits.get(i), x, 3 * screenHeight / 4, null);
            }
        }
    }

    private ArrayList<Bitmap> convertToBitmaps(int score) {
        ArrayList<Bitmap> digits = new ArrayList<>();
        if (score == 0) {
            digits.add(zero);
        }
        while (score > 0) {
            int lastDigits = score % 10;
            score /= 10;
            digits.add(map.get(lastDigits));
        }
        ArrayList<Bitmap> finalDigits = new ArrayList<>();
        for (int i = digits.size() - 1; i >= 0; i--) {
            finalDigits.add(digits.get(i));
        }
        return finalDigits;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public void saveScoreToPre(SharedPreferences pre) {
        //collision = true;
        scores[0] = pre.getInt("score1", 0);
        scores[1] = pre.getInt("score2", 0);
        scores[2] = pre.getInt("score3", 0);
        scores[3] = pre.getInt("score4", 0);
        scores[4] = pre.getInt("score5", 0);
        scores[5] = pre.getInt("score6", 0);
        if (score > scores[0]) {
            pre.edit().putInt("score1", score).apply();
            pre.edit().putInt("score2", scores[0]).apply();
            pre.edit().putInt("score3", scores[1]).apply();
            pre.edit().putInt("score4", scores[2]).apply();
            pre.edit().putInt("score5", scores[3]).apply();
            pre.edit().putInt("score6", scores[4]).apply();
            return;
        }
        if (score > scores[1]) {
            pre.edit().putInt("score2", score).apply();
            pre.edit().putInt("score3", scores[1]).apply();
            pre.edit().putInt("score4", scores[2]).apply();
            pre.edit().putInt("score5", scores[3]).apply();
            pre.edit().putInt("score6", scores[4]).apply();
            return;
        }
        if (score > scores[2]) {
            pre.edit().putInt("score3", score).apply();
            pre.edit().putInt("score4", scores[2]).apply();
            pre.edit().putInt("score5", scores[3]).apply();
            pre.edit().putInt("score6", scores[4]).apply();
            return;
        }
        if (score > scores[3]) {
            pre.edit().putInt("score4", score).apply();
            pre.edit().putInt("score5", scores[3]).apply();
            pre.edit().putInt("score6", scores[4]).apply();
            return;
        }
        if (score > scores[4]) {
            pre.edit().putInt("score5", score).apply();
            pre.edit().putInt("score6", scores[4]).apply();
            return;
        }
        if (score > scores[5]) {
            pre.edit().putInt("score6", score).apply();
            return;
        }
    }
}
