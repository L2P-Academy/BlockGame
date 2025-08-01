// Kai E

package blockGame.model;

public class InventorySlotModel {

	//attributes
			private ItemModel item = null;
			// private ToolModel
			private int quantity;
			private static final int MAX_STACK_SIZE = 64;

			
			
		//Constructor
		public InventorySlotModel() {
			this.item = null;
			this.quantity = 0;
		}


		//getter und setter
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
			this.quantity = quantity;
		}



		public static int getMaxStackSize() {
			return MAX_STACK_SIZE;
		}		
}		   
