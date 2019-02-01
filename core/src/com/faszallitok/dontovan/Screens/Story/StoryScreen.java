package com.faszallitok.dontovan.Screens.Story;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MusicPlayer;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class StoryScreen extends MyScreen {
    StoryStage storyStage;

    public StoryScreen(MyGdxGame game) {
        super(game);
        MusicPlayer.stopAllMusic();
        storyStage = new StoryStage(spriteBatch, game);
        Gdx.input.setInputProcessor(storyStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        storyStage.act(delta);
        storyStage.draw();
    }

    @Override
    public void init() {

    }
}
