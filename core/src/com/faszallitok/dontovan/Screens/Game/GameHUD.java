package com.faszallitok.dontovan.Screens.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyGdxGame;

public class GameHUD extends MyStage{

    private OneSpriteStaticActor menu;

    private GameScreen screen;

    public GameHUD(Batch batch, MyGdxGame game, final GameScreen screen) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);
        this.screen = screen;

        menu = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU_ICON));
        menu.setSize(30, 30);
        menu.setPosition(getViewport().getWorldWidth() - menu.getWidth() - 10, getViewport().getWorldHeight() -  menu.getHeight() - 10);
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                screen.isPaused = true;
            }
        });


        OneSpriteStaticActor menu_bg = new OneSpriteStaticActor(Assets.manager.get(Assets.DARK));
        menu_bg.setSize(menu.getWidth() + 2, menu.getHeight() + 2);
        menu_bg.setPosition(menu.getX() - 1, menu.getY() - 1);
        //addActor(menu_bg);
        addActor(menu);
    }


    @Override
    public void act(float delta) {
        super.act(delta);


    }

    @Override
    public void init() {

    }
}
