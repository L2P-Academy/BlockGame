// Kai E

package blockGame.model;

public class InventorySlotModel {

	//attributes
			private ItemModel item;
			private ToolModel tool;
			private int quantity;

		//Constructor
		public InventorySlotModel(ItemModel item, ToolModel tool, int quantity) {
		    this.item = item;
		    this.tool = tool;
		    this.quantity = quantity;
		    
		}

		public ItemModel getItem() {
			return item;
		}

		public void setItem(ItemModel item) {
			this.item = item;
		}

		public ToolModel getTool() {
			return tool;
		}

		public void setTool(ToolModel tool) {
			this.tool = tool;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
		
}
