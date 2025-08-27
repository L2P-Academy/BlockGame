// Abdulaziz & Mohamed


package blockGame.view;


import javax.swing.*;

import blockGame.controller.FontLoader;

import blockGame.controller.SoundController;


import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


public class InventoryView extends JFrame {

    private JTable inventoryTable;

    private JScrollPane scrollPane;

    private JPanel backGroundPnl, buttonPnl;  

    private JButton backBtn;

    private String imagePath = "/res/img/loadscreen_bg.png";


    public InventoryView() {

        setTitle("Inventar");

        setSize(500, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUndecorated(true); // ohne Fensterrahmen

        setLocationRelativeTo(null);


        // Hintergrundbild laden

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


        // Daten für Tabelle

        String[] spaltenNamen = {"Items", "Anzahl"};

        Object[][] daten = {

            {"Stein", 10},

            {"Holz", 5},

            {"Erz", 2}

        };


        // Tabelle erstellen

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

        

        // Button Panel

        buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        buttonPnl.setOpaque(false);

        buttonPnl.setMaximumSize(new Dimension(400, 60));

        buttonPnl.setAlignmentX(Component.CENTER_ALIGNMENT);

    

        backBtn = new JButton("Schließen");

        beautifyButton(backBtn);

        buttonPnl.add(backBtn);

        

        backBtn.addActionListener(new ActionListener() {			

            @Override

            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });


        // Button Panel ins Fenster setzen

        backGroundPnl.add(buttonPnl, BorderLayout.SOUTH);


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

        

    // Button verschönern

    public static void beautifyButton(JButton button) {

        button.setFocusPainted(false);

        button.setBackground(Color.DARK_GRAY);

        button.setForeground(Color.WHITE);

        button.setFont(FontLoader.loadPixelFont(18f));

    }

}