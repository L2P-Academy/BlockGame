// Martin & Christoph

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import blockGame.controller.SoundController;
import blockGame.model.BlockModel;
import blockGame.model.BlockRepository;

public class GameView extends JFrame {
	private JPanel backgroundPnl, blockPnl, toolPnl;
	private static final int DIRT_LAYERS = 1;
	private static final int ROWS = 16;
	private static final int COLS = 64;
	private static final int BLOCK_SIZE = 32;
	private String imagePath = "/res/img/maingame_bg.png";
	private static GameView instance;
	private SoundController soundController;

	public static GameView getInstance() {
		return instance;
	}

	// TODO: flexible blockSizes for different screen resolutions
	private int blockSize;

//	public GameView(int blockSize) {
//	this.blockSize = blockSize;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	int height = (int)JFrame.getHeight();
//	if (height < 480) {
//		blockSize = 16;
//	} else if (height < 720) { 
//		blockSize = 24;
//	} else if (height < 1080) {
//		blockSize = 32;
//	} else if (height < 1440) {
//		blockSize = 42;
//	} else if (height < 2160) {
//		blockSize = 54;
//	} else {blockSize = 64}

	public GameView(SoundController soundController) {
		// SoundController initialize
		this.soundController = soundController;
		soundController.playMusicLoop("src/res/sounds/music/pmtfoundation.wav");
		instance = this;
		setTitle("PixelMine!"); // frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // "X" -> close frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setUndecorated(true);

		// background Panel
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imagePath));
		backgroundPnl = new BackGroundPanel(bgIcon.getImage());
		backgroundPnl.setLayout(new BorderLayout());

		// tool Panel
		toolPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolPnl.setPreferredSize(new Dimension(100, 180));
		toolPnl.setBackground(Color.DARK_GRAY);

		ButtonGroup tools = new ButtonGroup();
		for (int i = 0; i < 10; i++) {
			JToggleButton btn = new JToggleButton(String.valueOf(i));
			btn.setFocusable(false);
			tools.add(btn);
		}

		// block Panel
		blockPnl = new JPanel(new GridLayout(ROWS, COLS));
		fillBlockPanelRandomly();

		backgroundPnl.add(toolPnl, BorderLayout.NORTH);
		backgroundPnl.add(blockPnl, BorderLayout.SOUTH);

		getContentPane().add(backgroundPnl);

		getRootPane().getInputMap().put(KeyStroke.getKeyStroke('i'), "openInventory");

		getRootPane().getActionMap().put("openInventory", new AbstractAction() {

			@Override

			public void actionPerformed(ActionEvent e) {

				InventoryView inventory = new InventoryView();

			}

		});

		getRootPane().getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "toggleMenu");
		getRootPane().getActionMap().put("toggleMenu", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showPauseMenu();

			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Block Mapping & Randomizer 
	private static final Map<Integer, Double> blockChances = Map.ofEntries(
			Map.entry(1, 0.2), // Dirt
			Map.entry(2, 0.25), // Stone
			Map.entry(3, 0.1), // Sand
			Map.entry(4, 0.1), // Coal
			Map.entry(5, 0.1), // Iron
			Map.entry(6, 0.08), // Copper
			Map.entry(7, 0.05), // Gold
			Map.entry(8, 0.03), // Diamond
			Map.entry(9, 0.08), // Clay
			Map.entry(10, 0.05) // Water
	);
	
	private static final Map<Integer, int[]> DEPTH_RANGES = Map.of(
		    4, new int[]{5, 15},   // Coal
		    5, new int[]{10, 20},  // Iron
		    6, new int[]{15, 25},  // Copper
		    7, new int[]{20, 32},  // Gold
		    8, new int[]{25, 32}   // Diamond
		);

	private BlockModel getRandomBlock(int x, int y) {
		double totalWeight = blockChances.values().stream().mapToDouble(Double::doubleValue).sum();
		double rand = Math.random() * totalWeight;
		double cumulative = 0.0;

		for (Map.Entry<Integer, Double> entry : blockChances.entrySet()) {
			cumulative += entry.getValue();
			if (rand <= cumulative) {
				BlockModel template = BlockRepository.getBlockByID(entry.getKey());
				if (template != null) {
					return new BlockModel(template.getId(), "Block", template.getItemName(), template.getTier(), x, y,
							template.getTextureImagePath()); 
				}
			}
		}
		// Fallback to dirt
		return BlockRepository.getBlockByID(1);
	}

	private BlockModel[][] world = new BlockModel[ROWS][COLS];

	private void fillBlockPanelRandomly() {
		Random rng = new Random();
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				BlockModel block;	
				
				if (row < DIRT_LAYERS) {
					block = BlockRepository.getBlockByID(1);
				} else {
					block = getRandomBlock(col, row);
				}
				
				world[row][col] = block;

				JPanel blockPanel = new JPanel();
				blockPanel.setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
				blockPanel.setLayout(new BorderLayout());
				blockPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				ImageIcon icon = new ImageIcon(getClass().getResource(block.getTextureImagePath()));
				blockPanel.add(new JLabel(icon), BorderLayout.CENTER);
				blockPnl.add(blockPanel);
			}
		}
	}

	private void showPauseMenu() {
		JDialog pauseDialog = new JDialog(this, "PAUSE", true);
		pauseDialog.setSize(400, 600);
		pauseDialog.setLocationRelativeTo(this);
		pauseDialog.setLayout(new GridLayout(6, 1));

		JButton resumeButton = new JButton("Fortsetzen");
		StartMenuView.beautifyButton(resumeButton);
		JButton loadButton = new JButton("Laden");
		StartMenuView.beautifyButton(loadButton);
		JButton saveButton = new JButton("Speichern");
		StartMenuView.beautifyButton(saveButton);
		JButton settingsButton = new JButton("Einstellungen");
		StartMenuView.beautifyButton(settingsButton);
		JButton menuButton = new JButton("Hauptmenü");
		StartMenuView.beautifyButton(menuButton);
		JButton exitButton = new JButton("Beenden");
		StartMenuView.beautifyButton(exitButton);

		resumeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();

			}
		});

		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				LoadGameView loadGame = new LoadGameView();
				loadGame.setAlwaysOnTop(true);

			}
		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				LoadGameView loadGame = new LoadGameView();
				loadGame.setAlwaysOnTop(true);

			}
		});

		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				new SettingsView(soundController);

			}
		});

		menuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				dispose();
				StartMenuView startMenu = new StartMenuView(soundController);
				startMenu.requestFocus();
			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				dispose();
			}
		});

		pauseDialog.add(resumeButton);
		pauseDialog.add(loadButton);
		pauseDialog.add(saveButton);
		pauseDialog.add(settingsButton);
		pauseDialog.add(menuButton);
		pauseDialog.add(exitButton);
		pauseDialog.setVisible(true);
	}
}
