package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tth.gamebirdshooting.GameManagerCallback;

import java.util.ArrayList;
import java.util.List;

public class BulletManager implements GameInterface, ObstacleCallback {
    private ArrayList<Bullet> listBullet = new ArrayList<>();
    private int screenHeight, screenWidth;
    private Resources resources;
    private GameManagerCallback callback;
    private int stt;//lan goi update, %6==0 thi new bullet
    private int x, y;

    public BulletManager(Resources resources, int screenHeight, int screenWidth, int xDraw, int yDraw, GameManagerCallback callback) {
        this.resources = resources;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        this.x = xDraw;
        this.y = yDraw;
        stt = 0;
        listBullet.add(new Bullet(resources, screenHeight, screenWidth, x, y, this));
    }

    @Override
    public void update() {
        stt++;
        if (stt == 10) {
            stt = 0;
            listBullet.add(new Bullet(resources, screenHeight, screenWidth, x, y, this));
        }
        List<Bullet> temp = new ArrayList<>();
        temp.addAll(listBullet);
        for (Bullet bullet : temp) {
            bullet.update();
        }
        //System.out.println(listBullet.size());
    }

    @Override
    public void draw(Canvas canvas) {
        for (Bullet bullet : listBullet) {
            bullet.draw(canvas);
        }
    }

    @Override
    public void obstacleOffcreen(Bullet bullet) {
        listBullet.remove(bullet);
        callback.removeBullet(bullet);
    }

    @Override
    public void updatePosition(Bullet bullet, Rect position) {
        callback.updatePosition(bullet, position);
    }

    @Override
    public void obstacleOffcreen(Bird bird) {

    }

    @Override
    public void updatePosition(Bird bird, Rect position) {

    }

    @Override
    public void updatePositionBulletBird(Bird bird, Rect position) {

    }

    @Override
    public void updatePosition(Bird bird, boolean crashShield) {

    }

    public void updateCurrentY(int currentY) {
        this.y = currentY;
    }
}
