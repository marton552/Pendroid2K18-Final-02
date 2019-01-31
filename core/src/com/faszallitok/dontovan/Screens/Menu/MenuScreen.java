package com.faszallitok.dontovan.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MusicPlayer;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;

    public MenuScreen(MyGdxGame game) {
        super(game);
        MusicPlayer.startMenuMusic();
        menuStage = new MenuStage(spriteBatch, game);
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void init() {

    }
}
