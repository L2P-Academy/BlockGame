package blockGame.controller;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class FontLoader {
	public static Font loadPixelFont(float size) {
		try {
			InputStream is = FontLoader.class.getResourceAsStream("/res/fonts/PressStart2P-Regular.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		} catch (Exception e) {
			e.printStackTrace();
			return new Font("Monospaced", Font.PLAIN, (int) size);
		}
	}

}
