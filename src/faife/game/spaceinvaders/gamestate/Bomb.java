package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bomb extends GameObject{
	
	private float speed;

	public Bomb(Texture tex, Vector2 position, float width, float height, GameState parent) {
		super(tex, position, width, height, parent);
		speed = 10;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		position.y -= .2 * delta * speed;
	}
}
