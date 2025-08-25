// Jörg & Vlad

package blockGame.controller;
import java.io.*;

public class SaveGameController {

    // Variables (currently unused, can be removed or used)
	public static String savegamePath = "/savegames";
    private static String savegameFileName = "savegame1.sav"; // Filename
    private static String savegameString; // only temporary
    private static String splitString = "AAAA"; // only temporary, this string is used to delimit the stored data/character values 

    // Platzhalter / Methoden
    private static String positionX() {
        return "100";
    }

    private static String positionY() {
        return "200";
    }

    private static String characterStats() {
        return "Leben:100;Mana:50";
    }

    private static String playerInventory() {
        return "Schwert,Bogen,Trank";
    }
    
    /**
     * Saves a savegame into a file.
     * @param fileName
     * @param saveGameString
     * @author Vladi und Jörg
     */
    public static void writeSavegame(String fileName, String saveGameString) {
        savegameFileName = fileName;
        //Savegamestring will be composed by character / data values splitted by splitString "AAAA". 
        savegameString = positionX() + splitString + positionY() + splitString + characterStats() + splitString + playerInventory();

        //writing savegameString into file 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savegameFileName))) {
            writer.write(savegameString);
            System.out.println("Spielstand erfolgreich gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Spielstands: " + e.getMessage());
        }
    }

    /**
     * loads a savegame from a file
     * @param fileName
     * @author Vladi und Jörg
     */
    public static void readSavegame(String fileName) {
        savegameFileName = fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(savegameFileName))) {
            savegameString = reader.readLine();
            //Split des Savegamestrings in die einzelnenen Attribute (Charakterattribute werden durch Variable splitString getrennt)
            String[] daten = savegameString.split(splitString); 
            String posX = daten[0];
            String posY = daten[1];
            String charStats = daten[2];
            String inventory = daten[3];

            //Platzhalter, derzeit Ausgabe der Charakterattribute des geladenen Spielstands
            System.out.println("Position X: " + posX);
            System.out.println("Position Y: " + posY);
            System.out.println("Charakterwerte: " + charStats);
            System.out.println("Inventar: " + inventory);
        } catch (IOException e) {
            System.err.println("Fehler beim Laden des Spielstands: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fehler: Spielstand-Datei beschädigt oder unvollständig.");
        }
    }

    /**
     * deletes a savegame file.
     * @param fileName
     * @author Vladi und Jörg
     */
    public static void deleteSavegame(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.delete()) {
            System.out.println("Spielstand erfolgreich gelöscht.");
        } else {
            System.err.println("Fehler beim Löschen des Spielstands.");
        }
    }

    /**
     * gets a list of saved savegame files 
     * @return returns a list of saved savegame files variable type File[], 6needs to import java.io.* 
     * @author Vladi und Jörg
     */
    public static File[] listSavegames() {
        File ordner = new File(savegamePath);
        
        if (!ordner.exists() || !ordner.isDirectory()) {
            System.err.println("Fehler: Der angegebene Pfad ist kein gültiges Verzeichnis.");
            return new File[0]; // returns emty file list
        }

        File[] savegameFiles = ordner.listFiles((dir, name) -> name.toLowerCase().endsWith(".sav"));

        if (savegameFiles == null || savegameFiles.length == 0) {
            System.out.println("Keine Spielstände gefunden.");
            return new File[0]; // returns emty file list
        } else {
            System.out.println("Gefundene Spielstände:");
            for (File datei : savegameFiles) {
                System.out.println("- " + datei.getName()); // Output to command interface 
            }
            return savegameFiles; // returns savegame file list
        }
    }
}