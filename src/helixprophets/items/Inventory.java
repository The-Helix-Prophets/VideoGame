package helixprophets.items;

import java.util.ArrayList;

public class Inventory {
	private static final int pageSize = 30;
	
	private ArrayList<Item> inventory;
	
	public Inventory() {
		inventory = new ArrayList<Item>();
	}
	
	/** TODO rewrite using exceptions
	 * Add Item to Inventory
	 * @param item Item to add
	 * @param index Place to add item
	 * @throws ItemExistsException Thrown if the intended slot is occupied already
	 */
	public void addItem(Item item, int index) throws InventoryException {
		if(inventory.get(index)!=null)
			throw new InventoryException("Slot is not Empty");
		inventory.set(index, item);
	}
	
	/** TODO rewrite using exceptions
	 * Remove Item from Inventory
	 * @param item Item to be removed
	 * @param index Place Item should be
	 * @throws InventoryException Thrown if the intended slot is unoccupied or the supplied item doesn't match the item in the slot
	 */
	public void removeItem(Item item, int index) throws InventoryException {
		if(inventory.get(index) == null)
			throw new InventoryException("Slot is already empty");
		if(item != inventory.get(index))
			throw new InventoryException("Item given doesn't match Item in Slot");
		inventory.set(index, null);
	}
	
	/** TODO rewrite using exceptions
	 * Move item in Inventory
	 * @param initIndex Slot the Item should be in
	 * @param newIndex Slot Item will end up in
	 * @throws InventoryException 
	 */
	public void moveItem(Item item, int initIndex, int newIndex) throws InventoryException {
		addItem(item, newIndex);
		try {
			removeItem(item, initIndex);
		} catch (InventoryException e) {
				removeItem(item, newIndex);
				throw new InventoryException(e.getMessage());
		}
	}
	
	/** TODO rewrite using exceptions
	 * Return single Item from Inventory
	 * @param index
	 * @return Item
	 * @throws InventoryException 
	 */
	public Item getItem(int index) throws InventoryException {
		if(inventory.get(index) != null)
			return inventory.get(index);
		throw new InventoryException("Slot is empty");
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Item[] getInventory(int page) {
		if(page==numPages())
			return (Item[]) inventory.subList(pageSize*(page-1), inventory.size()-1).toArray();
		return (Item[]) inventory.subList(pageSize*(page-1), (pageSize*(page-1))+pageSize-1).toArray();
	}
	
	public int numPages() {
		return (int) Math.ceil(inventory.size()/(double)pageSize);
	}
	
	private class InventoryException extends Exception {

		public InventoryException(String message) {
			super(message);
		}
	}
}
