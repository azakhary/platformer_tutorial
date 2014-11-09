package com.underwater.testplatformer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;


public class MenuStage extends Stage {

    private GameStage gameStage;

    public MenuStage(PlatformerResourceManager rm, GameStage stage) {
        super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));

        gameStage = stage;

        //Creatinga a scene loader and passing it a resource manager of game stage
        SceneLoader sl = new SceneLoader(rm);
        sl.setResolution(rm.currentResolution.name);

        // loading UI scene
        sl.loadScene("UIScene");

        // adding it's root composite item to the stage
        addActor(sl.getRoot());

        // Creating restart button, and adding a click listener to it
        SimpleButtonScript restartButton = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("restartBtn"));
        restartButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                //restarting game when clicked
                gameStage.restart();
            }
        });
    }
}
