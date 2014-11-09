package com.underwater.testplatformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

public class TestPlatformer extends ApplicationAdapter {

    private InputMultiplexer inputMultiplexer;

    private GameStage gameStage;
    private MenuStage menuStage;
	
	@Override
	public void create () {

        inputMultiplexer = new InputMultiplexer();

        gameStage = new GameStage();
        menuStage = new MenuStage(gameStage);

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
