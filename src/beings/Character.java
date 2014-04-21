package beings;

import org.newdawn.slick.opengl.Texture;

public abstract class Character {
	private Texture[] moveTextures;
	private Texture[] fightTextures;
	private Texture[] crawlTextures;
	
	private int moveSpeed;
	private int crawlSpeed;
	private int jumpHeight;
	
	
	public Character(Texture[] moveTextures, Texture[] fightTextures, Texture[] crawlTextures, int moveSpeed, int crawlSpeed, int jumpHeight) {
		this.moveTextures = moveTextures;
		this.moveSpeed = moveSpeed;
		this.fightTextures = fightTextures;
		this.jumpHeight = jumpHeight;
		this.crawlTextures = crawlTextures;
		this.crawlSpeed = crawlSpeed;
	}
	
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
