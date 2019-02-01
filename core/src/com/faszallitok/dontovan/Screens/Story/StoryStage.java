package com.faszallitok.dontovan.Screens.Story;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyButton;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Game.GameScreen;
import com.faszallitok.dontovan.Screens.Wire.WireScreen;
import com.faszallitok.dontovan.Screens.WordMatrix.WordMatrixScreen;

public class StoryStage extends MyStage{
    public StoryStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        MyButton combat = new MyButton("combat", game.getButtonStyle());
        combat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });

        combat.setX(combat.getX() + combat.getWidth());
        addActor(combat);



        MyButton wire = new MyButton("Wire", game.getButtonStyle());
        wire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new WireScreen(game));
            }
        });
        addActor(wire);

        MyButton mtrx = new MyButton("mátrix", game.getButtonStyle());
        mtrx.setX(mtrx.getWidth() * 2);
        mtrx.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new WordMatrixScreen(game));
            }
        });
        addActor(mtrx);

    }

    @Override
    public void init() {

    }
}
