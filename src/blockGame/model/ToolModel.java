// Kai E

package blockGame.model;

public class ToolModel extends ItemModel {
	
	/**
	 *  inherit from ItemModel
	 *  Tool Attributes
	 *  @author Kai E
	 */
	
	
	//attributes;
	private int durability;

	//Constructor
	public ToolModel(int id, String itemTyp, String itemName, int durability, int tier, String textureImagePath) {    
		super(id, itemTyp, itemName, tier, textureImagePath);
	    this.durability = durability;
	
	}

	//getter und setter
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
}

