import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RessourcePanel extends JPanel{
	
	Wonder wonder;
	BufferedImage ressourceImage;
	
	public RessourcePanel(Wonder wonder){
		this.wonder = wonder;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;

		if (ressourceImage == null){
			String ressource = ""+wonder.getName().substring(0,  wonder.getName().length()-1)+"/Face"+wonder.getName().charAt(wonder.getName().length()-1)+"/Ressource.png";
			try {
				ressourceImage = ImageIO.read(getImage(ressource));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		drawable.drawImage(ressourceImage, 0, 0, this);
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}

	public BufferedImage getRessourceImage() {
		return ressourceImage;
	}

	public void setRessourceImage(BufferedImage ressourceImage) {
		this.ressourceImage = ressourceImage;
	}
	
	
	
}
