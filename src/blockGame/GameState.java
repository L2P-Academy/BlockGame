// Dirk
package blockGame;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GameState {
	// fields
	public final int worldRows; // max Anzahl Zeilen
	public final int worldCols; // max Anzahl Spalten
	public int playerRow; // Spielerposition (Zeile)
	public int playerCol; // Spielerposition (Spalte)
	public record Coord(int col, int row) {}; // compact Class, simplified coordinates
	private final Map<Coord, Integer> blocks = new HashMap<>(); // mapping Coordinates and BlockIds later
	private static GameState instance;
	private LocalDateTime lastSavedDate;
	
	public static GameState getInstance() {
		return instance;
	}
	
	// constructor
	public GameState(int worldRows, int worldCols, int playerRow, int playerCol) {
		instance = this;
		this.worldRows = worldRows;
		this.worldCols = worldCols;
		this.playerRow = playerRow;
		this.playerCol = playerCol;
		// TODO: change to saveGame-created timestamp
		this.lastSavedDate = LocalDateTime.now();
	}
	
	// getter/setter
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
	
	// block logic
	public Map<Coord, Integer> getBlocks() {
		return blocks;
	}
	
	public void putBlock(int col, int row, int blockId) {
		blocks.put(new Coord(col, row), blockId);
	}
	
	public Integer getBlock(int col, int row) {
		return blocks.get(new Coord(col, row));
	}
	
	// Time stamp
	public LocalDateTime getLastSavedDate() {
		return lastSavedDate;
	}

	public void setLastSavedDate(LocalDateTime lastSavedDate) {
		this.lastSavedDate = lastSavedDate;
	}
}
