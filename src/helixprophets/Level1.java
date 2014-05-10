package helixprophets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.tiled.Layer;

public class Level1 extends BasicGameState {

	private TiledMap Level1;
	
	private boolean blocked[][];
	private ArrayList<Rectangle> blocks;
	private int tileSize = 64;
	
	private Keybinds keybinds;
	Texture rogueTexture;
	private Texture[] mageMoveTextures = new Texture[9];
	private Texture[] mageFightTextures = new Texture[9];
	private Texture[] mageCrawling = new Texture[9];
	
	private Texture[] fighterMoveTextures = new Texture[9];
	private Texture[] fighterFighting = new Texture[9];
	private Texture[] fighterCrawling = new Texture[9];
	
	private Texture[] rogueMoveTextures = new Texture[9];
	private Texture[] rogueFighting = new Texture[9];
	private Texture[] rogueCrawling = new Texture[9];
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Level1 = new TiledMap("res/level1.tmx","res");
		
		//this creates our collision blocks for our foreground layer of the tilemap
		blocked = new boolean[Level1.getWidth()][Level1.getHeight()];
		int layer = 1; 
		for(int i = 0; i < Level1.getWidth(); i++) {
		    for(int j = 0; j < Level1.getHeight(); j++) {
		        int tileID = Level1.getTileId(i, j, layer);
		        String value = Level1.getTileProperty(tileID, "blocked", "false");
		        if(value.equals("true")) {
		            blocked[i][j] = true;
		            blocks.add(new Rectangle((float)i * tileSize, (float)j * tileSize, tileSize, tileSize));
		        }
		    }
		}
		
		
		keybinds = new Keybinds();
		//putting the rogue move textures in the texture array
		for(int i = 1; i < 10; i++) {
			try {
				rogueMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/roguewalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 9; i++) {
			try {
				rogueFighting[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/roguefightframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 4; i++) {
			try {
				rogueCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/rogueCrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//putting the fighter move textures in the texture array
		for(int i = 1; i < 4; i++) {
			try {
				fighterFighting[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightfightframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 10; i++) {
			try {
				fighterMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightwalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 4; i++) {
			try {
				fighterCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/fightcrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//and finally, putting the mage textures in its texture array
		for(int i = 1; i < 10; i++) {
			try {
				mageFightTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magefight" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 10; i++) {
			try {
				mageMoveTextures[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magewalkframe" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1; i < 4; i++) {
			try {
				mageCrawling[i-1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/magecrawl" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//get the specified texture from the arrays.
		rogueTexture = fighterMoveTextures[8];
		
	}
//The render method displays things on the screen.
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {

		Level1.render(0,0,0);
		Level1.render(0,0,1);
				rogueTexture.bind(); 
				
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
			//Display.update();
			//Display.sync(60);
		}
		
//The update is where the action occurs, I believe.
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
//This is a simple method that gives this state an ID.
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
