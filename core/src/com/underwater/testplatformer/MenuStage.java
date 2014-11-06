package com.underwater.testplatformer;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;


public class MenuStage extends InputAdapter {

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private SceneLoader sl;

    public MenuStage(final GameStage gameStage) {

        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        //Creatinga a scene loader and passing it a resource manager of game stage
        sl = new SceneLoader(gameStage.essentials.rm);

        // loading UI scene
        sl.loadScene("UIScene");

        // Creating restart button, and adding a click listener to it
        SimpleButtonScript restartButton = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("restartBtn"));
        restartButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                //restarting game when clicked
                gameStage.restart();
            }
        });
    }

    public void render() {
        batch.begin();
        sl.getRoot().draw(batch, 1);
        batch.end();
    }
}
