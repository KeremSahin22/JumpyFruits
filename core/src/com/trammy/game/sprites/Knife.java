package com.trammy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Knife
{
    public static final int KNIFE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topKnife, bottomKnife;
    private Vector2 posTopKnife, posBotKnife;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Knife(float x)
    {
        topKnife = new Texture("toptube.png");
        bottomKnife = new Texture("bottomtube.png");
        rand = new Random();

        posTopKnife = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotKnife = new Vector2(x, posTopKnife.y - TUBE_GAP - bottomKnife.getHeight());

        boundsTop = new Rectangle( posTopKnife.x, posTopKnife.y, topKnife.getWidth(), topKnife.getHeight());
        boundsBot = new Rectangle( posBotKnife.x, posBotKnife.y, bottomKnife.getWidth(), bottomKnife.getHeight());
    }

    public Texture getTopKnife()
    {
        return topKnife;
    }

    public Texture getBottomKnife()
    {
        return bottomKnife;
    }

    public Vector2 getPosTopKnife()
    {
        return posTopKnife;
    }

    public Vector2 getPosBotKnife()
    {
        return posBotKnife;
    }

    public void reposition(float x)
    {
        posTopKnife.set(x, rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        posBotKnife.set(x, posTopKnife.y - TUBE_GAP - bottomKnife.getHeight());
        boundsTop.setPosition(posTopKnife.x, posTopKnife.y);
        boundsBot.setPosition(posBotKnife.x, posBotKnife.y);
    }

    public boolean collides( Rectangle player )
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose()
    {
        topKnife.dispose();
        bottomKnife.dispose();
    }
}
