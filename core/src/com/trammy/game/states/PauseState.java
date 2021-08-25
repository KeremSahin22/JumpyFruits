package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.trammy.game.JumpyFruits;

public class PauseState extends State{

    private GameStateManager gsm;
    private Stage stage;
    private Texture newGameBtnTexture, resumeBtnTexture;
    private Texture popUpBg;
    private Window popUp;
    private Texture menuBtnTexture;
    private Image bg, ground;
    private String charName, bgName;

    public PauseState(GameStateManager gsm){
        super(gsm);
        this.gsm = gsm;
        bg = new Image(new Texture("bg_grid.png"));
        ground = new Image(new Texture("ground3.png"));
        createButtons();
    }


    public PauseState(GameStateManager gsm, String charName, String bgName){
        super(gsm);
        this.gsm = gsm;
        this.bgName = bgName;
        this.charName = charName;

        if(bgName.equals("")) {
            bg = new Image(new Texture("bg_grid.png"));
            ground = new Image(new Texture("ground3.png"));

        }
        else {
            bg = new Image(new Texture(bgName));
            ground = new Image(new Texture("groundjf.png"));

        }
        ground.setPosition(0, -50);
        createButtons();

    }



    @Override
    public void handleInput() {

    }

    public void createButtons(){

        popUpBg= new Texture("popupbg.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(JumpyFruits.font, Color.BLACK, new TextureRegionDrawable(new TextureRegion(popUpBg)));
        popUp = new Window("", windowStyle);
        popUp.setVisible(true);
        popUp.setSize(popUpBg.getWidth(),popUpBg.getHeight());
        popUp.setPosition(cam.position.x- (popUp.getWidth() / 2.0f), cam.position.y);
        popUp.setClip(false);
        popUp.setTransform(true);

        newGameBtnTexture = new Texture("newgamebtn4.png");
        Drawable newGameButtonDrawable = new TextureRegionDrawable(new TextureRegion(newGameBtnTexture));
        ImageButton newGameBtn = new ImageButton(newGameButtonDrawable);
        newGameBtn.setSize(newGameBtnTexture.getWidth(),newGameBtnTexture.getHeight());
        newGameBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PlayState(gsm,charName,bgName));
                return true;
            }
        });



        resumeBtnTexture = new Texture("resumebtn.png");
        Drawable resumeButtonDrawable = new TextureRegionDrawable(new TextureRegion(resumeBtnTexture));
        ImageButton resumeBtn = new ImageButton(resumeButtonDrawable);
        resumeBtn.setSize(resumeBtnTexture.getWidth(), resumeBtnTexture.getHeight());
        resumeBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.pop();
                return true;
            }
        });

        menuBtnTexture = new Texture("mainmenubtn.png");
        Drawable menuButtonDrawable = new TextureRegionDrawable(new TextureRegion(menuBtnTexture));
        ImageButton mainMenuBtn = new ImageButton(menuButtonDrawable);
        mainMenuBtn.setSize(menuBtnTexture.getWidth(), menuBtnTexture.getHeight());
        mainMenuBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm,charName,bgName));
                return true;
            }
        });

        stage = new Stage(viewport, gsm.getBatch());

        popUp.setLayoutEnabled(false);
        popUp.add(newGameBtn);
        popUp.add(resumeBtn);
        popUp.add(mainMenuBtn);

        newGameBtn.setPosition(popUp.getWidth()*0.27f, popUp.getY() - popUp.getHeight()/3);
        resumeBtn.setPosition(popUp.getWidth()*0.27f, newGameBtn.getY() - newGameBtn.getHeight() - 12);
        mainMenuBtn.setPosition(popUp.getWidth()*0.27f, resumeBtn.getY() -  resumeBtn.getHeight() - 12);

        stage.addActor(bg);
        stage.addActor(ground);
        stage.addActor(popUp);
        Gdx.input.setInputProcessor(stage);


    }
    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        cam.update();
    }
}
