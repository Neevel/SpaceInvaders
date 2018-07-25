package faife.game.spaceinvaders.gameoverstate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class GameOverState extends ScreenAdapter {
	
	private int score;
	
	public GameOverState(int score) {
		this.score = score;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		System.out.println("Game over nap");
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
