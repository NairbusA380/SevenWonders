import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SevenWonders {

	private static Frame frame; // Fenetre de jeu
	private static Game game;
	private static Logger logger;
	private static FileHandler handler;
	

	public static void main(String args[]){
		int nbPlayers = 4;
		Calendar c = Calendar.getInstance();
		String filename = "log"+c.get(Calendar.DAY_OF_MONTH)+"_"+c.get(Calendar.MONTH)+"_"+c.get(Calendar.YEAR);
		File fichier = new File("src/Log/"+filename+".txt");
		try{
			fichier.createNewFile();
		}catch (IOException e){
		}
		
		logger = Logger.getLogger("logger");
		try {
			handler = new FileHandler("src/Log/"+filename+".txt");
			handler.setFormatter(new MyFormatter());
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {
			
			e.printStackTrace();
		}
		logger.log(Level.INFO, "----------- NOUVEAU LANCEMENT DU JEU -----------\n\n\n");
		game = new Game(nbPlayers);
		frame = new Frame();
		Frame.entireDraw();
	}

	public Frame getFrame() {
		return SevenWonders.frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		SevenWonders.game = game;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		SevenWonders.logger = logger;
	}

	

}
