package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.trammy.game.JumpyFruits;

public class MenuState extends State
{
    private Texture backgroundImg, playBtnImg, charactersImg, mapImg, popUpBg, wmButtonImg, bananaButtonImg, kiwiButtonImg, charCloseImg, nightBgImg, gridBgImg;
    private Stage stage, wStage;
    private ImageButton playButton, charactersButton, mapButton, wmButton, charCloseButton, kiwiButton, bananaButton, nightBgButton, gridBgButton;
    private Window popUpChar, popUpMap;


    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2.0f, JumpyFruits.HEIGHT / 2.0f);
        popUpBg= new Texture("popupbg.png");
        backgroundImg = new Texture("bg_grid.png");
        createWindows();
        createButtons();
        //opUp.set
    }

    public void createWindows(){

        Window.WindowStyle windowStyle = new Window.WindowStyle(JumpyFruits.font, Color.BLACK, new TextureRegionDrawable(new TextureRegion(popUpBg)));

        // creating character window
        popUpChar = new Window("", windowStyle);
        popUpChar.setVisible(false);
        popUpChar.setSize(popUpBg.getWidth(),popUpBg.getHeight());
        popUpChar.setPosition(cam.position.x- (popUpChar.getWidth() / 2.0f), cam.position.y);
        popUpChar.setClip(false);
        popUpChar.setTransform(true);

        //button for watermelon
        wmButtonImg = new Texture("wmsingle.png");
        wmButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(wmButtonImg)));
        wmButton.addListener( new InputListener()
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
                popUpChar.setVisible(false);
                gsm.set(new PlayState(gsm,"kiwi",""));
            }
        });

        //button for banana
        bananaButtonImg = new Texture("bananasingle.png");
        bananaButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(bananaButtonImg)));
        bananaButton.addListener( new InputListener()
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
                popUpChar.setVisible(false);
                gsm.set(new PlayState(gsm,"banana",""));
            }
        });

        //button for kiwi
        kiwiButtonImg = new Texture("kiwisingle.png");
        kiwiButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(kiwiButtonImg)));
        kiwiButton.addListener( new InputListener()
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
                popUpChar.setVisible(false);
                gsm.set(new PlayState(gsm,"kiwi",""));
            }
        });



        charCloseImg = new Texture("closebutton.png");
        charCloseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(charCloseImg)));
        charCloseButton.setPosition(0,0);
        charCloseButton.addListener(new InputListener()
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
                popUpChar.setVisible(false);

            }
        });
        // creating map window
        popUpMap = new Window("", windowStyle);
        popUpMap.setVisible(false);
        popUpMap.setSize(popUpBg.getWidth(),popUpBg.getHeight());
        popUpMap.setPosition(cam.position.x- (popUpMap.getWidth() / 2.0f), cam.position.y);
        popUpMap.setClip(false);
        popUpMap.setTransform(true);

        //button for night bg
        nightBgImg = new Texture("nightbgBtn.png");
        nightBgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(nightBgImg)));
        nightBgButton.addListener( new InputListener()
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
                popUpMap.setVisible(false);
                gsm.set(new PlayState(gsm,"","bgnight.png"));
            }
        });

        //button for grid bg
        gridBgImg = new Texture("gridbgBtn.png");
        gridBgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(gridBgImg)));
        gridBgButton.addListener( new InputListener()
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
                popUpMap.setVisible(false);
                gsm.set(new PlayState(gsm,"","bg_grid.png"));
            }
        });
        popUpChar.setLayoutEnabled(false);
        popUpChar.add(wmButton);
        popUpChar.add(bananaButton);
        popUpChar.add(kiwiButton);
        popUpChar.add(charCloseButton);
        wmButton.setPosition(popUpChar.getWidth()*0.10f, popUpChar.getY() - popUpChar.getHeight()/2 );
        bananaButton.setPosition(wmButton.getX() + wmButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2 );
        kiwiButton.setPosition(bananaButton.getX() + bananaButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2);

        popUpMap.setLayoutEnabled(false);
        popUpMap.add(nightBgButton);
        popUpMap.add(gridBgButton);
        nightBgButton.setPosition(popUpMap.getWidth()*0.10f, popUpMap.getY() - popUpMap.getHeight()/2);
        gridBgButton.setPosition(nightBgButton.getX() + nightBgButton.getWidth() + 10, popUpMap.getY() - popUpMap.getHeight()/2);
        charCloseButton.setPosition(popUpChar.getWidth() - charCloseButton.getWidth()*1.25f, popUpChar.getY()- charCloseButton.getHeight()*1.25f);

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
                gsm.set(new PlayState(gsm, "", ""));

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
                popUpChar.setVisible(true);
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
                popUpMap.setVisible(true);
            }
        });

        stage = new Stage(viewport, gsm.getBatch());
        stage.addActor(playButton);
        stage.addActor(charactersButton);
        stage.addActor(mapButton);
        stage.addActor(popUpChar);
        stage.addActor(popUpMap);
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
