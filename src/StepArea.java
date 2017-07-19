import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;

public class StepArea extends JPanel{

	private Step step;
	BufferedImage stepImage;

	public StepArea(Step step){
		this.step = step;
		stepImage = null;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		if (stepImage == null){
			try {
				stepImage = ImageIO.read(getImage(step.getURL()));
			} catch (IOException e) {
				System.err.println(this.step.toString());
				e.printStackTrace();
			}
		}
		drawable.drawImage(stepImage, 0, 0, this);
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
