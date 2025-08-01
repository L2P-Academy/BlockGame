// Tobias & Yakup

package blockGame.model;



public class BlockModel extends ItemModel {
	
	// Attribute
	private int posX;
	private int posY;
	
	// Konstruktor
	public BlockModel(int id, String itemTyp, String itemName, int tier, int posX, int posY, String textureImagePath) {
		super(id, itemTyp, itemName, tier, textureImagePath); //
		this.posX = posX;
		this.posY = posY;
			
	}

	// Getter & Setter
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	} 
} 