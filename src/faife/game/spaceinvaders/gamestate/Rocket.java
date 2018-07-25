package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import faife.game.spaceinvaders.util.Constants;

public class Rocket extends GameObject {
	
	private float speed;
	private boolean isActive;
	
	public Rocket(Texture tex, Vector2 position, float width, float height, GameState parent) {
		super(tex, position, width, height, parent);
		speed = Constants.ROCKET_BASESPEED * (parent.getLevel() + parent.getLevel() / 100);
		isActive = false;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if(isActive) {
			position.y += speed * delta;
		}
		if(position.y > Constants.VIRTUAL_HEIGHT) {
			kill();
		}
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setActive(boolean active) {
		isActive = active;
	}
	
	public void reset() {
		setPosition(Constants.ROCKET_REST_POS);
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	@Override
	public void kill() {
		setActive(false);
		reset();
	}
}
