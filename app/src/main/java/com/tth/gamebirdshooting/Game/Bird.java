package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tth.gamebirdshooting.R;

import java.util.Random;

public class Bird implements GameInterface {
    private Bitmap bird;
    private int widthBird, heightBird;
    private int screenHeight, screenWidth;
    private int x, y, speed, speedHigh;
    private ObstacleCallback callback;
    private boolean collision = false;
    private Rect position;

    public Bird(Resources resources, int screenHeight, int screenWidth, ObstacleCallback callback) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        widthBird = (int) resources.getDimension(R.dimen.bird_width);
        heightBird = (int) resources.getDimension(R.dimen.bird_height);
        speed = (int) resources.getDimension(R.dimen.bird_speed);
        speedHigh = (int) resources.getDimension(R.dimen.bird_speed_high);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.bird3_down);
        bird = Bitmap.createScaledBitmap(bitmap, widthBird, heightBird, false);
        Random random = new Random();
        x = screenWidth / 20 * 19;
        y = random.nextInt(screenHeight - bird.getHeight());
    }

    @Override
    public void update() {
        if (!collision) {
            if (x < screenWidth / 2) {
                x -= speedHigh;
            } else {
                x -= speed;
            }
            position = new Rect(x, y, x + bird.getWidth(), y + bird.getHeight());
            callback.updatePosition(this, position);
        } else {
            callback.obstacleOffcreen(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bird, x, y, null);
    }

    public void collision(boolean collision) {
        this.collision = collision;
    }
}
