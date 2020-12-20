package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tth.gamebirdshooting.R;

public class Bullet implements GameInterface {
    private Bitmap bullet;
    private int x, y, height, width, speedBullet;
    private int screenHeight, screenWidth;
    private ObstacleCallback callback;
    private boolean collision = false;
    private Rect position;

    public Bullet(Resources resources, int screenHeight, int screenWidth, int xDraw, int yDraw, ObstacleCallback callback) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        this.x = xDraw;
        this.y = yDraw;
        speedBullet = (int) resources.getDimension(R.dimen.bullet_speed);
        height = (int) resources.getDimension(R.dimen.bullet_height);
        width = (int) resources.getDimension(R.dimen.bullet_width);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.dan);
        bullet = Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    @Override
    public void update() {
        if (x > screenWidth || collision) {
            callback.obstacleOffcreen(this);
        } else {
            x += speedBullet;
            position = new Rect(x, y - bullet.getHeight() / 2, x + bullet.getWidth(), y + bullet.getHeight() / 2);
            callback.updatePosition(this, position);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bullet, x, y - bullet.getHeight() / 2, null);
    }

    public void collision(boolean collision) {
        this.collision = collision;
    }
}
