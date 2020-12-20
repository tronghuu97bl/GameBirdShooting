package com.tth.gamebirdshooting;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameManager gameManager;
    private static Canvas canvas;
    private long targetFPS = 30;
    private boolean running;

    public MainThread(GameManager gameManager, SurfaceHolder surfaceHolder) {
        this.gameManager = gameManager;
        this.surfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime, waitTime, timeMilis, targetTime;
        targetTime = 1000 / targetFPS;
        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameManager.update();
                    gameManager.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMilis = System.nanoTime() - startTime / 1000000;
            waitTime = targetTime - timeMilis;
            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
