package com.example.thelastknight.game;

import android.graphics.Canvas;

import com.example.framework.interfaces.IBoxCollidable;
import com.example.framework.interfaces.IGameObject;
import com.example.framework.scene.BaseScene;
import com.example.framework.util.CollisionHelper;

import java.util.ArrayList;

public class CollisionChecker implements IGameObject {
    private Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        //ArrayList<IGameObject> items = scene.getObjectsAt(MainScene.Layer.item);
        //for (int i = items.size() - 1; i >= 0; i--) {
        //    IGameObject gobj = items.get(i);
        //    if (!(gobj instanceof IBoxCollidable)) {
        //        continue;
        //    }
        //    if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
        //        scene.remove(MainScene.Layer.item, gobj);
        //    }
        //}
        //ArrayList<IGameObject> obstacles = scene.getObjectsAt(MainScene.Layer.obstacle);
        //for (int i = obstacles.size() - 1; i >= 0; i--) {
        //    Obstacle obstacle = (Obstacle) obstacles.get(i);
        //     if (CollisionHelper.collides(player, obstacle)) {
        //        player.hurt(obstacle);
        //    }
        // }
        //  }
    }
    @Override
    public void draw(Canvas canvas) {}
}
