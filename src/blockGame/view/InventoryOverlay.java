
package blockGame.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InventoryOverlay extends JPanel {

    private JTable inventoryTable;
    private JScrollPane scrollPane;
    private boolean visible = false;

    public InventoryOverlay() {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));

        String[] columnNames = {"Item", "Anzahl"};
        Object[][] data = {
            {"Stein", 10},
            {"Holz", 5},
            {"Erz", 2}
        };

        inventoryTable = new JTable(data, columnNames);
        inventoryTable.setFillsViewportHeight(true);
        inventoryTable.setRowHeight(30);
        inventoryTable.setOpaque(false);
        inventoryTable.setBackground(new Color(0,0,0,0));  // Transparent

        scrollPane = new JScrollPane(inventoryTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(visible);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Halbtransparenter schwarzer Hintergrund
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 0)); // Alpha 150 von 255
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();

        super.paintComponent(g);
    }

    public void toggleVisibility() {
        visible = !visible;
        setVisible(visible);
        revalidate();
        repaint();
    }
}
