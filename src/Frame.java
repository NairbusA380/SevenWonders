import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			for (Ressource r : Game.getPlayer(i).getRessources()) {
				ressourcePanels[i].add(new RessourcePanel(r));
			}
		}
	}

	//public void draw(){
	public static void entireDraw(){
				
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

		ArrayList<RessourcePanel> purchasedPanels = new ArrayList<RessourcePanel>();
		if (playerToShow.equals(Game.getPlayerTurn())){
			int i = playerToShow.getRessources().size();
			for (Object o : playerToShow.getPurchasedRessources()){
				Ressource ressource = (Ressource)o;
				RessourcePanel ressourcePanel = new RessourcePanel(ressource);
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
			scientifiquePanel.setBounds(885+60*i, 10, 56, 55);
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

		for (CardArea cardArea : playedCardAreas){
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

//	public static void playCard(Player p, int gold){
//		if (gold > 0){
//			SevenWonders.getLogger().log(Level.INFO, "On supprime "+gold+" panels de ressource PIECE au joueur "+p.getName());
//		}
//		int goldRemoved = 0;
//		for (RessourcePanel rp : ressourcePanels[p.getPlace()]){
//			if (goldRemoved < gold && rp.getRessource().equals(Ressource.PIECE)){
//				ressourcePanels[p.getPlace()].remove(rp);
//				goldRemoved++;
//			}
//			if (goldRemoved >= gold){
//				break;
//			}
//		}
//	}

	private static ArrayList<CardArea> drawDrawedCards(){
		Player player = Game.getPlayerToShow();
		SevenWonders.getLogger().log(Level.INFO, "Dessin des "+player.getDrawedCards().size()+" cartes que le joueur "+player.getName()+" a en main");
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
				widthStart = 850;
			heightStart = 120+bleu*15;
			bleu++;
			break;
			case ("red"):
				widthStart = 925;
			heightStart = 120+rouge*15;
			rouge++;
			break;
			case ("brown"):
				heightStart = 120+marron*15;
			widthStart = 700;
			marron++;
			break;
			case ("grey"):
				widthStart = 775;
			heightStart = 120+gris*15;
			gris++;
			break;
			case ("green"):
				widthStart = 1000;
			heightStart = 120+vert*15;
			vert++;
			break;
			case ("yellow"):
				widthStart = 1075;
			heightStart = 120+jaune*15;
			jaune++;
			break;
			case ("purple"):
				widthStart = 1150;
			heightStart = 120+violet*15;
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
			stepArea.setPreferredSize(new Dimension(256, 134));
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
			step.setPreferredSize(new Dimension(256, 134));

			StepInstructionLabel l = new StepInstructionLabel(s.getStep().toString());
			l.setForeground(Color.WHITE);
			s.setBounds(0, 0, 256, 134);
			step.add(s);
			l.setBounds(0, 144, 256, 0);
			Border b = l.getBorder();
			Border marginLeft = new EmptyBorder(0, 15, 0, 0);
			l.setBorder(new CompoundBorder(b, marginLeft));
			step.add(l);
			int y = frame.getHeight()-163;
			switch (nbSteps){
			case 2:
				step.setBounds(291+390*i, y, 256, 134);
				break;
			case 3:
				step.setBounds(192+348*i, y, 256, 134);
				break;
			case 4:
				step.setBounds(92+298*i, y, 256, 134);
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
		Player playerToShow = Game.getPlayerToShow();
		int i = 0;
		ArrayList<RessourcePanel> r = (ArrayList<RessourcePanel>)ressources.clone();
		r.addAll(purchasedRessources);
		for (RessourcePanel rp : r){
			Ressource ress = rp.getRessource();
			if (ress.equals(Ressource.PIECE)){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				container.add(rp);
				i++;
			}
		}
		for (RessourcePanel rp : r){
			Ressource ress = rp.getRessource();
			if (ress.isProduitManufacture()){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				if (playerToShow.equals(Game.getPlayerTurn().getLeftPlayer()) || playerToShow.equals(Game.getPlayerTurn().getRightPlayer())){
					if (rp.getMouseListeners().length == 0) {
						rp.addMouseListener(new RessourceListener(rp));
					}
				}
				container.add(rp);
				i++;
			}
		}
		for (RessourcePanel rp : r){
			Ressource ress = rp.getRessource();
			if (!ress.isProduitManufacture() && !ress.equals(Ressource.PIECE)){
				rp.setBounds(15+60*i, 10, 56, 55);
				rp.setOpaque(false);
				if (playerToShow.equals(Game.getPlayerTurn().getLeftPlayer()) || playerToShow.equals(Game.getPlayerTurn().getRightPlayer())){
					if (rp.getMouseListeners().length == 0) {
						rp.addMouseListener(new RessourceListener(rp));
					}
				}
				container.add(rp);
				i++;
			}
		}
	}

	static void passToNextPlayer(){
		for (RessourcePanel rp : ressourcePanels[Game.getPlayerTurn().getPlace()]){
			rp.setHasBeenPurchased(Game.getPlayerTurn().getPlace(), false);
		}
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

	public static void addToRessourcePanel(Ressource ressource, int place, int playerPosition){
		RessourcePanel ressourcePanel = new RessourcePanel(ressource);
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

	public static void goldUpdate() {
		for (int i = 0; i < Game.getNbPlayer(); i++){
			Player p = null;
			try {
				p = Game.getPlayer(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int gold = p.getNbGoldEarnedThisTurn() - p.getNbGoldPayedThisTurn();
			if (gold < 0){
				for (int j = 0; j < gold; i++){
					ressourcePanels[p.getPlace()].remove(i);
				}
			}
		}
	}

	public static void nextTurn(){
		// Tous les joueurs jouent leur carte

		for (int i = 0; i < Game.getNbPlayer(); i++){
			Player p = Game.getPlayer(i);
			int gold = p.playCard(p.getCardChoosen());
			//Frame.playCard(p, gold);
		}

		removeAllPurchase();

		//Attribution des pièces gagnées ou suppression des pièces perdues
		for (int i = 0; i < Game.getNbPlayer(); i++) {
			Player p = Game.getPlayer(i);
			int gold = p.getNbGoldEarnedThisTurn()-p.getNbGoldPayedThisTurn();
			if (gold > 0) {
				for (int j = 0; j < gold; j++){
					ressourcePanels[i].add(new RessourcePanel(Ressource.PIECE));
				}
			}else{
				for (int j = 0; j < -gold; j++){
					System.out.println("tentative de suppression");
					System.out.println(ressourcePanels[i].size());
					ressourcePanels[i] = suppressionPanel(p, Ressource.PIECE);
					System.out.println(ressourcePanels[i].size());
				}
			}
		}
	}

	public static void removeAllPurchase() {
		SevenWonders.getLogger().log(Level.INFO, "Oubli de tous les achats effectués par les joueurs");
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

	public static ArrayList<RessourcePanel> suppressionPanel(Player p, Ressource ressource) {
		boolean suppress = false;
		ArrayList<RessourcePanel> arpVide = new ArrayList<RessourcePanel>();
		ArrayList<RessourcePanel> arp = ressourcePanels[p.getPlace()];
		for (RessourcePanel rp : arp) {
			if (!rp.getRessource().equals(ressource) || suppress){
				arpVide.add(rp);
			}else {
				if (!suppress && rp.getRessource().equals(ressource)) {
					suppress = true;
				}
			}
		}
		return arpVide;
	}

	public static void setPurchased(RessourcePanel rp, int playerPlace, boolean purchased) {
		for (int i = 0; i < ressourcePanels[playerPlace].size(); i++) {
			if (ressourcePanels[playerPlace].get(i).equals(rp)) {
				ressourcePanels[playerPlace].get(i).setHasBeenPurchased(playerPlace, purchased);
				break;
			}
		}
	}
}
