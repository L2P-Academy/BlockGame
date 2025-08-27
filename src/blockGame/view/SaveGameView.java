// Chris

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatterBuilder;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import blockGame.GameState;
import blockGame.controller.FontLoader;
import blockGame.controller.SoundController;
import blockGame.controller.XMLController;

/**
 * SaveGameView represents the window for loading and saving game progress.
 * 
 * It includes:
 * - A title banner
 * - A table listing saved games
 * - Buttons for Save, Load, and Back
 * 
 * This view is typically accessed from the main menu or in-game pause menu.
 * @author Chris
 */
public class SaveGameView extends JFrame {
	// GUI attributes
	private JLabel gameTitleLbl;
	private JPanel buttonPnl, backgroundPnl, gameTitlePnl;
	private JButton loadGameBtn, backBtn, saveGameBtn;
	private String imagePath = "/res/img/loadscreen_bg.png";
	private JTable saveTable;
	private JTableHeader saveTableHeader;
	private DefaultTableModel saveTableModel;
	private SoundController soundController;
	private static SaveGameView instance;

	/**
	 * Returns the current instance of SaveGameView (Singleton-like behavior).
	 * @return SaveGameView instance
	 */
	public static SaveGameView getInstance() {
		return instance;
	}

	/**
	 * Constructor for SaveGameView.
	 * Sets up the entire UI including:
	 * - Background image
	 * - Title panel
	 * - Save/Load table
	 * - Buttons with custom styles
	 * Also initializes button listeners with sound effects.
	 */
	public SaveGameView(GameState gameState) {
		instance = this;
		setAlwaysOnTop(true);                        // keep window on top
		setTitle("Load Game - PixelMine");           // window title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setExtendedState(JFrame.MAXIMIZED_BOTH);     // fullscreen
		setUndecorated(true);                        // no window decorations

		//  Panels 
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());
		gameTitlePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		gameTitlePnl.setOpaque(false);
		buttonPnl = new JPanel(new FlowLayout());

		// Title 
		gameTitleLbl = new JLabel("Pixel Mine!", SwingConstants.CENTER);
		gameTitleLbl.setFont(FontLoader.loadPixelFont(64f));
		gameTitleLbl.setForeground(new Color(16, 62, 161));
		gameTitlePnl.add(gameTitleLbl);

		// Save Table 
		// TODO: "Playtime"-column = currentTime(ms) - lastSaveTime.parseInt
		String[] columnNames = {"Save Slot", "Date"};
		Object[][] data = {
				// Format: dd/mm/yyyy + hh/mm/ss as a String
			{"Save 1", "" + 
				gameState.getLastSavedDate().getDayOfMonth()+ "." + 
				gameState.getLastSavedDate().getMonth() + "." +
				gameState.getLastSavedDate().getYear() + " " +
				gameState.getLastSavedDate().getHour() + ":" +
				gameState.getLastSavedDate().getMinute() + ":" +
				gameState.getLastSavedDate().getSecond()}
//			{"Save 2", "2025-07-27", "01:20"},
//			{"Save 3", "2025-07-26", "00:30"}
		};

		// Table model with dummy save data
		saveTableModel = new DefaultTableModel(data, columnNames);
		saveTable = new JTable(saveTableModel);
		saveTable.setFont(FontLoader.loadPixelFont(16f));
		saveTable.setRowHeight(48);
		saveTable.setBackground(new Color(0, 0 , 0 , 150));
		saveTable.setForeground(Color.WHITE);

		// Header styling
		saveTableHeader = saveTable.getTableHeader();
		saveTableHeader.setFont(FontLoader.loadPixelFont(32f));
		saveTableHeader.setBackground(new Color(255, 200, 200)); 

		// Scroll pane enables scrolling inside the table
		JScrollPane scrollPane = new JScrollPane(saveTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		// Wrap table in its own panel
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Place table at the top
		backgroundPnl.add(tablePanel, BorderLayout.NORTH);

		// Buttons 
		loadGameBtn = new JButton("Load Game");
		beautifyButton(loadGameBtn);
		backBtn = new JButton("Back");
		beautifyButton(backBtn);
		saveGameBtn = new JButton("Save Game");
		beautifyButton(saveGameBtn);

		// Add buttons to button panel
		buttonPnl.add(saveGameBtn);
		buttonPnl.add(loadGameBtn);
		buttonPnl.add(backBtn);
		buttonPnl.setOpaque(false);

		// Add panels into background panel
		backgroundPnl.add(gameTitlePnl, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);

		//  Button Actions 

		// Load button: play sound + debug output
		loadGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController soundController2 = new SoundController();
				soundController2.playBtnSound(); 
				System.out.println("Loading game...");
				XMLController xmlController = new XMLController(gameState);
				dispose();
				
				new GameView(soundController2, gameState);
				
			}
		});

		// Back button: play sound + switch back to Menu / Game
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController soundController3 = new SoundController();
				soundController3.playBtnSound();

				// Return to GameView if it's active
				if (GameView.getInstance() != null && GameView.getInstance().isVisible()) {
					GameView.getInstance().setAlwaysOnTop(true);
					System.out.println("GameView focused");
				} 
				// Return to StartMenu if active
				else if (StartMenuView.getInstance() != null && StartMenuView.getInstance().isVisible()) {
					StartMenuView.getInstance().setAlwaysOnTop(true);
					System.out.println("StartMenuView focused");
				} else {
					new StartMenuView(soundController3);
				}

				// Close this view
				System.out.println("SaveGameView closed");
				dispose();
			}
		});

		// Save button: play sound (game saving logic can be added later)
		saveGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController soundController4 = new SoundController();
				soundController4.playBtnSound();
				System.out.println("Saving game...");
			}
		});

		//  Window setup 
		setContentPane(backgroundPnl); 
		setLocationRelativeTo(null);    // center on screen
		setVisible(true);               // make visible
	}

	/**
	 * Enhances a JButton with custom styles:
	 * - Custom font
	 * - Background / foreground colors
	 * - Rounded border
	 * - Hover effect
	 * 
	 * @param button The button to be styled
	 */
	public static void beautifyButton(JButton button) {
		button.setFocusPainted(false);
		button.setBackground(new Color(16, 62, 161));
		button.setForeground(Color.WHITE);
		button.setFont(FontLoader.loadPixelFont(18f));

		// Custom rounded border
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		// Hover color change effect
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
