// Tobias & Yakup

package blockGame.model;

import javax.swing.ImageIcon;

public class BlockModel {
	
	//Attributes
	private String name;
	private String materialList;
	private int posX;
	private int posY;
	private ImageIcon texture;
	private boolean isDestructable;
	
	// Constructor
	public BlockModel(String name,
						String materialList,
						int posX,
						int posY,
						ImageIcon texture,
						boolean isDestructable) {
		this.name = name;
		this.materialList = materialList;
		this.posX = posX;
		this.posY = posY;
		this.texture = texture;
		this.isDestructable = isDestructable;
		
	}
	
	// Getter & Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterialList() {
		return materialList;
	}

	public void setMaterialList(String materialList) {
		this.materialList = materialList;
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

	public ImageIcon getTexture() {
		return texture;
	}

	public void setTexture(ImageIcon texture) {
		this.texture = texture;
	}

	public boolean isDestructable() {
		return isDestructable;
	}

	public void setDestructable(boolean isDestructable) {
		this.isDestructable = isDestructable;
	}
	

}
