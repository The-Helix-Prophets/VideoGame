package helixprophets.beings.monsters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Cockatrice extends Boss {
	public void draw(int x, int y) throws SlickException{
		bossattack= new Image("res/cockatrice attack.png");
		bossvulner = new Image("res/cockatrice vulner.png");
		bossshield = new Image("res/cockatrice shielded.png");
		super.draw(x, y);
	}

	public void cycle(int time){
		timepassed+=time;
		if(timepassed>=1000){
			shielded=false;
		}
		if(timepassed>=3000){
			attack=true;
		}
		if(timepassed>=3500){
			shielded=true;
			attack=false;
			timepassed=0;
		}
}
}