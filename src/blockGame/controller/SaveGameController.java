// Jörg & Vlad

package blockGame.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGameController {
	
	//Variablem
	Private String saveGameDateipdad; //Dateipfad für Saveganme
	Private String saveGameDateiName; //Dateiname
	Private String saveGameString; // die zu speicherneden Daten
	
    // Platzhalter, später die korrekten Methoden für Position, Charakterwerte und Inventar
    private static String positionX() {
        return "100";
    }

    private static String positionY() {
        return "200";
    }

    private static String charakterWerte() {
        return "Leben:100;Mana:50";
    }

    private static String spielerInventar() {
        return "Schwert,Bogen,Trank";
    }
	
	//METHODEN
	//Spiel speichern
    public static void writeSavegame(String dateiName, String spielStand) {
        saveGameDateiName = dateiName;
        saveGameString = positionX() + "AAAA" + positionY() + "AAAA" + charakterWerte() + "AAAA" + spielerInventar();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveGameDateiName))) {
            writer.write(saveGameString);
            System.out.println("Spielstand erfolgreich gespeichert.");
        } 
//        catch (IOException e) {
//            System.err.println("Fehler beim Speichern des Spielstands: " + e.getMessage());
//        }
         
    // Spielstand Laden 
    public static void readSavegame(String dateiName) {
      	saveGameDateiName = dateiName;
       	try (BufferedReader reader = new BufferedReader(new FileReader(saveGameDateiName))) {
            saveGameString = reader.readLine();
            String[] daten = saveGameString.split("AAAA");
            String posX = daten[0];
            String posY = daten[1];
            String werte = daten[2];
            String inventar = daten[3];

            // Beispielausgabe Ausgabe des Spielstands
            System.out.println("Position X: " + posX);
            System.out.println("Position Y: " + posY);
            System.out.println("Charakterwerte: " + werte);
            System.out.println("Inventar: " + inventar);
        } 
       	catch (IOException e) {
            System.err.println("Fehler beim Laden des Spielstands: " + e.getMessage());
        }
       	catch (ArrayIndexOutOfBoundsException e) {
       		System.err.println("Fehler: Spielstand-Datei beschädigt oder unvollständig.");
        }
    }

	//Paltzhalter für Spielstand löschen 
	public static void deleteSavegame(String dateiName) {                 
				
	} 

}
