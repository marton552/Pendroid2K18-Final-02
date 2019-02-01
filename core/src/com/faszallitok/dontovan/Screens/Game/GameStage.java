package com.faszallitok.dontovan.Screens.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.faszallitok.dontovan.GlobalClasses.Assets;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.MyStage;
import com.faszallitok.dontovan.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.faszallitok.dontovan.MyBaseClasses.UI.MyLabel;
import com.faszallitok.dontovan.MyGdxGame;
import com.faszallitok.dontovan.Screens.End.EndScreen;
import com.faszallitok.dontovan.Screens.Wire.WireScreen;
import com.faszallitok.dontovan.Screens.WordMatrix.WordMatrixScreen;

import java.util.Random;

public class GameStage extends MyStage {
	private Random rand = new Random();

	public Virus player;
	public Virus enemy;

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

	public static int PLAYER_HP = 100;
	public static int ENEMY_HP = 100;
	public static int PLAYER_DAMAGE = 10;
	public static int ENEMY_DAMAGE = 15;

	private int oldPlayerHP = PLAYER_HP;
	private int oldEnemyHP = ENEMY_HP;

	OneSpriteStaticActor playerBar;
	OneSpriteStaticActor enemyBar;

	private float stepSize = 0;

	MyLabel dodgeLabel;
	double labelVisibleTill = 0;

	public boolean DODGE = false;
	public static boolean PLAYER_TURN = true;

	public boolean bevez = false;
	public double bevezTill;

	public GameStage(Batch batch, MyGdxGame game, final GameScreen screen, int state) {
		super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

		OneSpriteStaticActor bg = new OneSpriteStaticActor(Assets.manager.get(Assets.ARENA));
		bg.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
		addActor(bg);

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
		playerName.setPosition(20, getViewport().getWorldHeight() - playerName.getHeight() - 20);
		playerName.setColor(Color.BLACK);
		addActor(playerName);

        playerBar = new OneSpriteStaticActor(Assets.manager.get(Assets.BAR));
        playerBar.setWidth(400);
        playerBar.setPosition(20, playerName.getY() - playerBar.getHeight() - 5);

        OneSpriteStaticActor playerOutBar = new OneSpriteStaticActor(Assets.manager.get(Assets.BAR_OUT));
        playerOutBar.setSize(playerOutBar.getWidth() + 4, playerBar.getHeight() + 4);
        playerOutBar.setPosition(playerBar.getX() - 2, playerBar.getY() - 2);
        addActor(playerOutBar);
        addActor(playerBar);


		MyLabel enemyName = new MyLabel("Iván", game.getLabelStyle());
		enemyName.setPosition(getViewport().getWorldWidth() - enemyName.getWidth() - 20, getViewport().getWorldHeight() - enemyName.getHeight() - 20);
		enemyName.setColor(Color.BLACK);
		addActor(enemyName);

		enemyBar = new OneSpriteStaticActor(Assets.manager.get(Assets.BAR));
		enemyBar.setWidth(400);
		enemyBar.setPosition(getViewport().getWorldWidth() - enemyBar.getWidth() - 20, enemyName.getY() - enemyBar.getHeight() - 5);

		OneSpriteStaticActor enemyOutBar = new OneSpriteStaticActor(Assets.manager.get(Assets.BAR_OUT));
		enemyOutBar.setSize(enemyOutBar.getWidth() + 4, enemyBar.getHeight() + 4);
		enemyOutBar.setPosition(enemyBar.getX() - 2, enemyBar.getY() - 2);

		addActor(enemyOutBar);
		addActor(enemyBar);


		dodgeLabel = new MyLabel("Kikerülve!", game.getLabelStyle());
		dodgeLabel.setVisible(false);
		dodgeLabel.setColor(Color.YELLOW);
		addActor(dodgeLabel);

		stepSize = enemyBar.getWidth() / 100;

		if(state == 0) { bevez = true; bevezTill = System.currentTimeMillis() + 1000;}

		if(state == 1 || state == 2) hitEnemy();
		if(state == 3) hitPlayer();

		if(state == 2 || state == 4) DODGE = true;

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

	public void enemyGotHit() {
		if(DODGE == false) ENEMY_HP -= PLAYER_DAMAGE;
		else {
			dodgeLabel.setPosition(player.getX() + player.getWidth() / 2 - dodgeLabel.getWidth() / 2, player.getY() + player.getHeight() + dodgeLabel.getHeight() + 5);
			dodgeLabel.setVisible(true);
			labelVisibleTill = System.currentTimeMillis() + 1000;
		}
		System.out.println("enemyhp: "+PLAYER_HP);
		//TESZT:
        //hitPlayer();

		if(ENEMY_HP <= 0) {
			game.setScreen(new EndScreen(game, true));
		}
    }

    public void playerGotHit() {

		if(DODGE == false) PLAYER_HP -= ENEMY_DAMAGE;
		else {
			dodgeLabel.setPosition(enemy.getX() + enemy.getWidth() / 2 - dodgeLabel.getWidth() / 2, enemy.getY() + enemy.getHeight() + dodgeLabel.getHeight() + 5);
			dodgeLabel.setVisible(true);
			labelVisibleTill = System.currentTimeMillis() + 1000;
		}

		System.out.println("playerhp: "+PLAYER_HP);

		if(PLAYER_HP <= 0) {
			game.setScreen(new EndScreen(game, false));
		}

        //TESZT:
        //hitEnemy();
    }


    public void playerHittingAnimStop() {
		if(PLAYER_TURN == true) {
			PLAYER_TURN = false;
			game.setScreen(new WordMatrixScreen(game));
		}
	}

	public void enemyHittingAnimStop() {
		if(PLAYER_TURN == false) {
			PLAYER_TURN = true;
			game.setScreen(new WireScreen(game));
		}
	}

	private int tick = 0;
	private int animSpeed = 0;

	@Override
	public void act(float delta) {
		super.act(delta);

		if(bevez == true) {
			if(System.currentTimeMillis() > bevezTill) {
				game.setScreen(new WireScreen(game));
				return;
			}
		}

		if(dodgeLabel.isVisible()) {
			if(System.currentTimeMillis() > labelVisibleTill){
				dodgeLabel.setVisible(false);
			}
		}

		animSpeed++;
		if(animSpeed % 10 == 0) tick++;

		player.setY(player.getY() + ((float)Math.cos(tick) * 10) / 50);
		enemy.setY(enemy.getY() - ((float)Math.cos(tick) * 10) / 80);

		if(oldEnemyHP != ENEMY_HP) {
			if(oldEnemyHP > ENEMY_HP) {
				oldEnemyHP -= 1;
			}else {
				oldEnemyHP += 1;
			}
		}

		enemyBar.setWidth(oldEnemyHP * stepSize);


		if(oldPlayerHP != PLAYER_HP) {
			if(oldPlayerHP > PLAYER_HP) {
				oldPlayerHP -= 1;
			}else {
				oldPlayerHP += 1;
			}
		}

		playerBar.setWidth(oldPlayerHP * stepSize);

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
						enemyGotHit();

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
						playerHittingAnimStop();
					} else {
						player.setPosition(player.getX() - (playerHitPosX - playerStartPosX) / virusSpeed, player.getY() - (playerHitPosY - playerStartPosY) / virusSpeed);
					}
				}
			}
		}

		if(isEnemyHitting) {
			nextHitCounter++;
			if(hitStage == 0) {
				if (enemy.getX() <= enemyHitPosX && enemy.getY() <= enemyHitPosY) {
					//isEnemyHitting = false;
					hitStage = 1;
					hitX = enemy.getX();
					nextHitCounter = 0;
				} else {
					enemy.setPosition(enemy.getX() + (enemyHitPosX - enemyStartPosX) / virusSpeed, enemy.getY() + (enemyHitPosY - enemyStartPosY) / virusSpeed);
				}
			}else if(hitStage == 1) {
				if(nextHitCounter> nextHitCd) {
					//System.out.println(enemy.getX()+" <= "+hitX+" - 30");
					if (enemy.getX() <= hitX - 30) {
						hitStage = 2;
						playerGotHit();
					} else {
						enemy.setX(enemy.getX() - 5);
					}
				}
			}else if(hitStage == 2) {

				if (enemy.getX() >= hitX) {
					hitStage = 3;
					nextHitCounter = 0;
				} else {
					enemy.setX(enemy.getX() + 5);
				}

			}else if(hitStage == 3) {
				if(nextHitCounter> nextHitCd) {
					if (enemy.getX() >= enemyStartPosX && enemy.getY() >= enemyStartPosY) {
						isEnemyHitting = false;
						enemyHittingAnimStop();
                    } else {
						enemy.setPosition(enemy.getX() - (enemyHitPosX - enemyStartPosX) / virusSpeed, enemy.getY() - (enemyHitPosY - enemyStartPosY) / virusSpeed);
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
