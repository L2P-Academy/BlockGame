// Kristian

package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class CraftedBlockRepository {
	private static final List<CraftedBlockModel> BLOCKS = new ArrayList<>();
	
	static {
		BLOCKS.add(new CraftedBlockModel(201, "Crafted Block", "Wood", 0, 0, 0, "res/img/textures/wood")); 
		BLOCKS.add(new CraftedBlockModel(202, "Crafted Block", "Leaf", 0, 0, 0, "res/img/textures/leaf"));
		BLOCKS.add(new CraftedBlockModel(203, "Crafted Block", "Brick", 0, 0, 0, "res/img/textures/brick"));
		BLOCKS.add(new CraftedBlockModel(204, "Crafted Block", "Glas", 0, 0, 0, "res/img/textures/glas"));
	}
	
	public static List<CraftedBlockModel> getAllBlocks() {
		return BLOCKS;
	}
	
	public static CraftedBlockModel getCraftedBlockByID(int id) {
		return BLOCKS.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	}
}
