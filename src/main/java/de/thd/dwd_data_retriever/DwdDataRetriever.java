package de.thd.dwd_data_retriever;

import de.thd.dwd_data_retriever.csvinput.CsvFileReader;
import de.thd.dwd_data_retriever.datatypes.SeedAdjuster;
import de.thd.dwd_data_retriever.datatypes.StationData;
import de.thd.dwd_data_retriever.datatypes.StationMetaData;
import de.thd.dwd_data_retriever.datatypes.StationNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Diese Klasse implementiert <code>IDwdDataRetriever</code> zum Einlesen der DWD-Daten.
 *
 * @author Markus Eider (TH Deggendorf)
 */
public class DwdDataRetriever implements de.thd.dwd_data_retriever.IDwdDataRetriever {

    /**
     * Der Relativpfad zur Metadaten-Datei über die Wetterstations-Namen und -IDs.
     */
    private final static String stationMetaFilename = "files/stations.csv";

    /**
     * Die Relativpfade zu den Dateien mit den tatsächlichen Wetterstationsdaten.
     */
    private final static String[] dataFileNames = {
            "files/elsendorf_horneck.txt",
            "files/fuerstenzell.txt",
            "files/grainet-rehberg.txt",
            "files/mallersdorf_pfaffenberg_oberlindhart.txt",
            "files/oberviechtach.txt",
            "files/prackenbach_neuhaeusel.txt",
            "files/saldenburg-entschenreuth.txt",
            "files/zwiesel.txt",
            "files/falkenberg_kr_rottal_inn.txt",
            "files/gottfrieding.txt",
            "files/landshut.txt",
            "files/metten.txt",
            "files/passau_oberhaus.txt",
            "files/simbach_inn.txt",
            "files/straubing.txt"
    };

    /**
     * Allgemeine Stationsdaten.
     */
    private ArrayList<StationMetaData> stationMetadata;

    /**
     * Messwerte einer jeden Station.
     */
    private ArrayList<StationData> stationData;

    public DwdDataRetriever() {
        System.err.println("Bitte verwenden Sie den Konstruktor \"public DwdDataRetriever(int matrikelNummer) throws IOException\"");
        System.err.println("Beim Konstruktoraufruf fehlt Ihre Matrikelnummer!");
        System.exit(-1);
    }

    /**
     * Konstruktor. Bei Initialisierung eines <code>DwdDataRetriever</code>-Objektes werden alle Dateien
     * * aus dem Ordner <code>PROJEKTNAME/files</code> ausgelesen.
     *
     * @param matrikelNummer Ihre Matrikelnummer.
     * @param vorname        Ihr Vorname.
     * @param nachname       Ihr Nachname.
     * @throws IOException Wirft diese Exception, falls bspw. die Dateien nicht im Ordner <code>PROJEKTNAME/files/</code> liegen.
     */
    public DwdDataRetriever(int matrikelNummer, String vorname, String nachname) throws IOException {
        int seed = generateSeed(matrikelNummer, vorname, nachname);
        CsvFileReader reader = new CsvFileReader();
        stationMetadata = reader.readStationsFile(stationMetaFilename);
        SeedAdjuster.adjustMetaToSeed(stationMetadata, seed);
        stationData = reader.readProductFiles(dataFileNames);
        SeedAdjuster.adjustDataToSeed(stationMetadata, stationData, seed);
    }

    private int generateSeed(int matrikelnummer, String vorname, String nachname) {
        return Objects.hash(matrikelnummer, vorname, nachname);
    }

    @Override
    public ArrayList<java.lang.Integer> getStationIDs() {
        ArrayList<java.lang.Integer> ids = new ArrayList<>();
        for (StationMetaData station : stationMetadata) {
            ids.add(station.getStationId());
        }
        return ids;
    }

    @Override
    public StationMetaData getStationMetaData(int stationID) throws StationNotFoundException {
        for (StationMetaData station : stationMetadata) {
            if (station.getStationId() == stationID) {
                return station;
            }
        }
        throw new StationNotFoundException(stationID);
    }

    @Override
    public StationData getStationData(int stationID) throws StationNotFoundException {
        for (StationData station : stationData) {
            if (station.getStationId() == stationID) {
                return station;
            }
        }
        throw new StationNotFoundException(stationID);
    }

}
