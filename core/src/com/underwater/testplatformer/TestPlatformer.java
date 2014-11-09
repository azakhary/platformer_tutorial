package com.underwater.testplatformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.ResolutionEntryVO;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class TestPlatformer extends ApplicationAdapter {

    private PlatformerResourceManager rm;

    private InputMultiplexer inputMultiplexer;

    private GameStage gameStage;
    private MenuStage menuStage;
	
	@Override
	public void create () {

        rm = new PlatformerResourceManager();
        rm.initPlatformerResources();

        inputMultiplexer = new InputMultiplexer();

        gameStage = new GameStage(rm);
        menuStage = new MenuStage(rm, gameStage);

        inputMultiplexer.addProcessor(gameStage);
        inputMultiplexer.addProcessor(menuStage);

        Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act();
        gameStage.draw();

        menuStage.act();
        menuStage.draw();
	}
}
