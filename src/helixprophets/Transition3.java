package helixprophets;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Transition3 extends BasicGameState{
private Image transition;
private int delay=15000;
private int elapsedtime;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		transition = new Image("res/transition3.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		transition.draw();
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		elapsedtime+=arg2;
		if(elapsedtime>=delay){
			arg1.enterState(arg1.getCurrentStateID()+1);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

}