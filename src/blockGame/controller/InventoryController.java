// Kai E

package blockGame.controller;

import blockGame.model.InventoryModel;
import blockGame.model.ItemModel;

public class InventoryController {
	private static InventoryController instance;
	private InventoryModel inventory;
	
	public InventoryController() {
		inventory = new InventoryModel();
	}
	
	public static InventoryController getInstance() {
		if (instance == null) {
			instance = new InventoryController();
		}
		return instance;
	}
	
	public InventoryModel getInventory() {
		return inventory;
	}
	
	public void addItem(ItemModel item, int amount) {
		inventory.addItem(item, amount);
	}
}
