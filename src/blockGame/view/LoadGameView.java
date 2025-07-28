// Chris

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import blockGame.controller.FontLoader;

public class LoadGameView extends JFrame {
	// graphical attributes
	private JLabel gameTitleLbl;
	private JPanel buttonPnl, backgroundPnl, gameTitlePnl;
	private JButton loadGameBtn, backBtn;
	private String imagePath = "/res/img/loadscreen_bg.png";
	private JTable saveTable;
	
	// Constructor
	public LoadGameView() {
		setTitle("Start - PixelMine"); // frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // "X" -> close frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// panels
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());
		gameTitlePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		gameTitlePnl.setOpaque(false);
		buttonPnl = new JPanel(new FlowLayout());
		
		// label
		gameTitleLbl = new JLabel("Pixel Mine!", SwingConstants.CENTER);
		gameTitleLbl.setFont(FontLoader.loadPixelFont(64f));
		gameTitleLbl.setForeground(new Color(16, 62, 161));
		gameTitlePnl.add(gameTitleLbl);
		

		// Load game Tabbele
		
		String[] columnNames = {"Spielstand", "Datum", "Spielzeit"};
		Object[][] data = {
    {"Save 1", "28.07.2025", "00:45"},
    {"Save 2", "27.07.2025", "01:20"},
    {"Save 3", "26.07.2025", "00:30"}
};

		// Tabelle erstellen
		
		saveTable = new JTable(data, columnNames);
		saveTable.setFont(FontLoader.loadPixelFont(16f));
		saveTable.setRowHeight(48);
		saveTable.setBackground(new Color(255, 255, 255, 200));
		saveTable.setForeground(Color.BLACK);

		// ScrollPane um die Tabelle
		
		JScrollPane scrollPane = new JScrollPane(saveTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		// Panel für die Tabelle
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Tabelle ins Hintergrund-Panel einfügen
		backgroundPnl.add(tablePanel, BorderLayout.NORTH);

		
		// buttons
	
		loadGameBtn = new JButton("Spiel laden");
		beautifyButton(loadGameBtn);
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);
		
		// add buttons to panel
		
		buttonPnl.add(loadGameBtn);
		buttonPnl.add(backBtn);
		buttonPnl.setOpaque(false);
		
		// add elements to background
		backgroundPnl.add(gameTitlePnl, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);
		
		// ActionListeners for Buttons
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			new StartMenuView();
			dispose();			
			}
		});
		
		// add background to frame
		getContentPane().add(backgroundPnl); // füge Hintergrund dem Fenster hinzu
		setLocationRelativeTo(null); // Bildschirmmitte
		setVisible(true); // Sichtbarkeit setzen
	}
	
	// Modify Buttons
		public static void beautifyButton(JButton button) {
			button.setFocusPainted(false);
			button.setBackground(new Color(16, 62, 161));
			button.setForeground(Color.WHITE);
			button.setFont(FontLoader.loadPixelFont(18f));

			// Rounded Corners
			Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border roundedBorder = BorderFactory.createCompoundBorder(border,
					BorderFactory.createEmptyBorder(10, 20, 10, 20));
			button.setBorder(
					BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

			// color change when MouseOver is happening
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					button.setBackground(new Color(37, 232, 7));
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					button.setBackground(new Color(16, 62, 161));
				}
			});
		}
}		

