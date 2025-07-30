// Kai E

package blockGame.model;

public class InventorySlotModel {

	//attributes
			private ItemModel item;
			// private ToolModel
			private int quantity;

		//Constructor
		public InventorySlotModel(ItemModel item, int quantity) {
		    this.item = item;
		    // this.tool =toll
		    this.quantity = quantity;
		    
		    // Wenn Menge 0 Item entfernen
		    if (this.quantity ==0) {
		    	this.item = null;
		    }
		    
		
		}

		public ItemModel getItem() {
			return item;
		}

		public void setItem(ItemModel item) {
			this.item = item;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			if (quantity < 0) {
				throw new IllegalArgumentException("Menge darf nicht negativ sein.");
				
			}
			
		
			
		}
		
		
		
}
