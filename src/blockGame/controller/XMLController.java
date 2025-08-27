package blockGame.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;
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
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import blockGame.GameState;

// stores saveGame-Data in XML-format
public class XMLController {
	
	// Filepaths
	private String saveGamePath = "savegames/saveGame.xml";
	private GameState gameState;
	
	public XMLController(GameState gameState) {
		this.gameState = gameState;
	}
	/**
	 * Returns savegame as a file
	 * @return
	 */
	public GameState readSaveGameFromXML() {
		return readSaveGameFromXML(new File(saveGamePath));
		
	}
	// read XML-File from "savegames/saveGame.xml"
	/**
	 * Return a gamestate from XML 
	 * @return
	 */
	public static GameState readSaveGameFromXML(File xmlFile) {
		// Startpunkt: SaveGameView -> Datei darstellen -> Logik zum Auswählen/Laden in der View
		// 1. Datei einlesen (File-Objekt) -> GameView lädt Datei (oder Launcher?)
		try {
			if(!xmlFile.exists()) {
				System.err.println("Savegame Datei nicht gefunden!"+xmlFile.getAbsolutePath());
				return null;
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			Element rootElement = doc.getDocumentElement();
			// 2. XML-Elemente einlesen und in GameState ablegen (Festplatte -> RAM)
			// 		- Weltgröße, Spielerposition, Blöcke mit Koordinaten & IDs
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
			for(int i = 0; i < blockNodes.getLength(); i++) {
				Element blockElement = (Element) blockNodes.item(i);
				int c = Integer.parseInt(blockElement.getAttribute("c"));
				int id = Integer.parseInt(blockElement.getAttribute("id"));
				int r = Integer.parseInt(blockElement.getAttribute("r"));
				gameState.putBlock(c, r, id);
			}
			
			// inventory
			NodeList itemNodes = rootElement.getElementsByTagName("item");
			for(int i = 0; i < itemNodes.getLength(); i++) {
				Element blockElement = (Element) itemNodes.item(i);
				int id = Integer.parseInt(blockElement.getAttribute("id"));
				int number = Integer.parseInt(blockElement.getAttribute("number"));
//				gameState.putInventory(id,number);
//				System.out.println(id + " - " + String.valueOf(number) + " Stück");
			}
			
			System.out.println("Savegame geladen von: " + xmlFile.getAbsolutePath());
			return gameState;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim lesen der Datei!");
			return null;
		}
	}
	
	// create XML-File if none exists for coordinates
	/**
	 * writes a savegame file
	 */
	public void writeSaveGameFileXML() {
		new File(saveGamePath);
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();
			
			// root element for world & player state
			Element rootElement = doc.createElement("savegame");
			doc.appendChild(rootElement);
			
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
		    .sorted(Comparator
		        .comparingInt((Map.Entry<GameState.Coord, Integer> e) -> e.getKey().row())
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
			int itemSlots = 20; 											// wildcard
			for(int i = 0; i < itemSlots; i++) { 
				Element item = doc.createElement("item");
				int id = 3;												// wildcard 	
				item.setAttribute("id", String.valueOf(id));
				int numberOfPices = 23; 									// wildcard
				item.setAttribute("number", String.valueOf(numberOfPices));	
				inventory.appendChild(item);
			}
			
			// write contents into file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			File file = new File(saveGamePath);
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
	listSaveGames(); 											// testing method listSaveGames()
	
	/**
	 * Return the list of savegame files
	 * 
	 */
	}
    public static File[] listSaveGames() {
        // relative path to savegame folder in user directory
    		Path savegameDir = Paths.get(System.getProperty("user.dir")).resolve("savegames");
        File folder = savegameDir.toFile();
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Fehler: Verzeichnis nicht gefunden: " + savegameDir.toAbsolutePath());
            return new File[0];
        }

        File[] savegameFiles = folder.listFiles((dir, name) -> name.toLowerCase().startsWith("savegame"));
        
        if (savegameFiles == null || savegameFiles.length == 0) {
        		System.out.println("Keine Spielstände gefunden in: " + savegameDir.toAbsolutePath());
            return new File[0]; // returns empty file list
        } else {
        		// Output to command interface 
            System.out.println("Gefundene Spielstände:"); 
            for (File datei : savegameFiles) {
                System.out.println("- " + datei.getName()); // Output to command interface 
            }
            // order files by name - Thx 2 ChatGPT 4 this suggestion
            java.util.Arrays.sort(savegameFiles, java.util.Comparator.comparing(File::getName));
            return savegameFiles; // returns savegame file list
        }
    }
	
	

}
