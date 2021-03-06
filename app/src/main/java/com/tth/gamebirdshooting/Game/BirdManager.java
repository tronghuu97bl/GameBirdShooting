package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tth.gamebirdshooting.GameManagerCallback;

import java.util.ArrayList;
import java.util.List;

public class BirdManager implements GameInterface, ObstacleCallback {
    private int screenHeight, screenWidth;
    private List<Bird> listBird = new ArrayList<>();
    private GameManagerCallback callback;
    private Resources resources;
    private int speed, sum = 0; //toc do tao bird moi
    private String man;

    public BirdManager(Resources resources, int screenHeight, int screenWidth, GameManagerCallback callback, String man) {
        this.resources = resources;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;
        this.man = man;
        speed = 0;
        listBird.add(new Bird(resources, screenHeight, screenWidth, this, man));
        sum++;
    }

    @Override
    public void update() {
        speed++;
        if (speed >= 15) {
            speed = 0;
            if (listBird.size() < 6 && sum < 68) {
                listBird.add(new Bird(resources, screenHeight, screenWidth, this, man));
                sum++;
            }
        }
        List<Bird> temp = new ArrayList<>();
        temp.addAll(listBird);
        for (Bird bird : temp) {
            bird.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Bird bird : listBird) {
            bird.draw(canvas);
        }
    }

    @Override
    public void obstacleOffcreen(Bullet bullet) {

    }

    @Override
    public void updatePosition(Bullet bullet, Rect position) {

    }


    @Override
    public void obstacleOffcreen(Bird bird) {
        listBird.remove(bird);
        callback.removeBird(bird);
    }

    @Override
    public void updatePosition(Bird bird, Rect position) {
        callback.updatePosition(bird, position);
    }

    @Override
    public void updatePositionBulletBird(Bird bird, Rect position) {
        callback.updateBulletBird(bird, position);
    }

    @Override
    public void updatePosition(Bird bird, boolean crashShield) {
        callback.updateShield(bird, crashShield);
    }
}
