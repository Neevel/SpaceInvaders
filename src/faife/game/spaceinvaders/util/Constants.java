package faife.game.spaceinvaders.util;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	
	public static final String TITLE = "SpaceInvadersPro";
	public static final float VERSION = .01f;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static final int VIRTUAL_WIDTH = 20;
	public static final int VIRTUAL_HEIGHT = 15;
	
	public static final float PLAYER_BASESPEED = 10f;
	public static final Vector2 PLAYER_BASE_POS = new Vector2(VIRTUAL_WIDTH / 2, 0f);
	
	public static final float ENEMY_BASESPEED = 4f;
	
	public static final float ROCKET_BASESPEED = 50f;
	public static final Vector2 ROCKET_REST_POS = new Vector2(-10f, -10f);
	
	public static final float BOMB_BASESPEED = 20f;
	public static final Vector2 BOMB_REST_POS = new Vector2(0f, 20f);
}
