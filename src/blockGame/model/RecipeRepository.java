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
	
	// Holzschaufel
	
	RecipeModel woodShovelRecipe = new RecipeModel(ToolRepository.getToolByID(104));
	woodShovelRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 2);
	this.addRecipe(woodShovelRecipe);
	
	// Steinschaufel
	
	RecipeModel stoneShovelRecipe = new RecipeModel(ToolRepository.getToolByID(105));
	stoneShovelRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	stoneShovelRecipe.addIngredient(BlockRepository.getBlockByID(2), 1);
	this.addRecipe(stoneShovelRecipe);
	
	// Eisenschaufel
	
	RecipeModel ironShovelRecipe = new RecipeModel(ToolRepository.getToolByID(106));
	ironShovelRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	ironShovelRecipe.addIngredient(BlockRepository.getBlockByID(5), 1);
	this.addRecipe(ironShovelRecipe);
	
	// Ziegelstein (Brick)
	
	RecipeModel brickRecipe = new RecipeModel(UniqueBlockRepository.getUniqueBlockByID(203));
	brickRecipe.addIngredient(BlockRepository.getBlockByID(9), 2);
	this.addRecipe(brickRecipe);

	// Glas
	
	RecipeModel glasRecipe = new RecipeModel(UniqueBlockRepository.getUniqueBlockByID(204));
	glasRecipe.addIngredient(BlockRepository.getBlockByID(3), 1);
	this.addRecipe(glasRecipe);
	
	// Messer
	
	RecipeModel knifeRecipe = new RecipeModel(ToolRepository.getToolByID(107));
	knifeRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	knifeRecipe.addIngredient(BlockRepository.getBlockByID(5), 1);
	this.addRecipe(knifeRecipe);
	
	// Eisenschwert
	
	RecipeModel ironSwordRecipe = new RecipeModel(ToolRepository.getToolByID(108));
	ironSwordRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	ironSwordRecipe.addIngredient(BlockRepository.getBlockByID(5), 3);
	this.addRecipe(ironSwordRecipe);
	
	// Diamantschwert
	
	RecipeModel diamondSwordRecipe = new RecipeModel(ToolRepository.getToolByID(109));
	diamondSwordRecipe.addIngredient(UniqueBlockRepository.getUniqueBlockByID(201), 1);
	diamondSwordRecipe.addIngredient(BlockRepository.getBlockByID(8), 3);
	this.addRecipe(diamondSwordRecipe);
	
	
	
		// Getter & Setter
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

