package com.faszallitok.dontovan.Screens.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;

import java.util.ArrayList;
import java.util.Random;

public class GameStage extends MyStage {
	private Random rand = new Random();

	public Virus player;
	public Virus enemy;

	public ArrayList<Integer> list = new ArrayList<Integer>();

	public float playerHitPosX = 0; //Pozíció ahol megüti az enemy-t
	public float playerHitPosY = 0;

	public float enemyHitPosX = 0; //Pozíció ahol megüti az player-t
	public float enemyHitPosY = 0;

	public float playerStartPosX = 0; //Player kiinduló pozíció
	public float playerStartPosY = 0;

	public float enemyStartPosX = 0; //Enemmy kiinduló pozíció
	public float enemyStartPosY = 0;

	public int virusSpeed = 100;

	public boolean isEnemyHitting = false; //Amikor az enemy hitelődik.
	public boolean isPlayerHitting = false;

	public int hitStage = 0;
	public float hitX = 0;
	public int nextHitCounter = 0;
	public int nextHitCd = 20;

	public GameStage(Batch batch, MyGdxGame game, final GameScreen screen) {
		super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);


		player = new Virus(Assets.manager.get(Assets.PLAYER));
		player.setSize(player.getWidth() / 2.5f, player.getHeight() / 2.5f);
		player.setPosition( 50, 30);

		playerStartPosX = player.getX();
		playerStartPosY = player.getY();

		addActor(player);

		enemy = new Virus(Assets.manager.get(Assets.ENEMY));
		enemy.setSize(enemy.getWidth() / 2.5f, enemy.getHeight() / 2.5f);
		enemy.setPosition(getViewport().getWorldWidth() - enemy.getWidth() - 30, 150);

		enemyStartPosX = enemy.getX();
		enemyStartPosY = enemy.getY();

		enemyHitPosX = player.getX() + player.getWidth() - 100;
		enemyHitPosY = player.getY();
		addActor(enemy);

		playerHitPosX = enemy.getX() - player.getWidth() + 100;
		playerHitPosY = enemy.getY();

		MyLabel playerName = new MyLabel("Pendroid Man", game.getLabelStyle());
		playerName.setPosition(10, getViewport().getWorldHeight() - playerName.getHeight() - 50);
		playerName.setColor(Color.BLACK);
		addActor(playerName);

		MyLabel enemyrName = new MyLabel("Iván", game.getLabelStyle());
		enemyrName.setPosition(getViewport().getWorldWidth() - enemyrName.getWidth() - 10, getViewport().getWorldHeight() - enemyrName.getHeight() - 50);
		enemyrName.setColor(Color.BLACK);
		addActor(enemyrName);

		hitEnemy();
	}

	public float getAngle(float cx, float cy, float tx, float ty) {
		float angle = (float) Math.toDegrees(Math.atan2(ty - cy, tx - cx));

		if(angle < 0){
			angle += 360;
		}

		return angle;
	}

	public void hitEnemy() { //Amikor a player megüti az enemyt
		isPlayerHitting = true;
		hitStage = 0;
	}

	public void hitPlayer() { //Amikor az enemy üti a playert
		isEnemyHitting = true;
		hitStage = 0;
	}


	private int tick = 0;
	private int animSpeed = 0;

	@Override
	public void act(float delta) {
		super.act(delta);
		animSpeed++;
		if(animSpeed % 10 == 0) tick++;

		player.setY(player.getY() + ((float)Math.cos(tick) * 10) / 50);
		enemy.setY(enemy.getY() - ((float)Math.cos(tick) * 10) / 80);


		if(isPlayerHitting) {
			nextHitCounter++;
			if(hitStage == 0) {
				if (player.getX() >= playerHitPosX && player.getY() >= playerHitPosY) {
					//isPlayerHitting = false;
					hitStage = 1;
					hitX = player.getX();
					nextHitCounter = 0;
				} else {
					player.setPosition(player.getX() + (playerHitPosX - playerStartPosX) / virusSpeed, player.getY() + (playerHitPosY - playerStartPosY) / virusSpeed);
				}
			}else if(hitStage == 1) {
				if(nextHitCounter> nextHitCd) {
					if (player.getX() >= hitX + 30) {
						hitStage = 2;

					} else {
						player.setX(player.getX() + 5);
					}
				}
			}else if(hitStage == 2) {

				if (player.getX() <= hitX) {
					hitStage = 3;
					nextHitCounter = 0;
				} else {
					player.setX(player.getX() - 5);
				}

			}else if(hitStage == 3) {
				if(nextHitCounter> nextHitCd) {
					if (player.getX() <= playerStartPosX && player.getY() <= playerStartPosY) {
						isPlayerHitting = false;
					} else {
						player.setPosition(player.getX() - (playerHitPosX - playerStartPosX) / virusSpeed, player.getY() - (playerHitPosY - playerStartPosY) / virusSpeed);
					}
				}
			}
		}

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
