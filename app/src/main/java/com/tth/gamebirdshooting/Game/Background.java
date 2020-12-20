package com.tth.gamebirdshooting.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tth.gamebirdshooting.R;

public class Background implements GameInterface {
    private Bitmap bg;
    private int screenHeight, screenWidth;
    private int speedMoveBackground;
    private int x1, x2;

    public Background(Resources resources, int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg);
        bg = Bitmap.createBitmap(bitmap);
        speedMoveBackground = (int) resources.getDimension(R.dimen.speed_move_background);
        x1 = x2 = 0;
    }

    @Override
    public void update() {
        x1 -= speedMoveBackground;
        x2 -= speedMoveBackground;
        if (x2 < 0) {
            x1 = 0;
            x2 = screenWidth;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bg, x1, 0, null);
        canvas.drawBitmap(bg, x2, 0, null);
    }
}
