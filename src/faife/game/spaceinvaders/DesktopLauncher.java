package faife.game.spaceinvaders;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import faife.game.spaceinvaders.util.Constants;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = Constants.TITLE + " " + Constants.VERSION;
		cfg.width = Constants.WIDTH;
		cfg.height = Constants.HEIGHT;
		
		new LwjglApplication(new SIMain(), cfg);
	}

}
