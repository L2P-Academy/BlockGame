// Kristian

package blockGame.model;


public class UniqueBlockModel extends ItemModel{
	
	// Attribute
	private int posX;
	private int posY;

	// Konstruktor
	public UniqueBlockModel(int id, String itemTyp, String itemName, int tier, String textureImagePath) {
		super(id, itemTyp, itemName, tier, textureImagePath);
		

	}

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
