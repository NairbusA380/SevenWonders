import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{

	Frame frame; //Fenetre de jeu
	Wonder wonder;
	BufferedImage wonderImage;
	Game game;
	BufferedImage imageFond;

	public DrawingPanel(Frame f, Wonder wonder, Game game){
		super();
		this.frame = f;
		this.wonder = wonder;
		wonderImage = null;
		this.game = game;
		this.imageFond = null;
	}

	public DrawingPanel(Frame f, Wonder wonder, LayoutManager manager, Game game){
		super(manager);
		this.frame = f;
		this.wonder = wonder;
		wonderImage = null;
		this.game = game;
		this.imageFond = null;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;

		if (imageFond == null){
			if (wonderImage == null){
				try {
					wonderImage = ImageIO.read(getWonderImage(this.wonder.getURL()));
				} catch (IOException e) {
					System.err.println("L'image de la merveille n'existe pas");
					e.printStackTrace();
				}
			}
			imageFond = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
			imageFond.getGraphics().drawImage(wonderImage, 0, 0, this);

			int i = 0;

			for (FirstAgeCard c : game.firstAgeCards){
				BufferedImage cardImage = null;
				if (cardImage == null){
					try {
						cardImage = ImageIO.read(getFirstCardImage(c.image));
					} catch (IOException e) {
						System.err.println("L'image de la carte n'existe pas");
						e.printStackTrace();
					}
				}
				imageFond.getGraphics().drawImage(cardImage, 350+100*i, 50+20*i, 150, 232, this);
				
				i++;
			}
		}
		drawable.drawImage(imageFond, 0, 0, this);
	}

	private URL getWonderImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}

	private URL getFirstCardImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Cartes/Age1/"+nom);
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
}
