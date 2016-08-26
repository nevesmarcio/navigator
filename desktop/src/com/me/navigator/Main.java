package com.me.navigator;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pt.me.navigator.Navigator;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "navigator";
		cfg.width = 1024;
		cfg.height = 768;
		
		new LwjglApplication(new Navigator(), cfg);
	}
}
