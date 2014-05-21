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
	public void cycle(int time){
		timepassed=0;
		timepassed+=time;
		shielded=false;
		attack=false;
		if(timepassed>=2000){
			shielded=true;
			attack=true;
		}
	}
}
