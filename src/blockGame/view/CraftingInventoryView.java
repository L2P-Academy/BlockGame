// Abdulaziz & Mohamed
package blockGame.view;

import blockGame.view.CraftingView;
import blockGame.view.InventoryView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CraftingInventoryView {
    public CraftingInventoryView() {
        
        JFrame frame = new JFrame("InventoryCrafting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        // Hauptlabel
        JLabel infoLabel = new JLabel("Klicken Sie auf einen Button, um ein Fenster zu öffnen.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        JButton inventoryButton = new JButton("Inventar öffnen");
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryView();
            }
        }); 

        
        JButton craftingButton = new JButton("Crafting-Menü öffnen");
        craftingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CraftingView();
            }
        });

        // Button hinzufügen
        frame.add(inventoryButton);
        frame.add(craftingButton);
        frame.add(infoLabel);

        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
