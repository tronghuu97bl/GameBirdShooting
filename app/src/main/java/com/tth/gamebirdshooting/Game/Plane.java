package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tth.gamebirdshooting.GameManagerCallback;
import com.tth.gamebirdshooting.R;

public class Plane implements GameInterface {
    private Bitmap plane;
    private int x, y, currentY, desY, speed, speedHigh;
    private int screenHeight, screenWidth;
    private GameManagerCallback callback;
    private int height, width, score = 0;

    public Plane(Resources resources, int screenHeight, int screenWidth, GameManagerCallback callback) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        speed = (int) resources.getDimension(R.dimen.speed_move_plane);
        height = (int) resources.getDimension(R.dimen.plane_height);
        width = (int) resources.getDimension(R.dimen.plane_width);
        speedHigh = (int) resources.getDimension(R.dimen.speed_move_plane_high);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.plane);
        plane = Bitmap.createScaledBitmap(bitmap, width, height, false);
        x = screenWidth / 20;
        y = screenHeight / 2 - plane.getHeight() / 2;
        currentY = y + plane.getHeight() / 2; //tam may bay, hong sung
        desY = currentY;// vi tri tiep theo cua tam may bay
    }

    @Override
    public void update() {
        if (currentY < desY) {
            if (desY - currentY < speed) {
                y = desY - plane.getHeight() / 2;
                currentY = desY;
            } else {
                if (score > 28) {
                    y += speedHigh;
                    currentY += speedHigh;
                } else {
                    y += speed;
                    currentY += speed;
                }
            }
        }
        if (currentY > desY) {
            if (currentY - desY < speed) {
                y = desY - plane.getHeight() / 2;
                currentY = desY;
            } else {
                if (score > 28) {
                    y -= speedHigh;
                    currentY -= speedHigh;
                } else {
                    y -= speed;
                    currentY -= speed;
                }
            }
        }
        Rect position = new Rect(x, y, x + plane.getWidth(), y + plane.getHeight());
        callback.updatePosition(position, currentY);
    }

    public void onTouchEvent(float dis) {
        desY = (int) dis;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(plane, x, y, null);
    }

    public void updateScore(int score) {
        this.score = score;
    }
}
