// Kai E

package blockGame.model;

/**
 *  Inherits from ItemModel, extended with durability
 *  @author Kai E
 */
public class ToolModel extends ItemModel {
	

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

