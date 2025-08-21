// Dirk
package blockGame;

public class GameState {
	public final int worldRows; // max Anzahl Zeilen
	public final int worldCols; // max Anzahl Spalten
	public final int playerRow; // Spielerposition (Zeile)
	public final int playerCol; // Spielerposition (Spalte)
	public final int blockId; // Typ-Id: Dirt, Stone, ...
	public final int[][] blockCoordinates;
	
	// Constructor
	public GameState(int worldRows, int worldCols, int blockId, int playerRow, 
			int playerCol, int[][] blockCoordinates) {
		this.worldRows = worldRows;
		this.worldCols = worldCols;
		this.playerRow = playerRow;
		this.playerCol = playerCol;
		this.blockId = blockId;
		this.blockCoordinates = blockCoordinates;
	}

	public int getWorldRows() {
		return worldRows;
	}

	public int getWorldCols() {
		return worldCols;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public int getPlayerCol() {
		return playerCol;
	}

	public int getBlockId() {
		return blockId;
	}

	public int[][] getBlockCoordinates() {
		return blockCoordinates;
	}
	
	
	
	

}
