package com.example.thelastknight.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.thelastknight.R;
import com.example.framework.interfaces.IBoxCollidable;
import com.example.framework.interfaces.IGameObject;
import com.example.framework.objects.AnimSprite;
import com.example.framework.scene.BaseScene;
import com.example.framework.util.CollisionHelper;
import com.example.framework.view.Metrics;

import java.util.ArrayList;

public class Player extends AnimSprite implements IBoxCollidable {
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 1.0f;
    private RectF collisionRect = new RectF();
    protected Obstacle obstacle;

    public Player() {
        super(R.mipmap.chr_sd, 8.0f, 5.5f, 3.0f, 3.0f, 8, 1);
        fixCollisionRect();
    }

    protected enum State {
        running, jump, doubleJump, falling, slide, hurt, COUNT
    }
//    protected Rect[] srcRects
    protected static Rect[][] srcRects = {
            makeRects(100, 101, 102, 103), // State.running
            makeRects(7, 8),               // State.jump
            makeRects(1, 2, 3, 4),         // State.doubleJump
            makeRects(0),                  // State.falling
            makeRects(9, 10),              // State.slide
            makeRects(503, 504),           // State.hurt
    };
    protected static float[][] edgeInsets = {
            { 1.20f, 1.95f, 1.10f, 0.00f }, // State.running
            { 1.20f, 2.25f, 1.10f, 0.00f }, // State.jump
            { 1.20f, 2.20f, 1.10f, 0.00f }, // State.doubleJump
            { 1.20f, 1.80f, 1.20f, 0.00f }, // State.falling
            { 0.80f, 2.90f, 0.80f, 0.00f }, // slide
            { 1.20f, 1.95f, 1.10f, 0.00f }, // State.hurt
    };
    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 2 + (idx % 100) * 272;
            int t = 2 + (idx / 100) * 272;
            rects[i] = new Rect(l, t, l + 270, t + 270);
        }
        return rects;
    }

    @Override
    public void update() {
        switch (state) {
        case running:
        case slide:
            float foot = collisionRect.bottom;
            float floor = findNearestPlatformTop(foot);
            if (foot < floor) {
                state = State.falling;
                jumpSpeed = 0;
            }
            break;
        case hurt:
            if (!CollisionHelper.collides(this, obstacle)) {
                state = State.running;
                obstacle = null;
            }
            break;
        }
    }

    private float findNearestPlatformTop(float foot) {
        Platform platform = findNearestPlatform(foot);
        if (platform == null) return Metrics.game_height;
        return platform.getCollisionRect().top;
    }

    private Platform findNearestPlatform(float foot) {
        Platform nearest = null;
        MainScene scene = (MainScene) BaseScene.getTopScene();
        //ArrayList<IGameObject> platforms = scene.getObjectsAt(MainScene.Layer.platform);
        //float top = Metrics.game_height;
        //for (IGameObject obj: platforms) {
        //    Platform platform = (Platform) obj;
        //    RectF rect = platform.getCollisionRect();
        //    if (rect.left > x || x > rect.right) {
        //        continue;
        //    }
        //    //Log.d(TAG, "foot:" + foot + " platform: " + rect);
        //    if (rect.top < foot) {
        //        continue;
        //    }
        //    if (top > rect.top) {
        //        top = rect.top;
        //        nearest = platform;
        //    }
        //    //Log.d(TAG, "top=" + top + " gotcha:" + platform);
        //}
        //return nearest;
        return nearest;
    }

    private void fixCollisionRect() {
        float[] insets = edgeInsets[state.ordinal()];
        collisionRect.set(
                dstRect.left + insets[0],
                dstRect.top + insets[1],
                dstRect.right - insets[2],
                dstRect.bottom -  insets[3]);
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = srcRects[state.ordinal()];
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    protected State state = State.running;
    public void jump() {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        } else if (state == State.jump) {
            jumpSpeed = -JUMP_POWER;
            state = State.doubleJump;
        }
    }

    public void attack1() {

    }

    public void attack2() {

    }

    public void gaugeAttack() {

    }
    public void moveLeft() {

    }

    public void moveRight() {

    }
    public void hurt(Obstacle obstacle) {
        if (state == State.hurt) return;
        state = State.hurt;
        fixCollisionRect();
        this.obstacle = obstacle;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}
