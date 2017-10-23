import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

import org.omg.CORBA.IdentifierHelper;

public class Frame extends JFrame{

	DrawingPanel background;
	ArrayList<StepArea> stepAreas;
	DrawingPanel container;
	ArrayList<CardArea> cardAreas = new ArrayList<CardArea>(), playedCardAreas = new ArrayList<CardArea>();;
	KeyListener keyListener;
	RessourcePanel ressourcePanel;

	public Frame(){
		super("Seven Wonders");
		this.setPreferredSize(new Dimension(1237, 576));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
	}

	public void draw(){
		if (keyListener == null){
			keyListener = new KeyListener(this);
			this.addKeyListener(keyListener);
		}
		Wonder activeWonder = Game.getActiveWonder();
		Player activePlayer = Game.getPlayerTurn();
		stepAreas = new ArrayList<StepArea>();
		stepAreas = this.createStepsImage(activeWonder);
		ressourcePanel = new RessourcePanel(activeWonder);
		playedCardAreas = createPlayedCardsAreas(activePlayer);
		
		//Dessin de l'image de fond
		container = new DrawingPanel(this, activePlayer);
		container.setLayout(null);

		//Dessin des étapes (image + texte)		
		for (StepPanel step : drawSteps(createStepsImage(activeWonder))){
			container.add(step);
		}

		//Dessin de l'information de l'age
		JLabel age = new JLabel("Age "+(Game.getCurrentAge()));
		age.setBounds(100, 75, 50, 20);
		age.setForeground(Color.CYAN);
		container.add(age);

		//Dessin des cartes piochées
		ArrayList<CardArea> drawed = drawDrawedCards();
		ArrayList<CardArea> cardAreas = new ArrayList<CardArea>();
		for (CardArea cardArea : drawed){
			container.add(cardArea);
			cardAreas.add(cardArea);
		}

		//Dessin des cartes déjà jouées

		for (CardArea cardArea : playedCardAreas ){
			container.add(cardArea);
		}

		this.setContentPane(container);
		this.pack();
		this.repaint();
	}

	public void cardChosen(CardArea cardArea){
		container.remove(cardArea);
		cardAreas.remove(cardArea);
	}

	private ArrayList<CardArea> drawDrawedCards(){
		Player player = Game.getPlayerTurn();
		ArrayList<CardArea> cardAreas = new ArrayList<CardArea>();
		int i = 0;
		for (i = 0; i < player.getDrawedCards().size(); i++){
			CardArea cardArea;
			Card card = player.getDrawedCards().get(i);
			cardArea = new CardArea(card, player.canPlay(card), false);
			cardArea.setBounds(200+30*i, 100, card.getMiniWidth(), card.getMiniHeight());
			cardArea.setOpaque(false);
			cardArea.addMouseListener(new PlayedCardListener(this, card, cardArea.enabled));
			cardAreas.add(cardArea);
		}
		return cardAreas;
	}


	ArrayList<CardArea> createPlayedCardsAreas(Player player){
		ArrayList<CardArea> cardAreas = new ArrayList<CardArea>();
		int bleu = 0, vert = 0, jaune = 0, rouge = 0, marron = 0, gris = 0, violet = 0;
		int heightStart = 0;
		for (Card card : player.getPlayedCards()){
			CardArea cardArea = new CardArea(card, true, false);
			int widthStart = 0;
			switch (card.getColor()){
			case ("blue"):
				widthStart = 850+bleu*15;
				heightStart = 100+bleu*15;
				bleu++;
			break;
			case ("red"):
				widthStart = 925+rouge*15;
				heightStart = 100+rouge*15;
				rouge++;
			break;
			case ("brown"):
				heightStart = 100+marron*15;
				widthStart = 700+marron*15;
				marron++;
			break;
			case ("grey"):
				widthStart = 775+gris*15;
				heightStart = 100+gris*15;
				gris++;
			break;
			case ("green"):
				widthStart = 1000+vert*15;
				heightStart = 100+vert*15;
					vert++;
			break;
			case ("yellow"):
				widthStart = 1075;
				heightStart = 100+jaune*15;
				jaune++;
			break;
			case ("purple"):
				widthStart = 1150+violet*15;
				heightStart = 100+violet*15;
				violet++;
			break;
			default:
				break;
			}
			cardArea.setBounds(widthStart, heightStart, card.getMiniWidth(), card.getMiniHeight());
			
			cardArea.addMouseListener(new PlayedCardListener(this, card, true));
			cardArea.setOpaque(false);
			cardAreas.add(cardArea);
		}
		return cardAreas;
	}

	private ArrayList<StepArea> createStepsImage(Wonder wonder){
		ArrayList<StepArea> stepAreas = new ArrayList<StepArea>();
		for (int i = 0; i < wonder.getNbSteps(); i++){
			Step step = wonder.getStep()[i];
			StepArea stepArea = new StepArea(step);
			stepArea.setBackground(new Color(0, 0, 0, 0));
			stepArea.setPreferredSize(new Dimension(256, 115));
			stepAreas.add(stepArea);
		}
		return stepAreas;
	}

	private ArrayList<StepPanel> drawSteps(ArrayList<StepArea> stepAreas){
		ArrayList<StepPanel> stepPanels = new ArrayList<StepPanel>();
		int i = 0;
		int nbSteps = stepAreas.size();
		for (StepArea s : stepAreas){
			boolean validated = s.getStep().validated;
			StepPanel step = new StepPanel();
			step.setLayout(null);

			if (!validated){
				step.setBackground(new Color(0, 0, 0, 0));
			}else{
				step.setBackground(new Color(0, 150, 0, 0));
			}
			step.setPreferredSize(new Dimension(256, 115));

			StepInstructionLabel l = new StepInstructionLabel(s.getStep().toString());
			l.setForeground(Color.WHITE);
			s.setBounds(0, 0, 256, 115);
			step.add(s);
			l.setBounds(0, 125, 256, 0);
			step.add(l);
			switch (nbSteps){
			case 2:
				step.setBounds(241+390*i, 425, 256, 115);
				break;
			case 3:
				step.setBounds(142+348*i, 425, 256, 115);
				break;
			case 4:
				step.setBounds(42+298*i, 425, 256, 115);
				break;
			}
			step.setPreferredSize(new Dimension(256, 245));
			stepPanels.add(step);
			step.addMouseListener(new StepAreaListener(s, l, step, this));
			i++;
		}
		return stepPanels;
	}

	void passToNextPlayer(){
		this.setContentPane(new JPanel());
		this.draw();
	}

	void nextAge(){
		this.draw();
		this.pack();
		this.repaint();
	}

	void interAge(){
		this.setContentPane(new JPanel());
		this.repaint();
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/"+nom);
	}
}
