package helixprophets.beings.monsters;

import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

public abstract class Boss implements Renderable {

	protected int bosshealth=10000;
	protected Image bossattack;
	protected Image bossshield;
	protected Image bossvulner;
	protected boolean shielded=true;
	protected boolean attack=false;
	protected int bossx=0;
	protected int bossy=0;
	protected int timepassed=0;

	
	
	public void draw(float x, float y) {
		if(attack==true){
			bossattack.draw(x, y);
		}
		else if(shielded==true){
			bossshield.draw(x,y);
		}
		else{
			bossvulner.draw(x,y);
		}
	}
	
	public void cycle(int time){
		timepassed+=time;
		if(timepassed>=1000){
			shielded=false;
		}
		if(timepassed>=2000){
			attack=true;
		}
		if(timepassed>=3000){
			shielded=true;
			attack=false;
			timepassed=0;
		}
		
		
	}
	public void reducehealth(){
		if(shielded==false){

			bosshealth-=10;
		}
	}

	public boolean getAttackStatus(){
		return attack;
	}
	
	public void regen(){
		bosshealth=10000;
	}
	
	public int getHealth(){
		return bosshealth;
	}
	}
