package com.example.thelastknight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.scene.BaseScene;
import com.example.framework.view.GameView;
import com.example.thelastknight.R;
import com.example.thelastknight.game.MainScene;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene(this).pushScene();
    }


    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}
