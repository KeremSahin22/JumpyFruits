package com.trammy.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trammy.game.JumpyFruits;

public abstract class State
{
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected Viewport viewport;

    protected State( GameStateManager gsm )
    {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(JumpyFruits.WIDTH/2, JumpyFruits.HEIGHT/2,cam);
        mouse = new Vector3();

    }

    public abstract void handleInput();
    public abstract void update( float dt );
    public abstract void render( SpriteBatch sb );
    public abstract void dispose();
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

}
