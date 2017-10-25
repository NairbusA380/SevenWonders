import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame{

	DrawingPanel background;
	ArrayList<StepArea> stepAreas;
	DrawingPanel container;
	ArrayList<CardArea> cardAreas = new ArrayList<CardArea>(), playedCardAreas = new ArrayList<CardArea>();;
	ArrayList<StepPanel> stepPanel = new ArrayList<StepPanel>();
	KeyListener keyListener;
	RessourcePanel ressourcePanel;
	double pourcentageX;
	double pourcentageY;
	ArrowButton right = new ArrowButton(false, this);

	public Frame(){
		super("Seven Wonders");
		this.setPreferredSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		this.setSize(this.getPreferredSize());
		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setResizable(false);
		this.addComponentListener(new FrameListener(this));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
	}

	public void draw(){
		if (keyListener == null){
			keyListener = new KeyListener(this);
			this.addKeyListener(keyListener);
		}
		Wonder activeWonder = Game.getShowingWonder();
		Player playerToShow = Game.getPlayerToShow();
		stepAreas = new ArrayList<StepArea>();
		stepAreas = this.createStepsImage(activeWonder);
		ressourcePanel = new RessourcePanel(activeWonder);
		playedCardAreas = createPlayedCardsAreas(playerToShow);

		//Dessin de l'image de fond
		container = new DrawingPanel(this, playerToShow);
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

		// Dessin des ressources dispo
		JLabel ressources = new JLabel(Game.getPlayerToShow().getRessources().toString());
		ressources.setBounds((int)(250*pourcentageX), 0, 200, 100);
		ressources.setForeground(Color.YELLOW);
		container.add(ressources);

		//Dessin des cartes piochées
		if (Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			ArrayList<CardArea> drawed = drawDrawedCards();
			ArrayList<CardArea> cardAreas = new ArrayList<CardArea>();
			for (CardArea cardArea : drawed){
				container.add(cardArea);
				cardAreas.add(cardArea);
			}
		}

		//Dessin des cartes déjà jouées

		for (CardArea cardArea : playedCardAreas ){
			container.add(cardArea);
		}

		// Dessin des fleches Gauche et Droite
		ArrowButton left = new ArrowButton(true, this);
		left.addMouseMotionListener(null);
		right.addMouseMotionListener(null);
		left.setBounds(10, 250, 75, 75);	
		right.setBounds(this.getWidth()-85, 250, 75, 75);

		container.add(left);
		container.add(right);

		this.setContentPane(container);
		this.pack();
		this.repaint();
	}

	public void cardChosen(CardArea cardArea){
		container.remove(cardArea);
		cardAreas.remove(cardArea);
	}

	private ArrayList<CardArea> drawDrawedCards(){
		Player player = Game.getPlayerToShow();
		cardAreas = new ArrayList<CardArea>();
		int i = 0;
		for (i = 0; i < player.getDrawedCards().size(); i++){
			CardArea cardArea;
			Card card = player.getDrawedCards().get(i);
			cardArea = new CardArea(card, player.canPlay(card), false);
			cardArea.setLocation((int)((200+30*i)*pourcentageX), (int)(100*pourcentageY));
			cardArea.setBounds((int)((200+30*i)*pourcentageX), (int)(100*pourcentageY), card.getMiniWidth(), card.getMiniHeight());
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
		stepPanel = new ArrayList<StepPanel>();
		int i = 0;
		int nbSteps = stepAreas.size();
		for (StepArea s : stepAreas){
			boolean validated = s.getStep().validated;
			StepPanel step = new StepPanel(nbSteps);
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
			Border b = l.getBorder();
			Border marginLeft = new EmptyBorder(0, 15, 0, 0);
			l.setBorder(new CompoundBorder(b, marginLeft));
			step.add(l);
			int y = 457;
			switch (nbSteps){
			case 2:
				step.setBounds(241+390*i, y, 256, 115);
				break;
			case 3:
				step.setBounds(142+348*i, y, 256, 115);
				break;
			case 4:
				step.setBounds(42+298*i, y, 256, 115);
				break;
			}
			step.setPreferredSize(new Dimension(256, 245));
			stepPanel.add(step);
			step.addMouseListener(new StepAreaListener(s, l, step, this));
			i++;
		}
		return stepPanel;
	}

	void passToNextPlayer(){
		this.draw();
	}

	void nextAge(){
		this.draw();
		this.pack();
		this.repaint();
	}

	void interAge(){
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(layout);
		JLabel[] pointsCombat = new JLabel[Game.getNbPlayer()];
		for (int i = 0; i <Game.getNbPlayer(); i++){
			Player p = null;
			try {
				p = Game.getPlayer(i);
				pointsCombat[i] = new JLabel(""+p.countWarPoints()+" points de combat");
				panel.add(new JLabel(p.getName()));
				panel.add(pointsCombat[i]);
			} catch (Exception e) {
				e.printStackTrace();
				panel.add(new JLabel("Erreur lors de la récupération du joueur"));
				JLabel erreur = new JLabel("Erreur lors de la récupération du joueur");
				panel.add(erreur);
			}
		}
		JButton ok = new JButton("OK");
		ok.addActionListener(new InterAgeListener(this));
		panel.add(ok);
		this.setContentPane(panel);
		this.repaint();
		this.pack();
	}

	void finPartie(){
		FinPartiePanel finPanel= new FinPartiePanel(this);
		finPanel.setLayout(null);
		this.setContentPane(finPanel);
		this.repaint();
		this.pack();
	}
}
