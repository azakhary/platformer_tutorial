package com.underwater.testplatformer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.IBaseItem;


public class GameStage extends Overlap2DStage {

    private PlayerController player;

    public GameStage() {

        // This will create SceneLoader instance and configure all things like default resource manager, physics e.t.c
        initSceneLoader();

        // This will load MainScene data from json and make Actors from it
        sceneLoader.loadScene("MainScene");

        // Get the root actor and add it to stage
        addActor(sceneLoader.getRoot());

        player = new PlayerController(this);
        sceneLoader.getRoot().getCompositeById("player").addScript(player);

        for(IBaseItem item: sceneLoader.getRoot().getItems()) {
            if(item.getCustomVariables().getFloatVariable("platformSpeed") != null && item.isComposite()) {
                ((CompositeItem)item).addScript(new MovingPlatform());
            }
        }

    }

    public void act(float delta) {
        super.act(delta);

        ((OrthographicCamera)getCamera()).position.x = player.getActor().getX();
    }

    public void restart() {
        player.reset();
    }
}
