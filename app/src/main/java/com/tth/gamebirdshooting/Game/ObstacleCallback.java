package com.tth.gamebirdshooting.Game;

import android.graphics.Rect;

import java.util.List;

public interface ObstacleCallback {
    void obstacleOffcreen(Bird bird);

    void obstacleOffcreen(Bullet bullet);

    void updatePosition(Bullet bullet, Rect position);

    void updatePosition(Bird bird, Rect position);
}
