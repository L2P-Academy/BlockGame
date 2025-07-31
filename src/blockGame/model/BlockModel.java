// Tobias & Yakup

package blockGame.model;

import javax.swing.ImageIcon;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlockModel {
	
	// Attribute
	private String name;
	private String materialList;
	private int posX;
	private int posY;
	private ImageIcon texture;
	private boolean isDestructable;
	
	// Konstructor
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

	// Getter und Setter
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
	
	// Hilfsmethode zum Laden der Textur
    private static ImageIcon loadTexture(String fileName) {
        return new ImageIcon(BlockModel.class
            .getResource("/res/img/textures/" + fileName + ".png"));
        
     // Statische Block-Instanzen
     public static final BlockModel DIRT    = new BlockModel("Dirt",    "Soil",   0, 0, loadTexture("dirt"),    true);
     public static final BlockModel STONE   = new BlockModel("Stone",   "Cobblestone", 0, 0, loadTexture("stone"),   true);
     public static final BlockModel COPPER  = new BlockModel("Copper",  "Ore",    0, 0, loadTexture("copper"),  true);
     public static final BlockModel IRON    = new BlockModel("Iron",    "Ore",    0, 0, loadTexture("iron"),    true);
     public static final BlockModel GOLD    = new BlockModel("Gold",    "Ore",    0, 0, loadTexture("gold"),    true);
     public static final BlockModel DIAMOND = new BlockModel("Diamond", "Ore",    0, 0, loadTexture("diamond"), true);
     public static final BlockModel CLAY    = new BlockModel("Clay",    "Soil",   0, 0, loadTexture("clay"),    true);
     public static final BlockModel WATER   = new BlockModel("Water",   "Liquid", 0, 0, loadTexture("water"),   false);
     public static final BlockModel SAND    = new BlockModel("Sand",    "Granule",0, 0, loadTexture("sand"),    true);
     public static final BlockModel COAL    = new BlockModel("Coal",    "Ore",    0, 0, loadTexture("coal"),    true);
        
     // Unveränderliche Liste aller Blöcke
     public static final List<BlockModel> ALL_BLOCKS =
         Collections.unmodifiableList(Arrays.asList(
             DIRT, STONE, COPPER, IRON, GOLD,
             DIAMOND, CLAY, WATER, SAND, COAL
         ));
    } 