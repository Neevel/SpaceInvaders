package faife.game.spaceinvaders.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class HUD {
	
	private GameState parent;
	private BitmapFont font;
 	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	
	private int livesPos, scorePos, levelPos;
	
	public HUD(GameState parent) {
		this.parent = parent;
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SalemErgotism.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		font = generator.generateFont(parameter);
		
		int tmp;
		tmp = Gdx.graphics.getWidth() / 10;
		livesPos = (int) (tmp * .5f);
		scorePos = tmp * 4;
		levelPos = tmp * 8;
	}
	
	public void update() {
		
	}
	
	public void render(SpriteBatch batch) {
		Color c = batch.getColor();
		batch.setColor(c.r, c.g, c.b, 0.4f);
			font.draw(batch, "Level  " + parent.getLevel(), levelPos, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 40);
			font.draw(batch, "Lives  " + parent.getPlayer_lives(), livesPos, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 40);
			font.draw(batch, "Score  " + parent.getScore(), scorePos, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 40);
		batch.setColor(c);
	}
	
	public void dispose() {
		font.dispose();
		generator.dispose();
	}

}
