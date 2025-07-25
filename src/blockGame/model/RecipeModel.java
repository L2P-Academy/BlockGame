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
    public static final BlockModel WASSER = new BlockModel("Wasser", "Naturmaterial", 0, 0, null, true);
    public static final BlockModel KRÄUTER = new BlockModel("Kräuter", "Naturmaterial", 0, 0, null, true);
    public static final BlockModel ROHR = new BlockModel("Rohr", "Metall", 0, 0, null, true);
    public static final BlockModel BOHNEN = new BlockModel("Bohnen", "Naturmaterial", 0, 0, null, true);
    public static final BlockModel ZAHNRAD = new BlockModel("Zahnrad", "Metall", 0, 0, null, true);
    public static final BlockModel MOOS = new BlockModel("Moos", "Naturmaterial", 0, 0, null, true);
    public static final BlockModel FLEISCH = new BlockModel("Fleisch", "Essen", 0, 0, null, true);
    public static final BlockModel SCHÜSSEL = new BlockModel("Schüssel", "null", 0, 0, null, true);
    
  
    RecipeModel schaufel = new RecipeModel("Schaufel", List.of(RecipeModel.HOLZ, RecipeModel.EISEN), List.of(1, 1));
    RecipeModel messer = new RecipeModel("Messer", List.of(RecipeModel.HOLZ, RecipeModel.EISEN), List.of(1, 1));
    RecipeModel zahnrad = new RecipeModel("Zahnrad", List.of(RecipeModel.EISEN), List.of(1));
    RecipeModel schüssel = new RecipeModel("Schüssel", List.of(RecipeModel.HOLZ), List.of(1));
    RecipeModel rohr = new RecipeModel("Rohr", List.of(RecipeModel.EISEN), List.of(1));
    RecipeModel bohrer = new RecipeModel("Bohrer", List.of(RecipeModel.ROHR, RecipeModel.EISEN, RecipeModel.ZAHNRAD), List.of(1, 1, 1));
    RecipeModel dosenöffner = new RecipeModel("Dosenöffner", List.of(RecipeModel.EISEN), List.of(1));
    RecipeModel säge = new RecipeModel("Säge", List.of(RecipeModel.HOLZ, RecipeModel.EISEN), List.of(1, 1));
    RecipeModel healthPotion = new RecipeModel("Health-Potion", List.of(RecipeModel.WASSER, RecipeModel.KRÄUTER, RecipeModel.MOOS), List.of(1, 1, 1));
    RecipeModel eintopf = new RecipeModel("Eintopf", List.of(RecipeModel.FLEISCH, RecipeModel.BOHNEN, RecipeModel.SCHÜSSEL), List.of(1, 1, 1));
    
    
    

}
