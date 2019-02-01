package com.faszallitok.dontovan.Screens.WordMatrix;

import com.badlogic.gdx.Gdx;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyScreen;
import com.faszallitok.dontovan.MyGdxGame;

public class WordMatrixScreen extends MyScreen {

    WordMatrixStage wordMatrixStage;

    public WordMatrixScreen(MyGdxGame game) {
        super(game);

        wordMatrixStage = new WordMatrixStage(spriteBatch, game);
        Gdx.input.setInputProcessor(wordMatrixStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        wordMatrixStage.act(delta);
        wordMatrixStage.draw();
    }

    @Override
    public void init() {

    }
}
