package blockGame.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
	
	private List<RecipeModel> recipes;
	
	public RecipeRepository() {
		this.recipes = new ArrayList<>();
	
	// Rezepte
		
	// Holzspitzhacke
	
	RecipeModel woodPickaxeRecipe = new RecipeModel(ToolRepository.getToolByID(101));
	woodPickaxeRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 3);
	this.addRecipe(woodPickaxeRecipe);
	
	// Steinspitzhacke
	
	RecipeModel stonePickaxeRecipe = new RecipeModel(ToolRepository.getToolByID(102));
	stonePickaxeRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	stonePickaxeRecipe.addIngredient(BlockRepository.getBlockByID(2), 2);
	this.addRecipe(stonePickaxeRecipe);
	
	// Eisenspitzhacke
	
	RecipeModel ironPickaxeRecipe = new RecipeModel(ToolRepository.getToolByID(103));
	ironPickaxeRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	ironPickaxeRecipe.addIngredient(BlockRepository.getBlockByID(5), 2);
	this.addRecipe(ironPickaxeRecipe);
	
	
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

