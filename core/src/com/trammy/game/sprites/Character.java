package com.trammy.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Character
{
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation charAnimation;
    private Texture texture;
    private Sound sound;

    public Character(int x, int y, String charName)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        chooseTexture(charName);
        bounds = new Rectangle(x, y, texture.getWidth() / 3.0f, texture.getHeight());
        sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void chooseTexture(String charName){
        if(charName.equals("watermelon") || charName.equals("")) {
            texture = new Texture("wmanimation3.png");
            charAnimation = new Animation(texture, 3,0.5f);
        }
        else {
            texture = new Texture("kiwianimation.png");
            charAnimation = new Animation(texture, 2, 0.5f);
        }
    }
    public void update(float dt)
    {
       charAnimation.update(dt);
        if( position.y > 0 )
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }


    public Vector3 getPosition()
    {
        return position;
    }

    public TextureRegion getTexture()
    {
        return charAnimation.getFrame();
    }

    public void jump()
    {
        velocity.y = 250;
        sound.play(0.5f);
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose()
    {
        texture.dispose();
        sound.dispose();
    }
}
