package com.tth.gamebirdshooting;

import android.graphics.Rect;

import com.tth.gamebirdshooting.Game.Bird;
import com.tth.gamebirdshooting.Game.Bullet;
import com.tth.gamebirdshooting.Game.Plane;

public interface GameManagerCallback {
    void updatePosition(Rect position, int currentY);//update position plane

    void updatePosition(Bird bird, Rect position);

    void updatePosition(Bullet bullet, Rect position);

    void removeBullet(Bullet bullet);

    void removeBird(Bird bird);
}
