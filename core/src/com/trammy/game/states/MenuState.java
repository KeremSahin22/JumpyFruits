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
    private Texture background;
    private Texture playBtn;
    private Stage stage;
    private TextureRegion playBtnTextureRegion;
    private TextureRegionDrawable playBtnTextureDrawable;
    private ImageButton playButton;


    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2, JumpyFruits.HEIGHT / 2);
        background = new Texture("bg_grid.png");
        createButton();
    }

    public void createButton()
    {
        playBtn = new Texture("playbtn.png");
        playBtnTextureRegion = new TextureRegion(playBtn);
        playBtnTextureDrawable = new TextureRegionDrawable(playBtnTextureRegion);
        playButton = new ImageButton(playBtnTextureDrawable);
        playButton.setPosition(cam.position.x- (playBtn.getWidth() / 2.0f), cam.position.y);

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
        stage = new Stage(viewport, gsm.getBatch());
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput()
    {
        /**Vector3 mousePosition = new Vector3( Gdx.input.getX(),Gdx.input.getY(),0);
        mousePosition = cam.unproject(mousePosition);
        boolean isInXOfBtn = cam.position.x - (playBtn.getWidth() / 2.0f) <= mousePosition.x && mousePosition.x <= ( cam.position.x - (playBtn.getWidth() / 2.0f) ) + playBtn.getWidth();
        boolean isInYOfBtn = ( cam.position.y <= mousePosition.y ) && mousePosition.y <= cam.position.y + playBtn.getHeight();
        System.out.println(mousePosition.x + "----" + mousePosition.y);
        if(Gdx.input.isTouched() && isInXOfBtn && isInYOfBtn )
        {
            gsm.set(new PlayState(gsm));
        }*/
    }

    @Override
    public void update(float dt)
    {
        //handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        //sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2.0f), cam.position.y );
        sb.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        stage.dispose();
        System.out.println("Menu State Disposed");
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
        stage.getViewport().update(width,height,true);
    }
}
