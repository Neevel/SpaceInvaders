package faife.game.spaceinvaders.menustate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuState extends ScreenAdapter {
	
	private Stage stage;
	private Skin skin;
	private Table root, uiElements;
	private Label lblMenu;
	private TextButton txtBtnStart, txtBtnHighscore, txtBtnExit;
	private ButtonListener listener;
	
	@Override
	public void show() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
		listener = new ButtonListener();
		
		root = new Table();
		root.setFillParent(true);
		
		uiElements = new Table();
		
		lblMenu = new Label("Menu", skin);
		txtBtnStart = new TextButton("Start", skin);
		txtBtnStart.setName("start");
		txtBtnStart.addListener(listener);
		uiElements.add(txtBtnStart).width(stage.getViewport().getWorldWidth() / 3).padTop(stage.getViewport().getWorldHeight() / 60).padBottom(stage.getViewport().getWorldHeight() / 60).row();
		txtBtnHighscore = new TextButton("Highscore", skin);
		txtBtnHighscore.setName("highscore");
		txtBtnHighscore.addListener(listener);
		uiElements.add(txtBtnHighscore).width(stage.getViewport().getWorldWidth() / 3).padTop(stage.getViewport().getWorldHeight() / 60).padBottom(stage.getViewport().getWorldHeight() / 60).row();
		txtBtnExit = new TextButton("Exit", skin);
		txtBtnExit.setName("exit");
		txtBtnExit.addListener(listener);
		uiElements.add(txtBtnExit).width(stage.getViewport().getWorldWidth() / 3).padTop(stage.getViewport().getWorldHeight() / 60).padBottom(stage.getViewport().getWorldHeight() / 60).row();
				
		root.add(lblMenu).padBottom(stage.getViewport().getWorldHeight() / 20).row();
		root.add(uiElements);
		
		stage.addActor(root);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void dispose() {
	}
	
	@Override
	public void hide() {
		dispose();
	}

}
