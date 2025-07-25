// Tim

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import blockGame.controller.FontLoader;

public class SettingsView extends JFrame {
	// graphical attributes
	private JPanel settingsPnl, backgroundPnl, buttonPnl;
	private JLabel volumeLbl, resolutionLbl;
	private JSlider volumeSldr;
	private JComboBox<String> resolutionDropdown;
	private JButton backBtn, applyBtn;
	private String imagePath = "/res/img/startscreen_bg.png";
	
	// Konstruktor
	public SettingsView() {
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Panels
		// Hintergrund (gleich wie StartMenuView evtl. ändern)
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());
		
		settingsPnl = new JPanel(new GridLayout(3, 2, 20, 20));
		settingsPnl.setOpaque(false);
		buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Slider (Volume) & Dropdown (Resolution)
		volumeLbl = new JLabel("Lautstärke:");
		volumeSldr = new JSlider(0, 100, 50);
		
		resolutionLbl = new JLabel("Auflösung:");
		resolutionDropdown = new JComboBox<>(new String[] {
	            "800x600", "1280x720", "1920x1080"
	        });
		
		// Buttons
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);
		applyBtn = new JButton("Übernehmen");
		beautifyButton(applyBtn);
		
		// alles Hinzufügen
		settingsPnl.add(volumeLbl, BorderLayout.WEST);
		settingsPnl.add(volumeSldr, BorderLayout.WEST);
		settingsPnl.add(resolutionLbl, BorderLayout.WEST);
		settingsPnl.add(resolutionDropdown, BorderLayout.WEST);
		
		buttonPnl.add(backBtn);
		buttonPnl.add(applyBtn);
		
		// Layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(settingsPnl, BorderLayout.NORTH);
		getContentPane().add(buttonPnl, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
		// Action Listener (2 versch. Wege getestet)
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new StartMenuView();
				dispose();	
			}
		});
		
		applyBtn.addActionListener(e -> {
			// Einstellungen anwenden
			int volume = volumeSldr.getValue();
			String resolution = (String) resolutionDropdown.getSelectedItem();
			System.out.println("Lautstärke: " + volume + ", Auflösung: " + resolution);
		});
		
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
	



