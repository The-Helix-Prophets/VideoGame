package helixprophets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class Splash extends BasicGameState{
	
	private boolean haveSplash = true;
	private StateBasedGame game;
	Texture splashTexture = null;
	private static volatile boolean isSplash = true;

private int elapsedTime;
private final int DELAY = 3000;
 
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	this.game = game;

		try {
			splashTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/splash.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
 
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	//Init and bind image as texture
    	
    	
		
    			if(!splashTexture.equals(null)) {
    				splashTexture.bind(); // or GL11.glBind(texture.getTextureID());
    			} else
    				{
    				Color.black.bind();
    				}
    			
    		
    			
    			//Render Image centered on the screen
    			
    			if(!splashTexture.equals(null)) {
    				GL11.glBegin(GL11.GL_QUADS);
    				//if(fill) {
    					// Stretches image to screen size
    					GL11.glTexCoord2f(0,0);
    					GL11.glVertex2f(0,0);
    					GL11.glTexCoord2f(1,0);
    					GL11.glVertex2f(Display.getWidth(), 0);
    					GL11.glTexCoord2f(1,1);
    					GL11.glVertex2f(Display.getWidth(), Display.getHeight());
    					GL11.glTexCoord2f(0,1);
    					GL11.glVertex2f(0, Display.getHeight());
    				//} else {
    					// Centers image with no stretch
//    					GL11.glTexCoord2f(0,0);
//    					GL11.glVertex2f((Display.getWidth()/2)-(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)-(splashTexture.getTextureHeight()/2));
//    					GL11.glTexCoord2f(1,0);
//    					GL11.glVertex2f((Display.getWidth()/2)+(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)-(splashTexture.getTextureHeight()/2));
//    					GL11.glTexCoord2f(1,1);
//    					GL11.glVertex2f((Display.getWidth()/2)+(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)+(splashTexture.getTextureHeight()/2));
//    					GL11.glTexCoord2f(0,1);
//    					GL11.glVertex2f((Display.getWidth()/2)-(splashTexture.getTextureWidth()/2), (Display.getHeight()/2)+(splashTexture.getTextureHeight()/2));
    				//}
    				GL11.glEnd();
    			}
    			Display.update();
    			Display.sync(60);
    			
    			
    			
    			
    			
//    			  new Thread(new Runnable() {
//    				
//    				@Override
//    				public void run() {
//    					int time = 3000;
//    					long startTime = System.currentTimeMillis();
//    					while(System.currentTimeMillis()-startTime < time);
//    					isSplash = false;
//    				}
//    			}).start();
//    			  
    			  
    	}
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    	 elapsedTime += delta;

    	    if(elapsedTime >= DELAY) {

    	        game.enterState(1);

    	    }
 
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }
 
}