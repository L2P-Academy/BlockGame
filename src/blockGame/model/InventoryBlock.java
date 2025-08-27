// Abdulaziz & Mohamed

package blockGame.model;


public class InventoryModel {

    // Items & Anzahl
    private Object[][] tableData;

    // Map für Items und ihre Anzahl
    private Map<ItemModel, Integer> itemNumbers;

    // Konstruktor
    public InventoryModel() {
        itemNumbers = new HashMap<>();

        // Items hinzufügen
        ItemModel stone = new ItemModel("Stein");
        ItemModel wood  = new ItemModel("Holz");
        ItemModel ore   = new ItemModel("Erz");

        itemNumbers.put(stone, 10);
        itemNumbers.put(wood, 5);
        itemNumbers.put(ore, 2);

        updateTableData();
    }