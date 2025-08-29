// Adrian

package blockGame.model;

public class ItemModel {
	
	//Contains Information about blocks and their parameters.
	//attributes
	private int id;
	private String itemTyp;
	private String itemName; 
	private int tier;
	private String textureImagePath;

	/** 
	 * Constructor to initialize an item
	 * @param id The Number/ID for Blocks of the Block-Repository
	 * @param itemTyp Item_Type defines the Type of item. (Blocks)
	 * @param itemName Name of Blocks, "Coal" for example.
	 * @param tier Parameter which defines the required Tool by number. This numbers can be found in the Block-Repository
	 * @param textureImagePath The path of the image source
	 * @author Adrian
	 * 
	 */
	public ItemModel(int id, String itemTyp, String itemName, int tier,  String textureImagePath) {
		this.id = id;
		this.itemTyp = itemTyp;
		this.itemName = itemName;
		this.tier = tier; 
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ItemModel)) {
			return false;
		}
		ItemModel item = (ItemModel) o;
		return id == item.id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}

