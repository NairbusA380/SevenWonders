import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame{

	static DrawingPanel background;
	static ArrayList<StepArea> stepAreas;
	static DrawingPanel container;
	static ArrayList<CardArea> cardAreas = new ArrayList<CardArea>(), playedCardAreas = new ArrayList<CardArea>();;
	static ArrayList<StepPanel> stepPanel = new ArrayList<StepPanel>();
	static KeyListener keyListener;
	static ArrayList<RessourcePanel>[] ressourcePanels = new ArrayList[Game.getNbPlayer()];
	static double pourcentageX;
	static double pourcentageY;
	static ArrowButton right, left;
	static JOptionPane choixUtilisateur;
	static ArrayList<ScientifiquePanel> scientifiquePanels = new ArrayList<ScientifiquePanel>();
	static JFrame frame;
	static AgePanel agePanel;

	public Frame(){
		frame = new JFrame("Seven Wonders");
		frame.setPreferredSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		frame.setSize(frame.getPreferredSize());
		JFrame.setDefaultLookAndFeelDecorated(false);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addComponentListener(new FrameListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		for (int i = 0; i < Game.getNbPlayer(); i++){
			ressourcePanels[i] = new ArrayList<RessourcePanel>();
		}
	}

	//public void draw(){
	public static void entireDraw(){
//		for (RessourcePanel rp : ressourcePanels[Game.getPlayerTurn().getLeftPlayer().getPlace()]){
//			System.out.println("Je peux acheter la ressource "+rp.ressource+" à gauche ? "+!rp.hasBeenPurchased[Game.getPlayerTurn().getPlace()]);
//		}
//		for (RessourcePanel rp : ressourcePanels[Game.getPlayerTurn().getRightPlayer().getPlace()]){
//			System.out.println("Je peux acheter la ressource "+rp.ressource+" à droite ? "+!rp.hasBeenPurchased[Game.getPlayerTurn().getPlace()]);
//		}
		
		
		if (keyListener == null){
			keyListener = new KeyListener();
			frame.addKeyListener(keyListener);
		}
		Wonder activeWonder = Game.getShowingWonder();
		Player playerToShow = Game.getPlayerToShow();
		stepAreas = new ArrayList<StepArea>();
		stepAreas = createStepsImage(activeWonder);
		playedCardAreas = createPlayedCardsAreas(playerToShow);

		//Dessin de l'image de fond
		container = new DrawingPanel(playerToShow);
		container.setLayout(null);

		// Dessin des ressources disponibles
		ArrayList<RessourcePanel> panelsRessources = ressourcePanels[playerToShow.getPlace()];
		for (int i = 0; i < playerToShow.getRessources().size(); i++){
			Ressource ressource = (Ressource)playerToShow.getRessources().get(i);
			RessourcePanel ressourcePanel = null;
			if (panelsRessources.size() > i){
				ressourcePanel = panelsRessources.get(i);
			}else{
				ressourcePanel = new RessourcePanel(activeWonder, ressource);
				ressourcePanels[playerToShow.getPlace()].add(ressourcePanel);
			}

			if ((playerToShow.equals(Game.getPlayerTurn().getLeftPlayer()) || (playerToShow.equals(Game.getPlayerTurn().getRightPlayer()))) && !ressource.equals(Ressource.PIECE)){
				ressourcePanel.addMouseListener(new RessourceListener(ressourcePanel));
			}
		}
		ArrayList<RessourcePanel> purchasedPanels = new ArrayList<RessourcePanel>();
		if (playerToShow.equals(Game.getPlayerTurn())){
			System.out.println("Ajout des ressources achetées");
			int i = playerToShow.getRessources().size();
			for (Object o : playerToShow.getPurchasedRessources()){
				Ressource ressource = (Ressource)o;
				RessourcePanel ressourcePanel = new RessourcePanel(activeWonder, ressource);
				ressourcePanel.addMouseListener(new RessourceListener(ressourcePanel));
				purchasedPanels.add(ressourcePanel);
				i++;
			}
		}
		dessinerRessources(ressourcePanels[playerToShow.getPlace()], purchasedPanels);

		// Dessin du scientifique

		int i = 0;
		for (Scientifique s : playerToShow.getScientifique()){
			ScientifiquePanel scientifiquePanel = new ScientifiquePanel(s);
			scientifiquePanel.setBounds(815+60*i, 10, 56, 55);
			scientifiquePanel.setOpaque(false);
			scientifiquePanels.add(scientifiquePanel);
			container.add(scientifiquePanel);
			i++;
		}

		//Dessin des étapes (image + texte)		
		for (StepPanel step : drawSteps(createStepsImage(activeWonder))){
			container.add(step);
		}

		//Dessin de l'information de l'age
		agePanel = new AgePanel(Game.getCurrentAge());
		agePanel.setBounds(550, 5, 300, 111);
		agePanel.setOpaque(false);
		container.add(agePanel);

		//Dessin des cartes piochées
		if (playerToShow.equals(Game.getPlayerTurn())){
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
		ArrowButton left = new ArrowButton(true);
		ArrowButton right = new ArrowButton(false);
		left.addMouseMotionListener(null);
		right.addMouseMotionListener(null);
		left.setBounds(10, 250, 75, 75);	
		right.setBounds(frame.getWidth()-85, 250, 75, 75);

		container.add(left);
		container.add(right);

		frame.setContentPane(container);
		frame.pack();
		frame.repaint();
	}

	public static void cardChosen(CardArea cardArea, int gold){
		container.remove(cardArea);
		cardAreas.remove(cardArea);
		int goldRemoved = 0;
		for (RessourcePanel rp : ressourcePanels[Game.getPlayerTurn().getPlace()]){
			if (goldRemoved < gold && rp.ressource.equals(Ressource.PIECE)){
				ressourcePanels[Game.getPlayerTurn().getPlace()].remove(rp);
				goldRemoved++;
			}
			if (goldRemoved >= gold){
				break;
			}
		}
	}

	private static ArrayList<CardArea> drawDrawedCards(){
		Player player = Game.getPlayerToShow();
		cardAreas = new ArrayList<CardArea>();
		int i = 0;
		for (i = 0; i < player.getDrawedCards().size(); i++){
			CardArea cardArea;
			Card card = player.getDrawedCards().get(i);
			cardArea = new CardArea(card, true, false);
			cardArea.setLocation((int)((200+30*i)*pourcentageX), (int)(100*pourcentageY));
			cardArea.setBounds((int)((200+30*i)*pourcentageX), (int)(100*pourcentageY), cardArea.getMiniWidth(), cardArea.getMiniHeight());
			cardArea.setOpaque(false);
			cardArea.addMouseListener(new PlayedCardListener(card));
			cardAreas.add(cardArea);
		}
		return cardAreas;
	}


	static ArrayList<CardArea> createPlayedCardsAreas(Player player){
		ArrayList<CardArea> cardAreas = new ArrayList<CardArea>();
		int bleu = 0, vert = 0, jaune = 0, rouge = 0, marron = 0, gris = 0, violet = 0;
		int heightStart = 0;
		for (Card card : player.getPlayedCards()){
			CardArea cardArea = new CardArea(card, false, false);
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
			cardArea.setBounds(widthStart, heightStart, cardArea.getMiniWidth(), cardArea.getMiniHeight());

			cardArea.addMouseListener(new PlayedCardListener(card));
			cardArea.setOpaque(false);
			cardAreas.add(cardArea);
		}
		return cardAreas;
	}

	private static ArrayList<StepArea> createStepsImage(Wonder wonder){
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

	private static ArrayList<StepPanel> drawSteps(ArrayList<StepArea> stepAreas){
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
			int y = frame.getHeight()-145;
			switch (nbSteps){
			case 2:
				step.setBounds(291+390*i, y, 256, 115);
				break;
			case 3:
				step.setBounds(192+348*i, y, 256, 115);
				break;
			case 4:
				step.setBounds(92+298*i, y, 256, 115);
				break;
			}
			step.setPreferredSize(new Dimension(256, 245));
			stepPanel.add(step);
			step.addMouseListener(new StepAreaListener(s, l, step));
			i++;
		}
		return stepPanel;
	}
	
	static void dessinerRessources(ArrayList<RessourcePanel> ressources, ArrayList<RessourcePanel> purchasedRessources){
		Container c = frame.getContentPane();
		int i = 0;
		ArrayList<RessourcePanel> r = ressources;
		r.addAll(purchasedRessources);
		for (RessourcePanel rp : r){
			Ressource ress = rp.ressource;
			if (ress.equals(Ressource.PIECE)){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				container.add(rp);
				i++;
			}
		}
		for (RessourcePanel rp : r){
			Ressource ress = rp.ressource;
			if (ress.isProduitManufacture()){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				container.add(rp);
				i++;
			}
		}
		for (RessourcePanel rp : r){
			Ressource ress = rp.ressource;
			if (!ress.isProduitManufacture() && !ress.equals(Ressource.PIECE)){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				container.add(rp);
				i++;
			}
		}
		frame.setContentPane(c);
	}

	static void passToNextPlayer(){
		/*for (RessourcePanel rp : this.ressourcePanels[Game.getPlayerTurn().getPlace()]){
			rp.setHasBeenPurchased(Game.getPlayerTurn().getPlace(), false);
		}*/
		entireDraw();
	}

	static void nextAge(){
		entireDraw();
		frame.pack();
		frame.repaint();
	}

	static void interAge(){
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
		ok.addActionListener(new InterAgeListener());
		panel.add(ok);
		frame.setContentPane(panel);
		frame.repaint();
		frame.pack();
	}

	static void finPartie(){
		FinPartiePanel finPanel= new FinPartiePanel();
		finPanel.setLayout(null);
		frame.setContentPane(finPanel);
		frame.repaint();
		frame.pack();
	}

	public static void removeToRessourcePanel(int place, int playerPosition){
		ressourcePanels[playerPosition].remove(place);
	}

	public static void addToRessourcePanel(Ressource ressource, Wonder wonder, int place, int playerPosition){
		RessourcePanel ressourcePanel = new RessourcePanel(wonder, ressource);
		ressourcePanels[playerPosition].add(place, ressourcePanel);
	}

	public static ArrayList<RessourcePanel>[] getRessourcePanels() {
		return ressourcePanels;
	}

	public static void setRessourcePanels(ArrayList<RessourcePanel>[] rp) {
		ressourcePanels = rp;
	}

	public static ArrayList<RessourcePanel> getRessourcePanel(int playerPlace) {
		return ressourcePanels[playerPlace];
	}

	public static void setRessourcePanel(ArrayList<RessourcePanel> ressourcePanel, int playerPlace){
		ressourcePanels[playerPlace] = ressourcePanel;
	}

	public static void achatRessource(RessourcePanel ressourcePanel, int gold) {
		for (int i = 0; i < gold; i++){
			ressourcePanels[Game.getPlayerTurn().getPlace()].remove(i);
		}
	}
	
	public static void removeAllPurchase() {
		for (int i = 0; i < Game.getNbPlayer(); i++){
			for (RessourcePanel rp : ressourcePanels[i]){
				boolean[] hasBeenPurchased = new boolean[Game.getNbPlayer()];
				for (int j = 0; j < hasBeenPurchased.length; j++){
					hasBeenPurchased[j] = false;
				}
				rp.setHasBeenPurchased(hasBeenPurchased);
			}
		}
	}
}
