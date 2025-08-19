// Kristian

package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class UniqueBlockRepository {
	private static final List<UniqueBlockModel> BLOCKS = new ArrayList<>();
	
	static {
		BLOCKS.add(new UniqueBlockModel(201, "Unique Block", "Wood", 0, null));
		BLOCKS.add(new UniqueBlockModel(202, "Unique Block", "Leaf", 0, null));
	}
	
	public static List<UniqueBlockModel> getAllBlocks() {
		return BLOCKS;
	}
	
	public static UniqueBlockModel getUniqueBlockByID(int id) {
		return BLOCKS.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	}
}
