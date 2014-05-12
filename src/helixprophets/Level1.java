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
	private int tileSize = 64;
	
	private boolean direction = true;
	private int x = 128;
	private int y = 100;
	private int camx = 0;
	private int camy = -2*50;
	
	
	
	private Keybinds keybinds;
	Image rogueImage;
	private Image[] mageMoveImages = new Image[9];
	private Image[] mageFightImages = new Image[9];
	private Image[] mageCrawling = new Image[3];
	private Image[] mageMoveImagesFlipped = new Image[9];
	private Image[] mageFightImagesFlipped = new Image[9];
	private Image[] mageCrawlingFlipped = new Image[3];
	
	private Image[] fighterMoveImages = new Image[9];
	private Image[] fighterMoveImagesFlipped = new Image[9];
	private Image[] fighterFighting = new Image[3];
	private Image[] fighterFightingFlipped = new Image[3];
	private Image[] fighterCrawling = new Image[3];
	private Image[] fighterCrawlingFlipped = new Image[3];
	
	private Image[] rogueMoveImages = new Image[9];
	private Image[] rogueFighting = new Image[8];
	private Image[] rogueCrawling = new Image[3];
	private Image[] rogueMoveImagesFlipped = new Image[9];
	private Image[] rogueFightingFlipped = new Image[8];
	private Image[] rogueCrawlingFlipped = new Image[3];
	
	private Animation fighterMove;
	private Animation fighterMoveFlipped;
	private Animation fighterFight;
	private Animation fighterFightFlipped;
	private Animation fighterCrawl;
	private Animation fighterCrawlFlipped;
	
	private Animation mageMove;
	private Animation mageMoveFlipped;
	private Animation mageFight;
	private Animation mageFightFlipped;
	private Animation mageCrawl;
	private Animation mageCrawlFlipped;
	
	private Animation rogueMove;
	private Animation rogueMoveFlipped;
	private Animation rogueFight;
	private Animation rogueFightFlipped;
	private Animation rogueCrawl;
	private Animation rogueCrawlFlipped;
	
	
	
	
	@Override
	//this is where we set everything up 
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
		        }
		    }
		}
		
		
		keybinds = new Keybinds();
		//putting the rogue move textures in the texture array
		for(int i = 1; i < 10; i++) {
			 {
				rogueMoveImages[i-1] = new Image("res/roguewalkframe" + i + ".png");
				rogueMoveImages[i-1] = rogueMoveImages[i-1].getScaledCopy((float) .5);
				rogueMoveImagesFlipped[i-1] = rogueMoveImages[i-1].getFlippedCopy(true, false);
			} 
		}
		for(int i = 1; i < 9; i++) {
			 {
				rogueFighting[i-1] = new Image("res/roguefightframe" + i + ".png");
				rogueFighting[i-1] = rogueFighting[i-1].getScaledCopy((float) .5);
				rogueFightingFlipped[i-1] = rogueFighting[i-1].getFlippedCopy(true, false);
			} 
		}
		for(int i = 1; i < 4; i++) {
			 {
				rogueCrawling[i-1] = new Image("res/rogueCrawl" + i + ".png");
				rogueCrawling[i-1] = rogueCrawling[i-1].getScaledCopy((float) .5);
				rogueCrawlingFlipped[i-1] = rogueCrawling[i-1].getFlippedCopy(true, false);
			} 
		}
		
		//putting the fighter move textures in the texture array
		for(int i = 1; i < 4; i++) {
			 {
				fighterFighting[i-1] = new Image("res/fightfightframe" + i + ".png");
				fighterFighting[i-1] = fighterFighting[i-1].getScaledCopy((float) .5);
				fighterFightingFlipped[i-1] = fighterFighting[i-1].getFlippedCopy(true, false);
			} 
		}
		for(int i = 1; i < 10; i++) {
			 {
				fighterMoveImages[i-1] = new Image("res/fightwalkframe" + i + ".png");
				fighterMoveImages[i-1] = fighterMoveImages[i-1].getScaledCopy((float) .5);
				fighterMoveImagesFlipped[i-1] = fighterMoveImages[i-1].getFlippedCopy(true, false);
			} 
		}
		for(int i = 1; i < 4; i++) {
			 {
				fighterCrawling[i-1] = new Image("res/fightcrawl" + i + ".png");
				fighterCrawling[i-1] = fighterCrawling[i-1].getScaledCopy((float) .5);
				fighterCrawlingFlipped[i-1] = fighterCrawling[i-1].getFlippedCopy(true, false);
			} 
		}
		
		//and finally, putting the mage textures in its texture array
		for(int i = 1; i < 10; i++) {
			 {
				mageFightImages[i-1] = new Image("res/magefight" + i + ".png");
				mageFightImages[i-1] = mageFightImages[i-1].getScaledCopy((float) .5);
				mageFightImagesFlipped[i-1] = mageFightImages[i-1].getFlippedCopy(true, false);
			} 
		}
		for(int i = 1; i < 10; i++) {
			 {
				mageMoveImages[i-1] = new Image("res/magewalkframe" + i + ".png");
				mageMoveImages[i-1] = mageMoveImages[i-1].getScaledCopy((float) .5);
				mageMoveImagesFlipped[i-1] = mageMoveImages[i-1].getFlippedCopy(true, false);
			} }
		for(int i = 1; i < 4; i++) {
			{
				mageCrawling[i-1] = new Image("res/magecrawl" + i + ".png");
				mageCrawling[i-1] = mageCrawling[i-1].getScaledCopy((float) .5);
				mageCrawlingFlipped[i-1] = mageCrawling[i-1].getFlippedCopy(true, false);
			} 
		}
		//get the specified texture from the arrays.
		rogueImage = fighterMoveImages[8];
		fighterMove = new Animation(fighterMoveImages, 100);
		fighterMoveFlipped = new Animation(fighterMoveImagesFlipped, 100);
		fighterMove = new Animation(fighterMoveImages, 100);
		  fighterMoveFlipped = new Animation(fighterMoveImagesFlipped, 100);
		  fighterFight = new Animation(fighterFighting, 100);
		  fighterFightFlipped = new Animation(fighterFightingFlipped, 100);
		  fighterCrawl = new Animation(fighterCrawling, 100);
		  fighterCrawlFlipped = new Animation(fighterCrawlingFlipped, 100);
		
		  mageMove = new Animation(mageMoveImages, 100);
		  mageMoveFlipped = new Animation(mageMoveImagesFlipped, 100);
		  mageFight = new Animation(mageFightImages, 100);
		  mageFightFlipped = new Animation(mageFightImagesFlipped, 100);
		  mageCrawl = new Animation(mageCrawling, 100);
		  mageCrawlFlipped = new Animation(mageCrawlingFlipped, 100);
		
		  rogueMove = new Animation(rogueMoveImages, 100);
		  rogueMoveFlipped = new Animation(rogueMoveImages, 100);
		  rogueFight = new Animation(rogueFighting, 100);
		  rogueFightFlipped = new Animation(rogueFightingFlipped, 100);
		  rogueCrawl = new Animation(rogueCrawling, 100);
		  rogueCrawlFlipped = new Animation(rogueCrawlingFlipped, 100);
		
	}
//The render method displays things on the screen.
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		Level1.render(camx,camy,0);
		Level1.render(camx,camy,1);
//		arg2.drawString("Result: " + blocked[-(camx/64)][(y)/64] + camx + " " + y, 0,0);
		
		if(keybinds.getRawKeyState(Keyboard.KEY_D)==true && keybinds.getRawKeyState(Keyboard.KEY_S)==false && blocked[-((camx-300)/64)][(y+260)/64]==false){
			fighterMoveFlipped.draw(x,y);
			camx--;
			direction=true;
		}
			if(direction==true && keybinds.getRawKeyState(Keyboard.KEY_D)==false && keybinds.getRawKeyState(Keyboard.KEY_SPACE)==false && keybinds.getRawKeyState(Keyboard.KEY_S)==false){
				fighterMoveImagesFlipped[1].draw(x,y);}
			if(direction==false && keybinds.getRawKeyState(Keyboard.KEY_A)==false  && keybinds.getRawKeyState(Keyboard.KEY_SPACE)==false && keybinds.getRawKeyState(Keyboard.KEY_S)==false){
				fighterMoveImages[1].draw(x,y);
			}
			
			if(blocked[-((camx-300)/64)][(y+260)/64]==true && keybinds.getRawKeyState(Keyboard.KEY_D)==true)
				fighterMoveImagesFlipped[1].draw(x,y);
			if(blocked[-((camx-128)/64)][(y+260)/64]==true && keybinds.getRawKeyState(Keyboard.KEY_A)==true)
				fighterMoveImages[1].draw(x,y);
			
	
		
		if(keybinds.getRawKeyState(Keyboard.KEY_SPACE)==true && keybinds.getRawKeyState(Keyboard.KEY_S)==false){
			if(direction==true){
				fighterFightFlipped.draw(x,y);
			}
			else{
				fighterFight.draw(x,y);
			}
		}
		
		
		if(keybinds.getRawKeyState(Keyboard.KEY_A) == true && keybinds.getRawKeyState(Keyboard.KEY_S)==false && blocked[-((camx-128)/64)][(y+260)/64]==false){
			fighterMove.draw(x,y);
			camx++;
			direction=false;
		}
		
		if(keybinds.getRawKeyState(Keyboard.KEY_S)==true){
			if(keybinds.getRawKeyState(Keyboard.KEY_A)==false && keybinds.getRawKeyState(Keyboard.KEY_D)==false){
				if(direction==true){
					fighterCrawlingFlipped[1].draw(x,y);
					}
				else{fighterCrawling[1].draw(x,y);
				
				}
			}
			if(keybinds.getRawKeyState(Keyboard.KEY_A)==true){
				fighterCrawl.draw(x,y);
				camx++;
				direction = false;
			}
			if(keybinds.getRawKeyState(Keyboard.KEY_D)==true && blocked[-((camx-128)/64)][(y+150)/64]==false){
				fighterCrawlFlipped.draw(x,y);
				camx--;
				direction = true;
		}
		}
		
		if(keybinds.getRawKeyState(Keyboard.KEY_W)==true){

			y--;
		}
		else if(blocked[-((camx-129)/64)][(y+320)/64]==false){
			y++; }
		
		
//		
//		GL11.glBegin(GL11.GL_QUADS);
//			// Centers image with no stretch
//			GL11.glTexCoord2f(0,0);
//			GL11.glVertex2f((Display.getWidth()/2)-(rogueImage.getImageWidth()/8), (Display.getHeight()/2)-(rogueImage.getImageHeight()/8));
//			GL11.glTexCoord2f(1,0);
//			GL11.glVertex2f((Display.getWidth()/2)+(rogueImage.getImageWidth()/8), (Display.getHeight()/2)-(rogueImage.getImageHeight()/8));
//			GL11.glTexCoord2f(1,1);
//			GL11.glVertex2f((Display.getWidth()/2)+(rogueImage.getImageWidth()/8), (Display.getHeight()/2)+(rogueImage.getImageHeight()/8));
//			GL11.glTexCoord2f(0,1);
//			GL11.glVertex2f((Display.getWidth()/2)-(rogueImage.getImageWidth()/8), (Display.getHeight()/2)+(rogueImage.getImageHeight()/8));
//		GL11.glEnd();
		

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
