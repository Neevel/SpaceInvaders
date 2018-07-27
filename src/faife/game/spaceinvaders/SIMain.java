package faife.game.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import faife.game.spaceinvaders.menustate.MenuState;

public class SIMain extends Game {
	
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		setScreen(new MenuState());
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
