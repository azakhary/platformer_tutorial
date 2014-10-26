package com.underwater.testplatformer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;


public class MenuStage extends Stage {

    public MenuStage(final GameStage gameStage) {

        //Creatinga a scene loader and passing it a resource manager of game stage
        SceneLoader sl = new SceneLoader(gameStage.essentials.rm);

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
