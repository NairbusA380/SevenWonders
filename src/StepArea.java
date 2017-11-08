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
				e.printStackTrace();
			}
		}
		drawable.drawImage(stepImage, 0, 0, this);
		if (step.isValidated()){
			BufferedImage check = null;
			try {
				check = ImageIO.read(getImage("check.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			drawable.drawImage(check, 0, 0, this);
		}else{
			Step previous = step.previousStep;
			if (previous != null && !previous.isValidated()){
				BufferedImage croix = null;
				try {
					croix = ImageIO.read(getImage("croix.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				drawable.drawImage(croix, 0, 19, this);
			}
		}
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}

	public void setStepImage(BufferedImage stepImage) {
		this.stepImage = stepImage;
	}
	
	public BufferedImage getStepImage(){
		return this.stepImage;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
