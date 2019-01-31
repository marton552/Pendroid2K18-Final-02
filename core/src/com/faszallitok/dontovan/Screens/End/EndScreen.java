package com.faszallitok.dontovan.Screens.End;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MusicPlayer;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class EndScreen extends MyScreen {
    EndStage endStage;

    public EndScreen(MyGdxGame game, int death_type, int dealt_damage, int ellapsed_secs, int missed_strikes) {
        super(game);
        MusicPlayer.startMenuMusic();
        endStage = new EndStage(spriteBatch, game, death_type, dealt_damage, ellapsed_secs, missed_strikes);
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
