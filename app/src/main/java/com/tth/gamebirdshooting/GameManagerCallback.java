package com.tth.gamebirdshooting;

import android.graphics.Rect;

import com.tth.gamebirdshooting.Game.Bird;
import com.tth.gamebirdshooting.Game.Bullet;

public interface GameManagerCallback {
    void updatePosition(Rect position, int currentY);//update position plane

    void updatePosition(Bird bird, Rect position);

    void updatePosition(Bullet bullet, Rect position);

    void updateShield(Bird bird, boolean crashShield);

    void updateBulletBird(Bird bird, Rect bulletPosition);

    void removeBullet(Bullet bullet);

    void removeBird(Bird bird);

    void pressPause(int press);//0 == pause, 1 2 3...
}
