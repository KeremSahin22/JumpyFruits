package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.trammy.game.JumpyFruits;

public class MenuState extends State
{
    private Texture backgroundImg, playBtnImg, charactersImg, mapImg;
    private Stage stage;
    private ImageButton playButton, charactersButton, mapButton;


    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f);
        backgroundImg = new Texture("bg_grid.png");
        createButtons();
    }

    public void createButtons()
    {
        playBtnImg = new Texture("playbtn.png");
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playBtnImg)));
        playButton.setPosition(cam.position.x- (playBtnImg.getWidth() / 2.0f), cam.position.y);
        playButton.addListener( new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                gsm.set(new PlayState(gsm));

            }
        });

        charactersImg = new Texture("characterbtn.png");
        charactersButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(charactersImg)));
        charactersButton.setPosition(cam.position.x - charactersImg.getWidth()/2.0f, cam.position.y*0.70f);
        charactersButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        mapImg = new Texture("mapbtn.png");
        mapButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(mapImg)));
        mapButton.setPosition(cam.position.x - mapImg.getWidth()/2.0f, cam.position.y*0.50f );
        mapButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage = new Stage(viewport, gsm.getBatch());
        stage.addActor(playButton);
        stage.addActor(charactersButton);
        stage.addActor(mapButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void update(float dt)
    {

    }

    @Override
    public void render(SpriteBatch sb)
    {
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        stage.act(Gdx.graphics.getDeltaTime());
        sb.begin();
        sb.draw(backgroundImg, 0, 0);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose()
    {
        backgroundImg.dispose();
        stage.dispose();
        System.out.println("Menu State Disposed");
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        cam.update();
    }
}
