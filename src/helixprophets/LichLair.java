package helixprophets;

import helixprophets.beings.monsters.Boss;
import helixprophets.beings.monsters.Lich;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class LichLair extends Level {
	
	private Lich lich = new Lich();
	private String will;

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		layer=0;
		Map = new TiledMap("res/lich lair.tmx","res");
		super.mapsetup();
		super.init(arg0, arg1);
	}
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		super.render(arg0, arg1, arg2);
		lich.draw(camx+564, camy+460);
		if(lich.getHealth()>=50){
			arg2.setColor(Color.green);
		}
		else if(lich.getHealth()>=25){
			arg2.setColor(Color.yellow);
		}
		else if(lich.getHealth()>=0){
			arg2.setColor(Color.red);
		}
		arg2.drawString("LICH HEALTH: " + lich.getHealth(), camx+564, camy+460);
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		lich.cycle(arg2);
		if(attackzone[xcollide][ymid]==true && lich.getAttackStatus()==true){
			reset();
			lich.regen();
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
