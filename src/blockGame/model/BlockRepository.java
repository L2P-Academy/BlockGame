// Tobias & Yakup

package blockGame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory repository for all available BlockModel instances.
 * Provides methods to retrieve blocks by ID or get the full list.
 * 
 * @author Tobias, Yakup
 */
public class BlockRepository {

	// templates for all existing Blocks (excluding Items and Tools)
    private static final List<BlockModel> BLOCKS = new ArrayList<>();

    static {
        BLOCKS.add(new BlockModel(0,  "Block", "Air",     0, 0, 0, "/res/img/textures/tile_air_72px.png"));
        BLOCKS.add(new BlockModel(1,  "Block", "Dirt",    0, 0, 0, "/res/img/textures/tile_dirt_72px.png"));
        BLOCKS.add(new BlockModel(2,  "Block", "Stone",   1, 0, 0, "/res/img/textures/tile_stone_72px.png"));
        BLOCKS.add(new BlockModel(3,  "Block", "Sand",    0, 0, 0, "/res/img/textures/tile_sand_72px.png"));
        BLOCKS.add(new BlockModel(4,  "Block", "Coal",    1, 0, 0, "/res/img/textures/tile_coal_72px.png"));
        BLOCKS.add(new BlockModel(5,  "Block", "Iron",    2, 0, 0, "/res/img/textures/tile_iron_72px.png"));
        BLOCKS.add(new BlockModel(6,  "Block", "Copper",  2, 0, 0, "/res/img/textures/tile_copper_72px.png"));
        BLOCKS.add(new BlockModel(7,  "Block", "Gold",    2, 0, 0, "/res/img/textures/tile_gold_72px.png"));
        BLOCKS.add(new BlockModel(8,  "Block", "Diamond", 3, 0, 0, "/res/img/textures/tile_diamond_72px.png"));
        BLOCKS.add(new BlockModel(9,  "Block", "Clay",    0, 0, 0, "/res/img/textures/tile_clay_72px.png"));
        BLOCKS.add(new BlockModel(10, "Block", "Water",   0, 0, 0, "/res/img/textures/tile_water_72px.png"));
        BLOCKS.add(new BlockModel(11, "Block", "Wood",    0, 0, 0, "/res/img/textures/tile_wood_72px.png"));
        BLOCKS.add(new BlockModel(12, "Block", "Leaf",    0, 0, 0, "/res/img/textures/tile_leaf_72px.png"));
    }

    /**
     * Returns a read-only list of all block models.
     * @return list containing every BlockModel in the repository
     */
    public static List<BlockModel> getAllBlocks() {
        return BLOCKS;
    }

    /**
     * Searches for a block by its unique identifier.
     * @param id unique ID of the block
     * @return matching BlockModel, or null if no block with the given ID exists
     */
    public static BlockModel getBlockByID(int id) {
        return BLOCKS.stream()
                     .filter(b -> b.getId() == id)
                     .findFirst()
                     .orElse(null);
    }
}


