package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.trammy.game.JumpyFruits;
import com.trammy.game.sprites.Fork;
import com.trammy.game.sprites.Watermelon;

public class PlayState extends State
{
    private static final int KNIFE_SPACING = 125;
    private static final int KNIFE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private Watermelon watermelon;
    private Texture bg;
    private Array<Fork> forks;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        watermelon = new Watermelon(50,300);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2, JumpyFruits.HEIGHT / 2);
        bg = new Texture("bgff2.png");
        forks = new Array<Fork>();

        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        for(int i = 1; i <= KNIFE_COUNT; i++)
        {
            forks.add(new Fork(i * (KNIFE_SPACING + Fork.KNIFE_WIDTH)));
        }
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
            watermelon.jump();
    }


    @Override
    public void update ( float dt)
    {
        handleInput();
        updateGround();
        watermelon.update(dt);
        cam.position.x = watermelon.getPosition().x + 80;
        for (int i = 0; i < forks.size; i++)
        {
            Fork fork = forks.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > fork.getPosTopKnife().x + fork.getTopKnife().getWidth())
                fork.reposition(fork.getPosTopKnife().x + ((Fork.KNIFE_WIDTH + KNIFE_SPACING) * KNIFE_COUNT));

            if (fork.collides(watermelon.getBounds()))
                gsm.set(new PlayState(gsm));
        }

        if (watermelon.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));
        cam.update();
    }
    
    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(watermelon.getTexture(), watermelon.getPosition().x, watermelon.getPosition().y);
        for ( Fork fork : forks )
        {
            sb.draw(fork.getTopKnife(), fork.getPosTopKnife().x, fork.getPosTopKnife().y);
            sb.draw(fork.getBottomKnife(), fork.getPosBotKnife().x, fork.getPosBotKnife().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose()
    {
        bg.dispose();
        watermelon.dispose();
        ground.dispose();
        for( Fork fork : forks )
            fork.dispose();
        System.out.println("Play State Disposed");
    }

    private void updateGround()
    {
        if(cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2 ,0);
        if(cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2 ,0);
    }
}
