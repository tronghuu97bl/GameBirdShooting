package com.tth.gamebirdshooting;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tth.gamebirdshooting.Game.GameInterface;

public class GameOver implements GameInterface {
    private Bitmap gameOver;
    private int screenHeight, screenWidth;
    private int x, y;

    public GameOver(Resources resources, int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        x = (int) resources.getDimension(R.dimen.height_end_game);
        y = (int) resources.getDimension(R.dimen.width_end_game);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.gameover);
        gameOver = Bitmap.createScaledBitmap(bitmap, x, y, false);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameOver, screenWidth / 2 - gameOver.getWidth() / 2, screenHeight / 2 - gameOver.getHeight() / 2, null);
    }
}
