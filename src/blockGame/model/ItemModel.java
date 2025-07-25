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


}
