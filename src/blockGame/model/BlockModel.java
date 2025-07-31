// Tobias & Yakup

package blockGame.model;

import javax.swing.ImageIcon;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlockModel {
	
	// Attribute
	private final int ID;
	private String name;
	private int tier;
	private int posX;
	private int posY;
	private String texturePath;
	
	// Konstruktor
	 public BlockModel(int ID, String name, int tier, int posX, int posY, String texturePath) {
		 	this.ID = ID;
		 	this.name = name;
			this.tier = tier;
			this.posX = posX;
			this.posY = posY;
			this.texturePath = texturePath;
		}
	 
	 // Getter & Setter
	 public String getName() {
		return name;
	}

	
	public int getTier() {
		return tier;
	}

	
	public int getPosX() {
		return posX;
	}

	
	public int getPosY() {
		return posY;
	}

	
	public String getTexturePath() {
		return texturePath;
	}


	public int getID() {
		return ID;
	}
   
    } 