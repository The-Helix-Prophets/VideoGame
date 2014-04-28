package helixprophets.items;

import java.util.ArrayList;

public class Inventory {
	private Item[] inventory;
	
	public Inventory(int size) {
		inventory = new Item[size];
	}
	
	/** TODO rewrite using exceptions
	 * Add Item to Inventory
	 * @param item Item to add
	 * @param slot Place to add item
	 * @throws ItemExistsException Thrown if the intended slot is occupied already
	 */
	public void addItem(Item item, int slot) throws InventoryException {
		if(inventory[slot]!=null)
			throw new InventoryException("Slot is not Empty");
		inventory[slot]=item;
	}
	
	/** TODO rewrite using exceptions
	 * Remove Item from Inventory
	 * @param item Item to be removed
	 * @param slot Place Item should be
	 * @throws InventoryException Thrown if the intended slot is unoccupied or the supplied item doesn't match the item in the slot
	 */
	public void removeItem(Item item, int slot) throws InventoryException {
		if(inventory[slot] == null)
			throw new InventoryException("Slot is already empty");
		if(item != inventory[slot])
			throw new InventoryException("Item given doesn't match Item in Slot");
		inventory[slot]=null;
	}
	
	/** TODO rewrite using exceptions
	 * Move item in Inventory
	 * @param initSlot Slot the Item should be in
	 * @param newSlot Slot Item will end up in
	 * @throws InventoryException 
	 */
	public void moveItem(Item item, int initSlot, int newSlot) throws InventoryException {
		addItem(item, newSlot);
		try {
			removeItem(item, initSlot);
		} catch (InventoryException e) {
				removeItem(item, newSlot);
				throw new InventoryException(e.getMessage());
		}
	}
	
	/** TODO rewrite using exceptions
	 * Return single Item from Inventory
	 * @param slot
	 * @return
	 */
	public Item getItem(int slot) {
		if(inventory[slot] != null)
			return inventory[slot];
		return null;
	}
	/**
	 * Return entire Inventory Array
	 * @return
	 */
	public Item[] getInventory() {
		return inventory;
<<<<<<< HEAD
	}
	
	private class InventoryException extends Exception {

		public InventoryException(String message) {
			super(message);
		}
=======
>>>>>>> parent of cce9e8c... Inventory currently broken, but re-added .classpath, this may not go
	}
}
