package com.trammy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.trammy.game.JumpyFruits;
import com.trammy.game.sprites.Obstacle;
import com.trammy.game.sprites.Character;

public class PlayState extends State
{
    Preferences prefs = Gdx.app.getPreferences("game preferences");
    private static final int KNIFE_SPACING = 125;
    private static final int KNIFE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private int score, highScore;
    private String scoreText, charName;
    private String bgName, obsName;
    private Label scoreLabel;
    private Label.LabelStyle labelStyle;
    private Texture bg, ground, pauseBtnTexture;
    //private Image bgImg, groundImg, pauseBtnImg;
    private Character character;
    private Array<Obstacle> obstacles;
    private double oldForkPos = 0;
    private Vector2 groundPos1, groundPos2;
    private Stage hudStage;
    private ImageButton pauseBtn;
    private OrthographicCamera hudCam;
    private ExtendViewport hudViewport;


   public PlayState(GameStateManager gsm)
    {
        super(gsm);
        bgName = "";
        charName = "";
        obsName = "";
        chooseBg(bgName);
        character = new Character(50,300, charName);
        score = 0;

        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f);
        hudViewport = new ExtendViewport(JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f,hudCam);
        cam.setToOrtho(false, JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f);

        obstacles = new Array<Obstacle>();

        ground = new Texture("groundjf.png");
        System.out.println(ground.getHeight());
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        for(int i = 1; i <= KNIFE_COUNT; i++)
        {
            obstacles.add(new Obstacle(i * (KNIFE_SPACING + Obstacle.KNIFE_WIDTH), obsName));
        }

        createButtons();
    }

    public PlayState(GameStateManager gsm, String charName, String bgName){
        super(gsm);
        this.bgName = bgName;
        this.charName = charName;
        chooseBg(bgName);
        score = 0;
        highScore = 0;
        character = new Character(50,300,this.charName);

        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f);
        hudViewport = new ExtendViewport(JumpyFruits.WIDTH/2.0f, JumpyFruits.HEIGHT/2.0f,hudCam);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2.0f, JumpyFruits.HEIGHT / 2.0f);

        obstacles = new Array<Obstacle>();
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        for(int i = 1; i <= KNIFE_COUNT; i++)
        {
            obstacles.add(new Obstacle(i * (KNIFE_SPACING + Obstacle.KNIFE_WIDTH), obsName));
        }
        createButtons();
    }

    public void chooseBg(String bgName){
        if(bgName.equals("") || bgName.equals("bg_grid.png")) {
            bg = new Texture("bg_grid.png");
            obsName = "knife";
            ground = new Texture("ground3.png");
        }
        else {
            bg = new Texture(bgName);
            obsName = "fork";
            ground = new Texture("groundjf.png");
        }
    }
    public void createButtons()
    {
        scoreText = "" + 0;
        pauseBtnTexture = new Texture("pausebtn.png");
        Drawable pauseButtonDrawable = new TextureRegionDrawable(new TextureRegion(pauseBtnTexture));
        pauseBtn = new ImageButton(pauseButtonDrawable);
        pauseBtn.setPosition(cam.viewportWidth - pauseBtnTexture.getWidth()*1.2f,cam.viewportHeight);
        pauseBtn.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new PauseState(gsm, charName, bgName));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        //JumpyFruits.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        labelStyle = new Label.LabelStyle(JumpyFruits.font, Color.WHITE);
        scoreLabel = new Label(scoreText, labelStyle);
        scoreLabel.setPosition(hudCam.position.x - scoreLabel.getWidth()/2,cam.viewportHeight*0.85f);
        scoreLabel.setFontScale(0.8f);
        hudStage = new Stage(hudViewport,gsm.getBatch());
        hudStage.addActor(pauseBtn);
        hudStage.addActor(scoreLabel);
    }
    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched() && !pauseBtn.isPressed())
            character.jump();
    }

    @Override
    public void update ( float dt)
    {
        handleInput();
        updateGround();
        character.update(dt);
        cam.position.x = character.getPosition().x + 80;
        for (int i = 0; i < obstacles.size; i++)
        {
            Obstacle obstacle = obstacles.get(i);

            if( cam.position.x - (cam.viewportWidth / 2) > obstacle.getPosTopObstacle().x + obstacle.getTopObstacle().getWidth() )
                obstacle.reposition(obstacle.getPosTopObstacle().x + ((Obstacle.KNIFE_WIDTH + KNIFE_SPACING) * KNIFE_COUNT));


            if(character.getPosition().x >= (obstacle.getPosTopObstacle().x + obstacle.getTopObstacle().getWidth()) && oldForkPos != obstacle.getPosTopObstacle().x + obstacle.getTopObstacle().getWidth()  ) {
                addScore(1);
                scoreLabel.setText(scoreText);
                oldForkPos = obstacle.getPosTopObstacle().x + obstacle.getTopObstacle().getWidth();

            }

            if(obstacle.collides(character.getBounds())) {
                saveHighScore(score);
                gsm.set(new ScoreState(gsm, prefs, score, charName, bgName));
            }
        }
        if (character.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            saveHighScore(score);
            gsm.set(new ScoreState(gsm, prefs, score, charName, bgName));
        }
        cam.update();
        Gdx.input.setInputProcessor(hudStage);
    }

    public void saveHighScore(int score){
       highScore = prefs.getInteger("high score");
       if(score > highScore)
           highScore = score;
       prefs.putInteger("high score", highScore);
       prefs.flush();
    }
    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(character.getTexture(), character.getPosition().x, character.getPosition().y);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        for ( Obstacle obstacle : obstacles)
        {
            sb.draw(obstacle.getTopObstacle(), obstacle.getPosTopObstacle().x, obstacle.getPosTopObstacle().y);
            sb.draw(obstacle.getBottomObstacle(), obstacle.getPosBotObstacle().x, obstacle.getPosBotObstacle().y);
        }
        sb.end();
        hudStage.act(Gdx.graphics.getDeltaTime());
        hudStage.draw();

    }

    @Override
    public void dispose()
    {
        bg.dispose();
        character.dispose();
        ground.dispose();
        for( Obstacle obstacle : obstacles)
            obstacle.dispose();
        System.out.println("Play State Disposed");
        hudStage.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }

    private void updateGround()
    {
        if(cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2 ,0);
        if(cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2 ,0);
    }

    public void addScore(int value){
        score += value;
        scoreText = "" + score;
    }

}

