import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ArrowButton extends JButton {

	boolean isLeft;
	boolean isHover;
	
	public ArrowButton(boolean isLeft){
		super();
		this.isLeft = isLeft;
		this.isHover = false;
		setBorderPainted(false);
		setContentAreaFilled(false);
		addMouseListener(new ArrowListener(this));
	}
	
	public void paintComponent(Graphics g){
		Graphics2D drawable = (Graphics2D)g;
		BufferedImage image = null;
		String nameOfFile = "";
		if (isLeft){
			nameOfFile = "left";
		}else{
			nameOfFile = "right";
		}
		if (isHover){
			nameOfFile +="Hover";
		}
		nameOfFile += ".png";
		
		try {
			image = ImageIO.read(getArrowImage(nameOfFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawable.drawImage(image, 0, 0, null);
		
	}
	
	private URL getArrowImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
}
