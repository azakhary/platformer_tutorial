package com.uwsoft.platformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class PlatformerTutorial extends ApplicationAdapter {

	private SceneLoader sceneLoader;
	private Viewport viewport;
	private Player player;

	private UIStage uiStage;
	
	@Override
	public void create () {
		viewport = new FitViewport(266, 160);
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewport);

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());

		player = new Player();
		root.getChild("player").addScript(player);

		uiStage = new UIStage(sceneLoader.getRm());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

		uiStage.act();
		uiStage.draw();

		((OrthographicCamera)viewport.getCamera()).position.x = player.getX();
	}
}
