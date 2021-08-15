package com.trammy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Fork
{
    public static final int KNIFE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 100;
    private Texture topFork, bottomFork;
    private Vector2 posTopFork, posBotFork;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Fork(float x)
    {
        topFork = new Texture("forkTop.png");
        bottomFork = new Texture("forkBot.png");
        rand = new Random();

        posTopFork = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotFork = new Vector2(x, posTopFork.y - TUBE_GAP - bottomFork.getHeight());

        boundsTop = new Rectangle( posTopFork.x, posTopFork.y, topFork.getWidth(), topFork.getHeight());
        boundsBot = new Rectangle( posBotFork.x, posBotFork.y, bottomFork.getWidth(), bottomFork.getHeight());
    }

    public Texture getTopFork()
    {
        return topFork;
    }

    public Texture getBottomFork()
    {
        return bottomFork;
    }

    public Vector2 getPosTopFork()
    {
        return posTopFork;
    }

    public Vector2 getPosBotFork()
    {
        return posBotFork;
    }

    public void reposition(float x)
    {
        posTopFork.set(x, rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        posBotFork.set(x, posTopFork.y - TUBE_GAP - bottomFork.getHeight());
        boundsTop.setPosition(posTopFork.x, posTopFork.y);
        boundsBot.setPosition(posBotFork.x, posBotFork.y);
    }

    public boolean collides( Rectangle player )
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose()
    {
        topFork.dispose();
        bottomFork.dispose();
    }
}
