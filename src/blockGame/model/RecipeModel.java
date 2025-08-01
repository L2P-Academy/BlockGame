// Kristian

package blockGame.model;

import java.util.HashMap;
import java.util.Map;


public class RecipeModel {

	private ItemModel craftedItem;
	private Map<ItemModel, Integer> ingredients;
	
	// Konstruktor
	public RecipeModel(ItemModel craftedItem) {
		this.craftedItem = craftedItem;
		this.ingredients = new HashMap<>();
	}
	
	// Zutaten hinzufügen
	public void addIngredient(ItemModel item, int amount) {
		ingredients.put(item, amount);
	}
	
	// Getter & Setter
	public ItemModel getCraftedItem() {
		return craftedItem;
	}

	public void setCraftedItem(ItemModel craftedItem) {
		this.craftedItem = craftedItem;
	}

	public Map<ItemModel, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<ItemModel, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Rezept für: " + craftedItem.getItemName() + "\n");
		for (Map.Entry<ItemModel, Integer> entry : ingredients.entrySet()) {
			sb.append("- ").append(entry.getValue()).append("x ").append(entry.getKey().getItemName()).append("\n");
			
		}
		return sb.toString();
	}

	// Beispiel Recipe
	// RecipeModel pickaxeRecipe = new RecipeModel(ItemModel.PICKAXE);
	// pickaxeRecipe.addIngredient(ItemModel.IRON, 2);
	// pickaxeRecipe.addIngredient(ItemModel.WOOD, 1);
	
	}

