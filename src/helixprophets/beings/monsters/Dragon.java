package helixprophets.beings.monsters;

import helixprophets.beings.monsters.Boss;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dragon extends Boss {
	public void draw(int x, int y) throws SlickException{
		bossattack= new Image("res/dragon shielded.png");
		bossvulner = new Image("res/dragon vulner.png");
		bossshield = new Image("res/dragon shielded.png");
		super.draw(x, y);
	}
}
