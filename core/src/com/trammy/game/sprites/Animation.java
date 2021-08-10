package com.trammy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Animation
{
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Texture texture, int frameCount, float cycleTime)
    {
        TextureRegion region = new TextureRegion(texture);
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++)
        {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt)
    {

        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime)
        {
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
        {
            frame = 0;
        }

    }

    public TextureRegion getFrame()
    {
        return frames.get(frame);
    }
}
