package faife.game.spaceinvaders.gamestate;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import faife.game.spaceinvaders.SIMain;
import faife.game.spaceinvaders.gameoverstate.GameOverState;
import faife.game.spaceinvaders.util.Constants;

public class GameState extends ScreenAdapter {
	
	private SpriteBatch batch;
	private Texture background;
	private Camera gameCam;
	private Camera hudCam;
	private Viewport viewport;
	private Player player;
	private Rocket rocket;
	private ArrayList<Enemy> enemies;
	private Bomb bomb;
	private int level;
	private int player_lives;
	private int score;
	private HUD hud;
	
	@Override
	public void show() {
		batch = ((SIMain) Gdx.app.getApplicationListener()).getBatch();
	
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, gameCam);
		
		hudCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		hudCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		
		level = 1;
		player_lives = 3;
		score = 0;
		
		background = new Texture("textures/bg.jpg");
		
		player = new Player(new Texture("textures/player.png"), Constants.PLAYER_BASE_POS, 1f, 1f, this);
		rocket = new Rocket(new Texture("textures/rocket.png"), Constants.ROCKET_REST_POS, player.getWidth() * .2f, player.getHeight() * .8f, this);
		bomb = new Bomb(new Texture("textures/bomb.png"), Constants.BOMB_REST_POS, .2f, 1f, this);
		enemies = new ArrayList<>();
		hud = new HUD(this);
		
		init();
	}

	@Override
	public void dispose() {
		background.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameCam.update();
		hudCam.update();
		
		player.update(delta);
		rocket.update(delta);
		bomb.update(delta);
		
		for(Enemy e : enemies) {
			e.update(delta);
		}
			
		batch.setProjectionMatrix(gameCam.combined);
		batch.begin();
			batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
			batch.draw(player.getTex(), player.getPosition().x, 0, player.getWidth(), player.getHeight());
			batch.draw(rocket.getTex(), rocket.getPosition().x, rocket.getPosition().y, rocket.getWidth(), rocket.getHeight());
			for(Enemy e : enemies) {
				batch.draw(e.getTex(), e.getPosition().x, e.getPosition().y, e.getWidth(), e.getHeight());
			}
			batch.draw(bomb.getTex(), bomb.getPosition().x, bomb.getPosition().y, bomb.getWidth(), bomb.getHeight());
		batch.end();
		
		hud.update();
		batch.setProjectionMatrix(hudCam.combined);
		batch.begin();
			hud.render(batch);
		batch.end();
		
		checkCollisions();
		removeDeadObjects();
	
		if(enemies.isEmpty()) {
			level++;
			init();
		}
		
		if(player_lives < 1) {
			gameOver();
		}
	}
	
	private void checkCollisions() {
		// Collision check here (broad only, pixelperfect later)
		// enemy-rocket
		for(Enemy e : enemies) {
			if(e.getBoundingBox().overlaps(rocket.getBoundingBox())) {
				e.kill();
				rocket.kill();
				score += level;
			}
			// enemy-player
			if(e.getBoundingBox().overlaps(player.getBoundingBox())) {
				gameOver();
			}
		}
		// player - bomb
	}
	
	private void removeDeadObjects() {
		for(int i = 0; i < enemies.size(); i++) {
			if(!enemies.get(i).isAlive) {
				enemies.remove(i);
			}
		}
	}
	
	public void shoot(float x) {
		rocket.setPosition(new Vector2(x, 0));
		rocket.setActive(true);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	public Rocket getRocket() {
		return rocket;
	}

	public void changeDirection() {
		for(Enemy e : enemies) {
			e.toggleDirection();
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getPlayer_lives() {
		return player_lives;
	}

	public int getScore() {
		return score;
	}
	
	private void init() {
		player.setPosition(Constants.PLAYER_BASE_POS.x);
		rocket.reset();
		bomb.reset();
		
		enemies.clear();
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 10; x++) {
				enemies.add(new Enemy(new Texture("textures/enemy.png"), new Vector2(x * 1.5f, Constants.VIRTUAL_HEIGHT - 1f - (1.5f * y)), 1f, 1f, this));
			}
		}
		
	}
	
	private void gameOver() {
		((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverState(score));
	}
	

}
