package com.faszallitok.dontovan.Screens.Story;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyButton;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Game.GameScreen;

public class StoryStage extends MyStage{

    OneSpriteStaticActor bg;
    OneSpriteStaticActor jojo;
    public int currSlide = 0;
    private Music currMusic;


    public StoryStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        /*>MyButton combat = new MyButton("combat", game.getButtonStyle());
        combat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, 0));
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
        */

        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.STORY1));
        bg.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
        addActor(bg);

        jojo = new OneSpriteStaticActor(Assets.manager.get(Assets.JOJO));
        jojo.setVisible(false);
        jojo.setY(getViewport().getWorldHeight());
        addActor(jojo);

        MyButton skip = new MyButton("Átugrás", game.getButtonStyle());
        skip.setX(getViewport().getWorldWidth() - skip.getWidth());
        skip.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                currMusic.stop();
                game.setScreen(new GameScreen(game, 0));
            }
        });
        addActor(skip);

        nextSlide();
    }

    public void nextSlide() {
        if(currSlide == 0) {
            bg.setTexture(Assets.manager.get(Assets.STORY1));
            currMusic = Assets.manager.get(Assets.MUSIC1);
            currMusic.play();
            currMusic.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    nextSlide();
                }
            });
        }else if(currSlide == 1) {
            bg.setTexture(Assets.manager.get(Assets.STORY2));
            currMusic = Assets.manager.get(Assets.MUSIC2);
            currMusic.play();
            currMusic.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    nextSlide();
                }
            });
        }else if(currSlide == 2) {
            bg.setTexture(Assets.manager.get(Assets.STORY2));
            currMusic = Assets.manager.get(Assets.MUSIC3);
            currMusic.play();
            jojo.setVisible(true);
            currMusic.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    nextSlide();
                    jojo.setVisible(false);
                }
            });
        }else if(currSlide == 3) {
            bg.setTexture(Assets.manager.get(Assets.STORY3));
            currMusic = Assets.manager.get(Assets.MUSIC4);
            currMusic.play();
            currMusic.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    game.setScreen(new GameScreen(game, 0));
                }
            });
        }

        currSlide++;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(jojo.isVisible()) {
            jojo.setY(jojo.getY() - 10);
        }
    }

    @Override
    public void init() {

    }
}
