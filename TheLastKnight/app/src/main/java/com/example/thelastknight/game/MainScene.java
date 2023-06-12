package com.example.thelastknight.game;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.framework.objects.Button;
import com.example.framework.scene.BaseScene;
import com.example.framework.view.Metrics;
import com.example.thelastknight.R;

import java.util.Random;


public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;

    public enum Layer {
        bg, player, ui, platform ,obstacle, touch, controller, COUNT
    }
    public MainScene(Context context) {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.bg, -0.2f));

        player = new Player();
        add(Layer.player, player);

        add(Layer.touch, new Button(R.mipmap.atk_btn_1, 1.0f, 8.0f, 1.0f, 1.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveLeft();
                    player.attack1();

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.atk_btn_2, 1.0f, 6.5f, 1.0f, 1.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveLeft();
                    player.attack2();

                }
                return true;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.atk_btn_1, 15.0f, 8.0f, 1.0f, 1.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveRight();
                    player.attack1();

                }
                return true;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.atk_btn_2, 15.0f, 6.5f, 1.0f, 1.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveRight();
                    player.attack2();

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.gaugebar_min, 8.0f, 8.0f, 4.0f, 0.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.gaugeAttack();
                }
                return true;
            }
        }));
        //add(Layer.controller, new MapLoader(context));
        add(Layer.controller, new CollisionChecker(player));

        add(Layer.touch, new Button(R.mipmap.arrow_left, 0.0f, 0.0f, 8.0f, 9.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveLeft();
                }
                return true;
            }

        }));
        add(Layer.touch, new Button(R.mipmap.arrow_right, 8.0f, 0.0f, 8.0f, 9.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.moveRight();
                }
                return true;
            }

        }));

    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
