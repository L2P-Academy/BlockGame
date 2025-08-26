package blockGame.controller;

import java.io.File;
import java.util.Comparator;
import java.util.Map;

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

import blockGame.GameState;

// stores saveGame-Data in XML-format
public class XMLController {
	
	// Filepaths
	private String saveGamePath = "savegames/saveGame.xml";
	private GameState gameState;
	
	public XMLController(GameState gameState) {
		this.gameState = gameState;
	}
	
	// read XML-File from "savegames/saveGame.xml"
	public void readSaveGameFromXML() {
		// Startpunkt: SaveGameView -> Datei darstellen -> Logik zum Auswählen/Laden in der View
		// 1. Datei einlesen (File-Objekt) -> GameView lädt Datei (oder Launcher?)
		
		// 2. XML-Elemente einlesen und in GameState ablegen (Festplatte -> RAM)
		
		// 		- Weltgröße, Spielerposition, Blöcke mit Koordinaten & IDs
		
		// 3. GameView verwendet dann die GameState zum Aufbau der Spielwelt
		
	}
	
	// create XML-File if none exists for coordinates
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
		
	}
	
	

}
