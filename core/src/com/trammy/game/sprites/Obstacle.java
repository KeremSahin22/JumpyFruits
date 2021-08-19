package com.trammy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle
{
    public static final int KNIFE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 100;
    private Texture topObstacle, bottomObstacle;
    private Vector2 posTopObstacle, posBotObstacle;
    private Rectangle boundsTop, boundsBot;
    private Random rand;
    private String obsName;

    public Obstacle(float x, String obsName)
    {
        this.obsName = obsName;
        chooseObstacle(obsName);

        rand = new Random();

        posTopObstacle = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotObstacle = new Vector2(x, posTopObstacle.y - TUBE_GAP - bottomObstacle.getHeight());

        boundsTop = new Rectangle( posTopObstacle.x, posTopObstacle.y, topObstacle.getWidth(), topObstacle.getHeight());
        boundsBot = new Rectangle( posBotObstacle.x, posBotObstacle.y, bottomObstacle.getWidth(), bottomObstacle.getHeight());
    }

    public void chooseObstacle(String obsName){
        if(obsName.equals("knife")){
            topObstacle = new Texture("topKnife.png");
            bottomObstacle = new Texture("botKnife.png");
        }
        else{
            topObstacle = new Texture("forkTop.png");
            bottomObstacle = new Texture("forkBot.png");
        }
    }
    public Texture getTopObstacle()
    {
        return topObstacle;
    }

    public Texture getBottomObstacle()
    {
        return bottomObstacle;
    }

    public Vector2 getPosTopObstacle()
    {
        return posTopObstacle;
    }

    public Vector2 getPosBotObstacle()
    {
        return posBotObstacle;
    }

    public void reposition(float x)
    {
        posTopObstacle.set(x, rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        posBotObstacle.set(x, posTopObstacle.y - TUBE_GAP - bottomObstacle.getHeight());
        boundsTop.setPosition(posTopObstacle.x, posTopObstacle.y);
        boundsBot.setPosition(posBotObstacle.x, posBotObstacle.y);
    }

    public boolean collides( Rectangle player )
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose()
    {
        topObstacle.dispose();
        bottomObstacle.dispose();
    }
}
