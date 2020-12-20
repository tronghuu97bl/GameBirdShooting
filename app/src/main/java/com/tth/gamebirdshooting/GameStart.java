package com.tth.gamebirdshooting;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tth.gamebirdshooting.Game.GameInterface;

public class GameStart implements GameInterface {
    private Bitmap gameStart;
    private int screenHeight, screenWidth;
    private int x, y;

    public GameStart(Resources resources, int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        x = (int) resources.getDimension(R.dimen.height_start_game);
        y = (int) resources.getDimension(R.dimen.width_start_game);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.start);
        gameStart = Bitmap.createScaledBitmap(bitmap, x, y, false);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameStart, screenWidth / 2 - gameStart.getWidth() / 2, screenHeight / 2 - gameStart.getHeight() / 2, null);
    }
}
