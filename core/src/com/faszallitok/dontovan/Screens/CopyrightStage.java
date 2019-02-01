package com.faszallitok.dontovan.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Menu.MenuScreen;

public class CopyrightStage extends MyStage {
    public double nextTime = System.currentTimeMillis() + 2000;

    public CopyrightStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        OneSpriteStaticActor logo = new OneSpriteStaticActor(Assets.manager.get(Assets.PENLOGO));
        logo.setPosition(getViewport().getWorldWidth() / 2 - logo.getWidth() / 2, getViewport().getWorldHeight() / 2 - logo.getHeight() / 2);
        addActor(logo);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(System.currentTimeMillis() > nextTime) {
            game.setScreen(new MenuScreen(game));
            return;
        }
    }

    @Override
    public void init() {

    }
}
