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
	String url;
	Ressource ressource;
	boolean[] hasBeenPurchased = new boolean[Game.getNbPlayer()];


	public RessourcePanel(Wonder wonder, Ressource ressource){
		this.wonder = wonder;
		this.url = null;
		this.ressource = ressource;
		for (int i = 0; i < hasBeenPurchased.length; i++){
			hasBeenPurchased[i] = false;
		}
	}

	public RessourcePanel(Wonder wonder, String url, Ressource ressource){
		this.wonder = wonder;
		this.url = url;
		this.ressource = ressource;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;

		if (url == null){
			this.url = ressource.toString().toLowerCase()+".png";
		}
		try {
			ressourceImage = ImageIO.read(getRessourceImage(url));
		} catch (IOException e) {
			System.err.println(url);
		}
		drawable.drawImage(ressourceImage, 0, 0, null);
	}

	public Wonder getWonder() {
		return wonder;
	}

	public void setWonder(Wonder wonder) {
		this.wonder = wonder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private URL getRessourceImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Ressources/"+nom);
	}

	public BufferedImage getRessourceImage() {
		return ressourceImage;
	}

	public void setRessourceImage(BufferedImage ressourceImage) {
		this.ressourceImage = ressourceImage;
	}

	public void setHasBeenPurchased(int playerPlace, boolean b) {
		this.hasBeenPurchased[playerPlace] = b;		
	}
	
	public void setHasBeenPurchased(boolean[] b) {
		this.hasBeenPurchased = b;		
	}

	public boolean hasBeenPurchased(int playerPlace) {
		return hasBeenPurchased[playerPlace];
	}

}
