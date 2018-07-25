package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import faife.game.spaceinvaders.util.Constants;

public class Enemy extends GameObject {
	
	private int direction;					
	private float speed;
	
	public Enemy(Texture tex, Vector2 position, float width, float height, GameState parent) {
		super(tex, position, width, height, parent);
		this.parent = parent;
		
		direction = 1;
		speed = Constants.ENEMY_BASESPEED * (parent.getLevel() + (parent.getLevel() / 100));
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if(position.x + speed * delta * direction < Constants.VIRTUAL_WIDTH - width && position.x + speed * delta * direction > 0) {
			position.x += speed * direction * delta;
		} else {
			parent.changeDirection();
		}
	}
	
	public void toggleDirection() {
		position.y -= .3f;
		direction *= -1;
	}

}
