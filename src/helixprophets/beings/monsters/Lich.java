package helixprophets.beings.monsters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Lich extends Boss {
public void draw(int x, int y) throws SlickException{
	bossattack= new Image("res/lich attacks.png");
	bossvulner = new Image("res/lich readies attack.png");
	bossshield = new Image("res/lich.png");
	super.draw(x, y);
}
}
