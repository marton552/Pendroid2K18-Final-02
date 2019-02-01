package com.faszallitok.dontovan.Screens.WordMatrix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;

import java.util.Random;

public class WordMatrixStage extends MyStage {

    public String[] szavak = new String[]{"FA", "SZALLITOK", "PENDROID", "VERSENY", "BENDZSI", "MARTON", "VALTER", "MARTIN", "CSANY", "TELEFON", "JASKOSZILARD", "KANIZSA"};

    public int MAX_WORDS = 4;
    public int FOUND_WORDS = 0;

    public WordMatrixStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        Random rand = new Random();

        int[] helyek = new int[]{rand.nextInt(7), rand.nextInt(7) + 10, rand.nextInt(7) + 20};

        for (int i = 0; i < 29; i++) {
            boolean isWord = false;
            for(int j = 0; j < helyek.length; j++)
                if(helyek[j] == i) isWord = true;


            String randWord = szavak[rand.nextInt(rand.nextInt(szavak.length))];

            MyLabel label = new MyLabel(generateRow(12), game.getLabelStyle());
            label.setColor(Color.GREEN);
            label.setAlignment(Align.center);
            label.setX((35 * i) + 5);
            label.setY(label.getY() - 30);
            addActor(label);

        }

    }

    Random rand = new Random();

    private String generateRow(int length) {
        String txt = "";
        for (int i = 0; i < length; i++) {
            //65 - 90
            txt += (char)(rand.nextInt(25) + 65) + "\n";
        }

        return txt;
    }

    @Override
    public void init() {

    }
}
