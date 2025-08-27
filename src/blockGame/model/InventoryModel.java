// Abdulaziz & Mohamed

package blockGame.model;

import java.util.HashMap;
import java.util.Map;

public class InventoryModel {
	
	private ItemModel InventoryModel;
	private Map<ItemModel, Integer> itemNumbersMap;

	
	// Konstruktor 
	
	public InventoryModel (ItemModel InventoryModel) {
		this.InventoryModel = InventoryModel;
		this.itemNumbersMap = new HashMap<>();
	}

		// Item hinzufügen
		public void addItemNumber(ItemModel item, int amount) {
			itemNumbersMap.put(item, amount);
		} 
		
		
}