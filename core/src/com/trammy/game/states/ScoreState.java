package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.trammy.game.JumpyFruits;

public class ScoreState extends State{

    private Preferences prefs;
    private int highScore, currentScore;
    private Window popUp;
    private Texture popUpBg, menuBtnTexture, newGameBtnTexture;
    private Image ground, bg;
    private Label highScoreLabel, currentScoreLabel;
    private Label.LabelStyle labelStyle;
    private String highScoreText, currentScoreText, charName, bgName;
    private Stage stage;


    protected ScoreState(GameStateManager gsm, Preferences prefs, int currentScore, String charName, String bgName) {
        super(gsm);

        this.prefs = prefs;
        this.charName = charName;
        this.bgName = bgName;
        this.currentScore = currentScore;
        highScore = prefs.getInteger("high score");

        highScoreText = "High Score: " + highScore;
        currentScoreText = "Current Score: " + currentScore;

        if(bgName.equals("")) {
            bg = new Image(new Texture("bg_grid.png"));
            ground = new Image(new Texture("ground3.png"));

        }
        else {
            bg = new Image(new Texture(bgName));
            ground = new Image(new Texture("groundjf.png"));

        }
        ground.setPosition(0, -50);

        createWindow();

        highScoreLabel.setText(highScoreText);
        currentScoreLabel.setText(currentScoreText);


    }

    public void createWindow(){

        stage = new Stage(viewport, gsm.getBatch());

        popUpBg= new Texture("popupbg.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(JumpyFruits.font, Color.BLACK, new TextureRegionDrawable(new TextureRegion(popUpBg)));
        popUp = new Window("", windowStyle);
        popUp.setVisible(true);
        popUp.setSize(popUpBg.getWidth(),popUpBg.getHeight());
        popUp.setPosition(cam.position.x- (popUp.getWidth() / 2), cam.position.y);
        popUp.setClip(false);
        popUp.setTransform(true);


        menuBtnTexture = new Texture("mainmenubtn.png");
        Drawable menuButtonDrawable = new TextureRegionDrawable(new TextureRegion(menuBtnTexture));
        ImageButton mainMenuBtn = new ImageButton(menuButtonDrawable);
        mainMenuBtn.setSize(menuBtnTexture.getWidth(), menuBtnTexture.getHeight());
        mainMenuBtn.setPosition(cam.position.x - mainMenuBtn.getWidth()/2 , popUp.getY() - mainMenuBtn.getHeight() - 20);
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

        newGameBtnTexture = new Texture("newgamebtn4.png");
        Drawable newGameButtonDrawable = new TextureRegionDrawable(new TextureRegion(newGameBtnTexture));
        ImageButton newGameBtn = new ImageButton(newGameButtonDrawable);
        newGameBtn.setSize(newGameBtnTexture.getWidth(),newGameBtnTexture.getHeight());
        newGameBtn.setPosition(cam.position.x - mainMenuBtn.getWidth()/2 , mainMenuBtn.getY() - newGameBtn.getHeight() - 20);
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

        labelStyle = new Label.LabelStyle(JumpyFruits.font, Color.WHITE);
        highScoreLabel = new Label(highScoreText, labelStyle);
        highScoreLabel.setPosition(popUp.getWidth()*0.5f - highScoreLabel.getWidth()/2.75f, popUp.getY() - popUp.getHeight()/2);
        highScoreLabel.setFontScale(0.65f);

        currentScoreLabel = new Label(currentScoreText, labelStyle);
        currentScoreLabel.setPosition(popUp.getWidth()*0.5f - highScoreLabel.getWidth()/2.75f, highScoreLabel.getY() - highScoreLabel.getHeight() - 8);
        currentScoreLabel.setFontScale(0.65f);



        popUp.setLayoutEnabled(false);
        popUp.add(highScoreLabel);
        popUp.add(currentScoreLabel);

        stage.addActor(bg);
        stage.addActor(ground);
        stage.addActor(mainMenuBtn);
        stage.addActor(newGameBtn);
        stage.addActor(popUp);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        cam.update();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    @Override
    public void resize(int width, int height) {

    }
}
