// Kai E

package blockGame.model;

public class ToolModel extends ItemModel {

	 //attributes;
		private int durability;
		private int tier;

	//Constructor
	public ToolModel(int id, String itemTyp, String itemName, int durability, int tier, String textureImagePath) {    
		super(id, itemTyp, itemName, textureImagePath);
	    this.durability = durability;
	    this.tier = tier;
	
	}

	//getter und setter
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	

}
// quantity auf feste 1 für werkzeuge setzen, bei durabilitiy 0 werkzeug entvernen.
