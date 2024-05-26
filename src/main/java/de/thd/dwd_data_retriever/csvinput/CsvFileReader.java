package de.thd.dwd_data_retriever.csvinput;

import de.thd.dwd_data_retriever.datatypes.StationData;
import de.thd.dwd_data_retriever.datatypes.StationMetaData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse liest die DWD-Daten ein und gibt sie mittels Generics zurück.
 *
 * @author Markus Eider (TH Deggendorf)
 */
public class CsvFileReader {

    public CsvFileReader() {
        super();
    }

    /**
     * Liest die Datei stations.csv und gibt deren Inhalt zurück.
     *
     * @param fileName Der Dateiname mit Relativpfad vom Projektnamen aus.
     * @return Eine ArrayList mit Elementen des Typs StationMetadata
     * @throws IOException , falls bspw. die Datei an einem anderen Ort liegt.
     * @see StationMetaData
     */
    public ArrayList<StationMetaData> readStationsFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return StationMetaData.fromFileLines(lines);
    }

    /**
     * Liest eine *.txt-Datei einer entsprechenden Wetterstation und gibt deren Inhalt zurück.
     *
     * @param fileName Der Dateiname mit Relativpfad vom Projektnamen aus.
     * @return Die Wetterstationsdaten in Form der Klasse StationData
     * @throws IOException , falls bspw. die Datei an einem anderen Ort liegt.
     * @see StationData
     */
    private StationData readProductFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return StationData.fromFileLines(lines);
    }

    /**
     * Liest alle Wetterstationsdaten aus und gibt sie zurück.
     *
     * @param fileNames Die Dateinamen mit Relativpfad vom Projektnamen aus.
     * @return Alle Wetterstationsdaten
     * @see StationData
     */
    public ArrayList<StationData> readProductFiles(String[] fileNames) throws IOException {
        ArrayList<StationData> result = new ArrayList<>();
        for (String filename : fileNames) {
            result.add(readProductFile(filename));
        }
        return result;
    }
}
