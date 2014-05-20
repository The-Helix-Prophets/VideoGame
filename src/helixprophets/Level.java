package helixprophets;

import helixprophets.beings.Character;
import helixprophets.beings.classes.Fighter;
import helixprophets.beings.classes.Mage;
import helixprophets.beings.classes.Rogue;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Level extends BasicGameState {
	protected TiledMap Map;
	protected StateBasedGame game;
	
	protected boolean blocked[][];
	protected boolean death[][];
	protected boolean finish[][];
	protected boolean attackzone[][];
	protected boolean bosslocation[][];
	protected boolean attacking;
	protected Character player = new Fighter();
	protected int tileSize = 64;
	protected int layer = 0;
	protected boolean direction = true;
	protected int x = 128;
	protected int y = 100;
	protected int camx = 0;
	protected int camy = 0;
	protected float yspeed = 0;
	protected int yfoot = 0;
	protected int ymid = 0;
	protected int ycrawl=0;
	protected int xcollide = 0;
	protected int xcollideleft = 0;
	protected boolean jumping = false;
	
	
	
	protected Keybinds keybinds;
	Image rogueImage;
	protected Image[] mageMoveImages = new Image[9];
	protected Image[] mageFightImages = new Image[9];
	protected Image[] mageCrawling = new Image[3];
	protected Image[] mageMoveImagesFlipped = new Image[9];
	protected Image[] mageFightImagesFlipped = new Image[9];
	protected Image[] mageCrawlingFlipped = new Image[3];
	
	protected Image[] fighterMoveImages = new Image[9];
	protected Image[] fighterMoveImagesFlipped = new Image[9];
	protected Image[] fighterFighting = new Image[3];
	protected Image[] fighterFightingFlipped = new Image[3];
	protected Image[] fighterCrawling = new Image[3];
	protected Image[] fighterCrawlingFlipped = new Image[3];
	
	protected Image[] rogueMoveImages = new Image[9];
	protected Image[] rogueFighting = new Image[8];
	protected Image[] rogueCrawling = new Image[3];
	protected Image[] rogueMoveImagesFlipped = new Image[9];
	protected Image[] rogueFightingFlipped = new Image[8];
	protected Image[] rogueCrawlingFlipped = new Image[3];
	
	protected Animation fighterMove;
	protected Animation fighterMoveFlipped;
	protected Animation fighterFight;
	protected Animation fighterFightFlipped;
	protected Animation fighterCrawl;
	protected Animation fighterCrawlFlipped;
	
	protected Animation mageMove;
	protected Animation mageMoveFlipped;
	protected Animation mageFight;
	protected Animation mageFightFlipped;
	protected Animation mageCrawl;
	protected Animation mageCrawlFlipped;
	
	protected Animation rogueMove;
	protected Animation rogueMoveFlipped;
	protected Animation rogueFight;
	protected Animation rogueFightFlipped;
	protected Animation rogueCrawl;
	protected Animation rogueCrawlFlipped;
	
	
	public void reset(){
	}
	public void mapsetup(){

		
		blocked = new boolean[Map.getWidth()][Map.getHeight()];
		death =new boolean[Map.getWidth()][Map.getHeight()];
		attackzone =new boolean[Map.getWidth()][Map.getHeight()];
		bosslocation =new boolean[Map.getWidth()][Map.getHeight()];
		finish =new boolean[Map.getWidth()][Map.getHeight()];
		for(int i = 0; i < Map.getWidth(); i++) {
		    for(int j = 0; j < Map.getHeight(); j++) {
		        int tileID = Map.getTileId(i, j, layer);
		        
		        String value = Map.getTileProperty(tileID, "blocked", "false");
		        if(value.equals("true")) {
		            blocked[i][j] = true;
		
		        }
		        
		        }
		    }
		for(int i = 0; i < Map.getWidth(); i++) {
		    for(int j = 0; j < Map.getHeight(); j++) {
		        int tileID = Map.getTileId(i, j, layer);
		        
		        String value = Map.getTileProperty(tileID, "finish", "false");
		        if(value.equals("true")) {
		            finish[i][j] = true;
		
		        }
		        
		        }
		    }
		for(int i = 0; i < Map.getWidth(); i++) {
		    for(int j = 0; j < Map.getHeight(); j++) {
		        int tileID = Map.getTileId(i, j, layer);
		        String value1 = Map.getTileProperty(tileID, "death", "false");
		        if(value1.equals("true")) {
		            death[i][j] = true;
		        }
		}
		}
		for(int i = 0; i < Map.getWidth(); i++) {
		    for(int j = 0; j < Map.getHeight(); j++) {
		        int tileID = Map.getTileId(i, j, layer);
		        
		        String value = Map.getTileProperty(tileID, "bosslocation", "false");
		        if(value.equals("true")) {
		            bosslocation[i][j] = true;
		
		        }
		        
		        }
		    }
		for(int i = 0; i < Map.getWidth(); i++) {
		    for(int j = 0; j < Map.getHeight(); j++) {
		        int tileID = Map.getTileId(i, j, layer);
		        
		        String value = Map.getTileProperty(tileID, "attackzone", "false");
		        if(value.equals("true")) {
		            attackzone[i][j] = true;
		
		        }
		        
		        }
		    }
	}
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		this.game=arg1;
		keybinds = new Keybinds();
		player.initialize();
		player.classcheck(player);
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

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {

		for(int i=0; i<Map.getLayerCount(); i++)
			Map.render(camx,camy,i);
		
		//WALKING RIGHT
		if(keybinds.getRawKeyState(Keyboard.KEY_D)==true && keybinds.getRawKeyState(Keyboard.KEY_A)==false && keybinds.getRawKeyState(Keyboard.KEY_S)==false && blocked[xcollide][ymid]==false && blocked[xcollide][ycrawl]==false && bosslocation[xcollide][ymid]==false)
		{
			player.setDirection(true);
			player.setRunning(true);
			camx-=4;
		}
		//STANDING STILL
			if(direction==true && keybinds.getRawKeyState(Keyboard.KEY_D)==false && keybinds.getRawKeyState(Keyboard.KEY_SPACE)==false && keybinds.getRawKeyState(Keyboard.KEY_S)==false)
			{
				player.setRunning(false);
			}
			if(direction==false && keybinds.getRawKeyState(Keyboard.KEY_A)==false  && keybinds.getRawKeyState(Keyboard.KEY_S)==false)
			{
				player.setRunning(false);
			}
		//STANDING STILL DUE TO COLLISION
			if(blocked[xcollide][ymid]==true || blocked[xcollide][ycrawl]==true || bosslocation[xcollide][ymid]==true && keybinds.getRawKeyState(Keyboard.KEY_D)==true && keybinds.getRawKeyState(Keyboard.KEY_S)==false )
				player.setRunning(false);
			if(blocked[xcollideleft][ymid]==true || blocked[xcollideleft][ycrawl]==true && keybinds.getRawKeyState(Keyboard.KEY_A)==true && keybinds.getRawKeyState(Keyboard.KEY_S)==false)
				player.setRunning(false);
			
	
		//ATTACKING
		if(keybinds.getRawKeyState(Keyboard.KEY_SPACE)==true && keybinds.getRawKeyState(Keyboard.KEY_S)==false)
		{
			if(player.getDirection()==true)
			{
				attacking=true;
				player.setFighting(true);
			}
		}
		else{
			attacking=false;
			player.setFighting(false);
		}
		
		//WALKING LEFT
		if(camx<=0)
		{
		if(keybinds.getRawKeyState(Keyboard.KEY_A) == true && keybinds.getRawKeyState(Keyboard.KEY_D)==false && keybinds.getRawKeyState(Keyboard.KEY_S)==false && blocked[xcollideleft][ymid]==false && blocked[xcollideleft][ycrawl]==false && bosslocation[xcollideleft][ymid]==false)
		{
			player.setDirection(false);
			player.setRunning(true);
			camx+=4;
		}
		}
		//STANDING STILL DUE TO WALKING BOTH WAYS AT ONCE
		if(keybinds.getRawKeyState(Keyboard.KEY_A) == true && keybinds.getRawKeyState(Keyboard.KEY_D) == true && keybinds.getRawKeyState(Keyboard.KEY_S)==false)
		{
			if(player.getDirection())
			{
				player.setRunning(false);
			}
			if(player.getDirection()==false)
			{
				player.setRunning(false);
		}
	}
		//SAME BUT WITH CROUCHING
		if(keybinds.getRawKeyState(Keyboard.KEY_A) == true && keybinds.getRawKeyState(Keyboard.KEY_D) == true && keybinds.getRawKeyState(Keyboard.KEY_S)==true)
		{
			if(player.getDirection())
			{
				player.setRunning(false);
			}
			if(player.getDirection()==false)
			{
				player.setRunning(false);
		}
	}
		//CROUCHING
		if(keybinds.getRawKeyState(Keyboard.KEY_S)==true)
		{
			if(keybinds.getRawKeyState(Keyboard.KEY_A)==false && keybinds.getRawKeyState(Keyboard.KEY_D)==false)
			{
				player.setRunning(false);
			}
			//CRAWLING LEFT
			if(camx<=0)
			{
			if(keybinds.getRawKeyState(Keyboard.KEY_A)==true && keybinds.getRawKeyState(Keyboard.KEY_D)==false && blocked[xcollideleft][ycrawl]==false)
			{
				player.setDirection(false);
				player.setCrawling(true);
				player.setRunning(true);
				camx++;
			}
			}
			//CRAWLING RIGHT
			if(keybinds.getRawKeyState(Keyboard.KEY_D)==true && keybinds.getRawKeyState(Keyboard.KEY_A)==false && blocked[xcollide][ycrawl]==false)
			{
				player.setDirection(true);
				player.setCrawling(true);
				player.setRunning(true);
				camx--;
		}
		}
		else{
			player.setCrawling(false);
		}

		player.draw(x, y);
		
		
	
	
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(arg0.getInput().isKeyPressed(Keyboard.KEY_W)==true && jumping!=true){
			yspeed=(float) (-0.3*arg2);
            jumping = true;
		}
		if(jumping==true)
			yspeed+=.1;
		if(jumping==false){
			yspeed=0;
			
		}
		y+=yspeed;
		yfoot=(y+235+(-camy))/64;
		ymid=(y+128+(-camy))/64;
		ycrawl=(y+192+(-camy))/64;
		xcollide=(-((camx-180-30)/64));
		xcollideleft=(-(camx-140)/64);
		if(blocked[xcollide][yfoot]==false && jumping==false)
			jumping=true;
		else if(blocked[xcollide][yfoot]==true && blocked[xcollide][ymid]==false && jumping==true)
			jumping=false;
		if(y>=250){
			camy--;
			y-=.05;
		}
		if(y<=0){
			camy++;
		
		}
		
		if(death[xcollide][yfoot]==true){
			reset();
		}
		if(arg0.getInput().isKeyPressed(Keyboard.KEY_LCONTROL)==true){
			player.changeClass(player);
		}
		
		if(finish[xcollide][yfoot]==true){
			reset();
			game.enterState(game.getCurrentStateID()+1);
		}
		
	}

}
