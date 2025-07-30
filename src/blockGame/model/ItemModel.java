// Adrian

package blockGame.model;

public class ItemModel {
	
 //attributes
	private int id;
	private String itemTyp;
	private String itemName;
	private String textureImagePath;

//Constructor
public ItemModel(int id, String itemTyp, String itemName, String textureImagePath) {
    this.id = id;
    this.itemTyp = itemTyp;
    this.itemName = itemName;
    this.textureImagePath = textureImagePath;
}


//Getter und Setter

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}


public String getItemTyp() {
	return itemTyp;
}


public void setItemTyp(String itemTyp) {
	this.itemTyp = itemTyp;
}


public String getItemName() {
	return itemName;
}


public void setItemName(String itemName) {
	this.itemName = itemName;
}


public String getTextureImagePath() {
    return textureImagePath;
}


public void setTextureImagePath(String textureImagePath) {
    this.textureImagePath = textureImagePath;
}


@Override
public String toString() {
    return "Item: " + itemTyp + " (ID: " + id + ", Thing: " + itemTyp + ", Thingname: " + itemName + ", Texture: " + textureImagePath + ")";}


public static final ItemModel EISEN = new ItemModel(1, "Block", "Eisen", "textures/eisen.png");
public static final ItemModel HOLZ = new ItemModel(2, "Block", "Holz", "textures/holz.png");
public static final ItemModel ERDE = new ItemModel(3, "Block", "Erde", "textures/erde.png");
public static final ItemModel MOOS = new ItemModel(4, "Block", "Moos", "textures/moos.png");
public static final ItemModel BETON = new ItemModel(5, "Block", "Beton", "textures/beton.png");}
    
