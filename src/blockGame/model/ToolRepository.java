package blockGame.model;

	import java.util.ArrayList;
	import java.util.List;

	public class ToolRepository {
		private static final List<ToolModel> Tools = new ArrayList<>();
		
		static {
			Tools.add(new ToolModel(101,"Werkzeug", "Holzspitzhake", 30, 1, "/res/img/textures/pickaxe.png"));
			Tools.add(new ToolModel(102,"Werkzeug", "Steinspitzhake", 30, 2, "/res/img/textures/pickaxe.png"));
			Tools.add(new ToolModel(103,"Werkzeug", "Eisenspitzhake", 30, 3, "/res/img/textures/pickaxe.png"));
		}

		public static List<ToolModel> getAllTools() {
			return Tools;
		}
		public static ToolModel getToolByID(int id) {
			return Tools.stream()
					.filter(b -> b.getId() == id)
					.findFirst()
					.orElse(null);
		}
	}