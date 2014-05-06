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
			mageTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/roguesheet.png")));
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

	
	
	
	

	/*public void displayFighter(){
	
	}*/
	

	public void fight()
	{
		//Switch to magicness graphic
		// Read in position of cursor
		//If the mouse was clicked, create a box entity with gravity physics at that location
		//if the mouse was held down for half a second or more, create a platform entity
		//with gravity physics at that location
		//Start a timer where another object cannot be created until the object disappears (3 sec)
	
		/*What the code would look like, not including any classes not implemented yet
		 * drawTexture(fightTextures[0]);//magic casting stance
		 * Point cursor= mouse.getLocation(); 
		 * if(cursor==valid) //valid would be determined by whether or not that space is occupied by a platform or something
		 *{
		 *drawTexture(moveTextures[0]); //Standby
		 *  if(mouseClickDuration<500)
		 *  {
		 *    Entity<Box> box= new Entity<Box>(cursor.getX(), cursor.getY());
		 *    delay(3000); //Box lasts for 3 seconds;
		 *    box.remove(); //box disappears, a new entity can be made.
		 *  }
		 *  else
		 *  {
		 *    Entity<Platform> platform= new Entity<Platform>(cursor.getX(), cursor.getY());
		 *    delay(3000); //Platform lasts for 3 seconds;
		 *    platform.remove(); //platform disappears, a new entity can be made.
		 *  }
		 *}
		 *drawTexture(moveTextures[0]); //Standby
		 */

	}
}
/* The mage should share the highest jump height with the rogue but should have the lowest 
 * movespeed. His fight() should create a box or a platform at the mouse position,
 * which should both be affected by gravity. The box/platform should last for three or so seconds
 * and then disappear, and fight() should be inaccessible if a box/platform has been created 
 * within the last three or so seconds. Medium crawl speed
 * 
 * He can also see invisible secrets that other classes cant see. These secrets will be denoted
 *  by a flashy glowy circle around the secret's location, so the secret item or door is still
 *  accessible to any class if they knew beforehand where it was.
 */
