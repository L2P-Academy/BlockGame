package blockGame.model;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

	private static final List <ItemModel> Items = new ArrayList<>();
	
	static {
		Items.add(new ItemModel(51,"Items", "Iron_Ingot", "/res/img/textures/iron_ingot.png"));
		Items.add(new ItemModel(52,"Items", "Stick", "/res/img/textures/stick.png"));
		Items.add(new ItemModel(53,"Items", "Clay", "/res/img/textures/clay.png"));
		Items.add(new ItemModel(54,"Items", "Coal", "/res/img/textures/coal.png"));
		Items.add(new ItemModel(55,"Items", "Gold", "/res/img/textures/gold.png"));
		Items.add(new ItemModel(56,"Items", "Diamond", "/res/img/textures/diamond.png"));
		Items.add(new ItemModel(57,"Items", "Meat", "/res/img/textures/meat.png"));
	}

	public static List<ItemModel> getAllItems() {
		return Items;
	}
	public static ItemModel getItemByID(int id) {
		return Items.stream()
				.filter(b -> b.getId() == id)
				.findFirst()
				.orElse(null);
	}
}	
	
	
	
	
	
	

