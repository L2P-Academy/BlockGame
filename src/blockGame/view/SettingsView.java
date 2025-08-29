// Tim

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import blockGame.controller.FontLoader;
import blockGame.controller.SoundController;
import blockGame.controller.XMLController;
import blockGame.model.SettingsModel;

public class SettingsView extends JFrame {
	private static final long serialVersionUID = -8999579228546841722L;
	// graphical attributes
	private JPanel settingsPnl, backgroundPnl, buttonPnl;
	private JLabel musicLbl, effectLbl, resolutionLbl, fllscrnLbl;
	private JSlider musicSldr, effectSldr;
	private JComboBox<String> resDrpdwn;
	private JCheckBox flscrnBox;
	private JButton backBtn, applyBtn;
	private String imagePath = "/res/img/loadscreen_bg.png";
	private SoundController soundController;
	private static SettingsView instance;

	public static SettingsView getInstance() {
		return instance;
	}

	// Konstruktor
	public SettingsView(SoundController soundController) {
		this.soundController = soundController;
		// Instanz anlegen
		instance = this;
		setAlwaysOnTop(true);
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// Hintergrund laden
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());

		// Settings Panel
		settingsPnl = new JPanel(new GridLayout(6, 1, 10, 10));
		settingsPnl.setOpaque(true);
		settingsPnl.setBackground(new Color(0, 0, 0, 140));
		settingsPnl.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
		settingsPnl.setMaximumSize(new Dimension(500, 300));

		// Komponenten
		Font labelFont = FontLoader.loadPixelFont(16f);
		Color fontColor = Color.WHITE;

		musicLbl = new JLabel("Musik:");
		musicLbl.setFont(labelFont);
		musicLbl.setForeground(fontColor);

		effectLbl = new JLabel("Effekte:");
		effectLbl.setFont(labelFont);
		effectLbl.setForeground(fontColor);

		resolutionLbl = new JLabel("Auflösung:");
		resolutionLbl.setFont(labelFont);
		resolutionLbl.setForeground(fontColor);

		fllscrnLbl = new JLabel("Vollbild:");
		fllscrnLbl.setFont(labelFont);
		fllscrnLbl.setForeground(fontColor);

		musicSldr = new JSlider(0, 100, 50);
		effectSldr = new JSlider(0, 100, 50);
		resDrpdwn = new JComboBox<>(new String[] { "800x600", "1280x720", "1920x1080", "2560x1440" });
		flscrnBox = new JCheckBox();

		// Komponenten hinzufügen
		settingsPnl.add(musicLbl);
		settingsPnl.add(musicSldr);
		settingsPnl.add(effectLbl);
		settingsPnl.add(effectSldr);
		settingsPnl.add(resolutionLbl);
		settingsPnl.add(resDrpdwn);
		settingsPnl.add(fllscrnLbl);
		settingsPnl.add(flscrnBox);

		// Button Panel
		buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPnl.setOpaque(false);
		buttonPnl.setMaximumSize(new Dimension(400, 60));
		buttonPnl.setAlignmentX(Component.CENTER_ALIGNMENT);

		backBtn = new JButton("Zurück");
		applyBtn = new JButton("Übernehmen");
		beautifyButton(backBtn);
		beautifyButton(applyBtn);
		buttonPnl.add(backBtn);
		buttonPnl.add(applyBtn);

		// Aufbau
		JPanel centerWrapper = new JPanel();
		centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
		centerWrapper.setOpaque(false);

		centerWrapper.add(Box.createVerticalGlue());
		settingsPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerWrapper.add(settingsPnl);
		centerWrapper.add(Box.createVerticalGlue());

		backgroundPnl.add(centerWrapper, BorderLayout.CENTER);
		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);

		setContentPane(backgroundPnl);
		setLocationRelativeTo(null);
		setVisible(true);

		for (int i = 0; i < 3; i++) {
			settingsPnl.add(new JLabel());
		}

		// ActionListener für Buttonsound
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
			}
		});

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playBtnSound();
			}
		});

		// Action Listener (2 versch. Wege getestet)
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameView.getInstance() != null && GameView.getInstance().isVisible()) {
					GameView.getInstance().setAlwaysOnTop(true);
					System.out.println("GameView fokussiert");
				} else if (StartMenuView.getInstance() != null && StartMenuView.getInstance().isVisible()) {
					StartMenuView.getInstance().setAlwaysOnTop(true);
					System.out.println("StartMenuView fokussiert");
				}
				System.out.println("SettingsView geschlossen");
				dispose();
			}
		});

		applyBtn.addActionListener(e -> {
			// Einstellungen anwenden
			SettingsModel model = new SettingsModel(0, 0, 0, 0, true);
			model.setMusicVolume(musicSldr.getValue());
			model.setEffectVolume(effectSldr.getValue());
			String resolution = (String) resDrpdwn.getSelectedItem();
			if (resolution != null) {
				String[] parts = resolution.split("x");
				int width = Integer.parseInt(parts[0].trim());
				int height = Integer.parseInt(parts[1].trim());

				model.setResolutionX(width);
				model.setResolutionY(height);
				model.setFullscreen(flscrnBox.isSelected());
				setSize(width, height);
				setLocationRelativeTo(null);

				if (GameView.getInstance() != null) {
					GameView.getInstance().setSize(width, height);
					GameView.getInstance().setLocationRelativeTo(null);
				}

				if (StartMenuView.getInstance() != null) {
					StartMenuView.getInstance().setSize(width, height);
					StartMenuView.getInstance().setLocationRelativeTo(null);
				}
			}
			
			soundController.setVolume(model.getMusicVolume());
			soundController.setVolume(model.getEffectVolume());
			XMLController.saveSettingsToXML(model.getMusicVolume(), model.getEffectVolume(), model.getResolutionX(), model.getResolutionY(), true);
			System.out.println("Gespeichert");
			
		});

	}

	/**
	 * Sets the Background, Foreground, Font & Border of a Button. Adds a
	 * MouseListener for Coloring.
	 * 
	 * @param The target-Button for modification
	 * @author Christoph
	 */
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
