package com.faszallitok.dontovan;

import com.badlogic.gdx.audio.Music;
import com.faszallitok.dontovan.GlobalClasses.Assets;

public class MusicPlayer {


    public static boolean isMenuMusicPlaying = false;
    public static boolean isGameMusicPlaying = false;
    public static Music menuMusic;
    public static Music gameMusic;

    public static void startMenuMusic() {
        if(isMenuMusicPlaying == false) {

            if(isGameMusicPlaying) {
                gameMusic.stop();
                isGameMusicPlaying = false;
            }

            isMenuMusicPlaying = true;
            menuMusic = Assets.manager.get(Assets.MENU_MUSIC);
            menuMusic.setVolume(0.7f);
            menuMusic.setLooping(true);
            menuMusic.play();
        }

    }

    public static void startGameMusic() {
        if(isGameMusicPlaying == false) {
            if(isMenuMusicPlaying) {
                menuMusic.stop();
                isMenuMusicPlaying = false;
            }
            isGameMusicPlaying = true;
            gameMusic = Assets.manager.get(Assets.GAME_MUSIC);
            gameMusic.setVolume(0.7f);
            gameMusic.setLooping(true);
            gameMusic.play();
        }
    }

    public static void stopAllMusic() {
        if(isGameMusicPlaying) {
            gameMusic.stop();
            isGameMusicPlaying = false;
        }

        if(isMenuMusicPlaying) {
            menuMusic.stop();
            isMenuMusicPlaying = false;
        }
    }



}
