import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StepInstructionLabel extends JLabel{
	
	
	public StepInstructionLabel(String label){
		super(label);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		BufferedImage image = null;
		try {
			image = ImageIO.read(getImage("blackTransparent.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawable.setBackground(new Color(0, 0, 0, 175));
		drawable.drawImage(image, 0, 0, null);
		drawable.drawRect( 0, 0, this.getWidth()-1, this.getHeight()-1);
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
	
}
