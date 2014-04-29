package helixprophets.beings.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import helixprophets.beings.Character;

public class Mage extends Character {
	
	private static volatile boolean mageDisplayed = true;

	public Mage(Texture[] moveTextures, Texture[] fightTextures,
			Texture[] crawlTextures, int moveSpeed, int crawlSpeed,
			int jumpHeight) {
		super(moveTextures, fightTextures, crawlTextures, moveSpeed, crawlSpeed,
				jumpHeight);
		// TODO Auto-generated constructor stub
	}
	


	public void displayMage() {
		//Init and bind image as texture
		Texture mageTexture = null;
		try {
			mageTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mage.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!mageTexture.equals(null)) {
			mageTexture.bind(); // or GL11.glBind(texture.getTextureID());
		} else {
			Color.black.bind();
		}
		//Render Image centered on the screen
		if(!mageTexture.equals(null)) {
			GL11.glBegin(GL11.GL_QUADS);
			
				// Centers image with no stretch
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f((Display.getWidth()/2)-(mageTexture.getTextureWidth()/2), (Display.getHeight()/2)-(mageTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f((Display.getWidth()/2)+(mageTexture.getTextureWidth()/2), (Display.getHeight()/2)-(mageTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f((Display.getWidth()/2)+(mageTexture.getTextureWidth()/2), (Display.getHeight()/2)+(mageTexture.getTextureHeight()/2));
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f((Display.getWidth()/2)-(mageTexture.getTextureWidth()/2), (Display.getHeight()/2)+(mageTexture.getTextureHeight()/2));
			}
			GL11.glEnd();
		
		Display.update();
		Display.sync(60);
		
new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					mageDisplayed = false;
				}
			}
		}).start();
	}

	
	
	
	public int getXPosition(){
		return 0;
	}

	public int getYPosition(){
		return 0;
	}

	public void displayFighter(){
	
	}

}
