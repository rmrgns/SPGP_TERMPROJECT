package com.example.thelastknight.game;

import androidx.annotation.NonNull;

import com.example.framework.scene.RecycleBin;

import java.util.Random;

//import kr.ac.tukorea.ge.spgp2023.cookierun.R;
//import kr.ac.tukorea.ge.spgp2023.framework.scene.RecycleBin;

public class Platform extends MapObject {
    private Type type;

    public boolean canPass() {
        return type == Type.T_3x1;
    }
    public enum Type {
        T_10x2, T_2x2, T_3x1, COUNT;
        int resId() { return resIds[this.ordinal()]; }
        int width() { return widths[this.ordinal()]; }
        int height() { return heights[this.ordinal()]; }
        static int[] resIds = {
                //R.mipmap.cookierun_platform_480x48,
                //R.mipmap.cookierun_platform_124x120,
                //R.mipmap.cookierun_platform_120x40,
        };
        static int[] widths = { 10, 2, 3 };
        static int[] heights = { 2, 2, 1 };
        static Type random(Random random) {
            return Type.values()[random.nextInt(2)];
        }
    }
    private Platform() {
        super(MainScene.Layer.platform);
    }
    public static Platform get(Type type, float left, float top) {
        Platform platform = (Platform) RecycleBin.get(Platform.class);
        if (platform == null) {
            platform = new Platform();
        }
        platform.init(type, left, top);
        return platform;
    }
    public void init(Type type, float left, float top) {
        this.type = type;
        setBitmapResource(type.resId());
        width = type.width();
        height = type.height();
        // Platform 은 x,y 를 사용하지 않고 dstRect 만을 사용하도록 한다.
        dstRect.set(left, top, left + width, top + height);
    }

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + "(" + width + "x" + height + ")";
    }
}
