package helixprophets;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Level2 extends Level {



	@Override

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Map = new TiledMap("res/level2.tmx","res");
		layer = 0;
		camy= -3*45;
		super.mapsetup();
		super.init(arg0, arg1);
	}
	
	public void reset() {
		direction = true;
		x = 128;
		y = 0;
		camx = 0;
		camy = -3*45;
	}
	

	@Override
	public int getID() {
		return 3;
	}

}
