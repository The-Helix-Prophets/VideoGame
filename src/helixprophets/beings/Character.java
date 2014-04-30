package helixprophets.beings;

import helixprophets.Coords;
import helixprophets.items.Inventory;

import org.newdawn.slick.opengl.Texture;

public abstract class Character {
	private Texture[] moveTextures;
	private Texture[] fightTextures;
	private Texture[] crawlTextures;
	
	private int moveSpeed;
	private int crawlSpeed;
	private int jumpHeight;
	
	private Coords coords;
	
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
