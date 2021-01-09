package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.tth.gamebirdshooting.GameManagerCallback;
import com.tth.gamebirdshooting.R;

public class Pause implements GameInterface {
    private Bitmap pause, resume, restart, exit;
    private int heightPause, widthPause, width, height, distance;
    private int screenHeight, screenWidth, x, y;
    boolean pressPause = false;
    private GameManagerCallback callback;

    public Pause(Resources resources, int screenHeight, int screenWidth, GameManagerCallback callback) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        heightPause = (int) resources.getDimension(R.dimen.pause_height);
        widthPause = (int) resources.getDimension(R.dimen.pause_width);
        height = (int) resources.getDimension(R.dimen.resume_height);
        width = (int) resources.getDimension(R.dimen.resume_width);
        distance = (int) resources.getDimension(R.dimen.resume_distance);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.pause);
        pause = Bitmap.createScaledBitmap(bitmap, heightPause, widthPause, false);
        Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.resume);
        resume = Bitmap.createScaledBitmap(bitmap1, height, width, false);
        Bitmap bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.restart);
        restart = Bitmap.createScaledBitmap(bitmap2, height, width, false);
        Bitmap bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.exit);
        exit = Bitmap.createScaledBitmap(bitmap3, height, width, false);
    }

    @Override
    public void update() {
        //Log.d("test", String.valueOf(x) + " " + String.valueOf(y));
        if (pressPause) {
            if (x > screenWidth / 2 - resume.getWidth() / 2 && x < screenWidth / 2 + resume.getWidth() / 2 &&
                    y > screenHeight / 2 - restart.getHeight() / 2 - distance - resume.getHeight() && y < screenHeight / 2 - restart.getHeight() / 2 - distance) {
                callback.pressPause(1);
                setPressPause(false);
            }
            if (x > screenWidth / 2 - restart.getWidth() / 2 && x < screenWidth / 2 + restart.getWidth() / 2 &&
                    y > screenHeight / 2 - restart.getHeight() / 2 && y < screenHeight / 2 + restart.getHeight() / 2) {
                callback.pressPause(2);
            }
            if (x > screenWidth / 2 - exit.getWidth() / 2 && x < screenWidth / 2 + exit.getWidth() / 2 &&
                    y > screenHeight / 2 + restart.getHeight() / 2 + distance && y < screenHeight / 2 + restart.getHeight() / 2 + distance + exit.getHeight()) {
                callback.pressPause(3);
            }

        } else {
            if (x > screenWidth / 20 * 18 && x < screenWidth / 20 * 18 + pause.getWidth() && y > 10 && y < 10 + pause.getHeight()) {
                setPressPause(true);
                callback.pressPause(0);
            }
        }
    }

    public void setPressPause(boolean press) {
        this.pressPause = press;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!pressPause) {
            canvas.drawBitmap(pause, screenWidth / 20 * 18, 10, null);
        } else {
            canvas.drawBitmap(resume, screenWidth / 2 - resume.getWidth() / 2, screenHeight / 2 - restart.getHeight() / 2 - distance - resume.getHeight(), null);
            canvas.drawBitmap(restart, screenWidth / 2 - restart.getWidth() / 2, screenHeight / 2 - restart.getHeight() / 2, null);
            canvas.drawBitmap(exit, screenWidth / 2 - exit.getWidth() / 2, screenHeight / 2 + restart.getHeight() / 2 + distance, null);
        }
    }

    public void getOnTouch(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }
}
