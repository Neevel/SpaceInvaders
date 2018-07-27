package faife.game.spaceinvaders.gameoverstate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import faife.game.spaceinvaders.SIMain;

public class GameOverState extends ScreenAdapter {
	
	private Preferences highscore;
	private int score;
	private String userName;
	
	private SpriteBatch batch;
	private BitmapFont font;
 	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	
	public GameOverState(int score) {
		this.score = score;
		highscore = Gdx.app.getPreferences("highscore");
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SalemErgotism.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 1000;
		font = generator.generateFont(parameter);
		
		batch = ((SIMain) Gdx.app.getApplicationListener()).getBatch();
		userName = new String();
		
		checkScore();
	}
	
	private void checkScore() {
		if(score > highscore.getInteger("topScore", 0)) {
			createNewHighscore();
		}
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
//			highscore.putInteger("topScore", score);			
//			highscore.putString("topUser", getName());
//			highscore.flush();
//			System.out.println("new highscore by " + highscore.getString("topUser", "2Lazy4Name") + " with a score of " + highscore.getInteger("topScore"));
	}
	
	private void getName() {
		Gdx.input.getTextInput(new TextInputListener() {
			@Override
			public void input(String text) {
				userName = text;
			}
	           
			@Override
			public void canceled() {
				userName = "2Lazy4Name";
			}
        
			}, "Enter your Name", "", "");
		}
	
	private void createNewHighscore() {
		getName();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		dispose();		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	
}
