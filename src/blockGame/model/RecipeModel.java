// Kristian

package blockGame.model;

import java.util.ArrayList;

public class RecipeModel {

	//attributes
    private String recipeName;
    private int blockId;
	private int numberOfBlocks;
	
	
	//Konstruktoren
	public RecipeModel(String recipeName, int blockID, int numberOfBlocks) {
		this.recipeName = recipeName;
		this.blockId = blockID;
		this.numberOfBlocks = numberOfBlocks;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public int getBlockId() {
		return blockId;
	}


	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}


	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}


	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}
	
	
}