package com.example.thelastknight.game;

import android.graphics.RectF;
import android.util.Log;

import com.example.framework.interfaces.IBoxCollidable;
import com.example.framework.interfaces.IRecyclable;
import com.example.framework.objects.Sprite;
import com.example.framework.scene.BaseScene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable {
    private static final String TAG = MapObject.class.getSimpleName();
    public static final float SPEED = 3.0f;
    protected MainScene.Layer layer;
    protected MapObject(MainScene.Layer layer) {
        this.layer = layer;
    }

    @Override
    public void update() {
        float dx = -SPEED * BaseScene.frameTime;
        dstRect.offset(dx, 0);
        if (dstRect.right < 0) {
//            Log.d(TAG, "Removing:" + this);
            BaseScene.getTopScene().remove(layer, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
    @Override
    public void onRecycle() {}
}
