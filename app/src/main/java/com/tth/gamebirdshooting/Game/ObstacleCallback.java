package com.tth.gamebirdshooting.Game;

import android.graphics.Rect;

public interface ObstacleCallback {

    void obstacleOffcreen(Bullet bullet);

    void updatePosition(Bullet bullet, Rect position);


    void obstacleOffcreen(Bird bird);

    void updatePosition(Bird bird, Rect position);

    void updatePositionBulletBird(Bird bird, Rect position);

    void updatePosition(Bird bird, boolean crashShield);
}
