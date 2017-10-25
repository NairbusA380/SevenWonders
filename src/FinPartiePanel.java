import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FinPartiePanel extends JPanel {

	Frame frame;

	public FinPartiePanel(Frame frame){
		this.frame = frame;
	}

	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D)g;
		BufferedImage finImage = null;
		if (finImage == null){
			try {
				finImage = ImageIO.read(getFinImage("feuilleScore.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		drawable.drawImage(finImage, 0, 0, frame.getWidth()-16, frame.getHeight()-39, null);

		for (int i = 0; i < Game.getNbPlayer(); i++){
			Player p = null;
			try {
				p = Game.getPlayer(i);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int x = (int)((235+144*i)*frame.pourcentageX);
			int yBase = (int)(57*frame.pourcentageY);
			int increment = (int)(59*frame.pourcentageY);
			int centrage = (int)(20*frame.pourcentageX);
			drawable.drawString(p.getName(), x, yBase);
			drawable.drawString(""+p.countWarPoints(), x+centrage, yBase+increment);
			drawable.drawString(""+p.getGold()/3, x+centrage, yBase+increment*2);
			drawable.drawString(""+p.countStepPoints(), x+centrage, yBase+increment*3);
			drawable.drawString(""+p.countBluePoints(), x+centrage, yBase+increment*4);
			drawable.drawString(""+p.countYellowPoints(), x+centrage, yBase+increment*5);
			drawable.drawString(""+p.countPurplePoints(), x+centrage, yBase+increment*6);
			drawable.drawString(""+p.countGreenPoints(), x+centrage, yBase+increment*7);
			int total = p.countWarPoints()+p.getGold()+p.countStepPoints()+p.countBluePoints()+p.countYellowPoints()+p.countPurplePoints()+p.countGreenPoints();
			drawable.drawString(""+total, x+centrage, yBase+increment*8);
		}
	}
	private URL getFinImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
}
