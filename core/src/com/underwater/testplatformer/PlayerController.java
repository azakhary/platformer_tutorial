package com.underwater.testplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.SpriteAnimation;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by CyberJoe on 10/18/2014.
 */
public class PlayerController implements IScript {

    // Keep GameStage to access world later
    private Overlap2DStage stage;

    //Player
    private CompositeItem item;

    // Player Animation
    private SpriteAnimation animation;

    private boolean isWalking = false;
    private boolean isGrounded = false;

    private float moveSpeed;
    private float gravity;
    private float jumpSpeed;

    private float verticalSpeed = 0;
    private int jumpCounter = 0;

    private Vector2 initialCoordinates;

    public PlayerController(Overlap2DStage stage) {
        this.stage = stage;
    }

    @Override
    public void init(CompositeItem item) {
        this.item = item;


        moveSpeed = 220f * item.mulX;
        gravity = -1500f * item.mulY;
        jumpSpeed = 700f * item.mulY;


        animation = item.getSpriteAnimationById("animation");

        animation.pause();

        // Setting item origin at the center
        item.setOrigin(item.getWidth()/2, 0);

        //setting initial position for later reset
        initialCoordinates = new Vector2(item.getX(), item.getY());
    }

    @Override
    public void act(float delta) {
        // Check for control events

        boolean wasWalking = isWalking;

        isWalking = false;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Go right
            item.setX(item.getX() + delta*moveSpeed);
            item.setScaleX(1f);
            isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Go left
            item.setX(item.getX() - delta*moveSpeed);
            item.setScaleX(-1f);
            isWalking = true;
        }

        if(wasWalking == true && isWalking == false) {
            // Not walking anymore stop the animation
            animation.pause();
        }
        if(wasWalking == false && isWalking == true) {
            // just started to walk, start animation
            animation.start();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // Jump
            if(isGrounded || jumpCounter < 2) {
                verticalSpeed = jumpSpeed;
                isGrounded = false;
                jumpCounter++;
            }
        }

        // Gravity
        verticalSpeed += gravity*delta;

        // ray-casting for collision detection
        checkForCollisions();

        // set the position
        item.setY(item.getY() + verticalSpeed*delta);

    }

    public void reset() {
        item.setX(initialCoordinates.x);
        item.setY(initialCoordinates.y);
        verticalSpeed = 0;
    }

    /*
    Ray cast down, and if collision is happening stop player and reposition to closest point of collision
     */
    private void checkForCollisions() {

        float rayGap = item.getHeight()/2;

        // Ray size is the exact size of the deltaY change we plan for this frame
        float raySize = -(verticalSpeed+Gdx.graphics.getDeltaTime())*Gdx.graphics.getDeltaTime();

        if(raySize < 5f) raySize = 5f;

        // only check for collisions when moving down
        if(verticalSpeed > 0) return;

        // Vectors of ray from middle middle
        Vector2 rayFrom = new Vector2((item.getX()+item.getWidth()/2)*PhysicsBodyLoader.SCALE, (item.getY()+rayGap)*PhysicsBodyLoader.SCALE);
        Vector2 rayTo = new Vector2((item.getX()+item.getWidth()/2)*PhysicsBodyLoader.SCALE, (item.getY() - raySize)*PhysicsBodyLoader.SCALE);

        // Cast the ray
        stage.getWorld().rayCast(new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                // Stop the player
                verticalSpeed = 0;

                // reposition player slightly upper the collision point
                item.setY(point.y/PhysicsBodyLoader.SCALE+0.1f);

                // make sure it is grounded, to allow jumping again
                isGrounded = true;
                jumpCounter = 0;

                return 0;
            }
        }, rayFrom, rayTo);
    }

    public CompositeItem getActor() {
        return item;
    }

    @Override
    public void dispose() {

    }
}
