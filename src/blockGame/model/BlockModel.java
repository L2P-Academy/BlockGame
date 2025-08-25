// Tobias & Yakup

package blockGame.model;

/**
 * Represents a block in the game occupying a specific position on the game field.
 * Extends ItemModel by adding X and Y coordinates.
 * 
 * @author Tobias, Yakup
 */
public class BlockModel extends ItemModel {

    // Attributes
    private int posX;
    private int posY;

    /**
     * Constructs a new BlockModel with the specified properties.
     * @param id               Unique identifier for the block
     * @param itemType         Type of the item (e.g., "Block")
     * @param itemName         Human-readable name of the block
     * @param tier             Quality level or rarity of the block
     * @param posX             X-coordinate of the block on the game field
     * @param posY             Y-coordinate of the block on the game field
     * @param textureImagePath Path to the texture image file for rendering
     */
    public BlockModel(int id, String itemType, String itemName, int tier, int posX, int posY, String textureImagePath) {
        super(id, itemType, itemName, tier, textureImagePath);
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Returns the current X-coordinate of the block.
     * @return X-coordinate as an integer
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the X-coordinate of the block.
     * @param posX New X-coordinate as an integer
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns the current Y-coordinate of the block.
     * @return Y-coordinate as an integer
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the Y-coordinate of the block.
     * @param posY New Y-coordinate as an integer
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}