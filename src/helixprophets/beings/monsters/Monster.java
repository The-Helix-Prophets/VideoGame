package helixprophets.beings.monsters;

import helixprophets.Coords;
import helixprophets.items.Inventory;

import org.newdawn.slick.opengl.Texture;

public abstract class Monster {
	private Texture[] texture;
	
	private int moveSpeed;
	private int crawlSpeed;
	private int jumpHeight;
	
	private Coords coords;
	
	private int health; //How did I forget that?
	
	private static Inventory inventory = new Inventory();
}
