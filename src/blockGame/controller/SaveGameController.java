// Jörg & Vlad

package blockGame.controller;
import java.io.*;

public class SaveGameController {

    // Variablen (aktuell nicht genutzt, könnten entfernt oder verwendet werden)
    private static String saveGameDateipfad;
    private static String saveGameDateiName;
    private static String saveGameString;

    // Platzhaltermethoden
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
        saveGameDateiName = dateiName;
        saveGameString = positionX() + "AAAA" + positionY() + "AAAA" + charakterWerte() + "AAAA" + spielerInventar();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveGameDateiName))) {
            writer.write(saveGameString);
            System.out.println("Spielstand erfolgreich gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Spielstands: " + e.getMessage());
        }
    }

    // Spielstand laden
    public static void readSavegame(String dateiName) {
        saveGameDateiName = dateiName;
        try (BufferedReader reader = new BufferedReader(new FileReader(saveGameDateiName))) {
            saveGameString = reader.readLine();
            String[] daten = saveGameString.split("AAAA");
            String posX = daten[0];
            String posY = daten[1];
            String werte = daten[2];
            String inventar = daten[3];

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

    // Spielstände auflisten
    public static void listSavegames(String ordnerPfad) {
        File ordner = new File(ordnerPfad);

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