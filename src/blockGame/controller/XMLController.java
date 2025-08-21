package blockGame.controller;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
	private String gameStatePath = "res/savegames/gameState.xml";
	private String inventoryListPath = "res/savegames/inventoryList.xml";
	private GameState gameState;
	
	// create empty XML-File if none exists for coordinates
	public void xmlWriteGameStateInfo() {
		new File(gameStatePath);
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();
			
			// root element for world & player state
			Element rootElement = doc.createElement("savegame info");
			doc.appendChild(rootElement);
			
			Element worldRowsElement = doc.createElement(gameState.getWorldRows() + "");
			worldRowsElement.appendChild(doc.createTextNode(gameState.getWorldRows() + ""));
			rootElement.appendChild(worldRowsElement);
			
			Element worldColsElement = doc.createElement(gameState.getWorldCols() + "");
			worldColsElement.appendChild(doc.createTextNode(gameState.getWorldCols() + ""));
			rootElement.appendChild(worldColsElement);
			
			Element playerRowElement = doc.createElement(gameState.getPlayerRow() + "");
			playerRowElement.appendChild(doc.createTextNode(gameState.getPlayerRow() + ""));
			rootElement.appendChild(playerRowElement);
			
			Element playerColElement = doc.createElement(gameState.getPlayerCol() + "");
			playerColElement.appendChild(doc.createTextNode(gameState.getPlayerCol() + ""));
			rootElement.appendChild(playerColElement);
			
			// TODO: BlockCoordinates -> HashMap?
			
			// write contents into file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("res/xml/formatter.xslt")));
			DOMSource source = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(gameStatePath));
			transformer.transform(source, streamResult);
			
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
