package com.faszallitok.dontovan.Screens.End;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MusicPlayer;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class EndScreen extends MyScreen {
    EndStage endStage;

    public EndScreen(MyGdxGame game, boolean win) {
        super(game);
        MusicPlayer.startMenuMusic();
        endStage = new EndStage(spriteBatch, game, win);
        Gdx.input.setInputProcessor(endStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        endStage.act(delta);
        endStage.draw();
    }

    @Override
    public void init() {

    }
}
