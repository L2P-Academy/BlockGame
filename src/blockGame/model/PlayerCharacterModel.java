// Rene

package blockGame.model;

import java.util.ArrayList;
import java.util.List;


public class PlayerCharacterModel {
//attributes
	private int playerId;
	private String playerName;
	private int positionX;
	private int positionY;
	private ArrayList<ItemModel> inventoryList;
	
//Konstruktoren
	public PlayerCharacterModel(int playerId, String playerName, int positionX, int positionY, ArrayList inventoryList) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.positionX = positionX;
		this.positionY = positionY;
		this.inventoryList = inventoryList;
	} 
   		
	
	// Getter & Setter 
	public int getPlayerId() {
		return playerId;
	}

	

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
		
		}


	public ArrayList getInventoryList() {
		return inventoryList;
	}


	public void setInventoryList(ArrayList inventoryList) {
		this.inventoryList = inventoryList;
	}
	

	

	
}
