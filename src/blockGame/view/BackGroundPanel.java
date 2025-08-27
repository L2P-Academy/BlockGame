// Christoph

package blockGame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Specialized JPanel for Backgrounds in the Game, uses a Image when constructed
 *
 */
public class BackGroundPanel extends JPanel{
	private static final long serialVersionUID = 2594379063999212873L;
	private Image backgroundImage;
	
	public BackGroundPanel(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		setLayout(new BorderLayout());	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
	}
}