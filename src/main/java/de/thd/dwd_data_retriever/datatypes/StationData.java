package de.thd.dwd_data_retriever.datatypes;

import de.thd.dwd_data_retriever.util.DateUtil;
import de.thd.dwd_data_retriever.util.FloatParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Diese Klasse definiert die objektorientierte Darstellung der inhaltlichen DWD-Daten.
 *
 * @author Markus Eider (TH Deggendorf)
 */
public class StationData {

    /**
     * Der Identifier (ID) einer Wetterstation des Deutschen Wetterdienstes (DWD).
     */
    private int stationId;

    /**
     * Die Zeitstempel über alle Messwerte einer Wetterstation hinweg.
     * Es hängt von der Wetterstation ab, ob ein Messwert erst später hinzukommt!
     * Sprich: Das erste oder letzte Datum ist nicht gleich das erste oder letzte Datum für einen Parameter!
     */
    private ArrayList<Date> measuringDate;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> fm;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> nm;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> pm;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> rsk;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> sdk;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> shkTag;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> tnk;

    /**
     * Messwerte eines Parameters. <br>
     * Einzelne Elemente haben den Wert <code>null</code>, wenn es für den Zeitstempel keinen Wert gibt! <br>
     * Siehe iLearn für die Beschreibung des Parameters.
     */
    private ArrayList<Float> txk;

    public StationData() {
        super();
    }

    /**
     * Gibt den Wert des Felds <code>stationId</code> zurück.
     *
     * @return Der Wert von <code>stationId</code>
     */
    public int getStationId() {
        return stationId;
    }


    protected void setStationId(Integer id) {
        this.stationId = id;
    }

    /**
     * Gibt den Wert des Felds <code>measuringDate</code> zurück.
     *
     * @return Der Wert von <code>measuringDate</code>
     */
    public ArrayList<Date> getMeasuringDate() {
        return measuringDate;
    }

    /**
     * Gibt den Wert des Felds <code>fm</code> zurück.
     *
     * @return Der Wert von <code>nm</code>
     */
    public ArrayList<Float> getFm() {
        return fm;
    }

    /**
     * Gibt den Wert des Felds <code>nm</code> zurück.
     *
     * @return Der Wert von <code>nm</code>
     */
    public ArrayList<Float> getNm() {
        return nm;
    }

    /**
     * Gibt den Wert des Felds <code>pm</code> zurück.
     *
     * @return Der Wert von <code>pm</code>
     */
    public ArrayList<Float> getPm() {
        return pm;
    }

    /**
     * Gibt den Wert des Felds <code>rsk</code> zurück.
     *
     * @return Der Wert von <code>rsk</code>
     */
    public ArrayList<Float> getRsk() {
        return rsk;
    }

    /**
     * Gibt den Wert des Felds <code>sdk</code> zurück.
     *
     * @return Der Wert von <code>sdk</code>
     */
    public ArrayList<Float> getSdk() {
        return sdk;
    }

    /**
     * Gibt den Wert des Felds <code>shkTag</code> zurück.
     *
     * @return Der Wert von <code>shkTag</code>
     */
    public ArrayList<Float> getShkTag() {
        return shkTag;
    }

    /**
     * Gibt den Wert des Felds <code>tnk</code> zurück.
     *
     * @return Der Wert von <code>tnk</code>
     */
    public ArrayList<Float> getTnk() {
        return tnk;
    }

    /**
     * Gibt den Wert des Felds <code>txk</code> zurück.
     *
     * @return Der Wert von <code>txk</code>
     */
    public ArrayList<Float> getTxk() {
        return txk;
    }

    /**
     * Diese Methode initialisiert alle ArrayLists dieser Klasse, damit sie Objekte aufnehmen können.
     */
    private static void initializeArrayLists(StationData stationData) {
        stationData.measuringDate = new ArrayList<>();
        stationData.fm = new ArrayList<>();
        stationData.nm = new ArrayList<>();
        stationData.pm = new ArrayList<>();
        stationData.rsk = new ArrayList<>();
        stationData.sdk = new ArrayList<>();
        stationData.shkTag = new ArrayList<>();
        stationData.tnk = new ArrayList<>();
        stationData.txk = new ArrayList<>();
    }

    /**
     * Mit dieser Methode kann ein String (Zeile einer Datei) in ein String-Array (-> Spalten) umgewandelt werden.
     *
     * @param line Die Zeile
     * @return Ein String-Array
     */
    private static String[] getLineAsArray(String line) {
        return line.replace(" ", "").replace(";eor", "").split(";");
    }

    /**
     * Diese Methode wandelt alle Textinformationen einer DWD-Datei in entsprechenden die Repräsentationen in Java um.
     *
     * @param fileLines Alle Zeilen einer Datei
     * @return Ein <code>StationData</code>-Instanz mit allen Messwerten einer Wetterstation.
     */
    public static StationData fromFileLines(List<String> fileLines) {
        StationData stationData = new StationData();
        initializeArrayLists(stationData);
        stationData.stationId = java.lang.Integer.parseInt(getLineAsArray(fileLines.get(1))[StationDataColumn.STATIONS_ID.ordinal()]);
        for (int i = 1; i < fileLines.size(); i++) {
            String[] lineArray = getLineAsArray(fileLines.get(i));
            stationData.measuringDate.add(DateUtil.getDateFromString(lineArray[StationDataColumn.MESS_DATUM.ordinal()]));
            stationData.fm.add(FloatParser.parseFloat(lineArray[StationDataColumn.FM.ordinal()]));
            stationData.rsk.add(FloatParser.parseFloat(lineArray[StationDataColumn.RSK.ordinal()]));
            stationData.sdk.add(FloatParser.parseFloat(lineArray[StationDataColumn.SDK.ordinal()]));
            stationData.shkTag.add(FloatParser.parseFloat(lineArray[StationDataColumn.SHK_TAG.ordinal()]));
            stationData.nm.add(FloatParser.parseFloat(lineArray[StationDataColumn.NM.ordinal()]));
            stationData.pm.add(FloatParser.parseFloat(lineArray[StationDataColumn.PM.ordinal()]));
            stationData.txk.add(FloatParser.parseFloat(lineArray[StationDataColumn.TXK.ordinal()]));
            stationData.tnk.add(FloatParser.parseFloat(lineArray[StationDataColumn.TNK.ordinal()]));
        }
        return stationData;
    }

    @Override
    public String toString() {
        return "StationData{" +
                "stationID=" + stationId +
                ", measuringDate=" + measuringDate +
                ", fm=" + fm +
                ", nm=" + nm +
                ", pm=" + pm +
                ", rsk=" + rsk +
                ", sdk=" + sdk +
                ", shkTag=" + shkTag +
                ", tnk=" + tnk +
                ", txk=" + txk + '}';
    }
}

