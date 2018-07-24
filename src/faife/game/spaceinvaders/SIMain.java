package faife.game.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import faife.game.spaceinvaders.gamestate.GameState;

public class SIMain extends Game {
	
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		setScreen(new GameState());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}

}
