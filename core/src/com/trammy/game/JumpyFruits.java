package com.trammy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trammy.game.states.GameStateManager;
import com.trammy.game.states.MenuState;

public class JumpyFruits extends ApplicationAdapter
{

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Jumpy Fruits";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	public static BitmapFont font;
	
	@Override
	public void create()
	{
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("Consolas.fnt"));
		gsm = new GameStateManager(batch);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		gsm.resize(width, height);
	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		music.dispose();
		font.dispose();
	}

}
