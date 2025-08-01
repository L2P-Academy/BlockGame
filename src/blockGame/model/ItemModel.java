// Adrian

package blockGame.model;

public class ItemModel {
	
	//attributes
	private int id;
	private String itemTyp;
	private String itemName; 
	private int tier;
	private String textureImagePath;

	//Constructor
	public ItemModel(int id, String itemTyp, String itemName, int tier,  String textureImagePath) {
		this.id = id;
		this.itemTyp = itemTyp;
		this.itemName = itemName;
		this.tier = tier; //
		this.textureImagePath = textureImagePath;
	}


	//Getter und Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemTyp() {
		return itemTyp;
	}

	public void setItemTyp(String itemTyp) {
		this.itemTyp = itemTyp;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getTextureImagePath() {
		return textureImagePath;
	}

	public void setTextureImagePath(String textureImagePath) {
		this.textureImagePath = textureImagePath;
	}
}

