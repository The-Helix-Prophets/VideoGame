package helixprophets;

import org.lwjgl.input.Keyboard;

public class ThreadedKeybinds extends Thread {
	private boolean up, 
					down, 
					left,
					right,
					attack;
	private long updateTime = 0;
	
	private boolean threadRun,
					threadEnabled;
	
	public ThreadedKeybinds(boolean start) {
		super("Keybinds");
		if(start)
			startThread();
	}
	
	public void startThread() {
		setRunning(true);
		setEnabled(true);
		this.start();
	}
	
	public void endThread() {
		setEnabled(false);
	}
	
	public void run() {
		while(isRunning()) {
			if(isEnabled()) {
				while(Keyboard.next()) {
					switch(Keyboard.getEventKey()) {
						case Keyboard.KEY_W:
							setUp(Keyboard.getEventKeyState());
							break;
						case Keyboard.KEY_A:
							setLeft(Keyboard.getEventKeyState());
							break;
						case Keyboard.KEY_S:
							setDown(Keyboard.getEventKeyState());
							break;
						case Keyboard.KEY_D:
							setRight(Keyboard.getEventKeyState());
							break;
						case Keyboard.KEY_SPACE:
							setAttack(Keyboard.getEventKeyState());
							break;
						default:
							break;
					}
					setTime();
				}
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //Slight Speed Limiter
		}
	}
	
	public void update() {
		switch(Keyboard.getEventKey()) {
			case Keyboard.KEY_W:
				setUp(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_A:
				setLeft(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_S:
				setDown(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_D:
				setRight(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_SPACE:
				setAttack(Keyboard.getEventKeyState());
				break;
			default:
				break;
		}
		setTime();
	}
	
	public synchronized boolean isEnabled() { return threadEnabled;}
	public synchronized boolean isRunning() { return threadRun;}
	
	public synchronized void setEnabled(boolean state) { threadEnabled = state;}
	public synchronized void setRunning(boolean state) { threadRun = state;}
	
	private synchronized void setUp(boolean state) { up = state;}
	private synchronized void setDown(boolean state) { down = state;}
	private synchronized void setLeft(boolean state) { left = state;}
	private synchronized void setRight(boolean state) { right = state;}
	private synchronized void setAttack(boolean state) { attack = state;}
	private synchronized void setTime() {updateTime = System.currentTimeMillis();}
	
	public synchronized boolean getUp() { return up;}
	public synchronized boolean getDown() { return down;}
	public synchronized boolean getLeft() { return left;}
	public synchronized boolean getRight() { return right;}
	public synchronized boolean getAttack() { return attack;}
	public synchronized long getTime() {return updateTime;}
}
