package com.faszallitok.dontovan.Screens.About;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyButton;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.Menu.MenuScreen;

public class AboutStage extends MyStage {
    private MyButton back;
    private MyLabel title;
    private MyLabel info;

    public AboutStage(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        OneSpriteStaticActor bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BG));
        bg.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
        addActor(bg);

        title = new MyLabel("A Játékról", game.getLabelStyle());
        title.setAlignment(Align.center);
        title.setPosition(getViewport().getWorldWidth() / 2 - title.getWidth() / 2, getViewport().getWorldHeight() - 90);
        title.setFontScale(1.6f);
        addActor(title);

        info = new MyLabel("Egy védelmező vírus vagy, aki próbálja legyőzni a gonosz Ivánt.\nKét féle hackelés van, próbálj mind kettőbe ugyeskedni.\nAz egyikbe szót kell keresni, másikba az utat.\nSok szerencsét!\n\nKészítők: Fa Szállítók csapata", game.getLabelStyle());
        info.setAlignment(Align.center);
        info.setPosition(getViewport().getWorldWidth() / 2 - info.getWidth() / 2, getViewport().getWorldHeight() - 390);
        addActor(info);

        OneSpriteStaticActor logo = new OneSpriteStaticActor(Assets.manager.get(Assets.PENLOGO));

        back = new MyButton("Menü", game.getButtonStyle());
        back.setPosition(getViewport().getWorldWidth() / 2 - back.getWidth() / 2, 20 );
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getGame().setScreen(new MenuScreen(getGame()));
            }
        });
        addActor(back);
    }

    @Override
    public void init() {

    }
}
