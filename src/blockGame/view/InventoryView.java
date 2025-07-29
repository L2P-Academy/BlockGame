// Abdul & Mohamed

package blockGame.view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
public class InventoryView extends JFrame {
	private JTable inventoryTable;
    private int numberIcon;


public InventoryView(){
    setTitle("Inventar");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] spaltenNamen = {"Haben", "Anzahl"};
    Object[][] daten = {
        {"Stein", 10},
        {"Holz", 5},
        {"Erz", 2} 
    };

    inventoryTable = new JTable(daten, spaltenNamen);

    JScrollPane scrollPane = new JScrollPane(inventoryTable);
    getContentPane().add(scrollPane);

    setVisible(true);
    setLocationRelativeTo(null);
	}
}