package com.trammy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trammy.game.JumpyFruits;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JumpyFruits.WIDTH;
		config.height = JumpyFruits.HEIGHT;
		config.title = JumpyFruits.TITLE;
		new LwjglApplication(new JumpyFruits(), config);
	}
}
