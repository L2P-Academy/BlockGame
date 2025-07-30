// Kai E

package blockGame.model;

public class ToolModel extends ItemModel {

	 //attributes;
		private int durability;
		private int tier;

	//Constructor
	public ToolModel(int id, String itemTyp, String itemName, String textureImagePath, int durability, int tier) {    
		super(id, itemTyp, itemName, textureImagePath);
	    this.durability = durability;
	    this.tier = tier;
	
	}

	
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
	
	@Override
	public String toString() {
	    return "Item: " + getItemTyp() + " (ID: " + getId() + ", Thingname: " + getItemName() + ", Texture: " + getTextureImagePath() + ", Durability: " + durability + ", Tier: " + tier + ")";} 


	public static final ToolModel Holzspitzhake = new ToolModel(101, "Werkzeug", "Holzspitzhacke", "textures/holzspitzhake.png", 30, 1);
	public static final ToolModel Steinspitzhake = new ToolModel(102, "Werkzeug", "Steinspitzhacke", "textures/steinspitzhake.png", 60, 2);
	//public static final ToolModel Holzaxt = new ToolModel(103, "Werkzeug", "Holzaxt", "textures/holzaxt.png", 30, 1);
	//public static final ToolModel Steinaxt = new ToolModel(104, "Werkzeug", "Steinaxt", "textures/steinaxt.png", 60, 2);
	
}


// quantity auf feste 1 für werkzeuge setzen, bei durabilitiy 0 werkzeug entvernen.
