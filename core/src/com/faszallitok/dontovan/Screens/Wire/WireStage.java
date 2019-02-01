package com.faszallitok.dontovan.Screens.Wire;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyGdxGame;

public class WireStage extends MyStage{
    public WireStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        int[][] matrix = new int[3][3];
        System.out.println(""+matrix[0][1]);

    }

    @Override
    public void init() {

    }
}
