package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class BlockRepository {
	private static final List<BlockModel> BLOCKS = new ArrayList<>();
	
	static {					
		BLOCKS.add(new BlockModel(0, "Block" , "Air", 0, 0, 0, "/res/img/textures/tile_air_64px.png"));
		BLOCKS.add(new BlockModel(1, "Block" , "Dirt", 0, 0, 0, "/res/img/textures/tile_dirt_64px.png"));
		BLOCKS.add(new BlockModel(2, "Block" , "Stone", 1, 0, 0, "/res/img/textures/tile_stone_64px.png"));
		BLOCKS.add(new BlockModel(3, "Block" , "Sand", 0, 0, 0, "/res/img/textures/tile_sand_64px.png"));
		BLOCKS.add(new BlockModel(4, "Block" , "Coal", 1, 0, 0, "/res/img/textures/tile_coal_64px.png"));
		BLOCKS.add(new BlockModel(5, "Block" , "Iron", 2, 0, 0, "/res/img/textures/tile_iron_64px.png"));
		BLOCKS.add(new BlockModel(6, "Block" , "Copper", 2, 0, 0, "/res/img/textures/tile_copper_64px.png"));
		BLOCKS.add(new BlockModel(7, "Block" , "Gold", 2, 0, 0, "/res/img/textures/tile_gold_64px.png"));
		BLOCKS.add(new BlockModel(8, "Block" , "Diamond", 3, 0, 0, "/res/img/textures/tile_diamond_64px.png"));
		BLOCKS.add(new BlockModel(9, "Block" , "Clay", 0, 0, 0, "/res/img/textures/tile_clay_64px.png"));
		BLOCKS.add(new BlockModel(10, "Block" , "Water", 0, 0, 0, "/res/img/textures/tile_water_64px.png"));
		BLOCKS.add(new BlockModel(11, "Block" , "Wood", 0, 0, 0, "/res/img/textures/tile_wood_64px.png"));
		BLOCKS.add(new BlockModel(12, "Block" , "Leaf", 0, 0, 0, "/res/img/textures/tile_leaf_64px.png"));
	}

	public static List<BlockModel> getAllBlocks() {
		return BLOCKS;
	}
	public static BlockModel getBlockByID(int id) {
		return BLOCKS.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	}
}
