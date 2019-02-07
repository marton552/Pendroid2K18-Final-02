package com.faszallitok.dontovan.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyButton;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.About.AboutScreen;
import com.faszallitok.dontovan.Screens.Game.GameStage;
import com.faszallitok.dontovan.Screens.Story.StoryScreen;

public class MenuStage extends MyStage {

    private MyButton play;
    private MyButton about;
    private MyButton quit;

    public MenuStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        OneSpriteStaticActor bg = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU_BG));
        bg.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());

        addActor(bg);

        float w = getViewport().getWorldWidth();

        play = new MyButton("Játék", game.getButtonStyle());
        play.setPosition(w /2 - play.getWidth() / 2, 230);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameStage.PLAYER_HP = 100;
                GameStage.ENEMY_HP = 100;
                getGame().setScreen(new StoryScreen(getGame()));
            }
        });
        addActor(play);

        about = new MyButton("A Játékról", game.getButtonStyle());
        about.setPosition(w /2 - about.getWidth() / 2, 170);
        about.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getGame().setScreen(new AboutScreen(getGame()));
            }
        });
        addActor(about);

        quit = new MyButton("Kilépés", game.getButtonStyle());
        quit.setPosition(w /2 - quit.getWidth() / 2, 110);
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
        addActor(quit);
    }

    @Override
    public void init() {

    }
}
