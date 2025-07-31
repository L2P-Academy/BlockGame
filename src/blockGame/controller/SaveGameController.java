// Jörg & Vlad

package blockGame.controller;
import java.io.*;

public class SaveGameController {

    // Variablen (aktuell nicht genutzt, könnten entfernt oder verwendet werden)
    public static String savegamePath = "/savegames";
    private static String savegamefileName = "savegame1.sav"; // Dateiname
    private static String savegameString; // nur Temporäre, wird nach Umschreibung auf XML-Format unnötig; kompletter String der in/aus Datei geschrieben/gelesen wird.
    private static String splitString = "AAAA"; // nur Temporär, wird später unnötig. Vordefinierte Zeichenkette zum Abtrennen der einzelnenen Attribute des Charakters

    // Platzhalter / Methoden
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

// Spiel speichern
    public static void writeSavegame(String dateiName, String spielStand) {
        savegamefileName = dateiName;
        //der Spielstandstring wird aus den einzelnene Charakterattributen jeweils getrennt durch den oben splitString mit dem Wert "AAAA" zusammengebaut 
        savegameString = positionX() + splitString + positionY() + splitString + charakterWerte() + splitString + spielerInventar();

        //der Spielstandstring wird in die Datei geschrieben 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savegamefileName))) {
            writer.write(savegameString);
            System.out.println("Spielstand erfolgreich gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Spielstands: " + e.getMessage());
        }
    }

// Spielstand laden
    public static void readSavegame(String dateiName) {
        savegamefileName = dateiName;
        try (BufferedReader reader = new BufferedReader(new FileReader(savegamefileName))) {
            savegameString = reader.readLine();
            //Split des Savegamestrings in die einzelnenen Attribute (Charakterattribute werden durch Variable splitString getrennt)
            String[] daten = savegameString.split(splitString); 
            String posX = daten[0];
            String posY = daten[1];
            String werte = daten[2];
            String inventar = daten[3];

            //Platzhalter, derzeit Ausgabe der Charakterattribute des geladenen Spielstands
            System.out.println("Position X: " + posX);
            System.out.println("Position Y: " + posY);
            System.out.println("Charakterwerte: " + werte);
            System.out.println("Inventar: " + inventar);
        } catch (IOException e) {
            System.err.println("Fehler beim Laden des Spielstands: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fehler: Spielstand-Datei beschädigt oder unvollständig.");
        }
    }

// Spielstand löschen
    public static void deleteSavegame(String dateiName) {
        File file = new File(dateiName);
        if (file.exists() && file.delete()) {
            System.out.println("Spielstand erfolgreich gelöscht.");
        } else {
            System.err.println("Fehler beim Löschen des Spielstands.");
        }
    }

// Liste der vorhandenen Spielstände erstellen
    public static void listSavegames(String directoryPath) {
        File ordner = new File(directoryPath);

        if (!ordner.exists() || !ordner.isDirectory()) {
            System.err.println("Fehler: Der angegebene Pfad ist kein gültiges Verzeichnis.");
            return;
        }

        File[] dateien = ordner.listFiles((dir, name) -> name.toLowerCase().endsWith(".sav"));

        if (dateien == null || dateien.length == 0) {
            System.out.println("Keine Spielstände gefunden.");
        } else {
            System.out.println("Gefundene Spielstände:");
            for (File datei : dateien) {
                System.out.println("- " + datei.getName());
            }
        }
    }
}