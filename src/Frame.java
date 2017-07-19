import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame{

	DrawingPanel background;
	ArrayList<StepArea> stepAreas;
	DrawingPanel container;
	Game game;

	public Frame(Game game){
		super("Seven Wonders");
		this.game = game;
		this.setPreferredSize(new Dimension(1237, 576));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		Wonder activeWonder = Game.getPlayerTurn().getWonder();

		stepAreas = new ArrayList<StepArea>();
		for (int i = 0; i < activeWonder.getNbSteps(); i++){
			Step step = activeWonder.getStep()[i];
			StepArea stepArea = new StepArea(step);
			stepArea.setBackground(new Color(0, 0, 0, 0));
			stepArea.setPreferredSize(new Dimension(256, 115));
			stepAreas.add(stepArea);
		}

		//Dessin de l'image de fond
		container = new DrawingPanel(this, activeWonder, game);
		container.setLayout(null);
		
		//Dessin des étapes
		
		int i = 0;
		for (StepArea s : stepAreas){
			StepPanel step = new StepPanel();
			step.setLayout(null);
			
			step.setBackground(new Color(0, 0, 0, 0));
			step.setPreferredSize(new Dimension(256, 115));
			
			StepInstructionLabel l = new StepInstructionLabel(s.getStep().toString());
			l.setForeground(Color.WHITE);
			s.setBounds(0, 0, 256, 115);
			step.add(s);
			l.setBounds(0, 125, 256, 0);
			step.add(l);
			
			step.setBounds(100+380*i, 432, 256, 115);
			
			step.setPreferredSize(new Dimension(256, 245));
			
			container.add(step);
			step.addMouseListener(new StepAreaListener(s, l, step, this));
			i++;
		}
		
		// Dessin de la ressource
		
		RessourcePanel ressource = new RessourcePanel(activeWonder);
		ressource.setBounds(0, 0, 199, 80);
		
		container.add(ressource);
		
		this.setContentPane(container);
		this.pack();
		this.repaint();
	}
	
	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
}
