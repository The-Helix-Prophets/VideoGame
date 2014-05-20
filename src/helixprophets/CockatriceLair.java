package helixprophets;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import helixprophets.Level;
import helixprophets.beings.monsters.Cockatrice;

public class CockatriceLair extends Level {
	private Cockatrice dragon = new Cockatrice();


	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		layer=0;
		Map = new TiledMap("res/cockatrice lair.tmx","res");
		super.mapsetup();
		camy=-4*45;
		super.init(arg0, arg1);
	}
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		super.render(arg0, arg1, arg2);
		dragon.draw(camx, camy);
		if(dragon.getHealth()>=5000){
			arg2.setColor(Color.green);
		}
		else if(dragon.getHealth()>=2500){
			arg2.setColor(Color.yellow);
		}
		else if(dragon.getHealth()>=0){
			arg2.setColor(Color.red);
		}
		arg2.drawString("Cockatrice HEALTH: " + dragon.getHealth(), camx+564, camy+360);
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 7;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		dragon.cycle(arg2);
		if(attackzone[xcollide][ycrawl]==true && dragon.getAttackStatus()==true){
			reset();
			dragon.regen();
		}
		if(bosslocation[(xcollide*64+128)/64][ymid]==true && attacking==true){
			dragon.reducehealth();
		}
		if(dragon.getHealth()<=0){
			game.enterState(8);
		}
		super.update(arg0, arg1, arg2);
		
	}

	public void reset() {
		direction = true;
		x = 128;
		y = 100;
		camx = -64;
		camy = -6*45;
	}
	
	

}
