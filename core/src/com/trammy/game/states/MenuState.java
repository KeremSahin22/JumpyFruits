package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
    private Texture playBtnImg, charactersImg, mapImg, popUpBg, wmButtonImg, bananaButtonImg, kiwiButtonImg, charCloseImg, nightBgImg, gridBgImg, sunnyBgImg;
    private Stage stage;
    private ImageButton playButton, charactersButton, mapButton, wmButton, charCloseButton, kiwiButton, bananaButton, nightBgButton, gridBgButton, mapCloseButton, sunnyBgButton;
    private Image opaqueBg;
    private Window popUpChar, popUpMap;
    private Image backgroundImg;
    private String charName,bgName;


    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        charName = "watermelon";
        bgName = "bgnight.png";
        backgroundImg = new Image(new Texture(bgName));
        backgroundImg.setPosition(0, 0);
        opaqueBg = new Image(new Texture("opaqueBg.png"));
        opaqueBg.setVisible(false);
        createWindows();
        createButtons();
    }

    public MenuState(GameStateManager gsm, String charName, String bgName)
    {
        super(gsm);
        this.charName = charName;
        this.bgName = bgName;
        backgroundImg = new Image(new Texture(bgName));
        backgroundImg.setPosition(0,0);
        opaqueBg = new Image(new Texture("opaqueBg.png"));
        opaqueBg.setVisible(false);
        createWindows();
        createButtons();
    }

    public void createWindows(){

        // creating character window
        popUpBg= new Texture("popupbg.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(JumpyFruits.font, Color.BLACK, new TextureRegionDrawable(new TextureRegion(popUpBg)));
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
                wmButton.addAction(Actions.moveTo(wmButton.getX() + 1.5f, wmButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                wmButton.setPosition(popUpChar.getWidth()*0.10f, popUpChar.getY() - popUpChar.getHeight()/2);
                popUpChar.setVisible(false);
                charName = "watermelon";
                opaqueBg.setVisible(false);
                //gsm.set(new PlayState(gsm,"watermelon",bgName));
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
                bananaButton.addAction(Actions.moveTo(bananaButton.getX() + 1.5f, bananaButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                bananaButton.setPosition(wmButton.getX() + wmButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2);
                popUpChar.setVisible(false);
                charName = "banana";
                opaqueBg.setVisible(false);
                //gsm.set(new PlayState(gsm,"banana",bgName));
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
                kiwiButton.addAction(Actions.moveTo(kiwiButton.getX() + 1.5f, kiwiButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                kiwiButton.setPosition(bananaButton.getX() + bananaButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2);
                popUpChar.setVisible(false);
                charName = "kiwi";
                opaqueBg.setVisible(false);
                //gsm.set(new PlayState(gsm,charName,bgName));
            }
        });

        //close button for the character pop up screen
        charCloseImg = new Texture("closebutton.png");
        charCloseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(charCloseImg)));
        charCloseButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                charCloseButton.addAction(Actions.moveTo(charCloseButton.getX() + 1.5f, charCloseButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                charCloseButton.setPosition(popUpChar.getWidth() - charCloseButton.getWidth()*1.25f, popUpChar.getY()- charCloseButton.getHeight()*2.75f);
                popUpChar.getColor().a=1;
                popUpChar.clearActions();
                popUpChar.addAction(Actions.sequence(Actions.fadeOut(0.3f), Actions.run(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                popUpChar.setVisible(false);
                            }
                        })));
                opaqueBg.setVisible(false);
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
                nightBgButton.addAction(Actions.moveTo(nightBgButton.getX() + 1.5f, nightBgButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                nightBgButton.setPosition(popUpMap.getWidth()*0.10f, popUpMap.getY() - popUpMap.getHeight()/2);
                popUpMap.setVisible(false);
                bgName = "bgnight.png";
                backgroundImg.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(bgName))));
                opaqueBg.setVisible(false);
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
                gridBgButton.addAction(Actions.moveTo(gridBgButton.getX() + 1.5f, gridBgButton.getY() - 1.5f));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                gridBgButton.setPosition(nightBgButton.getX() + nightBgButton.getWidth() + 10, popUpMap.getY() - popUpMap.getHeight()/2);
                popUpMap.setVisible(false);
                bgName = "bg_grid.png";
                backgroundImg.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(bgName))));
                opaqueBg.setVisible(false);
            }
        });

        //button for sunny bg
        sunnyBgImg = new Texture("sunnybgBtn.png");
        sunnyBgButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(sunnyBgImg)));
        sunnyBgButton.addListener( new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                sunnyBgButton.addAction(Actions.moveTo(sunnyBgButton.getX() + 1.5f, sunnyBgButton.getY() - 1.5f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                sunnyBgButton.setPosition(gridBgButton.getX() + nightBgButton.getWidth() + 10, popUpMap.getY() - popUpMap.getHeight()/2);
                popUpMap.setVisible(false);
                bgName = "sunnybg.png";
                backgroundImg.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(bgName))));
                opaqueBg.setVisible(false);
            }
        });

        //adding close button for the map
        mapCloseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(charCloseImg)));
        mapCloseButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                mapCloseButton.addAction(Actions.moveTo(mapCloseButton.getX() + 1.5f, mapCloseButton.getY() - 1.5f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                mapCloseButton.setPosition(popUpMap.getWidth() - mapCloseButton.getWidth()*1.25f, popUpMap.getY()- mapCloseButton.getHeight()*2.75f);
                popUpMap.getColor().a=1;
                popUpMap.clearActions();
                popUpMap.addAction(Actions.sequence(Actions.fadeOut(0.3f), Actions.run(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        popUpMap.setVisible(false);

                    }
                })));
                opaqueBg.setVisible(false);
            }
        });

        //adding the items to the character pop up window
        popUpChar.setLayoutEnabled(false);
        popUpChar.add(wmButton);
        popUpChar.add(bananaButton);
        popUpChar.add(kiwiButton);
        popUpChar.add(charCloseButton);
        wmButton.setPosition(popUpChar.getWidth()*0.10f, popUpChar.getY() - popUpChar.getHeight()/2 );
        bananaButton.setPosition(wmButton.getX() + wmButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2 );
        kiwiButton.setPosition(bananaButton.getX() + bananaButton.getWidth() + 10, popUpChar.getY() - popUpChar.getHeight()/2);
        charCloseButton.setPosition(popUpChar.getWidth() - charCloseButton.getWidth()*1.25f, popUpChar.getY()- charCloseButton.getHeight()*2.75f);

        //adding the items to the map pop up window
        popUpMap.setLayoutEnabled(false);
        popUpMap.add(nightBgButton);
        popUpMap.add(gridBgButton);
        popUpMap.add(sunnyBgButton);
        popUpMap.add(mapCloseButton);
        nightBgButton.setPosition(popUpMap.getWidth()*0.10f, popUpMap.getY() - popUpMap.getHeight()/2);
        gridBgButton.setPosition(nightBgButton.getX() + nightBgButton.getWidth() + 10, popUpMap.getY() - popUpMap.getHeight()/2);
        sunnyBgButton.setPosition(gridBgButton.getX() + nightBgButton.getWidth() + 10, popUpMap.getY() - popUpMap.getHeight()/2);
        mapCloseButton.setPosition(popUpMap.getWidth() - mapCloseButton.getWidth()*1.25f, popUpMap.getY()- mapCloseButton.getHeight()*2.75f);


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
                playButton.addAction(Actions.moveTo(playButton.getX() + 1.5f, playButton.getY() - 1.5f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                playButton.setPosition(cam.position.x- (playBtnImg.getWidth() / 2.0f), cam.position.y);
                gsm.set(new PlayState(gsm, charName, bgName));
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
                charactersButton.addAction(Actions.moveTo(charactersButton.getX() + 1.5f, charactersButton.getY() - 1.5f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                charactersButton.setPosition(cam.position.x - charactersImg.getWidth()/2.0f, cam.position.y*0.70f);
                popUpChar.getColor().a=0;
                if(!popUpMap.isVisible())
                    popUpChar.setVisible(true);
                popUpChar.addAction(Actions.fadeIn(0.3f));
                opaqueBg.setVisible(true);


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
                mapButton.addAction(Actions.moveTo(mapButton.getX() + 1.5f, mapButton.getY() - 1.5f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                mapButton.setPosition(cam.position.x - mapImg.getWidth()/2.0f, cam.position.y*0.50f);
                popUpMap.getColor().a=0;
                if(!popUpChar.isVisible())
                    popUpMap.setVisible(true);
                popUpMap.addAction(Actions.fadeIn(0.3f));
                opaqueBg.setVisible(true);


            }

        });

        stage = new Stage(viewport, gsm.getBatch());
        stage.addActor(backgroundImg);
        stage.addActor(playButton);
        stage.addActor(charactersButton);
        stage.addActor(mapButton);
        stage.addActor(opaqueBg);
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
        //buttonsTouchUp(scaleCharButton);
    }


    @Override
    public void render(SpriteBatch sb)
    {

        cam.update();
        stage.getBatch().setProjectionMatrix(cam.combined);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose()
    {
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
