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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;
import java.util.HashMap;
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
import javax.swing.Timer;

import blockGame.controller.SoundController;
import blockGame.model.BlockModel;
import blockGame.model.BlockRepository;
import java.awt.event.KeyEvent;


public class GameView extends JFrame {
	private JLabel toolLbl0, toolLbl1, toolLbl2, toolLbl3, toolLbl4, toolLbl5, toolLbl6, toolLbl7, toolLbl8, toolLbl9,
			playerLbl;
	private JPanel backgroundPnl, blockPnl, toolPnl;
	private static final int AIR_LAYERS = 15;
	private static final int DIRT_LAYERS = 16;
	private static final int ROWS = 31;
	private static final int COLS = 64;
	private String imagePath = "/res/img/maingame_bg.png";
	private static GameView instance;
	private SoundController soundController;
	private int playerRow = 14;
	private int playerCol = 0;
	private static final int BASE_BLOCK_SIZE = 72; // Baseline 72px @1440p
	private int blockSize;
	private final java.util.Map<String, ImageIcon> iconCache = new HashMap<>();
	private InventoryView inventoryView;


	public static GameView getInstance() {
		return instance;
	}

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

		// Wrapper class for scaling
		JPanel worldWrapperPnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		worldWrapperPnl.setOpaque(false);
		worldWrapperPnl.add(blockPnl);

		// Erste Blockgröße bestimmen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		blockSize = computeBlockSize(screenSize.width, screenSize.height);
		applyPreferredWorldSize();

		fillBlockPanelRandomly();

		backgroundPnl.add(toolPnl, BorderLayout.NORTH);
		backgroundPnl.add(worldWrapperPnl, BorderLayout.SOUTH);

		getContentPane().add(backgroundPnl);

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("I"), "toggleInventory");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
				"toggleMenu");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "moveUp");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "moveLeft");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "moveDown");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "moveRight");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"),
				"toggleBuildOrMineMode");
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "openCrafting");

		getRootPane().getActionMap().put("openCrafting", new AbstractAction() {

			@Override

			public void actionPerformed(ActionEvent e) {

				new CraftingView();

			}

		});
		
		getRootPane().getActionMap().put("toggleInventory", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        toggleInventory();
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
			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
    
		// Spieler-Label (animiertes GIF) statt highlight
		ImageIcon playerIcon = getScaledIcon("/res/img/player_IDLE_72px.gif", blockSize, true);
		playerLbl = new JLabel(playerIcon);
		playerLbl.setPreferredSize(new Dimension(blockSize, blockSize));
		worldLabels[playerRow][playerCol].setLayout(new BorderLayout());
		worldLabels[playerRow][playerCol].add(playerLbl, BorderLayout.CENTER);

		// Resize-Handling (debounced)
		addComponentListener(new java.awt.event.ComponentAdapter() {
			private Timer debounce;

			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				if (debounce != null && debounce.isRunning()) {
					debounce.restart();
				} else {
					debounce = new Timer(120, ev -> {
						debounce.stop();
						onResizeRebuild();
					});
					debounce.setRepeats(false);
					debounce.start();
				}
			}
		});
	}
	private void toggleInventory() {
	    
	        inventoryView = new InventoryView();
	       
	    } 
	

	/**
	 * Moves the player-Label to any side.
	 * @param dRow sets the direction of the row, positive = downwards
	 * @param dCol sets the direction of the column, positive = right side
	 * @author Marc, Christoph
	 */
	private void movePlayer(int dRow, int dCol) {
		int newRow = playerRow + dRow;
		int newCol = playerCol + dCol;
		if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS) {
			return;
		}
		// alten Platz räumen
		if (playerLbl != null && worldLabels[playerRow][playerCol] != null) {
			worldLabels[playerRow][playerCol].remove(playerLbl);
			worldLabels[playerRow][playerCol].revalidate();
			worldLabels[playerRow][playerCol].repaint();
		}
		playerRow = newRow;
		playerCol = newCol;
		// neuen Platz setzen
		worldLabels[playerRow][playerCol].setLayout(new BorderLayout());
		worldLabels[playerRow][playerCol].add(playerLbl, BorderLayout.CENTER);
		worldLabels[playerRow][playerCol].revalidate();
		worldLabels[playerRow][playerCol].repaint();
	}

	// Block Mapping & Randomizer
	private static final Map<Integer, Double> blockChances = Map.ofEntries(Map.entry(1, 0.2), // Dirt
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
	/*
	 * private static final Map<Integer, int[]> DEPTH_RANGES = Map.of( 4, new
	 * int[]{5, 15}, // Coal 5, new int[]{10, 20}, // Iron 6, new int[]{15, 25}, //
	 * Copper 7, new int[]{20, 32}, // Gold 8, new int[]{25, 32} // Diamond );
	 */

	/**
	 * Gets a random Block from the BlockRepository-Class.
	 * @param x the Block row
	 * @param y the Block col
	 * @return A BlockModel for world generation.
	 */
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

	/**
	 * Fills the blockPanel of the game world with randomized blocks.
	 * @author Christoph
	 */
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
				singleBlockLbl.setPreferredSize(new Dimension(blockSize, blockSize));
				singleBlockLbl.setLayout(new BorderLayout());

				// outline only for non-air blocks
				if (block.getId() != 0) {
					ImageIcon icon = getScaledIcon(block.getTextureImagePath(), blockSize, false);
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
	/*
	 * private JLabel highlightAt(int row, int col) { JLabel lbl =
	 * worldLabels[row][col]; if (lbl == null) { return null; } lbl.setOpaque(true);
	 * lbl.setBackground(new Color(37, 232, 7, 50));
	 * lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN)); lbl.repaint();
	 * return lbl; }
	 * 
	 * private void unhighlightAt(int row, int col) { JLabel lbl =
	 * worldLabels[row][col]; if (lbl == null) { return; } lbl.setOpaque(false);
	 * lbl.setBackground(null); // Blockrahmen nur für Blöcke, die NICHT Luft sind
	 * if (world[row][col] != null && world[row][col].getId() != 0) {
	 * lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK)); } else {
	 * lbl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
	 * } lbl.repaint(); }
	 */

	/**
	 * Ersetzt den Block an der angegebenen Weltposition durch einen neuen Blocktyp.
	 * <p>
	 * Validiert zuerst die Koordinaten, holt das Block-Template aus dem {@link BlockRepository},
	 * erzeugt daraus ein neues {@link BlockModel} (mit korrekten Weltkoordinaten) und
	 * aktualisiert anschließend die UI via {@link #refreshBlockLabel(int, int)}.
	 * </p>
	 *
	 * @param row Zeilenindex in der Welt (0-basiert)
	 * @param col Spaltenindex in der Welt (0-basiert)
	 * @param newBlockByID ID des Ziel-Blocks (z. B. 0 = Luft, 1 = Dirt, …)
	 * @author Marc
	 */

	public void replaceBlockAt(int row, int col, int newBlockByID) {
		// Überprüfen ob die Koordinaten innerhalb des Bereichs liegen
		if (!isInside(row, col))
			return;
		// Überprüfen ob der Block existiert
		BlockModel template = BlockRepository.getBlockByID(newBlockByID);
		if (template == null)
			return;

		BlockModel newBlock = new BlockModel(template.getId(), "Block", template.getItemName(), template.getTier(), col,
				row, template.getTextureImagePath());

		world[row][col] = newBlock;
		// UI Aktualisierung
		refreshBlockLabel(row, col);
	}

	
	/**
	 * Schaltet an der angegebenen Weltposition zwischen Bauen und Abbauen um.
	 * <p>
	 * Wenn am Ziel kein Luft-Block (ID != 0) liegt, wird er zu Luft (abbauen).
	 * Liegt dort Luft (ID == 0), wird Dirt (ID 1) platziert (bauen).
	 * Intern nutzt die Methode {@link #replaceBlockAt(int, int, int)}.
	 * </p>
	 *
	 * @param row Zeilenindex in der Welt (0-basiert)
	 * @param col Spaltenindex in der Welt (0-basiert)
	 * @author Marc
	 */

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

	/**
	 * Prüft, ob die übergebenen Weltkoordinaten innerhalb der gültigen Grenzen liegen.
	 *
	 * @param row Zeilenindex in der Welt (0-basiert)
	 * @param col Spaltenindex in der Welt (0-basiert)
	 * @return {@code true}, wenn 0 ≤ row &lt; ROWS und 0 ≤ col &lt; COLS; sonst {@code false}
	 * @author Marc
	 */

	private boolean isInside(int row, int col) {
		return row >= 0 && row < ROWS && col >= 0 && col < COLS;
	}

	/**
	 * Aktualisiert das UI-Label für den Block an der angegebenen Position.
	 * <p>
	 * Entfernt vorhandene UI-Komponenten, setzt die PreferredSize auf die aktuelle {@code blockSize},
	 * fügt (sofern kein Luft-Block) das skalierte Textur-Icon hinzu und stellt den passenden Rahmen ein.
	 * Abschließend werden {@code revalidate()} und {@code repaint()} aufgerufen.
	 * </p>
	 *
	 * <p><b>Hinweis:</b> Erwartet, dass {@code world[row][col]} und {@code worldLabels[row][col]}
	 * initialisiert sind.</p>
	 *
	 * @param row Zeilenindex in der Welt (0-basiert)
	 * @param col Spaltenindex in der Welt (0-basiert)
	 * @author Marc
	 */

	private void refreshBlockLabel(int row, int col) {
		JLabel lbl = worldLabels[row][col];
		if (lbl == null) {
			return;
		}
		BlockModel block = world[row][col];
		lbl.removeAll();
		lbl.setPreferredSize(new Dimension(blockSize, blockSize));

		if (block != null && block.getId() != 0) {
			ImageIcon icon = getScaledIcon(block.getTextureImagePath(), blockSize, false);
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

	/**
	 * Shows the Pause Menu and Game Dialog for saving, closing and resuming etc. the game.
	 * @author Christoph
	 */
	private void showPauseMenu() {
		JDialog pauseDialog = new JDialog(this, "PAUSE", true);
		pauseDialog.setSize(400, 600);
		pauseDialog.setLocationRelativeTo(this);
		pauseDialog.setLayout(new GridLayout(6, 1));
		pauseDialog.setUndecorated(true);

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
	
	private void applyPreferredWorldSize() {
		Dimension preferredDim = new Dimension(COLS * blockSize, ROWS * blockSize);
		blockPnl.setPreferredSize(preferredDim);
		blockPnl.setMinimumSize(preferredDim);
		blockPnl.setMaximumSize(preferredDim);
		blockPnl.revalidate();
	}

	/**
	 * Berechnet die dynamische Blockgröße ({@code blockSize}) in Pixeln für die aktuelle Fensterfläche.
	 * <p>
	 * Zieht die Höhe der Tool-Leiste ab und bestimmt dann die größtmögliche quadratische Kachelgröße,
	 * die sowohl in die verfügbare Breite (COLS) als auch in die verfügbare Höhe (ROWS) passt.
	 * Das Ergebnis wird auf einen sinnvollen Bereich geklemmt (mindestens 24 px, höchstens 2× BASE_BLOCK_SIZE).
	 * </p>
	 *
	 * @param availW verfügbare Breite in Pixeln
	 * @param availH verfügbare Höhe in Pixeln
	 * @return berechnete Kachelgröße in Pixeln
	 * @author Marc
	 */

	private int computeBlockSize(int availW, int availH) {
		// Tool-Leiste oben abziehen, damit das Grid passt
		int toolH = (toolPnl != null) ? toolPnl.getPreferredSize().height : 0;
		int gridW = availW;
		int gridH = Math.max(0, availH - toolH);

		// width fitting
		int byWidth = gridW / COLS;
		
		// height fitting
		int byHeight = gridH / ROWS;
		
		int size = Math.min(byWidth, byHeight);
		size = Math.max(24, Math.min(size, (int) Math.floor(BASE_BLOCK_SIZE * 2.0)));
		return size;
	}

	/**
	 * Reagiert auf Größenänderungen des Fensters und baut die Darstellung konsistent neu auf.
	 * <p>
	 * Schritte:
	 * <ol>
	 *   <li>Neue {@code blockSize} via {@link #computeBlockSize(int, int)} berechnen.</li>
	 *   <li>Für jede Zelle die PreferredSize setzen und das Block-Icon neu skalieren.</li>
	 *   <li>Spieler-Icon entsprechend neu skalieren und das {@code playerLbl} in der aktuellen Zelle platzieren.</li>
	 *   <li>{@link #applyPreferredWorldSize()} aufrufen, anschließend {@code revalidate()} und {@code repaint()}.</li>
	 * </ol>
	 * Führt nur Arbeit aus, wenn sich die {@code blockSize} tatsächlich geändert hat.
	 * </p>
	 * 
	 * @author Marc
	 */

	private void onResizeRebuild() {
		int newSize = computeBlockSize(getWidth(), getHeight());
		if (newSize == blockSize)
			return;
		blockSize = newSize;

		// Welt neu skalieren
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				JLabel lbl = worldLabels[r][c];
				if (lbl == null)
					continue;
				lbl.removeAll();
				lbl.setPreferredSize(new Dimension(blockSize, blockSize));
				BlockModel block = world[r][c];
				if (block != null && block.getId() != 0) {
					ImageIcon icon = getScaledIcon(block.getTextureImagePath(), blockSize, false);
					lbl.add(new JLabel(icon), BorderLayout.CENTER);
					lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				} else {
					lbl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
				}
			}
		}

		// Spieler neu setzen
		if (playerLbl != null) {
			ImageIcon playerIcon = getScaledIcon("/res/img/player_IDLE_72px.gif", blockSize, true);
			playerLbl.setIcon(playerIcon);
			playerLbl.setPreferredSize(new Dimension(blockSize, blockSize));
			worldLabels[playerRow][playerCol].setLayout(new BorderLayout());
			worldLabels[playerRow][playerCol].add(playerLbl, BorderLayout.CENTER);
		}

		applyPreferredWorldSize();
		blockPnl.revalidate();
		blockPnl.repaint();
	}

	/**
	 * Gibt ein skaliertes (und gecachtes) {@link ImageIcon} für den angegebenen Ressourcenpfad zurück.
	 * <p>
	 * Unterstützt:
	 * <ul>
	 *   <li><b>PNG</b>: Skalierung mit Nearest-Neighbor (scharfe Pixelart).</li>
	 *   <li><b>GIF (animiert)</b>: einfache Laufzeit-Skalierung; je nach JVM können Animationsdetails variieren.</li>
	 * </ul>
	 * Ergebnisse werden per Key {@code "path@size#gif|#static"} im Speicher gecacht, um
	 * erneute Decodes/Skalierungen zu vermeiden.
	 * </p>
	 *
	 * @param path Klassenpfad zur Ressource (z. B. {@code "/res/img/player_IDLE_72px.gif"})
	 * @param size Zielgröße (Breite = Höhe) in Pixeln
	 * @param isAnimatedGif {@code true}, wenn es sich um ein animiertes GIF handelt
	 * @return skaliertes {@link ImageIcon} oder {@code null}, wenn die Ressource nicht gefunden wurde
	 * @author Marc
	 */

	private ImageIcon getScaledIcon(String path, int size, boolean isAnimatedGif) {
		String key = path + "@" + size + (isAnimatedGif ? "#gif" : "#static");
		ImageIcon cached = iconCache.get(key);
		if (cached != null)
			return cached;

		URL url = getClass().getResource(path);
		if (url == null)
			return null;

		if (isAnimatedGif) {
			// Einfache Skalierung für GIFs (in der Praxis meist ok)
			ImageIcon raw = new ImageIcon(url);
			if (size == BASE_BLOCK_SIZE) {
				iconCache.put(key, raw);
				return raw;
			}
			Image scaled = raw.getImage().getScaledInstance(size, size, Image.SCALE_FAST);
			ImageIcon result = new ImageIcon(scaled);
			iconCache.put(key, result);
			return result;
		} else {
			// Statische PNGs → nearest-neighbor (Pixelart)
			try {
				BufferedImage src = javax.imageio.ImageIO.read(url);
				BufferedImage dst = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = dst.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
				g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
				g.drawImage(src, 0, 0, size, size, null);
				g.dispose();
				ImageIcon result = new ImageIcon(dst);
				iconCache.put(key, result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				ImageIcon fallback = new ImageIcon(url);
				iconCache.put(key, fallback);
				return fallback;
			}
		}
	}
}
