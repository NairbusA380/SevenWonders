import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class DoubleBufferedPanel extends JPanel {

	BufferedImage image;

	public DoubleBufferedPanel(BufferedImage image){
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		drawable.drawImage(image, 0, 0, this);
	}
}