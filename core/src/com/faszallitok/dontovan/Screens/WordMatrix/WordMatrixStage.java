package com.faszallitok.dontovan.Screens.WordMatrix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Game.GameScreen;

import java.util.Random;

public class WordMatrixStage extends MyStage {

    public String[] szavak = new String[]{"FA", "SZALLITOK", "PENDROID", "VERSENY", "BENDZSI", "MARTON", "VALTER", "MARTIN", "CSANY", "TELEFON", "SZILARD", "KANIZSA", "EGERSZEG"};

    public int MAX_WORDS = 4;
    public int FOUND_WORDS = 0;

    public MyLabel timeLabel;
    public MyLabel foundLabel;
    public double nextTime = System.currentTimeMillis() + 1000;
    public int TIME = 30;

    public WordMatrixStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        Random rand = new Random();

        timeLabel = new MyLabel("30", game.getLabelStyle());
        timeLabel.setPosition(getViewport().getWorldWidth() / 2 - timeLabel.getWidth() / 2, getViewport().getWorldHeight() - timeLabel.getHeight() - 5);
        addActor(timeLabel);

        foundLabel = new MyLabel(FOUND_WORDS+"/"+MAX_WORDS, game.getLabelStyle());
        foundLabel.setPosition(getViewport().getWorldWidth() - foundLabel.getWidth() - 10, getViewport().getWorldHeight() - foundLabel.getHeight() - 5);
        addActor(foundLabel);

        MyLabel infoLabel = new MyLabel("Védekezz!\nKeresd a szavakat!", game.getLabelStyle());
        //infoLabel.setAlignment(Align.center);
        infoLabel.setPosition(10, getViewport().getWorldHeight() - infoLabel.getHeight());
        addActor(infoLabel);

        int[] helyek = new int[]{rand.nextInt(7), rand.nextInt(7) + 10, rand.nextInt(7) + 20};

        for (int i = 0; i < 29; i++) {
            boolean isWord = false;
            for(int j = 0; j < helyek.length; j++)
                if(helyek[j] == i) isWord = true;

            int startAt = 0;
            String randWord = "";
            if(isWord) {
                randWord = szavak[rand.nextInt(szavak.length)];
                startAt = rand.nextInt(12-randWord.length());
                System.out.println("itt kezdődik: "+startAt+" eddig: "+(startAt+randWord.length()));
            }


            MyLabel label = new MyLabel(generateRow(12, startAt, randWord.length()), game.getLabelStyle());
            label.setColor(Color.GREEN);
            label.setAlignment(Align.center);
            label.setX((35 * i) + 5);
            label.setY(label.getY() - 30);

            addActor(label);

            if(isWord) {
                String newWord = "";
                for (int j = 0; j < randWord.length(); j++) {
                    newWord += randWord.charAt(j) + "\n";
                }
                final MatrixWord szo = new MatrixWord(newWord, game.getLabelStyle());
                System.out.println("minsuz: "+(startAt));

                if(startAt != 0) startAt += 1;

                szo.setPosition(label.getX(), label.getY() + label.getHeight() - szo.getHeight() - (startAt)*42); //- startAt * 30);
                szo.setAlignment(Align.center);
                szo.setColor(Color.GREEN);
                szo.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        event.getListenerActor().setColor(Color.WHITE);
                        if(((MatrixWord)event.getListenerActor()).found == false) {
                            FOUND_WORDS++;
                            foundLabel.setText(FOUND_WORDS+"/"+MAX_WORDS);
                            ((MatrixWord) event.getListenerActor()).found = true;

                            if(MAX_WORDS == FOUND_WORDS) {
                                game.setScreen(new GameScreen(game, 4));
                            }
                        }
                    }
                });
                addActor(szo);
            }

        }

    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if(System.currentTimeMillis() > nextTime) {
            nextTime = System.currentTimeMillis() + 1000;
            TIME -= 1;
            timeLabel.setText(TIME+"");
            if(TIME == 0) {
                game.setScreen(new GameScreen(game,3 ));
                return;
            }
        }
    }

    Random rand = new Random();

    private String generateRow(int length, int placeholderAt, int placeholderLength) {
        String txt = "";
        //placeholderAt = 0;
        for (int i = 0; i < length; i++) {
            txt += (char)(rand.nextInt(25) + 65) + "";
            //65 - 90
            /*if(placeholderLength == 0) {
                txt += (char)(rand.nextInt(25) + 65) + "\n";
            }else  {
                if(i >= placeholderAt && i <= placeholderAt+placeholderLength){
                    txt += "\n\n";
                    //System.out.println("i: "+i);
                }else{
                    txt += (char)(rand.nextInt(25) + 65) + "\n";
                }
            }*/
            //System.out.println(txt);
        }

        if(placeholderLength != 0) {

            String placeholder = "";
            for(int i = 0; i < placeholderLength; i++) {
                placeholder += " ";
            }

            if(placeholderAt != 0) {
                System.out.println("Nem nullánál kezdődik!");
                String kitolto = ((char)(rand.nextInt(25) + 65)) + ""+((char)(rand.nextInt(25) + 65));
                txt = kitolto + txt.substring(0, placeholderAt - 1) + placeholder + txt.substring(placeholderAt + placeholderLength + 1, txt.length());
            }else {
                String kitolto = ((char)(rand.nextInt(25) + 65)) + "";
                txt = placeholder + kitolto + txt.substring(placeholderLength + 1, txt.length());
            }
        }

        System.out.println("EZ JÓ? : '"+txt+"'");

        String matrix = "";
        for(int i = 0; i < txt.length(); i++) {
            matrix += txt.charAt(i) + "\n";
        }

        return matrix;
    }

    @Override
    public void init() {

    }
}
