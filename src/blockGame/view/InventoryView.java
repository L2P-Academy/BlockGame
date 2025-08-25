// Abdulaziz & Mohamed

package blockGame.view;

import javax.swing.*;
import blockGame.controller.FontLoader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryView extends JFrame {
    private JTable inventoryTable;
    private JScrollPane scrollPane;
    private JPanel backGroundPnl;  
    private String imagePath = "/res/img/loadscreen_bg.png";

    public InventoryView() {
        setTitle("Inventar");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true); // ohne Fensterrahmen
        setLocationRelativeTo(null);
        
        // --- Hintergrundbild laden ---
        ImageIcon bgInventory = new ImageIcon(getClass().getResource(imagePath));
        backGroundPnl = new BackGroundPanel(bgInventory.getImage()); 
        backGroundPnl.setLayout(new BorderLayout());
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("I"), "toggleInventory");
        
        getRootPane().getActionMap().put("toggleInventory", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      dispose();
		    }
		});

        // --- Daten für Tabelle ---
        String[] spaltenNamen = {"Haben", "Anzahl"};
        Object[][] daten = {
            {"Stein", 10},
            {"Holz", 5},
            {"Erz", 2}
        };

        // --- Tabelle erstellen ---
        inventoryTable = new JTable(daten, spaltenNamen);
        beautifyTable(inventoryTable);

        // Transparenz einstellen
        inventoryTable.setOpaque(false);
        inventoryTable.setBackground(new Color(0, 0, 0, 0)); 
        inventoryTable.setForeground(Color.WHITE);
        setUndecorated(true);
        setOpacity(0.9f);

        // Scrollpane hinzufügen
        scrollPane = new JScrollPane(inventoryTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Scrollpane ins Hintergrundpanel setzen
        backGroundPnl.add(scrollPane, BorderLayout.CENTER);

        // Panel ins Fenster setzen
        setContentPane(backGroundPnl);

        setVisible(true);
    }
    

    // verschönern die Tabelle
    private void beautifyTable(JTable table) {
        table.setFont(FontLoader.loadPixelFont(18f));
        table.setRowHeight(45);
        table.getTableHeader().setFont(FontLoader.loadPixelFont(18f));
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setForeground(Color.BLACK);
    }
}


