package helixprophets;

import org.lwjgl.input.Keyboard;

public class Keybinds {
	private static class Key {
		public int value;
		private Key(int value) {
			this.value = value;
		}
		public static Key up = new Key(Keyboard.KEY_W);
		public static Key down = new Key(Keyboard.KEY_S);
		public static Key left = new Key(Keyboard.KEY_A);
		public static Key right = new Key(Keyboard.KEY_D);
		public static Key attack = new Key(Keyboard.KEY_SPACE);
	}
	
	public boolean getUp() { return getKeyState(Key.up);}
	public boolean getDown() { return getKeyState(Key.down);}
	public boolean getLeft() { return getKeyState(Key.left);}
	public boolean getRight() { return getKeyState(Key.right);}
	public boolean getAttack() { return getKeyState(Key.attack);}
	
	public boolean getKeyState (Key key) {
		return Keyboard.isKeyDown(key.value);
	}
}
