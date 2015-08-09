package com.uwsoft.platformer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by azakhary on 8/4/2015.
 */
public class Player implements IScript {

    private Entity player;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;

    private Vector2 speed;
    private float gravity = -500f;

    private final float jumpSpeed = 200f;

    @Override
    public void init(Entity entity) {
        player = entity;

        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);

        speed = new Vector2(100, 0);
    }

    @Override
    public void act(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            transformComponent.x-=speed.x*delta;
            transformComponent.scaleX = -1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            transformComponent.x+=speed.x*delta;
            transformComponent.scaleX = 1f;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            speed.y = jumpSpeed;
        }

        speed.y+=gravity*delta;

        transformComponent.y += speed.y*delta;
    }

    public float getX() {
        return transformComponent.x;
    }

    public float getY() {
        return transformComponent.y;
    }

    @Override
    public void dispose() {

    }

    public float getWidth() {
        return dimensionsComponent.width;
    }
}
