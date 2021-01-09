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
    private Bitmap bullet;
    private Bitmap shield;
    private int widthBird, heightBird;
    private int screenHeight, screenWidth;
    private int x, y, z, speed, speedHigh;
    private int xBullet, yBullet, heightBullet, widthBullet;
    private ObstacleCallback callback;
    private boolean collision = false, moveUp = false, crashShield = false, bulletCollision = false;
    private Rect position, bulletPosition;
    private String man;

    public Bird(Resources resources, int screenHeight, int screenWidth, ObstacleCallback callback, String man) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        this.man = man;
        widthBird = (int) resources.getDimension(R.dimen.bird_width);
        heightBird = (int) resources.getDimension(R.dimen.bird_height);
        speed = (int) resources.getDimension(R.dimen.bird_speed);
        speedHigh = (int) resources.getDimension(R.dimen.bird_speed_high);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.bird3_down);
        bird = Bitmap.createScaledBitmap(bitmap, widthBird, heightBird, false);
        Random random = new Random();
        x = screenWidth / 20 * 19;
        y = random.nextInt(screenHeight - bird.getHeight());
        if (man.equals("man2") || man.equals("man4")) {
            z = random.nextInt(2);
            if (z == 1) moveUp = false;
            else moveUp = true;
        }
        if (man.equals("man3") || man.equals("man4")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.shield);
            shield = Bitmap.createScaledBitmap(bitmap1, widthBird, heightBird, false);
        }
        if (man.equals("man5")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.dan2);
            heightBullet = (int) resources.getDimension(R.dimen.bullet_height);
            widthBullet = (int) resources.getDimension(R.dimen.bullet_width);
            bullet = Bitmap.createScaledBitmap(bitmap1, widthBullet, heightBullet, false);
            xBullet = x - bird.getWidth() / 2;
            yBullet = y + bird.getHeight() / 2;
        }
    }

    @Override
    public void update() {
        if (man.equals("man5") && !bulletCollision) {
            xBullet -= speedHigh;
            bulletPosition = new Rect(xBullet, yBullet, xBullet + bullet.getWidth(), yBullet + bullet.getHeight());
            callback.updatePositionBulletBird(this, bulletPosition);
        }
        if (!collision) {
            if (x < screenWidth / 2) {
                x -= speedHigh;
            } else {
                x -= speed;
            }
            if (man.equals("man2") || man.equals("man4")) {
                if (man.equals("man2")) x -= speed / 2;
                if (moveUp) {
                    y -= speedHigh;
                    if (y < 0) {
                        moveUp = false;
                    }
                } else {
                    y += speedHigh;
                    if (y > screenHeight) {
                        moveUp = true;
                    }
                }
            }
            position = new Rect(x, y, x + bird.getWidth(), y + bird.getHeight());
            callback.updatePosition(this, position);
            if (man.equals("man3") || man.equals("man4")) {
                callback.updatePosition(this, crashShield);
            }
        } else {
            callback.obstacleOffcreen(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bird, x, y, null);
        if (man.equals("man3") || man.equals("man4")) {
            if (!crashShield)
                canvas.drawBitmap(shield, x, y, null);
        }
        if (man.equals("man5")) {
            if (!bulletCollision) {
                canvas.drawBitmap(bullet, xBullet, yBullet, null);
            }
        }
    }

    public void collision(boolean collision) {
        this.collision = collision;
    }

    public void setShield(boolean crashShield) {
        this.crashShield = crashShield;
    }

    public void updateBullet(boolean b) {
        this.bulletCollision = true;
    }
}
