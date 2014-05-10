package helixprophets;

import org.lwjgl.input.Keyboard;

public class Keybinds {
	public static class Key {
		public final int value;
		
		private Key(int value) {
			this.value = value;
		}
		public static final Key up = new Key(Keyboard.KEY_W);
		public static final Key down = new Key(Keyboard.KEY_S);
		public static final Key left = new Key(Keyboard.KEY_A);
		public static final Key right = new Key(Keyboard.KEY_D);
		public static final Key attack = new Key(Keyboard.KEY_SPACE);
		public static final Key change = new Key(Keyboard.KEY_LCONTROL);
	}
	
	public boolean getUp() { return getKeyState(Key.up);}
	public boolean getDown() { return getKeyState(Key.down);}
	public boolean getLeft() { return getKeyState(Key.left);}
	public boolean getRight() { return getKeyState(Key.right);}
	public boolean getAttack() { return getKeyState(Key.attack);}
	public boolean getChange() { return getKeyState(Key.change);}
	
	public boolean getKeyState (Key key) {
		return getRawKeyState(key.value);
	}
	
	public boolean getRawKeyState (int key) {
		return Keyboard.isKeyDown(key);
	}
}
