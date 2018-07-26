package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import faife.game.spaceinvaders.util.Constants;

public class Bomb extends GameObject{
	
	private float speed;
	private boolean isActive;

	public Bomb(Texture tex, Vector2 position, float width, float height, GameState parent) {
		super(tex, position, width, height, parent);
		speed = Constants.BOMB_BASESPEED * (parent.getLevel() + parent.getLevel() / 100);
		isActive = false;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if(isActive) {
			position.y -= .2 * delta * speed;
		}
		if(position.y < -height) {
			kill();
		}
	}
	
	public void reset() {
		setPosition(Constants.BOMB_REST_POS);
	}
	
	public void setPosition(Vector2 pos) {
		position.set(pos);
	}

	public boolean isActive() {
		return isActive;
	}
	
	@Override
	public void kill() {
		setActive(false);
		reset();
	}

	public void setActive(boolean active) {
		isActive = active;
		
	}
}
