package helixprophets;

import helixprophets.beings.classes.Fighter;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.tiled.Layer;

public class Level1 extends Level {

	

	@Override

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Map = new TiledMap("res/level1.tmx","res");
		layer = 1;
		camy=-9*45;
		super.mapsetup();
		super.init(arg0, arg1);
	}
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public void reset() {
		direction = true;
		x = 128;
		y = 100;
		camx = 0;
		camy = -9*45;
	}
	
	}

	

