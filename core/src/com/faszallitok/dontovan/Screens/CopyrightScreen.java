package com.faszallitok.dontovan.Screens;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class CopyrightScreen extends MyScreen{
    CopyrightStage stage;

    public CopyrightScreen(MyGdxGame game) {
        super(game);
        setBackGroundColor(1, 1,1);
        stage = new CopyrightStage(spriteBatch, game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void init() {

    }
}
