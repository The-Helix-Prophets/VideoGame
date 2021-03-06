package helixprophets;

import static org.lwjgl.opengl.GL11.*;
import helixprophets.beings.classes.Mage;
import helixprophets.beings.classes.Rogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.loading.DeferredResource;
 


//this is a state based game with multiple states
//0 is the splash screen
//1 is level 1
public class Main extends StateBasedGame {
	
	public Main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	public static void main(String[] args) {
		String title = "I'm not Saying You're Obligated to Explore this Castle, but...";
		//this gamecontainer thing is what Slick2D uses. OpenGL and lwjgl are still usable within the container
		//but our methods must pass through these parameters in order to work right.
	      try {
	         AppGameContainer container = new AppGameContainer(new Main(title));
	         container.setDisplayMode(800,600,false);
	         container.setTargetFrameRate(60);
	         container.start();
	      } catch (SlickException e) {
	         e.printStackTrace();
	      }
		
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new Splash());
		addState(new Level1());
		addState(new Transition1());
		addState(new LichLair());
		addState(new Transition2());
		addState(new Level2());
		addState(new Transition3());
		addState(new CockatriceLair());
		addState(new Transition4());
		addState(new Level3());
		addState(new Transition5());
		addState(new DragonLair());
		addState(new Transition6());
		
	}
}
	
