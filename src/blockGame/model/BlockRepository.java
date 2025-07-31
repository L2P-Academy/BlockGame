package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class BlockRepository {
	private static final List<BlockModel> BLOCKS = new ArrayList<>();
	
	static {
		BLOCKS.add(new BlockModel(1, "Dirt", 0, 0, 0, "/res/img/textures/tile_dirt_64px.png"));
		BLOCKS.add(new BlockModel(2, "Stone", 1, 0, 0, "/res/img/textures/tile_stone_64px.png"));
		BLOCKS.add(new BlockModel(3, "Sand", 0, 0, 0, "/res/img/textures/tile_sand_64px.png"));
		BLOCKS.add(new BlockModel(4, "Coal", 1, 0, 0, "/res/img/textures/tile_coal_64px.png"));
		BLOCKS.add(new BlockModel(5, "Iron", 2, 0, 0, "/res/img/textures/tile_iron_64px.png"));
		BLOCKS.add(new BlockModel(6, "Copper", 2, 0, 0, "/res/img/textures/tile_copper_64px.png"));
		BLOCKS.add(new BlockModel(7, "Gold", 2, 0, 0, "/res/img/textures/tile_gold_64px.png"));
		BLOCKS.add(new BlockModel(8, "Diamond", 3, 0, 0, "/res/img/textures/tile_diamond_64px.png"));
		BLOCKS.add(new BlockModel(9, "Clay", 0, 0, 0, "/res/img/textures/tile_clay_64px.png"));
		BLOCKS.add(new BlockModel(10, "Water", 0, 0, 0, "/res/img/textures/tile_water_64px.png"));
	}

	public static List<BlockModel> getAllBlocks() {
		return BLOCKS;
	}
	public static BlockModel getBlockByID(int id) {
		return BLOCKS.stream()
				.filter(b -> b.getID() == id)
				.findFirst()
				.orElse(null);
	}
}
