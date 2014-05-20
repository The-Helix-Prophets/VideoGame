package helixprophets.beings;

import helixprophets.Coords;
import helixprophets.Keybinds;
import helixprophets.beings.classes.Fighter;
import helixprophets.beings.classes.Mage;
import helixprophets.beings.classes.Rogue;
import helixprophets.items.Inventory;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Character implements Renderable {
	protected Texture[] moveTextures;
	protected Texture[] fightTextures;
	protected Texture[] crawlTextures;

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
	

	protected Animation move;
	protected Animation moveFlipped;
	protected Animation fight;
	protected Animation fightFlipped;
	protected Animation crawl;
	protected Animation crawlFlipped;
	protected Image stillFlipped;
	protected Image still;
	protected Image cstillFlipped;
	protected Image cstill;
	
	protected int moveSpeed;
	protected int crawlSpeed;
	protected int jumpHeight;
	
	protected boolean fighting=false;
	protected boolean running=false;
	protected boolean crawling=false;
	protected boolean direction=true;
	
//	
	protected Coords coords;
	
	protected int health; //How did I forget that?
	
	protected static Inventory inventory = new Inventory();
	
	/**
	 * Assigns Texture Arrays and General Character Variables
	 * @param moveTextures Textures for when character moves
	 * @param fightTextures Textures for when character fights
	 * @param crawlTextures Textures for when character crawls
	 * @param moveSpeed Speed (Pixels?) at which character moves
	 * @param crawlSpeed Speed at which character crawls
	 * @param jumpHeight Speed at which character jumps
	 */

	public void draw(float x, float y) {
		if(running==true && crawling==false && fighting==false){
			if(direction==true){
				moveFlipped.draw(x,y);
			}
			if(direction==false){
				move.draw(x,y);
			}
		}
		if(crawling==true && running==true){
			if(direction==true){
				crawlFlipped.draw(x,y);
			}
			if(direction==false){
				crawl.draw(x,y);
			}
		}
		if(fighting==true){
			fightFlipped.draw(x,y);
		}
		if(running==false && crawling==false && fighting==false){
			if(direction==true){
				stillFlipped.draw(x,y);
			}
			if(direction==false){
				still.draw(x,y);
			}
		}
		if(running==false && crawling==true){
			if(direction==true){
				cstillFlipped.draw(x,y);
			}
			if(direction==false){
				cstill.draw(x,y);
			}
		}
	}
	
	public void setFighting(boolean b){
		fighting=b;
	}
	public void setRunning(boolean b){
		running=b;
	}
	public void setDirection(boolean b){
		direction=b;
	}
	public void setCrawling(boolean b){
		crawling=b;
	}
	public void changeClass(Character c){
		if(c instanceof Fighter){
			move=rogueMove;
			  moveFlipped=rogueMoveFlipped;
			  fight=rogueFight;
			  fightFlipped=rogueFightFlipped;
			  crawl=rogueCrawl;
			  crawlFlipped=rogueCrawlFlipped;
			stillFlipped = rogueMoveImagesFlipped[1];
			still=rogueMoveImages[1];
			cstillFlipped=rogueCrawlingFlipped[1];
			cstill=rogueCrawling[1];
			c = new Rogue();
		}
		else if(c instanceof Rogue){
			  move=mageMove;
			  moveFlipped=mageMoveFlipped;
			  fight=mageFight;
			  fightFlipped=mageFightFlipped;
			  crawl=mageCrawl;
			  crawlFlipped=mageCrawlFlipped;
			  stillFlipped = mageMoveImagesFlipped[1];
				still=mageMoveImages[1];
				cstillFlipped=mageCrawlingFlipped[1];
				cstill=mageCrawling[1];
			 c = new Mage();
	
		}
		else if(c instanceof Mage){

			  move=fighterMove;
			  moveFlipped=fighterMoveFlipped;
			  fight=fighterFight;
			  fightFlipped=fighterFightFlipped;
			  crawl=fighterCrawl;
			  crawlFlipped=fighterCrawlFlipped;
			  stillFlipped = fighterMoveImagesFlipped[1];
				still=fighterMoveImages[1];
				cstillFlipped=fighterCrawlingFlipped[1];
				cstill=fighterCrawling[1];
				c = new Fighter();
		}
	}
	
	
	/**
	 * Handles character jump textures and movement
	 * @param threaded if you want the jump to operate concurrently to calling method
//	 */
//	public void jump(boolean threaded) {
//		if(threaded) {
//			new Thread(new Runnable() {
//				public void run() {
//					jump();
//				}
//			}).start();
//		} else {
//			jump();
//		}
//	}
	public void initialize() throws SlickException {

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
		  rogueMoveFlipped = new Animation(rogueMoveImagesFlipped, 100);
		  rogueFight = new Animation(rogueFighting, 100);
		  rogueFightFlipped = new Animation(rogueFightingFlipped, 100);
		  rogueCrawl = new Animation(rogueCrawling, 100);
		  rogueCrawlFlipped = new Animation(rogueCrawlingFlipped, 100);
		  
	}
	
	/**
	 * Handles character crawl textures and movement
	 * @param threaded if you want the jump to operate concurrently to calling method
	 */
	
	public void classcheck(Character c){
		 if(c instanceof Rogue){
			  move=rogueMove;
			  moveFlipped=rogueMoveFlipped;
			  fight=rogueFight;
			  fightFlipped=rogueFightFlipped;
			  crawl=rogueCrawl;
			  crawlFlipped=rogueCrawlFlipped;
			stillFlipped = rogueMoveImagesFlipped[1];
			still=rogueMoveImages[1];
			cstillFlipped=rogueCrawlingFlipped[1];
			cstill=rogueCrawling[1];
			  
		  }
		  if(c instanceof Mage){
			  move=mageMove;
			  moveFlipped=mageMoveFlipped;
			  fight=mageFight;
			  fightFlipped=mageFightFlipped;
			  crawl=mageCrawl;
			  crawlFlipped=mageCrawlFlipped;
			  stillFlipped = mageMoveImagesFlipped[1];
				still=mageMoveImages[1];
				cstillFlipped=mageCrawlingFlipped[1];
				cstill=mageCrawling[1];
		  }
		  if(c instanceof Fighter){
			  move=fighterMove;
			  moveFlipped=fighterMoveFlipped;
			  fight=fighterFight;
			  fightFlipped=fighterFightFlipped;
			  crawl=fighterCrawl;
			  crawlFlipped=fighterCrawlFlipped;
			  stillFlipped = fighterMoveImagesFlipped[1];
				still=fighterMoveImages[1];
				cstillFlipped=fighterCrawlingFlipped[1];
				cstill=fighterCrawling[1];
		  }
	}
	public void crawl(boolean threaded) {
		if(threaded) {
			new Thread(new Runnable() {
				public void run() {
					crawl();
				}
			}).start();
		} else {
			crawl();
		}
	}
	public boolean getDirection(){
		return direction;
	}
	private void crawl() {
		
			
	}
	
	/**
	 * Handles character fight textures
	 * @param threaded if you want the jump to operate concurrently to calling method
	 */
	public void fight(boolean threaded) {
		if(threaded) {
			new Thread(new Runnable() {
				public void run() {
					fight();
				}
			}).start();
		} else {
			fight();
		}
	}
	public void fight() {
		
	}
	
}
