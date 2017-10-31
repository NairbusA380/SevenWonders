import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScientifiquePanel extends JPanel{
	
	Scientifique scientifique;
	BufferedImage scientifiqueImage = null;
	
	public ScientifiquePanel(Scientifique s){
		this.scientifique = s;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;

		try {
			scientifiqueImage = ImageIO.read(getScientifiqueImage(scientifique.toString().toLowerCase()+".png"));
		} catch (IOException e) {
		}
		drawable.drawImage(scientifiqueImage, 0, 0, null);
	}

	private URL getScientifiqueImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Scientifique/"+nom);
	}
}
