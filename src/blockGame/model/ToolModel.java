// Kai E

package blockGame.model;

public class ToolModel {

	 //attributes
		private int id;
		private String tool;
		private String textureImagePath;
		private int durability;
		private String tier;

	//Constructor
	public ToolModel(int id, String tool, String textureImagePath, int durability, String tier) {
	    this.id = id;
	    this.tool = tool;
	    this.textureImagePath = textureImagePath;
	    this.durability = durability;
	    this.tier = tier;
	
	}

		// Getter und Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getTextureImagePath() {
		return textureImagePath;
	}

	public void setTextureImagePath(String textureImagePath) {
		this.textureImagePath = textureImagePath;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}
	
	@Override
	public String toString() {
	    return "Tool: " + tool + " (ID: " + id + ", Texture: " + textureImagePath + ")";}


	public static final ToolModel Holzspitzhake = new ToolModel(101, "Holzspitzhake", "textures/holzspitzhake.png", 30, "Tier 1");
	public static final ToolModel Steinspitzhake = new ToolModel(102, "Steinspitzhake", "textures/steinspitzhake.png", 60, "Tier 2");
	public static final ToolModel Holzaxt = new ToolModel(103, "Holzaxt", "textures/holzaxt.png", 30, "Tier 1");
	public static final ToolModel Steinaxt = new ToolModel(104, "Steinaxt", "textures/steinaxt.png", 60, "Tier 2");
	
}
