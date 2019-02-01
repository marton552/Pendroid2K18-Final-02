package com.faszallitok.dontovan.Screens.Wire;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class WireScreen extends MyScreen{
    WireStage wireStage;

    public WireScreen(MyGdxGame game) {
        super(game);
        wireStage = new WireStage(spriteBatch, game);
        Gdx.input.setInputProcessor(wireStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        wireStage.act(delta);
        wireStage.draw();
    }

    @Override
    public void init() {

    }
}
