import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AgePanel extends JPanel {

	BufferedImage ageImage = null;
	int age;
	
	public AgePanel(int age){
		this.age = age;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		try {
			ageImage = ImageIO.read(getAgeImage("age"+age+".png"));
		} catch (IOException e) {
			System.err.println("eee");
		}
		drawable.drawImage(ageImage, 0, 0, null);
	}
	
	private URL getAgeImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Age/"+nom);
	}
}
