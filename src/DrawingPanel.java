import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{

	Player player;
	BufferedImage wonderImage;
	BufferedImage imageFond;

	public DrawingPanel(Player player){
		super();
		this.player = player;
		wonderImage = null;
		this.imageFond = null;
	}

	public DrawingPanel(Player player, LayoutManager manager){
		super(manager);
		this.player = player;
		wonderImage = null;
		this.imageFond = null;
	}
	
	public void paintComponent(Graphics g){
		Wonder wonder = player.getWonder();
		Graphics2D drawable = (Graphics2D)g;

		if (player.getDoubleBuffer() == null){
			BufferedImage buffer = new BufferedImage((int)(1253*Frame.pourcentageX), (int)(615*Frame.pourcentageY), BufferedImage.TYPE_INT_RGB);
			Graphics2D drawableBufferedImage = (Graphics2D)buffer.getGraphics();

			/* Dessin de l'image de fond */
			drawBackgroundImage(drawableBufferedImage, wonder);

			//Dessin de l'information du joueur

			drawableBufferedImage.setColor(Color.CYAN);
			drawableBufferedImage.drawString(Game.getPlayerToShow().getName(), (int)(1100*Frame.pourcentageX), (int)(50*Frame.pourcentageY));

			/* dessin de l'image buffer */
			player.setDoubleBuffer(buffer);
		}
		drawable.drawImage((Image)player.getDoubleBuffer(), 0, 0, null);			
	}

	private void drawBackgroundImage(Graphics2D drawable, Wonder wonder){
		if (imageFond == null){
			if (wonderImage == null){
				try {
					wonderImage = ImageIO.read(getWonderImage(wonder.getURL()));
				} catch (IOException e) {
					System.err.println("L'image de la merveille n'existe pas");
					e.printStackTrace();
				}
			}
		}
		drawable.drawImage(wonderImage, 0, 0, Frame.frame.getWidth(), Frame.frame.getHeight(), null);

	}

	private URL getWonderImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player p){
		this.player = p;
		this.paintComponent(this.getGraphics());
	}
}
