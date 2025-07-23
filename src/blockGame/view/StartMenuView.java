package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import blockGame.controller.FontLoader;

public class StartMenuView extends JFrame {
	// graphical attributes
	private JLabel gameTitleLbl;
	private JPanel buttonPnl, backgroundPnl;
	private JButton newGameBtn, loadGameBtn, settingsBtn, exitBtn;
	private String imagePath = "/res/img/startscreen_bg.png";
	
	// graphical constructor
	public StartMenuView() {
		setTitle("Start - PixelMine"); // frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // "X" -> close frame
		setSize(1920, 1080);
		
		// panels
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		System.out.println(getClass().getResource(imagePath));
		backgroundPnl.setLayout(new BorderLayout());
		buttonPnl = new JPanel(new FlowLayout());
		
		gameTitleLbl = new JLabel("Pixel Mine!", SwingConstants.CENTER);
		gameTitleLbl.setFont(FontLoader.loadPixelFont(32f));
		gameTitleLbl.setForeground(Color.RED);
				
		// buttons
		newGameBtn = new JButton("Neues Spiel");
		beautifyButton(newGameBtn);
		loadGameBtn = new JButton("Spiel laden");
		beautifyButton(loadGameBtn);
		settingsBtn = new JButton("Einstellungen");
		beautifyButton(settingsBtn);
		exitBtn = new JButton("Beenden");
		beautifyButton(exitBtn);
		
		buttonPnl.add(newGameBtn);
		buttonPnl.add(loadGameBtn);
		buttonPnl.add(settingsBtn);
		buttonPnl.add(exitBtn);
		
		backgroundPnl.add(gameTitleLbl, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);
		
		getContentPane().add(backgroundPnl); // füge Hintergrund dem Fenster hinzu
		setLocationRelativeTo(null); // Bildschirmmitte
		setVisible(true); // Sichtbarkeit setzen
	}
	
	// Modify Buttons
		public void beautifyButton(JButton button) {
			button.setFocusPainted(false);
			button.setBackground(new Color(212, 160, 23));
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
					button.setBackground(new Color(212, 160, 23));
				}
			});
		}
}
