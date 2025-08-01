package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
	
	private List<RecipeModel> recipes;
	
	public RecipeRepository() {
		this.recipes = new ArrayList<>();
	}
	
	public void addRecipe(RecipeModel recipe) {
		recipes.add(recipe);
		
	}
	
	public List<RecipeModel> getAllRecipes() {
		return new ArrayList<>(recipes);
			
	}
	
	public RecipeModel findByCraftedItem(String itemName) {
		for (RecipeModel recipe : recipes) {
			if (recipe.getCraftedItem().getItemName().equalsIgnoreCase(itemName)) {
				return recipe;
				
			}
		}
		return null;
	}

}
