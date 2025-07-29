// Tim

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import blockGame.controller.FontLoader;

public class SettingsView extends JFrame {
	// graphical attributes
	private JPanel settingsPnl, backgroundPnl, buttonPnl;
	private JLabel musicLbl, effectLbl, resolutionLbl;
	private JSlider musicSldr, effectSldr;
	private JComboBox<String> resolutionDropdown;
	private JButton backBtn, applyBtn;
	private String imagePath = "/res/img/loadscreen_bg.png";
	private static SettingsView instance;
	
	public static SettingsView getInstance() {
		return instance;
	}
	
	// Konstruktor
	public SettingsView() {
		// Instanz anlegen
		instance = this;
		setAlwaysOnTop(true);
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Panels
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());
		
		settingsPnl = new JPanel(new GridLayout(6, 1, 10, 10));
		settingsPnl.setBackground(new Color(0, 0, 0, 140));
		settingsPnl.setOpaque(true);
		settingsPnl.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
		settingsPnl.setMaximumSize(new Dimension(600, 300));
		settingsPnl.setPreferredSize(new Dimension(600, 300));
		
		buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPnl.setOpaque(false);
		
		
		// Slider (Volume) & Dropdown (Resolution) --> vllt. Checkbox für Vollbild oder Windowed einbauen
		musicLbl = new JLabel("Lautstärke (Musik):");
		musicSldr = new JSlider(0, 100, 50);
		musicSldr.setPreferredSize(new Dimension(200,20));
		
		effectLbl = new JLabel("Lautstärke (Effekte):");
		effectSldr = new JSlider(0, 100, 50);
		
		resolutionLbl = new JLabel("Auflösung:");
		resolutionDropdown = new JComboBox<>(new String[] {
	            "800x600", "1280x720", "1920x1080"
	        });
		
 
		
		JPanel centerWrapper = new JPanel(new GridBagLayout());
		centerWrapper.setOpaque(false);
		centerWrapper.add(settingsPnl);
		
		backgroundPnl.add(centerWrapper, BorderLayout.CENTER);
		
		// Schriftart
				Font labelFont = FontLoader.loadPixelFont(16f);
				musicLbl.setFont(labelFont);
				effectLbl.setFont(labelFont);
				resolutionLbl.setFont(labelFont);
				
				Color fontColor = Color.WHITE;
				musicLbl.setForeground(fontColor);
				effectLbl.setForeground(fontColor);
				resolutionLbl.setForeground(fontColor);
		
		// Buttons
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);
		applyBtn = new JButton("Übernehmen");
		beautifyButton(applyBtn);
		
		// alles Hinzufügen
		//musicLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		//effectLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		//resolutionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		settingsPnl.add(musicLbl);
		settingsPnl.add(musicSldr);
		settingsPnl.add(effectLbl);
		settingsPnl.add(effectSldr);
		settingsPnl.add(resolutionLbl);
		settingsPnl.add(resolutionDropdown);
		
		buttonPnl.add(backBtn);
		buttonPnl.add(applyBtn);
		
		for (int i = 0; i < 3; i++) {

            settingsPnl.add(new JLabel());
        }
		
		// Layout
		backgroundPnl.add(settingsPnl, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);
		setContentPane(backgroundPnl);
		setVisible(true);
		// Action Listener (2 versch. Wege getestet)
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameView.getInstance() != null && GameView.getInstance().isVisible()) {
					GameView.getInstance().setAlwaysOnTop(true);
				} else if (StartMenuView.getInstance() != null && StartMenuView.getInstance().isVisible()) {
					StartMenuView.getInstance().setAlwaysOnTop(true);	
				}
				dispose();	
			}
		});
		
		applyBtn.addActionListener(e -> {
			// Einstellungen anwenden
			int effectVolume = effectSldr.getValue();
			int musicVolume = musicSldr.getValue();
			String resolution = (String) resolutionDropdown.getSelectedItem();
			System.out.println("Gespeichert:");
			System.out.println("Musik: " + musicVolume + "%");
			System.out.println("Effekte: " + effectVolume + "%");
			System.out.println("Auflösung: " + resolution);
                    
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
				@Override
				public void dispose() {
					instance = null;
					super.dispose();
				}
	}
	



