package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import faife.game.spaceinvaders.util.Constants;

public class Player extends GameObject {
	
	private float speed;
	
	public Player(Texture tex, Vector2 position, float width, float height, GameState parent) {
		super(tex, position, width, height, parent);
		
		this.parent = parent;
		
		speed = Constants.PLAYER_BASESPEED * (parent.getLevel() + parent.getLevel() / 100);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if(boundingBox.x + speed * delta < Constants.VIRTUAL_WIDTH - width) {
				position.x += speed * delta;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(boundingBox.x - speed * delta > 0) {
				position.x -= speed * delta;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			if(!parent.getRocket().isActive()) {
				parent.shoot(position.x + width / 2);
			}
		}
	}
	
	public void setPosition(float x) {
		position.set(x, 0);
	}

}
