package com.tth.gamebirdshooting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.tth.gamebirdshooting.Game.Background;
import com.tth.gamebirdshooting.Game.Bird;
import com.tth.gamebirdshooting.Game.BirdManager;
import com.tth.gamebirdshooting.Game.Bullet;
import com.tth.gamebirdshooting.Game.BulletManager;
import com.tth.gamebirdshooting.Game.Plane;
import com.tth.gamebirdshooting.Game.Score;

import java.util.HashMap;

public class GameManager extends SurfaceView implements SurfaceHolder.Callback, GameManagerCallback {
    private static final String APP_NAME = "Bird_Shooting";
    private MainThread mainThread;
    private Context context;
    private DisplayMetrics dm;
    private Plane plane;
    private Background background;
    private BulletManager bulletManager;
    private BirdManager birdManager;
    private HashMap<Bullet, Rect> positionBulletHashMap;
    private HashMap<Bird, Rect> positionBirdHashMap;
    private Rect positionPlane;
    private int currentY, initX, score;
    private GameState gameState = GameState.INITIAL;
    private GameStart gameStart;
    private GameOver gameOver;
    private Score scoreGame;
    private GameCongratulation congratulation;
    private MediaPlayer mpGameOver, mpGameCongra, mpGamePlay, mpShoot;

    @Override
    protected int getWindowAttachCount() {
        return super.getWindowAttachCount();
    }

    public GameManager(Context context, AttributeSet attributeSet) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        mainThread = new MainThread(this, getHolder());
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        initGame();
        initSound();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    public void initGame() {
        score = 0;
        positionBulletHashMap = new HashMap<>();
        positionBirdHashMap = new HashMap<>();
        currentY = dm.heightPixels / 2;
        initX = dm.widthPixels / 20 + 320;
        plane = new Plane(getResources(), dm.heightPixels, dm.widthPixels, this);
        background = new Background(getResources(), dm.heightPixels, dm.widthPixels);
        bulletManager = new BulletManager(getResources(), dm.heightPixels, dm.widthPixels, initX, currentY, this);
        birdManager = new BirdManager(getResources(), dm.heightPixels, dm.widthPixels, this);
        gameStart = new GameStart(getResources(), dm.heightPixels, dm.widthPixels);
        gameOver = new GameOver(getResources(), dm.heightPixels, dm.widthPixels);
        scoreGame = new Score(getResources(), dm.heightPixels, dm.widthPixels);
        congratulation = new GameCongratulation(getResources(), dm.heightPixels, dm.widthPixels);
    }

    public void initSound() {
        mpGameOver = MediaPlayer.create(getContext(), R.raw.gameover);
        mpGameCongra = MediaPlayer.create(getContext(), R.raw.congratulation);
        mpGamePlay = MediaPlayer.create(getContext(), R.raw.music);
        mpShoot = MediaPlayer.create(getContext(), R.raw.plane_shoot);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        switch (gameState) {
            case PLAYING:
                mpShoot.start();
                background.update();
                plane.update();
                bulletManager.updateCurrentY(currentY);
                bulletManager.update();
                birdManager.update();
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            switch (gameState) {
                case INITIAL:
                    background.draw(canvas);
                    plane.draw(canvas);
                    gameStart.draw(canvas);
                    break;
                case PLAYING:
                    background.draw(canvas);
                    plane.draw(canvas);
                    bulletManager.draw(canvas);
                    birdManager.draw(canvas);
                    scoreGame.draw(canvas);
                    calculation();
                    break;
                case GAME_OVER:
                    background.draw(canvas);
                    plane.draw(canvas);
                    bulletManager.draw(canvas);
                    birdManager.draw(canvas);
                    gameOver.draw(canvas);
                    scoreGame.draw(canvas);
                    break;
                case GAME_WIN:
                    background.draw(canvas);
                    congratulation.draw(canvas);
                    break;
            }
        }


    }

    public void calculation() {
        boolean out = false;
        if (score >= 68) {
            gameState = GameState.GAME_WIN;
            scoreGame.saveScoreToPre(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
            mpGamePlay.stop();
            mpGameCongra.start();
            return;
        }
        for (Bird bird : positionBirdHashMap.keySet()) {
            Rect psBird = positionBirdHashMap.get(bird);
            for (Bullet bullet : positionBulletHashMap.keySet()) {
                Rect psBullet = positionBulletHashMap.get(bullet);
                //check game over
                if (psBird.left < positionPlane.right && psBird.top > positionPlane.top - 50 && psBird.top < positionPlane.bottom - 50) {
                    gameState = GameState.GAME_OVER;
                    scoreGame.saveScoreToPre(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
                    mpGamePlay.stop();
                    mpGameOver.start();
                    break;
                }
                if (psBird.left < positionPlane.right && psBird.bottom < positionPlane.top && psBird.bottom > positionPlane.bottom) {
                    gameState = GameState.GAME_OVER;
                    scoreGame.saveScoreToPre(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
                    mpGamePlay.stop();
                    mpGameOver.start();
                    break;
                }
                //check va cham
                if (psBullet.right > psBird.left && psBullet.bottom < psBird.bottom && psBullet.bottom > psBird.top && psBullet.left < psBird.left) {
                    score++;
                    plane.updateScore(score);
                    scoreGame.updateScore(score);
                    bullet.collision(true);
                    bird.collision(true);
                    out = true;
                    break;
                }
                if (psBullet.right > psBird.left && psBullet.top < psBird.bottom && psBullet.top > psBird.top && psBullet.left < psBird.left) {
                    score++;
                    plane.updateScore(score);
                    scoreGame.updateScore(score);
                    bullet.collision(true);
                    bird.collision(true);
                    out = true;
                    break;
                }
            }
            if (out) break;
            if (psBird.left < 0) {
                gameState = GameState.GAME_OVER;
                scoreGame.saveScoreToPre(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
                mpGamePlay.stop();
                mpGameOver.start();
                break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gameState) {
            case INITIAL:
                mpGamePlay = MediaPlayer.create(getContext(), R.raw.music);
                mpGamePlay.start();
                gameState = GameState.PLAYING;
                break;
            case PLAYING:
                float dis = event.getY();
                plane.onTouchEvent(dis);
                break;
            case GAME_OVER:
            case GAME_WIN:
                initGame();
                gameState = GameState.INITIAL;
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void updatePosition(Rect position, int currentY) {
        positionPlane = position;
        this.currentY = currentY;
    }

    @Override
    public void updatePosition(Bird bird, Rect position) {
        if (positionBirdHashMap.containsKey(bird)) {
            positionBirdHashMap.remove(bird);
        }
        positionBirdHashMap.put(bird, position);
    }

    @Override
    public void updatePosition(Bullet bullet, Rect position) {
        if (positionBulletHashMap.containsKey(bullet)) {
            positionBulletHashMap.remove(bullet);
        }
        positionBulletHashMap.put(bullet, position);
    }

    @Override
    public void removeBullet(Bullet bullet) {
        positionBulletHashMap.remove(bullet);
    }

    @Override
    public void removeBird(Bird bird) {
        positionBirdHashMap.remove(bird);
    }
}
