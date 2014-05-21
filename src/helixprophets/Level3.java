package helixprophets;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Level3 extends Level {

	

	@Override

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Map = new TiledMap("res/level 3.tmx","res");
		layer = 0;
		camy=-6*45;
		super.mapsetup();
		super.init(arg0, arg1);
	}
	
	public void reset() {
		direction = true;
		x = 128;
		y = 100;
		camx = 0;
		camy = -6*45;
	}
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

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

		super.update(arg0, arg1, arg2);
	}


	public int getID() {
		// TODO Auto-generated method stub
		return 9;
	}

}
