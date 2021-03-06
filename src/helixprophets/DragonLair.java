package helixprophets;

import helixprophets.Level;
import helixprophets.beings.monsters.Boss;
import helixprophets.beings.monsters.Dragon;
import helixprophets.beings.monsters.Lich;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class DragonLair extends Level {
	
	private Dragon dragon = new Dragon();
	private String will;

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		layer=0;
		Map = new TiledMap("res/dragon lair.tmx","res");
		super.mapsetup();
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
		arg2.drawString("Dragon HEALTH: " + dragon.getHealth(), camx+564, camy+460);
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 11;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(attackzone[xcollide][ycrawl]==true && dragon.getAttackStatus()==true){
				reset();
				dragon.regen();
			}
		if(bosslocation[(xcollide*64+128)/64][ymid]==true && attacking==true && player.getSorcery()==false){
				dragon.reducehealth();
			}
		if(bosslocation[arrowx/64][ymid]==true && player.getRogueish()==true){
				dragon.reducehealthshot();
	}

		if(arg0.getInput().isKeyPressed(Keyboard.KEY_SPACE)==true && player.getSorcery()==true){
			dragon.cycle(arg2);
		}
	
		if(arg0.getInput().isKeyPressed(Keyboard.KEY_LCONTROL)==true){
		if(classchangecount==1)
		{
		player.changeRogue(player);
		classchangecount+=1;
		}
		else if(classchangecount==2)
		{
			player.changeMage(player);
			classchangecount+=1;
		}
		else if(classchangecount==3)
		{
			player.changeFighter(player);
			classchangecount=1;
		}
		}
		if(dragon.getHealth()<=0){
			game.enterState(12);
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