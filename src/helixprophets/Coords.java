package helixprophets;

/**
 * Simple container for coords of an on screen object
 * @author Gustave Abel Michel III
 *
 */
public class Coords {
	private int x;
	private int y;
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incramentX(int x) {
		this.x += x;
	}
	
	public void incramentY(int y) {
		this.y += y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ProtectedCoords getProtectedCoords() {
		return new ProtectedCoords(x,y);
	}
	
	/**
	 * Allows Coords to be retrieved but not edited
	 * For passing into collision detection or something
	 * 
	 * @author Gustave Abel Michel III
	 */
	private class ProtectedCoords extends Coords {
		public ProtectedCoords(int x, int y) { super(x, y); }
		
		//Blanked out to keep for being used
		public void setCoords(int x, int y) {}
		public void setX(int x) {}
		public void setY(int y) {}
		public void incramentX(int x) {}
		public void incramentY(int y) {}
	}
}
