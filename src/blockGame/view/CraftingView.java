// Kai E

package blockGame.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import blockGame.controller.SoundController;
import blockGame.controller.UIController;

import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
/**
 * Class for the Craftingwindow
 * 
 * @author Kai E
 */
public class CraftingView extends JFrame {
	private static final long serialVersionUID = -2966491183214805422L;
	private JTable craftingTable;
	private JButton craftButton, zurückButton;

	public CraftingView() {
		setTitle("Crafting Menü");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		String[] spaltenNamen = { "Gegenstand", "Benötigte Materialien" };

		Object[][] daten = { { "Holzspitzhacke", "5x Holz" }, { "Steinspitzhacke", "3x Stein", "2 Holz" }, };

		// Tabelle
		craftingTable = new JTable(daten, spaltenNamen);
		craftingTable.setRowHeight(30);
		craftingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // nur 1 Rezept wählen

		JScrollPane scrollPane = new JScrollPane(craftingTable);
		add(scrollPane, BorderLayout.CENTER);

		/**
		 * Functions for the Craft- and Backbutton
		 * @author Kai E
		 */
		// Buttonpanel
		JPanel buttonPanel = new JPanel(new BorderLayout());

		// Craft-Button
		craftButton = new JButton("Craften");
		UIController.beautifyButton(craftButton);
		buttonPanel.add(craftButton, BorderLayout.WEST);

		// Zurück-Button
		zurückButton = new JButton("Zurück");
		UIController.beautifyButton(zurückButton);
		buttonPanel.add(zurückButton, BorderLayout.EAST);

		add(buttonPanel, BorderLayout.SOUTH);

		// CraftButton Funktion
		craftButton.addActionListener(e -> {
			int selectedRow = craftingTable.getSelectedRow();
			if (selectedRow >= 0) {
				String item = (String) craftingTable.getValueAt(selectedRow, 0);
				JOptionPane.showMessageDialog(this, item + " wurde gecraftet!", "Crafting erfolgreich",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Bitte wähle zuerst ein Rezept aus!", "Fehler",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		// Zurückbutton Funktion
		zurückButton.addActionListener(e -> {
			dispose();
		});

		// ActionListener für Buttonsound
		zurückButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController soundController2 = new SoundController();
				soundController2.playBtnSound();
			}
		});

		craftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController soundController3 = new SoundController();
				soundController3.playBtnSound();
			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
