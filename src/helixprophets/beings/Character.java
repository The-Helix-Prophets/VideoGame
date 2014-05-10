package helixprophets.beings;

import helixprophets.Coords;
import helixprophets.items.Inventory;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Character {
	private Texture[] moveTextures;
	private Texture[] fightTextures;
	private Texture[] crawlTextures;

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
	
	private int moveSpeed;
	private int crawlSpeed;
	private int jumpHeight;
	
	private Coords coords;
	
	private int health; //How did I forget that?
	
	private static Inventory inventory = new Inventory();
	
	/**
	 * Assigns Texture Arrays and General Character Variables
	 * @param moveTextures Textures for when character moves
	 * @param fightTextures Textures for when character fights
	 * @param crawlTextures Textures for when character crawls
	 * @param moveSpeed Speed (Pixels?) at which character moves
	 * @param crawlSpeed Speed at which character crawls
	 * @param jumpHeight Speed at which character jumps
	 */
	public Character(Texture[] moveTextures, Texture[] fightTextures, Texture[] crawlTextures, int moveSpeed, int crawlSpeed, int jumpHeight) {
		this.moveTextures = moveTextures;
		this.moveSpeed = moveSpeed;
		this.fightTextures = fightTextures;
		this.jumpHeight = jumpHeight;
		this.crawlTextures = crawlTextures;
		this.crawlSpeed = crawlSpeed;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1){
		
	}
	/**
	 * Handles character jump textures and movement
	 * @param threaded if you want the jump to operate concurrently to calling method
	 */
	public void jump(boolean threaded) {
		if(threaded) {
			new Thread(new Runnable() {
				public void run() {
					jump();
				}
			}).start();
		} else {
			jump();
		}
	}
	private void jump() {
		
	}
	
	/**
	 * Handles character crawl textures and movement
	 * @param threaded if you want the jump to operate concurrently to calling method
	 */
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
