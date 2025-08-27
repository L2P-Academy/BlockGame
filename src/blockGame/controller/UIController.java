package blockGame.controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;

/**
 * Controller for generic UI elements of the game
 */
public class UIController {
	
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
		
		/**
		 * Beautifies a table with Font, size, back-/foreground
		 * @param desired table for better UI in game-style
		 */
	    public static void beautifyTable(JTable table) {
	        table.setFont(FontLoader.loadPixelFont(18f));
	        table.setRowHeight(45);
	        table.getTableHeader().setFont(FontLoader.loadPixelFont(18f));
	        table.getTableHeader().setBackground(Color.DARK_GRAY);
	        table.getTableHeader().setForeground(Color.WHITE);
	        table.setForeground(Color.BLACK);
	    }
}
