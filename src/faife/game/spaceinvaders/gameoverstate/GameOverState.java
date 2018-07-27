package faife.game.spaceinvaders.gameoverstate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import faife.game.spaceinvaders.SIMain;
import faife.game.spaceinvaders.menustate.MenuState;

public class GameOverState extends ScreenAdapter {
	
	private Preferences highscore;
	private int score;
	private String userName;
	private boolean gotName;
	private boolean scoreIsSet;
	
	private SpriteBatch batch;
	private BitmapFont font;
 	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	
	public GameOverState() {
		init();
		scoreIsSet = true;
	}
	
	public GameOverState(int score) {
		this.score = score;

		init();
		gotName = false;
		scoreIsSet = false;
		checkScore();
	}
	
	private void init() {
		highscore = Gdx.app.getPreferences("highscore");
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SalemErgotism.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
		
		batch = ((SIMain) Gdx.app.getApplicationListener()).getBatch();
	}
	
	private void checkScore() {
		if(score > highscore.getInteger("firstScore", 0)) {
			createNewHighscore();
		}
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(scoreIsSet) {
			batch.begin();
				font.draw(batch, "Highscore", Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 5);
				font.draw(batch, highscore.getString("firstName") + ": " + highscore.getInteger("firstScore"), Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2);
			batch.end();
			
			if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuState());
			}
		}
		
		if(gotName) {
			saveHighscore();
		}
		
		
	}
	
	private void getUserName() {
		Gdx.input.getTextInput(new TextInputListener() {
			@Override
			public void input(String text) {
				userName = text;
				gotName = true;
			}
	           
			@Override
			public void canceled() {
				userName = "2Lazy4Name";
				gotName = true;
			}
        
			}, "Highscore! Enter your Name", "", "");
		}
	
	private void createNewHighscore() {
		getUserName();
	}
	
	private void saveHighscore() {
		highscore.putString("firstName", userName);
		highscore.putInteger("firstScore", score);
		highscore.flush();
		scoreIsSet = true;
	}

	@Override
	public void dispose() {
		generator.dispose();
		font.dispose();
	}

	@Override
	public void hide() {
		dispose();		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	
}
