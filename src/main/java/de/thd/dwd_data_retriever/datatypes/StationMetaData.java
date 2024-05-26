package de.thd.dwd_data_retriever.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Diese Klasse definiert die objektorientierte Darstellung der DWD-Stationsinformationen.
 *
 * @author Markus Eider (TH Deggendorf)
 */
public class StationMetaData {

    /*
     * Der relative Dateiname zur Textdatei.
     * */
    private String filename;

    /**
     * Der Name der Wetterstation. Das ist in der Regel der Ort.
     */
    private String stationName;

    /**
     * Der Identifier (ID) einer Wetterstation des Deutschen Wetterdienstes (DWD).
     */
    private int stationId;

    public StationMetaData() {
        super();
    }

    /**
     * Gibt den Wert des Felds <code>stationName</code> zurück.
     *
     * @return Der Wert von <code>stationName</code>
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Gibt den Wert des Felds <code>stationId</code> zurück.
     *
     * @return Der Wert von <code>stationId</code>
     */
    public int getStationId() {
        return stationId;
    }

    public static StationMetaData fromFileLine(String line) {
        String[] columnValues = line.split(",");
        StationMetaData stationInformation = new StationMetaData();
        stationInformation.stationName = columnValues[StationMetadataColumn.STATION_NAME.ordinal()];
        stationInformation.filename = columnValues[StationMetadataColumn.FILENAME.ordinal()];
        stationInformation.stationId = java.lang.Integer.parseInt(columnValues[StationMetadataColumn.STATION_ID.ordinal()]);
        return stationInformation;
    }

    public static ArrayList<StationMetaData> fromFileLines(List<String> fileLines) {
        ArrayList<StationMetaData> stationInformation = new ArrayList<StationMetaData>();
        for (int i = 1; i < fileLines.size(); i++) {
            stationInformation.add(fromFileLine(fileLines.get(i)));
        }
        return stationInformation;
    }

    protected static ArrayList<Integer> getIds(ArrayList<StationMetaData> stationMetaData) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (StationMetaData meta : stationMetaData) {
            ids.add(meta.getStationId());
        }
        return ids;
    }


    @Override
    public String toString() {
        return "StationMetadata{" +
                ", stationName='" + stationName + '\'' +
                ", stationID=" + stationId + '}';
    }

}

