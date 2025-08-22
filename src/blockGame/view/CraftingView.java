// Kai E

package blockGame.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

	public class CraftingView extends JFrame {
		private JTable craftingTable;
		private JButton craftButton;


	public CraftingView(){
		setTitle("Crafting Menü");
		setSize(600, 800);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setLayout(new BorderLayout());
    		
    	
    	String[] spaltenNamen = {"Gegenstand", "Benötigte Materialien"};
    	
    	
    	Object[][] daten = {
    		{"Holzspitzhacke", "5x Holz"},
    		{"Steinspitzhacke", "3x Stein", "2 Holz"},
    	};

    
    	// Tabelle
    	craftingTable = new JTable(daten, spaltenNamen);
    	craftingTable.setRowHeight(30);
    	craftingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // nur 1 Rezept wählen
    
    
    	JScrollPane scrollPane = new JScrollPane(craftingTable);
    	add(scrollPane, BorderLayout.CENTER);
    
    
    	// Craft-Button
    	craftButton = new JButton("Craften");
    	add(craftButton, BorderLayout.SOUTH);
    
    
    	// Listener für den Button
    	craftButton.addActionListener(e -> {
    		int selectedRow = craftingTable.getSelectedRow();
    		if (selectedRow != -1) {
    			String item = (String) craftingTable.getValueAt(selectedRow, 0);
    			JOptionPane.showMessageDialog(this, 
    				item + " wurde gecraftet!",
    				"Crafting erfolgreich",
    				JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			JOptionPane.showMessageDialog(this, 
    				"Bitte wähle zuerst ein Rezept aus!",
    				"Fehler",
    				JOptionPane.WARNING_MESSAGE);
    		}
    	});   
    
    	setLocationRelativeTo(null);
    	setVisible(true);
    }
}
