package faife.game.spaceinvaders.gamestate;

import java.util.ArrayList;

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
import faife.game.spaceinvaders.util.Constants;

public class GameState extends ScreenAdapter {
	
	private SpriteBatch batch;
	private Texture background;
	private Camera gameCam;
	private Viewport viewport;
	private Player player;
	private Rocket rocket;
	private ArrayList<Enemy> enemies;
	private Bomb bomb;
	
	@Override
	public void show() {
		batch = ((SIMain) Gdx.app.getApplicationListener()).getBatch();
	
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, gameCam);
		
		background = new Texture("textures/bg.jpg");
		player = new Player(new Texture("textures/player.png"), new Vector2(10, 7.5f), 1f, 1f, this);
		rocket = new Rocket(new Texture("textures/rocket.png"), new Vector2(-10f, -10f), player.getWidth() * .2f, player.getHeight() * .8f, this);
		enemies = new ArrayList<>();
		
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 10; x++) {
				enemies.add(new Enemy(new Texture("textures/enemy.png"), new Vector2(x * 1.5f, Constants.VIRTUAL_HEIGHT - 1f - (1.5f * y)), 1f, 1f, this));
			}
		}
		bomb = new Bomb(new Texture("textures/bomb.png"), new Vector2(10f, Constants.VIRTUAL_HEIGHT), .2f, 1f, this);
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
		
		// Collision check here (broad only, pixelperfect later)
		
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
	

}
