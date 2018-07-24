package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	protected Texture tex;
	protected Vector2 position;
	protected float width, height;
	protected GameState parent;
	protected Rectangle boundingBox;
	protected boolean isAlive;
	
	public GameObject(Texture tex, Vector2 position, float width, float height, GameState parent) {
		this.tex = tex;
		this.position = position;
		this.width = width;
		this.height = height;
		this.parent = parent;
		boundingBox = new Rectangle(position.x, position.y, width, height);
		isAlive = true;
	}
	
	public void update(float delta) {
		boundingBox.set(position.x, position.y, width, height);
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Texture getTex() {
		return tex;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void kill() {
		isAlive = false;
	}
	
}
