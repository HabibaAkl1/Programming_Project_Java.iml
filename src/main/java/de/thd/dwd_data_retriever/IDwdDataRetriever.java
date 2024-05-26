package de.thd.dwd_data_retriever;

import de.thd.dwd_data_retriever.datatypes.StationMetaData;
import de.thd.dwd_data_retriever.datatypes.StationData;
import de.thd.dwd_data_retriever.datatypes.StationNotFoundException;

import java.util.ArrayList;

/**
 * Schnittstelle zum Einlesen der DWD-Daten.
 *
 * @author Markus Eider (TH Deggendorf)
 */
public interface IDwdDataRetriever {

    /**
     * Gibt die Identifier (IDs) der verfügbaren Wetterstationen zurück.
     *
     * @return Eine ArrayList mit Integer-Instanzen der Wetterstations-IDs.
     */
    public ArrayList<java.lang.Integer> getStationIDs();

    /**
     * Gibt die Meta-Daten einer Wetterstation zurück.
     *
     * @param stationID Die ID einer Wetterstation.
     * @return Die Metadaten einer Wetterstation.
     * @throws StationNotFoundException Exception wird geworfen, wenn es die <code>stationID</code> nicht gibt.
     */
    public StationMetaData getStationMetaData(int stationID) throws StationNotFoundException;

    /**
     * Gibt die tatsächlichen Messdaten einer Wetterstation zurück.
     *
     * @param stationID Die ID einer Wetterstation.
     * @return Die Messwerte der Wetterstation.
     * @throws StationNotFoundException Exception wird geworfen, wenn es die <code>stationID</code> nicht gibt.
     */
    public StationData getStationData(int stationID) throws StationNotFoundException;
}

