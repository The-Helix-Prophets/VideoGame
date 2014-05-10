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
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.tiled.TiledMap;



public class Main {
	private boolean haveSplash = true; //set to false to skip splash screen 
										//(have it up for minimum amount of time)
	
	private static volatile boolean isSplash = true;
	Texture splashTexture = null;
	
	private Texture[] mageMoveTextures = new Texture[9];
	private Texture[] mageFightTextures = new Texture[9];
	private Texture[] mageCrawling = new Texture[9];
	
	private Texture[] fighterMoveTextures = new Texture[9];
	private Texture[] fighterFighting = new Texture[9];
	private Texture[] fighterCrawling = new Texture[9];
	
	private Texture[] rogueMoveTextures = new Texture[9];
	private Texture[] rogueFighting = new Texture[9];
	private Texture[] rogueCrawling = new Texture[9];
	
	Texture rogueTexture;
	
	private Texture monsters;
	private Texture terrain;
	
	public static void main(String[] args) {
		Main game = new Main();
		String title = "I'm not Saying You're Obligated to Explore this Castle, but...";
		game.initDisplay(title, 800, 600);
		game.splash(true);
		game.open();
		game.play();
		game.close();
		
	}
	
	/**
	 * Initializes the Display and OpenGL
	 * @param title Window Title
	 * @param width Window Width
	 * @param height Window Height
	 */
	public void initDisplay(String title, int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
			Display.setVSyncEnabled(true);
			
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);              
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 
		GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/**
	 * Displays a Splash Screen for a minimum of one second while game loads
	 */
	public void splash(boolean fill) {
		//Init and bind image as texture
		
		
		try {
			splashTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/splash.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!splashTexture.equals(null)) {
			splashTexture.bind(); // or GL11.glBind(texture.getTextureID());
		} else
			{
			Color.black.bind();
			}
		
	
		
		//Render Image centered on the screen
		
		if(!splashTexture.equals(null)) {
			GL11.glBegin(GL11.GL_QUADS);
			if(fill) {
				// Stretches image to screen size
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(0,0);
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(Display.getWidth(), 0);
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(Display.getWidth(), Display.getHeight());
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(0, Display.getHeight());
			} else {
				// Centers image with no stretch
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f((Display.getWidth()/2)-(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)-(splashTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f((Display.getWidth()/2)+(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)-(splashTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f((Display.getWidth()/2)+(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)+(splashTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f((Display.getWidth()/2)-(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)+(splashTexture.getTextureHeight()/2));
			}
			GL11.glEnd();
		}
		Display.update();
		Display.sync(60);
		
		
		
		
		  new Thread(new Runnable() {
			
			@Override
			public void run() {
				int time = 3000;
				long startTime = System.currentTimeMillis();
				while(System.currentTimeMillis()-startTime < time);
				isSplash = false;
			}
		}).start();
		  
		  
	}
	
	
	/**
	 * Initializes Game Setup
	 */
	public void open() {
	
		//putting the rogue move textures in the texture array
		for(int i = 1; i < 10; i++)
		{
			try {
				rogueMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/roguewalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 9; i++)
		{
			try {
				rogueFighting[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/roguefightframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 4; i++)
		{
			try {
				rogueCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/rogueCrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//putting the fighter move textures in the texture array
		
		for(int i = 1; i < 4; i++)
		{
			try {
				fighterFighting[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightfightframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 10; i++)
		{
			try {
				fighterMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightwalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 4; i++)
		{
			try {
				fighterCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightcrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	//and finally, putting the mage textures in its texture array
		
		for(int i = 1; i < 10; i++)
		{
			try {
				mageFightTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magefight" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 10; i++)
		{
			try {
				mageMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magewalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < 4; i++)
		{
			try {
				mageCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magecrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		//we have the array of textures, try-catch no longer needed.
		/*try {
			TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magewalkframe1.png")));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//get the specified texture from the arrays.
		rogueTexture = fighterMoveTextures[8];
		
		while(haveSplash&&isSplash); //wait for splash screen to be over
	}
	
	/**
	 * Main 
	 */
	public void play() {
		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); 
			if(!rogueTexture.equals(null)) {
				rogueTexture.bind(); 
			} else {
				Color.black.bind();
			}
			if(!rogueTexture.equals(null)) {
				
				GL11.glBegin(GL11.GL_QUADS);
					// Centers image with no stretch
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f((Display.getWidth()/2)-(rogueTexture.getTextureWidth()/8), (Display.getHeight()/2)-(rogueTexture.getTextureHeight()/8));
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f((Display.getWidth()/2)+(rogueTexture.getTextureWidth()/8), (Display.getHeight()/2)-(rogueTexture.getTextureHeight()/8));
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f((Display.getWidth()/2)+(rogueTexture.getTextureWidth()/8), (Display.getHeight()/2)+(rogueTexture.getTextureHeight()/8));
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f((Display.getWidth()/2)-(rogueTexture.getTextureWidth()/8), (Display.getHeight()/2)+(rogueTexture.getTextureHeight()/8));
				GL11.glEnd();
			}
			Display.update();
			Display.sync(60);
		}
		
		
		/*Mage mage = new Mage(null, null, null, 5, 10, 7);
		while(!(Keyboard.getEventKey() == Keyboard.KEY_Q))
		{
		mage.displayMage();
		}
		*/
		//Poll Keyboard
		//Update Positions
		//Process attacks, map position changes, ect
	}
	
	/**
	 * Closes Game
	 */
	public void close() {
		//Save state maybe?????
		Display.destroy();
	}
}
