package helixprophets;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class LichLair extends Level {

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		layer=0;
		Map = new TiledMap("res/lich lair.tmx","res");
		super.mapsetup();
		super.init(arg0, arg1);
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	

}
