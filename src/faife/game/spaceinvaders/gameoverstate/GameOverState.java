package faife.game.spaceinvaders.gameoverstate;

import com.badlogic.gdx.ScreenAdapter;

public class GameOverState extends ScreenAdapter {
	
	private int score;
	
	public GameOverState(int score) {
		this.score = score;
	}
	
	@Override
	public void render(float delta) {
		
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
