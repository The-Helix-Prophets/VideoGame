package helixprophets;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
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
	
	public static void main(String[] args) {
		Main game = new Main();
		String title = "Game Title";
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
		} catch (IOException e1) {
			e1.printStackTrace();
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
		
		while(isSplash)
			Display.update();
	}
	
	/**
	 * Main 
	 */
	public void play() {
		//Move to a loadTexture Method
		Texture splashTexture = null;
		try {
			splashTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/character.png")));
		} catch (IOException e1) {
			System.exit(0);
			e1.printStackTrace();
		}
		int x = 0, y = 0;
		boolean wState = false, aState = false, sState = false, dState = false;
		boolean close = false;
		while(!Display.isCloseRequested() && !close) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			while(Keyboard.next()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState())
					close = true;
				if(Keyboard.getEventKey() == Keyboard.KEY_W ) {
					if(Keyboard.getEventKeyState())
						wState = true;
					else
						wState = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_A ) {
					if(Keyboard.getEventKeyState())
						aState = true;
					else
						aState = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_S ) {
					if(Keyboard.getEventKeyState())
						sState = true;
					else
						sState = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_D ) {
					if(Keyboard.getEventKeyState())
						dState = true;
					else
						dState = false;
				}
			}
			if(wState)
				y-=5;
			if(sState)
				y+=5;
			if(aState)
				x-=5;
			if(dState)
				x+=5;
				
			//Render Image
			splashTexture.bind(); // or GL11.glBind(texture.getTextureID());
			if(!splashTexture.equals(null)) {
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f(x, y);
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(x+128,y);
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(x+128,y+128);
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f(x,y+128);
				GL11.glEnd();
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
