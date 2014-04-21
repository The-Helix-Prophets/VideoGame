package helixprophets;

import static org.lwjgl.opengl.GL11.*;

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


public class Main {
	private static volatile boolean isSplash = true;
	
	private Texture[] mageMoveTextures = new Texture[9];
	private Texture[] mageFightTextures = new Texture[9];
	
	private Texture[] mageFighting = new Texture[9];
	private Texture[] fighterMoveTextures = new Texture[9];
	private Texture[] fighterFighting = new Texture[9];
	private Texture[] rogueMoveTextures = new Texture[9];
	private Texture[] rogueFighting = new Texture[9];
	
	private Texture monsters;
	private Texture terrain;
	
	public static void main(String[] args) {
		Main game = new Main();
		String title = "I’m not Saying You’re Obligated to Explore this Castle, but...";
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
		Texture splashTexture = null;
		try {
			splashTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/splash.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!splashTexture.equals(null)) {
			splashTexture.bind(); // or GL11.glBind(texture.getTextureID());
		} else {
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
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					isSplash = false;
				}
			}
		}).start();
	}
	
	/**
	 * Initializes Game Setup
	 */
	public void open() {
		try {
			mageMoveTextures[0] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mage.png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(isSplash)
			Display.update();
	}
	
	/**
	 * Main 
	 */
	public void play() {
		
		boolean close = false, wState = false, aState = false, sState = false, dState = false;
		while(!Display.isCloseRequested() && !close) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			while(Keyboard.next()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState())
					close = true;
				
				if(Keyboard.getEventKey() == Keyboard.KEY_W )
					wState = Keyboard.getEventKeyState();
				if(Keyboard.getEventKey() == Keyboard.KEY_A )
					aState = Keyboard.getEventKeyState();
				if(Keyboard.getEventKey() == Keyboard.KEY_S )
					sState = Keyboard.getEventKeyState();
				if(Keyboard.getEventKey() == Keyboard.KEY_D )
					dState = Keyboard.getEventKeyState();
				
			}
			Display.update();
		}
	}
	
	/**
	 * Closes Game
	 */
	public void close() {
		Display.destroy();
	}
}
