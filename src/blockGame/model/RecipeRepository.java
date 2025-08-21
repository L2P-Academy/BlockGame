// Kristian

package blockGame.model;

import java.util.ArrayList;
import java.util.List;


public class RecipeRepository {
	
	private List<RecipeModel> recipes;
	
	private static final List<ToolModel> TOOLS = new ArrayList<>();
	
	// Tools
	
	static {
		TOOLS.add(new ToolModel(101,"Werkzeug", "Holzspitzhake", 30, 1, "/res/img/textures/pickaxe.png"));
		TOOLS.add(new ToolModel(102,"Werkzeug", "Steinspitzhake", 30, 2, "/res/img/textures/pickaxe.png"));
		TOOLS.add(new ToolModel(103,"Werkzeug", "Eisenspitzhake", 30, 3, "/res/img/textures/pickaxe.png"));
		TOOLS.add(new ToolModel(104,"Werkzeug", "Holzschaufel", 30, 1, "/res/img/textures/shovel.png"));
		TOOLS.add(new ToolModel(105,"Werkzeug", "Steinschaufel", 30, 2, "/res/img/textures/shovel.png"));               // TODO -> Texturen hinzufügen
		TOOLS.add(new ToolModel(106,"Werkzeug", "Eisenschaufel", 30, 3, "/res/img/textures/shovel.png"));
		TOOLS.add(new ToolModel(107,"Waffe", "Messer", 30, 2, "/res/img/textures/knife"));
		TOOLS.add(new ToolModel(108,"Waffe", "Eisenschwert", 30, 3, "/res/img/textures/ironSword"));
		TOOLS.add(new ToolModel(109,"Waffe", "Diamantschwert", 50, 4, "/res/img/textures/diamondSword"));
	}
	
	// Crafted Blocks

	private static final List<CraftedBlockModel> CRAFTEDBLOCKS = new ArrayList<>();
	
	static {
		
		CRAFTEDBLOCKS.add(new CraftedBlockModel(201, "Crafted Block", "Brick", 0, 0, 0, "res/img/textures/brick"));
		CRAFTEDBLOCKS.add(new CraftedBlockModel(202, "Crafted Block", "Glas", 0, 0, 0, "res/img/textures/glas"));
	}
	
	public RecipeRepository() {
		this.recipes = new ArrayList<>();
	
	// Rezepte
		
	// Holzspitzhacke
	
	RecipeModel woodPickaxeRecipe = new RecipeModel(RecipeRepository.getToolByID(101));
	woodPickaxeRecipe.addIngredient(BlockRepository.getBlockByID(11), 3);
	this.addRecipe(woodPickaxeRecipe);
	
	// Steinspitzhacke
	
	RecipeModel stonePickaxeRecipe = new RecipeModel(RecipeRepository.getToolByID(102));
	stonePickaxeRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	stonePickaxeRecipe.addIngredient(BlockRepository.getBlockByID(2), 2);
	this.addRecipe(stonePickaxeRecipe);
	
	// Eisenspitzhacke
	
	RecipeModel ironPickaxeRecipe = new RecipeModel(RecipeRepository.getToolByID(103));
	ironPickaxeRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	ironPickaxeRecipe.addIngredient(BlockRepository.getBlockByID(5), 2);
	this.addRecipe(ironPickaxeRecipe);
	
	// Holzschaufel
	
	RecipeModel woodShovelRecipe = new RecipeModel(RecipeRepository.getToolByID(104));
	woodShovelRecipe.addIngredient(BlockRepository.getBlockByID(11), 2);
	this.addRecipe(woodShovelRecipe);
	
	// Steinschaufel
	
	RecipeModel stoneShovelRecipe = new RecipeModel(RecipeRepository.getToolByID(105));
	stoneShovelRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	stoneShovelRecipe.addIngredient(BlockRepository.getBlockByID(2), 1);
	this.addRecipe(stoneShovelRecipe);
	
	// Eisenschaufel
	
	RecipeModel ironShovelRecipe = new RecipeModel(RecipeRepository.getToolByID(106));
	ironShovelRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	ironShovelRecipe.addIngredient(BlockRepository.getBlockByID(5), 1);
	this.addRecipe(ironShovelRecipe);
	
	// Ziegelstein (Brick)
	
	RecipeModel brickRecipe = new RecipeModel(RecipeRepository.getCraftedBlockByID(201));
	brickRecipe.addIngredient(BlockRepository.getBlockByID(9), 2);
	this.addRecipe(brickRecipe);

	// Glas
	
	RecipeModel glasRecipe = new RecipeModel(RecipeRepository.getCraftedBlockByID(202));
	glasRecipe.addIngredient(BlockRepository.getBlockByID(3), 1);
	this.addRecipe(glasRecipe);
	
	// Messer
	
	RecipeModel knifeRecipe = new RecipeModel(RecipeRepository.getToolByID(107));
	knifeRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	knifeRecipe.addIngredient(BlockRepository.getBlockByID(5), 1);
	this.addRecipe(knifeRecipe);
	
	// Eisenschwert
	
	RecipeModel ironSwordRecipe = new RecipeModel(RecipeRepository.getToolByID(108));
	ironSwordRecipe.addIngredient(BlockRepository.getBlockByID(11), 1);
	ironSwordRecipe.addIngredient(BlockRepository.getBlockByID(5), 3);
	this.addRecipe(ironSwordRecipe);
	
	// Diamantschwert
	
	RecipeModel diamondSwordRecipe = new RecipeModel(RecipeRepository.getToolByID(109));
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
	
	public static List<ToolModel> getAllTools(){        
		return TOOLS;
		
	}
	
	public static ToolModel getToolByID(int id) {
		return TOOLS.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	}
	
	public static List<CraftedBlockModel> getAllCraftedBlocks() {
		return CRAFTEDBLOCKS;	
		
		
	}
	
	public static CraftedBlockModel getCraftedBlockByID(int id) {
		return CRAFTEDBLOCKS.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	
	}
}
	

