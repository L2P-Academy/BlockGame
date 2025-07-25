// Kristian

package blockGame.model;

import java.util.List;
import javax.swing.ImageIcon;

public class RecipeModel {

    private String recipeName;
    private List<BlockModel> blocks;
    private List<Integer> blockCounts;

    // Konstruktor
    public RecipeModel(String recipeName, List<BlockModel> blocks, List<Integer> blockCounts) {
        this.recipeName = recipeName;
        this.blocks = blocks;
        this.blockCounts = blockCounts;
    }

    // Getter und Setter
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<BlockModel> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockModel> blocks) {
        this.blocks = blocks;
    }

    public List<Integer> getBlockCounts() {
        return blockCounts;
    }

    public void setBlockCounts(List<Integer> blockCounts) {
        this.blockCounts = blockCounts;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rezept: ").append(recipeName).append(" -> ");
        for (int i = 0; i < blocks.size(); i++) {
            builder.append(blockCounts.get(i)).append("x ").append(blocks.get(i).getName());
            if (i < blocks.size() - 1) builder.append(", ");
        }
        return builder.toString();
    }

    //Platzhalter-Blöcke
    public static final BlockModel HOLZ = new BlockModel("Holz", "Naturmaterial", 0, 0, null, true);
    public static final BlockModel EISEN = new BlockModel("Eisen", "Metall", 0, 0, null, true);
  
    RecipeModel schaufel = new RecipeModel("Schaufel", List.of(RecipeModel.HOLZ, RecipeModel.EISEN), List.of(1, 1));
    

}
