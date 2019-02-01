package com.faszallitok.dontovan.Screens.Wire;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyGdxGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WireStage extends MyStage{

    public int[] list = new int[8];

    public WireStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);
        generalas();
    }

    public void generalas(){
        boolean xyz=true;
        int hossz = 5;
        int start = randomInt(0,hossz);
        int end=randomInt(0,hossz);
        int[][] matrix = new int[hossz][hossz];
        while(xyz){
            for (int i = 0; i<hossz; i++){
                for (int k = 0; k<hossz;k++){
                    matrix[i][k]=0;
                }
            }
            start = randomInt(0,hossz);
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

            for (int i = 0; i<hossz; i++){
                for (int k = 0; k<hossz;k++){
                    System.out.print(matrix[i][k]);
                }System.out.println("");
            }
            if(kocka==999)break;
        }}
        for (int i = 0; i<hossz; i++){
            for (int k = 0; k<hossz;k++){
                System.out.print(matrix[i][k]);
            }System.out.println("");
        }
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
    public void init() {

    }
}
