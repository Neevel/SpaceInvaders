package faife.game.spaceinvaders.menustate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import faife.game.spaceinvaders.gameoverstate.GameOverState;
import faife.game.spaceinvaders.gamestate.GameState;

public class ButtonListener extends ClickListener {
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		super.clicked(event, x, y);
		if(event.getListenerActor().getName().equals("start")) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new GameState());
		}
		if(event.getListenerActor().getName().equals("highscore")) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverState());
		}
		if(event.getListenerActor().getName().equals("exit")) {
			Gdx.app.exit();
		}
	}

}
