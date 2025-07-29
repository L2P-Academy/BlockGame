// Adrian

package blockGame.model;

public class ItemModel {
	
 //attributes
	private int id;
	private String block;
	private String textureImagePath;

//Constructor
public ItemModel(int id, String block, String textureImagePath) {
    this.id = id;
    this.block = block;
    this.textureImagePath = textureImagePath;
}


//Getter und Setter

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getBlock() {
    return block;
}

public void setBlock(String block) {
    this.block = block;
}

public String getTextureImagePath() {
    return textureImagePath;
}

public void setTextureImagePath(String textureImagePath) {
    this.textureImagePath = textureImagePath;
}


@Override
public String toString() {
    return "Item: " + block + " (ID: " + id + ", Texture: " + textureImagePath + ")";}


public static final ItemModel EISEN = new ItemModel(1, "Eisen", "textures/eisen.png");
public static final ItemModel HOLZ = new ItemModel(2, "Holz", "textures/holz.png");
public static final ItemModel ERDE = new ItemModel(3, "Erde", "textures/erde.png");
public static final ItemModel MOOS = new ItemModel(4, "Moos", "textures/moos.png");
public static final ItemModel BETON = new ItemModel(5, "Beton", "textures/beton.png");}
    
