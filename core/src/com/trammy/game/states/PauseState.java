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
        bg = new Texture("bgff.png");
    }
    @Override
    public void handleInput() {

    }

    public void createButtons(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 3;
        int col_width = Gdx.graphics.getWidth() / 3;

        newGameBtnTexture = new Texture("newgamebtn4.png");
        Drawable newGameButtonDrawable = new TextureRegionDrawable(new TextureRegion(newGameBtnTexture));
        ImageButton newGameBtn = new ImageButton(newGameButtonDrawable);
        newGameBtn.setSize(col_width,(float)(row_height));
        newGameBtn.setPosition(Gdx.graphics.getWidth() /7 *2  ,Gdx.graphics.getHeight() / 2);
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
        stage.addActor(newGameBtn);


        resumeBtnTexture = new Texture("resumebtn.png");
        Drawable resumeButtonDrawable = new TextureRegionDrawable(new TextureRegion(resumeBtnTexture));
        ImageButton resumeBtn = new ImageButton(resumeButtonDrawable);
        resumeBtn.setSize(resumeBtnTexture.getWidth(), resumeBtnTexture.getHeight());
        resumeBtn.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
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
        stage.addActor(resumeBtn);


        menuBtnTexture = new Texture("mainmenubtn.png");
        Drawable menuButtonDrawable = new TextureRegionDrawable(new TextureRegion(menuBtnTexture));
        ImageButton mainMenuBtn = new ImageButton(menuButtonDrawable);
        resumeBtn.setSize(menuBtnTexture.getWidth(), menuBtnTexture.getHeight());
        resumeBtn.setPosition(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        resumeBtn.addListener(new InputListener(){
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
        stage.addActor(mainMenuBtn);


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
}
