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

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, JumpyFruits.WIDTH / 2, JumpyFruits.HEIGHT / 2);
        background = new Texture("bgnight.png");
        playBtn = new Texture("playbtn.png");

        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Drawable playBtnDrawable = new TextureRegionDrawable(new TextureRegion(playBtn));
        ImageButton button = new ImageButton(playBtnDrawable);
        button.setSize(col_width*4,(float)(row_height*2));
        button.setPosition(col_width,Gdx.graphics.getHeight()-row_height*6);
        button.addListener(new InputListener()
        {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                super.touchUp(event, x, y, pointer, button);
                //gsm.set(new PlayState(gsm))//DOKUNULUNCA YAPMAN GEREKEN ŞEYİ HALLET :)
            }
        });
        stage.addActor(button);


    }

    @Override
    public void handleInput()
    {
        Vector3 mousePosition = new Vector3( Gdx.input.getX(),Gdx.input.getY(),0);
        mousePosition = cam.unproject(mousePosition);
        boolean isInXOfBtn = cam.position.x - (playBtn.getWidth() / 2.0f) <= mousePosition.x && mousePosition.x <= ( cam.position.x - (playBtn.getWidth() / 2.0f) ) + playBtn.getWidth();
        boolean isInYOfBtn = ( cam.position.y <= mousePosition.y ) && mousePosition.y <= cam.position.y + playBtn.getHeight();
        System.out.println(mousePosition.x + "----" + mousePosition.y);
        if(Gdx.input.isTouched() && isInXOfBtn && isInYOfBtn )
        {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        JumpyFruits.font.draw(sb, "Jumpy Fruits", 100, 150);
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2.0f), cam.position.y );
        sb.end();

    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
