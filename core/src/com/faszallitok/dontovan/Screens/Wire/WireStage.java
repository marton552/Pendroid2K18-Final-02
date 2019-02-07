package com.faszallitok.dontovan.Screens.Wire;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Game.GameScreen;

import java.util.Arrays;

public class WireStage extends MyStage{

    public int[] list = new int[8];
    public static AssetDescriptor<Texture>[] skins = new AssetDescriptor[]{Assets.WIRE0, Assets.WIRE1, Assets.WIRE2, Assets.WIRE3, Assets.WIRE4,Assets.WIRE5,Assets.WIRE6};
    OneSpriteStaticActor nyak;
    OneSpriteStaticActor[][] actors;
    int[][] referncia;
    int[][] kevert;
    int rnd;

    public int maxTime = 30;
    public double nextTime = System.currentTimeMillis() + 1000;

    MyLabel time;

    public WireStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);
        nyak = new OneSpriteStaticActor(Assets.manager.get(Assets.NYAKNYAK));
        addActor(nyak);
        nyak.setPositionCenterOfActorToCenterOfViewport();
        rnd = 3;
        kevert = new int[rnd][rnd];
        referncia = generalas(rnd);
        for (int i = 0; i<rnd; i++){
            for (int k = 0; k<rnd;k++){
                kevert[i][k] = referncia[i][k];
            }
        }
        int save;
        for (int i = 0; i<rnd; i++){
            for (int k = 0; k<rnd;k++){
                int rand1 = randomInt(0,rnd);
                int rand2 = randomInt(0,rnd);
                save = kevert[rand1][rand2];
                kevert[rand1][rand2] = kevert[i][k];
                kevert[i][k] = save;
                System.out.println(rand2+" "+rand1);
            }
        }
        rajzolas(rnd, kevert);

        time = new MyLabel("30", game.getLabelStyle());
        time.setPosition(getViewport().getWorldWidth() - time.getWidth(), getViewport().getWorldHeight() / 2 - time.getHeight() / 2);
        addActor(time);

        MyLabel info = new MyLabel("Segíts Pendroid\nman-nek a\nhackelésbe!\nCsak egy mód\nlehetséges!\nKattints arra\na két elemre\namit cserélni\nakarsz.", game.getLabelStyle());
        info.setY(getViewport().getWorldHeight() / 2- info.getHeight() / 2);
        info.setX(10);
        info.setAlignment(Align.center);

        addActor(info);
    }

    public int[] csere1 = new int[4];
    public int csereQ = 1;

    int firstClickedI = -1;
    int firstClickedK = -1;

    int savek = -1;
    int savei = -1;
    int count = 0;

    public void funct(int i, int k){

        if(firstClickedK==-1){
        firstClickedI = i;
        firstClickedK = k; }
        else{
        savei = i;
        savek = k;
        int ref1 = kevert[firstClickedI][firstClickedK];
        int ref2 = kevert[savei][savek];
        int ref3 = kevert[savei][savek];
        kevert[savei][savek] = ref1;
        kevert[firstClickedI][firstClickedK]=ref3;
        Texture t = actors[i][k].getTexture();
        actors[i][k].setTexture(Assets.manager.get(skins[ref1]));

        actors[firstClickedI][firstClickedK].setTexture(t);
        firstClickedK = -1;
        firstClickedI = -1;
        savek = -1;
        savei = -1;

            for (int g = 0; g<rnd; g++){
                for (int h = 0; h<rnd;h++){
                    if(kevert[g][h] == referncia[g][h])count++;
                    System.out.println(count);
                }
            }
            if(count==rnd*rnd){
                //System.out.println("grat");
                game.setScreen(new GameScreen(game, 1));
            }
            else count=0;
    }}

    public void rajzolas(int rnd, int[][] asd){

        actors = new OneSpriteStaticActor[rnd][rnd];

        int endvolt = 1;
        int endvolt2 = 1;

        for (int i = 0; i<rnd; i++){
            for (int k = 0; k<rnd;k++){

                if (i==start && endvolt == 1){
                    OneSpriteStaticActor start =new OneSpriteStaticActor(Assets.manager.get(Assets.START));
                    start.setDebug(false);
                    addActor(start);
                    start.setSize((float)(start.getWidth()*1.66),(float)(start.getHeight()*1.66));
                    start.setPosition(nyak.getX()+ nyak.getWidth()/rnd*k-start.getWidth(),nyak.getY()+nyak.getHeight()-(nyak.getHeight()/rnd*i+1)-start.getHeight());
endvolt = 2;
                }

                if (i==end && endvolt2==1){
                    OneSpriteStaticActor end =new OneSpriteStaticActor(Assets.manager.get(Assets.END));
                    end.setDebug(false);
                    addActor(end);
                    end.setSize((float)(end.getWidth()*1.66),(float)(end.getHeight()*1.66));
                    end.setPosition(nyak.getX()+ nyak.getWidth(),nyak.getY()+nyak.getHeight()-(nyak.getHeight()/rnd*i+1)-end.getHeight());
endvolt2=2;
                }

                final int g = i;
                final int h = k;
            actors[i][k]=new OneSpriteStaticActor(Assets.manager.get(skins[asd[i][k]]));
                actors[i][k].setDebug(false);
            addActor(actors[i][k]);
                actors[i][k].setSize((float)(actors[i][k].getWidth()*1.66),(float)(actors[i][k].getHeight()*1.66));
            actors[i][k].setPosition(nyak.getX()+ nyak.getWidth()/rnd*k,nyak.getY()+nyak.getHeight()-(nyak.getHeight()/rnd*i+1)-actors[i][k].getHeight());
            actors[i][k].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    funct(g, h);
                }});
            System.out.print(asd[i][k]);
        }
            System.out.println();
    }
    }

    int start;
    int end;

    public int[][] generalas(int rnd){
        boolean xyz=true;
        int hossz = rnd;
        start = 0;
        end=randomInt(0,hossz);
        int[][] matrix = new int[hossz][hossz];
        while(xyz){
            for (int i = 0; i<hossz; i++){
                for (int k = 0; k<hossz;k++){
                    matrix[i][k]=0;
                }
            }
            start = 0;
            end = randomInt(0,hossz);
            int x = 0;
            int y = 0;
            int honnanjott = 1;
            int mennyi = 0;
            int kocka =0;
        while (true){
            mennyi = 0;
            listreset(list);
            System.out.println(x+" "+y);
            if(x-1 == -1 && honnanjott != 1){

                list[1] = 999; list[5] = 999; list[6] = 999;
            }
            if(x>=1){
            if(matrix[x-1][y]!=0){
                list[5] = 999; list[6] = 999;
                if(x==1){
                    list[1] = 999;
                }
            }}
            if(x+1 == hossz && honnanjott != 3 ){
                list[3] = 999; list[4] = 999; list[6] = 999;
            }
            if(x<hossz-2){
            if(matrix[x+1][y]!=0){
                list[3] = 999; list[4] = 999; list[6] = 999;
            }}
            //y
            if(y-1 == -1 && honnanjott != 2){
                list[2] = 999; list[4] = 999; list[5] = 999;
            }
            if(y<hossz-2){
            if(matrix[x][y+1]!=0){
                list[2] = 999; list[4] = 999; list[5] = 999;
            }}
            if(y+1 == hossz && honnanjott != 4){
                list[1] = 999; list[2] = 999; list[3] = 999;
            }
            if(y>1){
            if(matrix[x][y-1]!=0){
                list[1] = 999; list[2] = 999; list[3] = 999;
            }}

            if(honnanjott==1){
                list[2]=999; list[3]=999; list[4]=999;
            }
            else if(honnanjott==2){
                list[1]=999; list[3]=999; list[6]=999;
            }
            else if(honnanjott==3){
                list[1]=999; list[2]=999; list[5]=999;
            }
            else if(honnanjott==4){
                list[4]=999; list[5]=999; list[6]=999;
            }

            Arrays.sort(list);
            while (list[mennyi]!=999){
                mennyi++;
            }
            kocka = randomInt(1,mennyi);
            kocka = list[kocka];
            System.out.println(kocka);
            //            matrix[x][y] = randomInt(0,7);
            matrix[y][x]=kocka;
            if(matrix[end][hossz-1]!=0){
                if(honnanjott==1){
                    matrix[end][hossz-1]=6;
                    xyz=false;
                    break;
                }
                else if(honnanjott==2){
                    matrix[end][hossz-1]=4;
                    xyz=false;
                    break;
                }
                else if(honnanjott==4){
                    matrix[end][hossz-1]=3;
                    xyz=false;
                    break;
                }
            }
            if(kocka==1 && honnanjott ==1){honnanjott=2; y++;
            }
            else if(kocka==1 && honnanjott ==4){honnanjott=3; x--;
            }
            else if(kocka==2 && honnanjott ==2){honnanjott=2; y++;
            }
            else if(kocka==2 && honnanjott ==4){honnanjott=4; y--;
            }
            else if(kocka==3 && honnanjott ==4){honnanjott=1; x++;
            }
            else if(kocka==3 && honnanjott ==3){honnanjott=2; y++;
            }
            else if(kocka==4 && honnanjott ==2){honnanjott=1; x++;
            }
            else if(kocka==4 && honnanjott ==3){honnanjott=4; y--;
            }
            else if(kocka==5 && honnanjott ==1){honnanjott=4; y--;
            }
            else if(kocka==5 && honnanjott ==2){honnanjott=3; x--;
            }
            else if(kocka==6 && honnanjott ==1){honnanjott=1; x++;
            }
            else if(kocka==6 && honnanjott ==3){honnanjott=3; x--;
            }

            if(kocka==999)break;
        }}
        for (int i = 0; i<hossz; i++){
            for (int k = 0; k<hossz;k++){
                System.out.print(matrix[i][k]);
            }System.out.println("");
        }
        return matrix;
    }

    static int randomInt(int min, int max) {
        return (int)Math.floor(Math.random() * (max - min) ) + min;
    }
   static int[] listreset(int[] g){
        for (int i = 0; i<7; i++){
            g[i]=(i);
        }
        g[7]=999;
        return g;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(nextTime < System.currentTimeMillis()){
            nextTime = System.currentTimeMillis() + 1000;
            time.setText(maxTime+"");
            maxTime--;
            if(maxTime <= 0) {
                game.setScreen(new GameScreen(game, 2));
                return;
            }
        }
    }

    @Override
    public void init() {

    }
}
