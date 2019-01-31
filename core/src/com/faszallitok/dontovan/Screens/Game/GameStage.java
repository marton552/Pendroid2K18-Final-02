package com.faszallitok.dontovan.Screens.Game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyGdxGame;

import java.util.ArrayList;
import java.util.Random;

public class GameStage extends MyStage {
	private Random rand = new Random();

	public OneSpriteStaticActor szunyog;

	public float szunyogDirX = 0;
	public float szunyogDirY = 0;

	public int mapW = 5000;
	public int mapH = 5000;

	public float szunyog_speed = 6; // def: 6

	public float HUNGER = 100; //Éhség
	public float hungerLoss = 0.04f; //Milyen éhes legyen időközönként
	public boolean isSucking = false; //Jelenleg vért szív-e
	public float suckTime = 100; //Mennyi idő egy szívás
	public float currSuckTime = 0; //Mennyi ideje szívott
	public float suckSpeed = 1; //Szívás gyorsaság
	public float suckHunger = 10; //Mennyi éhséget vonjon szívás után
	public int currSuckingArea = 0; //Jelenleg melyik területet szívja

	public int dashCD = 300; //Dash töltési idő
	public int dashCurrCD = dashCD; //Hol tart a visszatöltés
	public float dashSpeed = 2; //Mennyivel szorozza a gyorsaságot, dashkor
	public int dashDuration = 20; //Mennyi ideig legyen dash
	public int dashCurrDur = 0; //Mennyi ideje megy a dash.
	public boolean isDashing = false;

	public int maxSuckedAreas = 30;

	public int lastStrike = 0; //Hány actnyira volt az utolsó csapás
	public int strikeRarityMin = 800; //Milyen gyakran legyen csapás(maximum)
	public int strikeRarityMax = 2000; //Milyen gyakran legyen csapás(maximum)
	public int nextStrike = 0;
	public int nextStrikeCounter = 0;
	public boolean isStriking = false; //Lecsapás van-e
	public int nextStrikeDownRarityMin = 300;
	public int nextStrikeDownRarityMax = 500;
	public int nextStrikeDown = 0;
	public int nextStrikeDownCounter = 0;
	public float strikingAtX = 0;
	public float strikingAtY = 0;
	public boolean strikeInbound = false; //Lecsapás célzás
	public float strikeSpeed = 13;
	public float strikeMinW;
	public float strikeMinH;
	public float strikeMaxW;
	public float strikeMaxH;

	public float strikeDestX = 0;
	public float strikeDestY = 0;
	public float strikeCurrX = 0;
	public float strikeCurrY = 0;
	public float strikeDestSpeed = 30;


	//Stats
	private int dealtDamage = 0;
	private long ellapsedTime = System.currentTimeMillis();
	private int ellapsedSecs = 0;
	private int missedStrikes = 0;

	public Sound bzz;


	private ArrayList<OneSpriteStaticActor> suckAreas = new ArrayList<OneSpriteStaticActor>();
	private ArrayList<OneSpriteStaticActor> suckedAreas = new ArrayList<OneSpriteStaticActor>();

	private OneSpriteStaticActor map;

	private OneSpriteStaticActor arm;
	private OneSpriteStaticActor arm_shadow;


	public GameStage(Batch batch, MyGdxGame game, final GameScreen screen) {
		super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

	}

	public float getAngle(float cx, float cy, float tx, float ty) {
		float angle = (float) Math.toDegrees(Math.atan2(ty - cy, tx - cx));

		if(angle < 0){
			angle += 360;
		}

		return angle;
	}


	@Override
	public void act(float delta) {
		super.act(delta);



	}

	@Override
	public void draw() {

		/*getBatch().setProjectionMatrix(getCamera().combined);
		getBatch().begin();
		map.draw(getBatch(), 1);
		for(int i = 0; i < suckedAreas.size(); i++) {
			suckedAreas.get(i).draw(getBatch(), 1);
		}
		getBatch().end();
		*/
		super.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override public void init() {}
}
