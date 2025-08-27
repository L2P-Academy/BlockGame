// Christoph

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import blockGame.GameState;
import blockGame.controller.FontLoader;
import blockGame.controller.SoundController;
import blockGame.controller.XMLController;

public class StartMenuView extends JFrame {
	// graphical attributes
	private JLabel gameTitleLbl;
	private JPanel buttonPnl, backgroundPnl, gameTitlePnl;
	private JButton newGameBtn, loadGameBtn, settingsBtn, exitBtn;
	private String imagePath = "/res/img/startscreen_bg.png";
	private static StartMenuView instance;
	private SoundController soundController;
	
	public static StartMenuView getInstance() {
		return instance;
	}
	// graphical constructor
	public StartMenuView(SoundController soundController) {
		instance = this;
		setTitle("Start - PixelMine"); // frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // "X" -> close frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// SoundController initialize
		this.soundController = soundController;
		soundController.playMusicLoop("src/res/sounds/music/PixelMineIntro.wav");
		
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
		
		// buttons
		newGameBtn = new JButton("Neues Spiel");
		beautifyButton(newGameBtn);
		loadGameBtn = new JButton("Spiel laden");
		beautifyButton(loadGameBtn);
		settingsBtn = new JButton("Einstellungen");
		beautifyButton(settingsBtn);
		exitBtn = new JButton("Beenden");
		beautifyButton(exitBtn);
		
		// add buttons to panel
		buttonPnl.add(newGameBtn);
		buttonPnl.add(loadGameBtn);
		buttonPnl.add(settingsBtn);
		buttonPnl.add(exitBtn);
		buttonPnl.setOpaque(false);
		
		// add elements to background
		backgroundPnl.add(gameTitlePnl, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);
		
		// ActionListeners for Buttons
		newGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
				soundController.stopMusicLoop();
				new GameView(soundController);
				dispose();
			}
		});
		
		loadGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
				GameState gameState = XMLController.readSaveGameFromXML(new File("savegames/saveGame.xml"));
				new SaveGameView(gameState);
				dispose();
			}
		});
		
		settingsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
				new SettingsView(soundController);
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
				soundController.stopMusicLoop();
				dispose();
			}
		});
		

		
		// add background to frame
		getContentPane().add(backgroundPnl); // füge Hintergrund dem Fenster hinzu
		setLocationRelativeTo(null); // Bildschirmmitte
		setVisible(true); // Sichtbarkeit setzen
	}
	
	/**
	 * Sets the Background, Foreground, Font & Border of a Button. Adds a MouseListener for Coloring.
	 * @param The target-Button for modification
	 * @author Christoph
	 */
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
