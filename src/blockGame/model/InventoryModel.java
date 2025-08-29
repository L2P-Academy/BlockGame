// Abdulaziz & Mohamed

package blockGame.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InventoryModel {
	
	private Map<ItemModel, Integer> itemNumbersMap;
	
	// Konstruktor 
	public InventoryModel () {
		this.itemNumbersMap = new HashMap<>();
	}

		// Item hinzufügen
		public void addItem(ItemModel item, int amount) {
			itemNumbersMap.merge(item, amount, Integer::sum);			
		}

		public Map<ItemModel, Integer> getItemNumbersMap() {
			return Collections.unmodifiableMap(itemNumbersMap);
		}		
		
}