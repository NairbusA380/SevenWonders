import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class StepInstructionLabel extends JLabel{
	
	
	public StepInstructionLabel(String label){
		super(label);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		
		drawable.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	}
	
}
