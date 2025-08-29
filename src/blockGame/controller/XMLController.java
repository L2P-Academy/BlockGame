package blockGame.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import blockGame.GameState;

// stores saveGame-Data in XML-format
public class XMLController {

	// Filepaths
	private String saveGamePath = "savegames/saveGame_";
	private static String settingsPath = "settings/settings.xml";
	private GameState gameState;

	public XMLController(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Returns savegame as a file
	 * 
	 * @return
	 */
	public GameState readSaveGameFromXML() {
		return readSaveGameFromXML(new File(saveGamePath));

	}

	// read XML-File from "savegames/saveGame.xml"
	/**
	 * Return a gamestate from XML
	 * 
	 * @return
	 * @author Christoph, Vladi, Jörg
	 */
	public static GameState readSaveGameFromXML(File xmlFile) {
		// Startpunkt: SaveGameView -> Datei darstellen -> Logik zum Auswählen/Laden in
		// der View
		// 1. Datei einlesen (File-Objekt) -> GameView lädt Datei (oder Launcher?)
		try {
			if (!xmlFile.exists()) {
				System.err.println("Savegame Datei nicht gefunden!" + xmlFile.getAbsolutePath());
				return null;
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Element rootElement = doc.getDocumentElement();
			// 2. XML-Elemente einlesen und in GameState ablegen (Festplatte -> RAM)
			// - Weltgröße, Spielerposition, Blöcke mit Koordinaten & IDs
			// World

			Element worldElement = (Element) rootElement.getElementsByTagName("world").item(0);
			int rows = Integer.parseInt(worldElement.getAttribute("rows"));
			int cols = Integer.parseInt(worldElement.getAttribute("cols"));
			// Player position
			Element playerElement = (Element) rootElement.getElementsByTagName("player").item(0);
			int pRow = Integer.parseInt(playerElement.getAttribute("row"));
			int pCol = Integer.parseInt(playerElement.getAttribute("col"));

			GameState gameState = new GameState(rows, cols, pRow, pCol);

			// Blocks information
			NodeList blockNodes = rootElement.getElementsByTagName("b");
			for (int i = 0; i < blockNodes.getLength(); i++) {
				Element blockElement = (Element) blockNodes.item(i);
				int c = Integer.parseInt(blockElement.getAttribute("c"));
				int id = Integer.parseInt(blockElement.getAttribute("id"));
				int r = Integer.parseInt(blockElement.getAttribute("r"));
				gameState.putBlock(c, r, id);
			}

			// inventory
			NodeList itemNodes = rootElement.getElementsByTagName("item");
			for (int i = 0; i < itemNodes.getLength(); i++) {
				Element blockElement = (Element) itemNodes.item(i);
				int id = Integer.parseInt(blockElement.getAttribute("id"));
				int number = Integer.parseInt(blockElement.getAttribute("number"));
			}

			System.out.println("Savegame geladen von: " + xmlFile.getAbsolutePath());
			return gameState;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim lesen der Datei!");
			return null;
		}
	}

	/**
	 * create XML-File if none exists for coordinates
	 * @param saveGameName
	 * @author Christoph, Vladi, Jörg
	 */
	public void writeSaveGameFileXML(/**String saveGameName*/) {
		// input dialog for saveGameName
		String saveGameName = JOptionPane.showInputDialog(null, "Bitte eindeutige Speicher-ID angeben:", "Eingabe", JOptionPane.QUESTION_MESSAGE);
		String saveGamePathLocal = saveGamePath + saveGameName + ".xml"; // builds filename of savegame file
		File newFile = new File(saveGamePathLocal);   

		//checking for duplicate filenames
		if (newFile.exists()) {
			//Yes/No confirm dialog
			int yesNo = JOptionPane.showConfirmDialog(null, "Die Datei existiert bereits, soll sie überschrieben werden?", "Bestätigung", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        if (yesNo != JOptionPane.YES_OPTION) { 
	        		System.out.println("Speichern abgebrochen.");
	        		return;
	        }
	        System.out.println("Spielstand wird gespeichert...");
		}
		System.out.println("...gespeichert.");
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();

			// root element for world & player state
			Element rootElement = doc.createElement("savegame");
			doc.appendChild(rootElement);

			// savegame ID
			Element saveGameID = doc.createElement("SavegameID");
			saveGameID.setAttribute("ID", saveGameName);
			rootElement.appendChild(saveGameID);
			
			// time information 
			Element time = doc.createElement("time");
			LocalTime timeNow = LocalTime.now();
			LocalDate dateNow = LocalDate.now();

	        time.setAttribute("day", String.valueOf(dateNow.getDayOfMonth()));
	        time.setAttribute("month", String.valueOf(dateNow.getMonthValue()));
			time.setAttribute("year", String.valueOf(dateNow.getYear()));
			time.setAttribute("hour", String.valueOf(timeNow.getHour()));
			time.setAttribute("minute", String.valueOf(timeNow.getMinute()));
			time.setAttribute("second", String.valueOf(timeNow.getSecond()));
			rootElement.appendChild(time);

			// time stamp
			Element timeStamp = doc.createElement("TimeStamp");
			timeStamp.setAttribute("date", dateNow.toString());
			timeStamp.setAttribute("time", timeNow.toString());
			rootElement.appendChild(timeStamp);
			
			// world size information
			Element world = doc.createElement("world");
			world.setAttribute("rows", String.valueOf(gameState.getWorldRows()));
			world.setAttribute("cols", String.valueOf(gameState.getWorldCols()));
			rootElement.appendChild(world);

			// player information
			Element player = doc.createElement("player");
			player.setAttribute("row", String.valueOf(gameState.getPlayerRow()));
			player.setAttribute("col", String.valueOf(gameState.getPlayerCol()));
			rootElement.appendChild(player);

			// blocks information
			Element blocks = doc.createElement("blocks");
			rootElement.appendChild(blocks);
			// write blocks - in order
			gameState.getBlocks().entrySet().stream()
					.sorted(Comparator.comparingInt((Map.Entry<GameState.Coord, Integer> e) -> e.getKey().row())
							.thenComparingInt(e -> e.getKey().col()))
					.forEach(e -> {
						GameState.Coord k = e.getKey();
						int id = e.getValue();

						Element b = doc.createElement("b");
						b.setAttribute("c", String.valueOf(k.col()));
						b.setAttribute("r", String.valueOf(k.row()));
						b.setAttribute("id", String.valueOf(id));
						blocks.appendChild(b);
					});

			// inventory informations
			Element inventory = doc.createElement("inventory");
			rootElement.appendChild(inventory);

			// write items in order
			int itemSlots = 20; // wildcard
			for (int i = 0; i < itemSlots; i++) {
				Element item = doc.createElement("item");
				int id = 3; // wildcard
				item.setAttribute("id", String.valueOf(id));
				int numberOfPices = 23; // wildcard
				item.setAttribute("number", String.valueOf(numberOfPices));
				inventory.appendChild(item);
			}

			// write contents into file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			File file = new File(saveGamePathLocal);
			file.getParentFile().mkdirs();
			transformer.transform(new DOMSource(doc), new StreamResult(file));

			System.out.println("Savegame gespeichert unter: " + file.getAbsolutePath());

			// possible exceptions
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		listSaveGames(); // testing method listSaveGames()
	}

	/**
	 * gets a list of all savegame files 
	 * @return File array of found savegame files
	 * @author Christoph, Vladi, Jörg
	 */
	public static File[] listSaveGames() { 
		// relative path to savegame folder in user directory
		Path savegameDir = Paths.get(System.getProperty("user.dir")).resolve("savegames");
		File folder = savegameDir.toFile();

		if (!folder.exists() || !folder.isDirectory()) {
			System.err.println("Fehler: Verzeichnis nicht gefunden: " + savegameDir.toAbsolutePath());
			return null;
		}

		File[] savegameFiles = folder.listFiles((dir, name) -> name.toLowerCase().startsWith("savegame"));

		if (savegameFiles == null || savegameFiles.length == 0) {
			System.out.println("Keine Spielstände gefunden in: " + savegameDir.toAbsolutePath());
			return null;
		} else {
			// Output to command interface
			System.out.println("Gefundene Spielstände:");
			for (File datei : savegameFiles) {
				System.out.println("- " + datei.getName()); // Output to command interface
			}
			return savegameFiles; // returns savegame file list
		}
	}

	/**
	 * saves settings in XML file settings.xml, 
	 * @Example XMLController.saveSettingsToXML(0.1f, 0.2f, 1024, 768, true);
	 * 
	 * @param soundVolume - float - Volume for Sound
	 * @param sfxVolume - float - Volume for SFX 
	 * @param resX - int - horizontal screen resolution  
	 * @param resY - int - vertical screen resolution
	 * @param fullScreenOn - boolean - true if full screen mode is used
	 * @author Christoph, Vladi, Jörg
	 */
	public static void saveSettingsToXML(int soundVolume, int sfxVolume, int resX, int resY, boolean fullScreenOn) {
		new File(settingsPath);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();

			// root element for settings
			Element rootElement = doc.createElement("Settings");
			doc.appendChild(rootElement);

			// sound volume
			Element sound_Volume = doc.createElement("SoundVolume");
			sound_Volume.setAttribute("Volume", String.valueOf(soundVolume));
//			soundVolume.setAttribute("muted", String.valueOf(gameState.getSoundVolumeMute())); // sound muted or not
			rootElement.appendChild(sound_Volume);

			// SFX volume
			Element SFXVolume = doc.createElement("SFXVolume");

			SFXVolume.setAttribute("Volume", String.valueOf(sfxVolume));
//			SFXVolume.setAttribute("muted", String.valueOf(gameState.getSFXVolumeMute()));	// SFX muted or not
			rootElement.appendChild(SFXVolume);

			// Screen resolution
			Element resolution = doc.createElement("Resolution");
			resolution.setAttribute("X", String.valueOf(resX));
			resolution.setAttribute("Y", String.valueOf(resY));
			rootElement.appendChild(resolution);

			// fullScreen
			Element fullscreen = doc.createElement("Fullscreen");
			fullscreen.setAttribute("On", String.valueOf(fullScreenOn));
			rootElement.appendChild(fullscreen);
			
			// write contents into file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			File file = new File(settingsPath);
			file.getParentFile().mkdirs();
			transformer.transform(new DOMSource(doc), new StreamResult(file));

			System.out.println("Settings gespeichert unter: " + file.getAbsolutePath());

			// possible exceptions
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * reads settings from settings/settings.xml  
	 * @Example 
	 * Object[] settings = xmlController.readSettingsFromXML();
	 * 
     *	if (settings != null) {
     *       float soundVolume   = (float) settings[0];
     *       float sfxVolume     = (float) settings[1];
     *       int resolutionX     = (int)   settings[2];
     *       int resolutionY     = (int)   settings[3];
     *       boolean fullscreen  = (boolean) settings[4];
     *       System.out.println("Sound Volume: " + soundVolume);
     *       System.out.println("SFX Volume: " + sfxVolume);
     *       System.out.println("Resolution: " + resolutionX + "x" + resolutionY);
     *       System.out.println("Fullscreen: " + fullscreen);
     *   }
     *   
     *
	 * @return Object[] { float soundVolume, float sfxVolume, int resolutionX, int resolutionY, boolean fullScreen }
	 * @author Christoph, Vladi, Jörg
	 */
	public static Object[] readSettingsFromXML() {
		File settingsFile = new File(settingsPath);

		try {
			if (!settingsFile.exists()) {
				System.err.println("Settings Datei nicht gefunden!" + settingsFile.getAbsolutePath());
				return null;
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(settingsFile);
			doc.getDocumentElement().normalize();

			Element rootElement = doc.getDocumentElement();

			// Sound Volume
			Element soundVolume = (Element) rootElement.getElementsByTagName("SoundVolume").item(0);
			int sVol = Integer.parseInt(soundVolume.getAttribute("Volume"));
			
			//  SFX Volume
			Element sfxVolume = (Element) rootElement.getElementsByTagName("SFXVolume").item(0);
			int sfxVol = Integer.parseInt(sfxVolume.getAttribute("Volume"));
			
			//resolution X and Y
			Element resolution = (Element) rootElement.getElementsByTagName("Resolution").item(0);
			int resX = Integer.parseInt(resolution.getAttribute("X"));
			int resY = Integer.parseInt(resolution.getAttribute("Y"));

			// fullscreen
			Element fullscreen = (Element) rootElement.getElementsByTagName("fullscreen").item(0);
			boolean fullScreenOn = Boolean.parseBoolean(fullscreen.getAttribute("On"));			
			
			System.out.println("Einstellungen geladen von: " + settingsFile.getAbsolutePath());
			return new Object[] { sVol, sfxVol, resX, resY, fullScreenOn};

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim lesen der Datei!");
			return null;
		}
	}

	/**
	 * deletes a file in savegame folder need complete filename without path
	 * @param fileName - string - full Filename of the file without path
	 * @Example XMLController.deleteSavegame("saveGame_1234.xml");  
	 */
    public static void deleteSavegame(String fileName) {
        
		String saveGamePathLocal = "savegames/" + fileName; // builds filename of savegame file
		File file = new File(saveGamePathLocal);
		
        int yesNo = JOptionPane.showConfirmDialog(null, "Den Spielstand wirklich löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yesNo != JOptionPane.YES_OPTION) { 
        		System.out.println("Löschen abgebrochen.");
        		return;
        }
        System.out.println("Spielstand wird gelöscht...");
        
        if (file.exists() && file.delete()) {
            System.out.println("Spielstand erfolgreich gelöscht.");
        } else {
            System.err.println("Fehler beim Löschen des Spielstands.");
        }
    }
    
    /**
     * reads the timestamp from XML savegame file, 
     * @Example LocalDateTime timeStamp = XMLController.getTimeStampFromSaveGame("saveGame_1.xml");
     * @param fileName - String - full file name without path 
     * @return timestamp from XML savegame file
     * @author Christoph, Vladi, Jörg
     */
    public static LocalDateTime getTimeStampFromSaveGame(String fileName) {
		File tempFile = new File("savegames/" + fileName);

		try {
			if (!tempFile.exists()) {
				System.err.println("Savegame Datei nicht gefunden!" + tempFile.getAbsolutePath());
				return null;
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(tempFile);
			doc.getDocumentElement().normalize();

			Element rootElement = doc.getDocumentElement();

			// TimeStamp
			Element timeStamp = (Element) rootElement.getElementsByTagName("TimeStamp").item(0);
			String tempDateString = timeStamp.getAttribute("date");
			String tempTimeString = timeStamp.getAttribute("time");
			
	        LocalDate date = LocalDate.parse(tempDateString);
	        LocalTime time = LocalTime.parse(tempTimeString);
	        
	        LocalDateTime dateTime = LocalDateTime.of(date, time);
						
			System.out.println("Zeitstempel geladen von: " + tempFile.getAbsolutePath());
			return dateTime;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim lesen der Datei!");
			return null;
		}
	}        
}
