package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.trammy.game.JumpyFruits;

public class PauseState extends State{

    private GameStateManager gsm;
    private Stage stage;
    private Texture newGameBtnTexture, resumeBtnTexture;
    private Texture bg;
    private Texture menuBtnTexture;

    public PauseState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2, JumpyFruits.HEIGHT / 2);

        this.gsm = gsm;
        bg = new Texture("bg_grid.png");
    }

    public PauseState(GameStateManager gsm, String bgName){
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2, JumpyFruits.HEIGHT / 2);
        this.gsm = gsm;
        if(bgName.equals(""))
            bg = new Texture("bg_grid.png");
        else
            bg = new Texture(bgName);
    }

    @Override
    public void handleInput() {

    }

    public void createButtons(){


        newGameBtnTexture = new Texture("newgamebtn4.png");
        Drawable newGameButtonDrawable = new TextureRegionDrawable(new TextureRegion(newGameBtnTexture));
        ImageButton newGameBtn = new ImageButton(newGameButtonDrawable);
        newGameBtn.setSize(newGameBtnTexture.getWidth(),newGameBtnTexture.getHeight());
        newGameBtn.setPosition(cam.position.x - newGameBtnTexture.getWidth()/2  ,cam.position.y);
        newGameBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PlayState(gsm));
                return true;
            }
        });



        resumeBtnTexture = new Texture("resumebtn.png");
        Drawable resumeButtonDrawable = new TextureRegionDrawable(new TextureRegion(resumeBtnTexture));
        ImageButton resumeBtn = new ImageButton(resumeButtonDrawable);
        resumeBtn.setSize(resumeBtnTexture.getWidth(), resumeBtnTexture.getHeight());
        resumeBtn.setPosition(cam.position.x - resumeBtnTexture.getWidth()/2  ,cam.position.y + newGameBtnTexture.getHeight() + 10);
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
        mainMenuBtn.setPosition(cam.position.x - resumeBtnTexture.getWidth()/2  ,cam.position.y - newGameBtnTexture.getHeight() - 10);
        mainMenuBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm));
                return true;
            }
        });

        stage = new Stage(viewport, gsm.getBatch());
        stage.addActor(newGameBtn);
        stage.addActor(mainMenuBtn);
        stage.addActor(resumeBtn);
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
        //sb.draw(bg, 0, 0);
        sb.end();
        createButtons();
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
    }
}
