package faife.game.spaceinvaders.gameoverstate;

import com.badlogic.gdx.Input.TextInputListener;

public class TextListener implements TextInputListener {
	
	private String userInput;

	@Override
	public void canceled() {
		userInput = "2Lazy4Name";
	}

	@Override
	public void input(String s) {
		userInput = s;
	}
	
	public String getInput() {
		return userInput;
	}

}
