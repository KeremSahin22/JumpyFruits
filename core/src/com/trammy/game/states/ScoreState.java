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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.trammy.game.JumpyFruits;

public class ScoreState extends State{

    private Preferences prefs;
    private int highScore, currentScore;
    private Window popUp;
    private Texture popUpBg, menuBtnTexture;
    private ImageButton mainMenuBtn;
    private Label highScoreLabel, currentScoreLabel;
    private Label.LabelStyle labelStyle;
    private String highScoreText, currentScoreText, charName, bgName;
    private Stage stage;


    protected ScoreState(GameStateManager gsm, Preferences prefs, int currentScore, String charName, String bgName)
    {
        super(gsm);
        this.prefs = prefs;
        this.charName = charName;
        this.bgName = bgName;
        this.currentScore = currentScore;
        highScore = prefs.getInteger("high score");
        highScoreText = "High Score: " + highScore;
        currentScoreText = "Current Score: " + currentScore;
        stage = new Stage(viewport, gsm.getBatch());
        createWindow();
    }

    public void createWindow()
    {
        popUpBg= new Texture("popupbg.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(JumpyFruits.font, Color.BLACK, new TextureRegionDrawable(new TextureRegion(popUpBg)));
        popUp = new Window("", windowStyle);
        popUp.setVisible(true);
        popUp.setSize(popUpBg.getWidth(),popUpBg.getHeight());
        popUp.setPosition(cam.position.x- (popUp.getWidth() / 2), cam.position.y);
        popUp.setClip(false);
        popUp.setTransform(true);


        labelStyle = new Label.LabelStyle(JumpyFruits.font, Color.WHITE);
        highScoreLabel = new Label(highScoreText, labelStyle);
        highScoreLabel.setPosition(popUp.getWidth()*0.5f - highScoreLabel.getWidth()/2.75f, popUp.getY() - popUp.getHeight()/2);
        highScoreLabel.setFontScale(0.65f);

        currentScoreLabel = new Label(currentScoreText, labelStyle);
        currentScoreLabel.setPosition(popUp.getWidth()*0.5f - highScoreLabel.getWidth()/2.75f, highScoreLabel.getY() - highScoreLabel.getHeight() - 8);
        currentScoreLabel.setFontScale(0.65f);

        menuBtnTexture = new Texture("mainmenubtn.png");
        Drawable menuButtonDrawable = new TextureRegionDrawable(new TextureRegion(menuBtnTexture));
        mainMenuBtn = new ImageButton(menuButtonDrawable);
        mainMenuBtn.setSize(menuBtnTexture.getWidth(), menuBtnTexture.getHeight());
        mainMenuBtn.setPosition(cam.position.x - mainMenuBtn.getWidth()/2 , popUp.getY() - mainMenuBtn.getHeight() - 20);

        System.out.println("ANANIZISSSS");
        mainMenuBtn.addListener(new ClickListener()
        {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Press a Button");
                gsm.set(new MenuState(gsm,charName,bgName));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }
        });
        popUp.setLayoutEnabled(false);
        popUp.add(highScoreLabel);
        popUp.add(currentScoreLabel);
        stage.addActor(popUp);
        stage.addActor(mainMenuBtn);
        highScoreLabel.setText(highScoreText);
        currentScoreLabel.setText(currentScoreText);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput()
    {
    }

    @Override
    public void update(float dt)
    {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.getBatch().setProjectionMatrix(cam.combined);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        cam.update();
    }
}
