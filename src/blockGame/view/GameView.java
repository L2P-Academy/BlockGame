// Martin & Christoph

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Random;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GameView extends JFrame {
	private JPanel backgroundPnl, blockPnl, toolPnl;
	private static final int ROWS = 16;
	private static final int COLS = 32;
	private static final int BLOCK_SIZE = 32; // 64 ~ square
	private String imagePath = "/res/img/maingame_bg.png";
	
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



	public GameView() {
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
		toolPnl.setPreferredSize(new Dimension(100, 60));
		toolPnl.setBackground(Color.DARK_GRAY);
		
		ButtonGroup tools = new ButtonGroup();
		for(int i = 0; i < 10; i++) {
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
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void fillBlockPanelRandomly() {
		Random rng = new Random();
		for (int i = 0; i < ROWS * COLS; i++) {
			JPanel rngBlock = new JPanel();
			rngBlock.setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
			rngBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			// random colors
			rngBlock.setBackground(new Color(rng.nextInt(100, 255), rng.nextInt(100, 255), rng.nextInt(100, 255)));
			blockPnl.add(rngBlock);
		}
	}
}
