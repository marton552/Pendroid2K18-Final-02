package com.faszallitok.dontovan.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.CopyrightScreen;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(MyGdxGame game) {
        super(game);
    }
    BitmapFont bitmapFont = new BitmapFont();
    TextureAtlas atlas = new TextureAtlas("atlasok/ying-yang-loading.atlas");
    OneSpriteAnimatedActor loading = new OneSpriteAnimatedActor(atlas);
    {
        loading.setFps(20);
        loading.setLooping(true);
        loading.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    TextureAtlas atlas2 = new TextureAtlas("atlasok/logoFinalHor.atlas");
    OneSpriteAnimatedActor logo = new OneSpriteAnimatedActor(atlas2);
    {
        logo.setFps(8);
        logo.setLooping(true);
        //logo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //logo.setY(Gdx.graphics.getHeight() - logo.getHeight() - 10);
        logo.setY(10);
        logo.setX(Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2);
    }

    @Override
    public void show() {
        Assets.manager.finishLoading();
        Assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        loading.draw(spriteBatch, 1f);
        loading.act(delta);
        logo.draw(spriteBatch, 1f);
        logo.act(delta);
        bitmapFont.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", 0, 0);
        spriteBatch.end();
        if (Assets.manager.update()) {
            Assets.afterLoaded();
            game.setScreen(new CopyrightScreen(game));
            //MusicPlayer.playMenuMusic();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void init() {
        setBackGroundColor(0f, 0f, 0f);
    }
}