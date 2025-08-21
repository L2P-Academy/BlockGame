// Martin & Christoph

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.util.Iterator;
import java.util.Map;
//import java.util.Random;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import blockGame.controller.SoundController;
import blockGame.model.BlockModel;
import blockGame.model.BlockRepository;

public class GameView extends JFrame {
	private JLabel toolLbl0, toolLbl1, toolLbl2, toolLbl3, toolLbl4, toolLbl5,
	toolLbl6, toolLbl7, toolLbl8, toolLbl9;
	private JPanel backgroundPnl, blockPnl, toolPnl;
	private static final int AIR_LAYERS = 15;
	private static final int DIRT_LAYERS = 16;
	private static final int ROWS = 31;
	private static final int COLS = 64;
	private static final int BLOCK_SIZE = 32;
	private String imagePath = "/res/img/maingame_bg.png";
	private static GameView instance;
	private SoundController soundController;
	private int playerRow = 14;
	private int playerCol = 0;

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
		toolPnl.setPreferredSize(new Dimension(100, 120));
		toolPnl.setBackground(Color.DARK_GRAY);
		
		toolLbl0 = new JLabel();
		toolLbl0.setPreferredSize(new Dimension(110, 110));
		toolLbl0.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl1 = new JLabel();
		toolLbl1.setPreferredSize(new Dimension(110, 110));
		toolLbl1.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl2 = new JLabel();
		toolLbl2.setPreferredSize(new Dimension(110, 110));
		toolLbl2.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl3 = new JLabel();
		toolLbl3.setPreferredSize(new Dimension(110, 110));
		toolLbl3.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl4 = new JLabel();
		toolLbl4.setPreferredSize(new Dimension(110, 110));
		toolLbl4.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl5 = new JLabel();
		toolLbl5.setPreferredSize(new Dimension(110, 110));
		toolLbl5.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl6 = new JLabel();
		toolLbl6.setPreferredSize(new Dimension(110, 110));
		toolLbl6.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl7 = new JLabel();
		toolLbl7.setPreferredSize(new Dimension(110, 110));
		toolLbl7.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl8 = new JLabel();
		toolLbl8.setPreferredSize(new Dimension(110, 110));
		toolLbl8.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolLbl9 = new JLabel();
		toolLbl9.setPreferredSize(new Dimension(110, 110));
		toolLbl9.setBorder(BorderFactory.createLineBorder(new Color(37, 232, 7)));
		
		toolPnl.add(toolLbl0);
		toolPnl.add(toolLbl1);
		toolPnl.add(toolLbl2);
		toolPnl.add(toolLbl3);
		toolPnl.add(toolLbl4);
		toolPnl.add(toolLbl5);
		toolPnl.add(toolLbl6);
		toolPnl.add(toolLbl7);
		toolPnl.add(toolLbl8);
		toolPnl.add(toolLbl9);
		
		ButtonGroup tools = new ButtonGroup();
		for (int i = 0; i < 10; i++) {
			JToggleButton btn = new JToggleButton(String.valueOf(i));
			btn.setFocusable(false);
			tools.add(btn);
		}

		// block Panel
		blockPnl = new JPanel(new GridLayout(ROWS, COLS));
		blockPnl.setOpaque(false);
		fillBlockPanelRandomly();

		backgroundPnl.add(toolPnl, BorderLayout.NORTH);
		backgroundPnl.add(blockPnl, BorderLayout.SOUTH);

		getContentPane().add(backgroundPnl);

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke('i'), "openInventory");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("ESCAPE"), "toggleMenu");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("W"), "moveUp");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("A"), "moveLeft");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("S"), "moveDown");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("D"), "moveRight");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke("SPACE"), "toggleBuildOrMineMode");
		
		getRootPane().getActionMap().put("openInventory", new AbstractAction() {

			@Override

			public void actionPerformed(ActionEvent e) {

				InventoryView inventory = new InventoryView();

			}

		});
		getRootPane().getActionMap().put("toggleMenu", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showPauseMenu();

			}
		});
		getRootPane().getActionMap().put("moveUp", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer(-1, 0);
				
			}		
		});
		getRootPane().getActionMap().put("moveLeft", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer(0, -1);
				
			}		
		});
		getRootPane().getActionMap().put("moveDown", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer(+1, 0);
				
			}		
		});
		getRootPane().getActionMap().put("moveRight", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer(0, +1);
				
			}		
		});
		getRootPane().getActionMap().put("toggleBuildOrMineMode", new AbstractAction() {
		    
			@Override 
			public void actionPerformed(ActionEvent e) {
		    	toggleBuildOrMineMode(playerRow, playerCol);
		        highlightAt(playerRow, playerCol);
		    }
		});

		setLocationRelativeTo(null);
		setVisible(true);
		
		highlightAt(playerRow, playerCol);
	}
	
	private void movePlayer(int dRow, int dCol) {
		int newRow = playerRow + dRow;
		int newCol = playerCol + dCol;
		if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS) {
			return;
		}
		unhighlightAt(playerRow, playerCol);
		playerRow = newRow;
		playerCol = newCol;
		highlightAt(playerRow, playerCol);
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
	
	// ungenutzt momentan, da die Tiefe nicht berücksichtigt wird
/*	private static final Map<Integer, int[]> DEPTH_RANGES = Map.of(
		    4, new int[]{5, 15},   // Coal
		    5, new int[]{10, 20},  // Iron
		    6, new int[]{15, 25},  // Copper
		    7, new int[]{20, 32},  // Gold
		    8, new int[]{25, 32}   // Diamond
		); */

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
	private JLabel[][] worldLabels = new JLabel[ROWS][COLS];
	
	private void fillBlockPanelRandomly() {
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				BlockModel block;	
				
				if (row < AIR_LAYERS) {
					block = BlockRepository.getBlockByID(0);
				} else if (row < DIRT_LAYERS) {
					block = BlockRepository.getBlockByID(1);
				} else {
					block = getRandomBlock(col, row);
				}
				
				world[row][col] = block;
				block.setPosX(col);
				block.setPosY(row);

				JLabel singleBlockLbl = new JLabel(); 
				singleBlockLbl.setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
				singleBlockLbl.setLayout(new BorderLayout());
				
				// outline only for non-air blocks
				if (block.getId() != 0) {
					ImageIcon icon = new ImageIcon(getClass().getResource(block.getTextureImagePath()));
					singleBlockLbl.add(new JLabel(icon), BorderLayout.CENTER);
					singleBlockLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				} else {
					singleBlockLbl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
				}				
				worldLabels[row][col] = singleBlockLbl;
				blockPnl.add(singleBlockLbl);
			}
		}
	}

	private JLabel highlightAt(int row, int col) {
		JLabel lbl = worldLabels[row][col];
		if (lbl == null) {
			return null;
		}
		lbl.setOpaque(true);
		lbl.setBackground(new Color(37, 232, 7, 50));
		lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		lbl.repaint();
		return lbl;
	}
	
	private void unhighlightAt(int row, int col) {
		JLabel lbl = worldLabels[row][col];
		if (lbl == null) {
			return;
		}
		lbl.setOpaque(false);
		lbl.setBackground(null);
		// Blockrahmen nur für Blöcke, die NICHT Luft sind
		if (world[row][col] != null && world[row][col].getId() != 0) {
			lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else {
			lbl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
		}
		lbl.repaint();
	}
	
	public void replaceBlockAt(int row, int col, int newBlockByID) {
		// Überprüfen ob die Koordinaten innerhalb des Bereichs liegen
		if (!isInside(row, col)) 
			return;
		// Überprüfen ob der Block existiert
		BlockModel template = BlockRepository.getBlockByID(newBlockByID);
		if (template == null) 
			return;
		
		BlockModel newBlock = new BlockModel(
				template.getId(), 
				"Block", 
				template.getItemName(), 
				template.getTier(), 
				col, 
				row,
				template.getTextureImagePath()
				);
		
		world[row][col] = newBlock;
		// UI Aktualisierung
		refreshBlockLabel(row, col);
		}
	
	// Schalter zum Wechseln zw. BUILD_MODE und MINE_MODE
	public void toggleBuildOrMineMode(int row, int col) {
		// Überprüfen ob die Koordinaten innerhalb des Bereichs liegen
		if (!isInside(row, col)) 
			return;
		
		BlockModel current = world[row][col];
		int currentId = (current != null) ? current.getId() : 0;

		if (currentId != 0) {
			// abbauen -> Luft
			replaceBlockAt(row, col, 0);
		} else {
			// bauen -> Dirt
			replaceBlockAt(row, col, 1);
		}
	}
	
	// Prüft ob Koordinaten innerhalb des Bereichs liegen
	private boolean isInside(int row, int col) {
		return row >= 0 && row < ROWS && col >= 0 && col < COLS;
	}
	
	// Aktualisiert das Label für den Block an der angegebenen Position
	private void refreshBlockLabel(int row, int col) {
		JLabel lbl = worldLabels[row][col];
		if (lbl == null) {
			return;
		}
		BlockModel block = world[row][col];
		// Leeren des Labels
		lbl.removeAll();
		
		if (block != null && block.getId() != 0) {
			ImageIcon icon = new ImageIcon(getClass().getResource(block.getTextureImagePath()));
			lbl.add(new JLabel(icon), BorderLayout.CENTER);
			lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else {
			lbl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
		}
		lbl.setOpaque(false);
		lbl.setBackground(null);
		lbl.revalidate();
		lbl.repaint();
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
				SaveGameView loadGame = new SaveGameView();
				loadGame.setAlwaysOnTop(true);

			}
		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.dispose();
				SaveGameView loadGame = new SaveGameView();
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
