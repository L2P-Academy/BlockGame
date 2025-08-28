// Christoph
package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import blockGame.GameState;
import blockGame.controller.FontLoader;
import blockGame.controller.SoundController;
import blockGame.controller.UIController;
import blockGame.controller.XMLController;

public class StartMenuView extends JFrame {
	private static final long serialVersionUID = -8652081252464315205L;
	// graphical attributes
	private JLabel gameTitleLbl;
	private JPanel buttonPnl, backgroundPnl, gameTitlePnl;
	private JButton newGameBtn, loadGameBtn, settingsBtn, exitBtn;
	private String imagePath = "/res/img/startscreen_bg.png";
	private static StartMenuView instance;
	
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
		UIController.beautifyButton(newGameBtn);
		loadGameBtn = new JButton("Spiel laden");
		UIController.beautifyButton(loadGameBtn);
		settingsBtn = new JButton("Einstellungen");
		UIController.beautifyButton(settingsBtn);
		exitBtn = new JButton("Beenden");
		UIController.beautifyButton(exitBtn);
		
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
				// TODO: SaveGameView decides
				new SaveGameView(soundController);
				
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
}
