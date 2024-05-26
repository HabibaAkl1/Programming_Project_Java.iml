package de.thd.dwd_data_retriever.datatypes;

public class StationNotFoundException extends Exception {

    public StationNotFoundException(int stationID) {
        super("Konnte die Station mit ID \"" + stationID + "\" nicht finden!");
    }
}
