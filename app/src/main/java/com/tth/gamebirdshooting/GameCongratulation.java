package com.tth.gamebirdshooting;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tth.gamebirdshooting.Game.GameInterface;

public class GameCongratulation implements GameInterface {
    private Bitmap congra;
    private int screenHeight, screenWidth;
    private int x, y;

    public GameCongratulation(Resources resources, int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        x = (int) resources.getDimension(R.dimen.congra_height);
        y = (int) resources.getDimension(R.dimen.congra_width);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.congra);
        congra = Bitmap.createScaledBitmap(bitmap, x, y, false);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(congra, screenWidth / 2 - congra.getWidth() / 2, screenHeight / 2 - congra.getHeight() / 2, null);
    }
}
