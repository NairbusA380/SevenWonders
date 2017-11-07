import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StepInstructionPanel extends JPanel{

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		BufferedImage image = null;
		try {
			image = ImageIO.read(getImage("transparent.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawable.drawImage(image, 0, 0, this);
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
	
}
