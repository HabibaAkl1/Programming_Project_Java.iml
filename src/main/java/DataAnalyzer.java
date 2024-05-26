



import de.thd.dwd_data_retriever.IDwdDataRetriever;
import de.thd.dwd_data_retriever.datatypes.StationData;
import de.thd.dwd_data_retriever.datatypes.StationMetaData;
import de.thd.dwd_data_retriever.datatypes.StationNotFoundException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;



public class DataAnalyzer {
    private IDwdDataRetriever dataRetriever;/* dataRetriever implementiert IDwdDataRetriever*/

    public DataAnalyzer(IDwdDataRetriever dataRetriever)/*Constructor*/ {
        this.dataRetriever = dataRetriever;
    }

    public String getStationName(int stationId) { /* StationMetaData klasse verwendet um methode getStationName zu verwenden*/

        try {
            StationMetaData metadata_Station = dataRetriever.getStationMetaData(stationId);
            return metadata_Station.getStationName();
        } catch (StationNotFoundException e) {
            return "Station Name Nicht gefunden";
        }
    }

    /*getters*/
    /*HochstrNiederschlag*/
    public String getNamemaxNiederschlag() {
        float maxNiederschlag = 0; /* initializiern zu null*/
        String station_Name = "";
        List<Integer> stationIDs = dataRetriever.getStationIDs();

        for (Integer stationId : stationIDs) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                for (Float value : data.getRsk()) {   /*RSK = tägliche Niederschlagshöhe*/
                    if (value != null && value > maxNiederschlag) { /*Prüft, ob der aktuelle Niederschlagswert größer ist. Wenn ja, dann wird dieser Wert zurückgegeben
                     */
                        maxNiederschlag = value;
                        station_Name = getStationName(stationId);
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station" + stationId + " nicht gefunden");
            }
        }
        return station_Name;
    }

    /*Hochster Schnee Wert*/
    public String getNameMaxSchnee() {
        float maxSchnee = 0;
        String station_Name = "";
        List<Integer> stationIDs = dataRetriever.getStationIDs();
        for (Integer stationId : stationIDs) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                for (Float value : data.getShkTag()) { /*SHK:Tageswert Schneehöhe*/
                    if (value != null && value > maxSchnee) {
                        maxSchnee = value;
                        station_Name = getStationName(stationId);
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station " + stationId + "  nicht gefunden ");
            }
        }
        return station_Name;
    }


    /* Hochster Temperatur*/
    public String getNameMaxTemperatur() {
        float maxTemperatur = 0;
        String station_Name = "";
        for (Integer stationId : dataRetriever.getStationIDs()) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                for (Float value : data.getTxk()) {
                    if (value != null && value > maxTemperatur) {
                        maxTemperatur = value;
                        station_Name = getStationName(stationId);
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station " + stationId + "  nicht gefunden ");
            }
        }
        return station_Name;
    }

    /*Niedrigest Temperatur*/

    public String getNameMinTemperatur() {
        float minTemperatur = 0;
        String station_Name = "";
        for (Integer stationId : dataRetriever.getStationIDs()) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                for (Float value : data.getTnk()) {/*Tagesminimum der Lufttemperatur*/
                    if (value != null && value < minTemperatur) { /*prüft, ob der aktuelle Wert niedriger ist, dann wird dieser Wert zurückgegeben*/
                        minTemperatur = value;
                        station_Name = getStationName(stationId);
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station " + stationId + " nicht gefunden");
            }
        }
        return station_Name;
    }

    /*Hochster Wind Im mittel*/
    public String getNameMaxWind() {
        float maxWind = 0;
        String station_Name = "";
        for (Integer stationId : dataRetriever.getStationIDs()) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                for (Float value : data.getFm()) {/*Tagesmittel Windgeschwindigkeit*/
                    if (value != null && value > maxWind) {
                        maxWind = value;
                        station_Name = getStationName(stationId);
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station" + stationId + "nicht gefunden");
            }
        }
        return station_Name;
    }

    /*
        /*Alteste Station Datum*/
    public String getStationoldest() {
        int oldestStationId = -1; /*  d.h keine StationsID Jetzt gefunded */
        /*oldestDate objekt von Type Date */
        /* Alteste Datum  */
        Date oldestDate = new Date(Long.MAX_VALUE); //Setzen von „oldestDate“ auf das aktuellste Datum,
        // Daher wird jedes tatsächliche Datum im Vergleich dazu früher liegen

        String oldestStationName = "";

        List<Integer> stationIDs = dataRetriever.getStationIDs();
        for (Integer stationId : stationIDs) {
            try {
                StationData data = dataRetriever.getStationData(stationId);
                List<Date> measuringDates = data.getMeasuringDate();/*holt die Daten von MeasuringDate Methode */
                /*"iterates" jedes Datum in der Liste
                , um die ältesten Daten zu finden und dann mit dem 'oldesDate' Datum zu vergleichen*/

                for (Date date : measuringDates) {
                    if (date.compareTo(oldestDate) < 0) {
                        oldestDate = date;
                        oldestStationId = stationId;
                    }
                }
            } catch (StationNotFoundException e) {
                System.out.println("Station " + stationId + " not found");
            }
        }

        if (oldestStationId != -1) { /* pruft ob eine Station gefunded wird */
            try {
                StationMetaData data = dataRetriever.getStationMetaData(oldestStationId);
                oldestStationName = data.getStationName();
            } catch (StationNotFoundException e) {
                System.out.println("Station" + oldestStationId + " nicht gefunden");


            }
        }


        return oldestStationName;
    }

    /*    TemperaturVergleichen*/
      /* minTemperaturoldeststaion


         maxTemerturoldeststaion
         calculateAverage
         letzteJahr - ersteJahr





         ersteJahr_minTemperatur
         ersteJahr_maxTemperatur
       */

    public void TemperaturAnalyzer(int oldestStationId) {
        if (oldestStationId == -1) {
            System.out.println("Invalid oldest station ID");
            return;
        }

        List<Date> dates;
        List<Float> minTemp;
        List<Float> maxTemp;

        try {
            StationData data = dataRetriever.getStationData(oldestStationId);
            dates = data.getMeasuringDate();
            minTemp = data.getTnk();
            maxTemp = data.getTxk();
        } catch (StationNotFoundException e) {
            throw new RuntimeException(e);
        }

        Date ersteDate = new Date(Long.MAX_VALUE);
        Date letzteDate = new Date(Long.MIN_VALUE);
        float sumMinTempErsteJahr = 0;
        float sumMaxTempErsteJahr = 0;
        float sumMinTempLetzteJahr = 0;
        float sumMaxTempLetzteJahr = 0;
        int countErsteJahr = 0;
        int countLetzteJahr = 0;

        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = dates.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = date.getYear();

            if (year < ersteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()) {
                ersteDate = dates.get(i);
                sumMinTempErsteJahr = minTemp.get(i);
                sumMaxTempErsteJahr = maxTemp.get(i);
                countErsteJahr = 1;
            } else if (year == ersteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()) {
                sumMinTempErsteJahr += minTemp.get(i);
                sumMaxTempErsteJahr += maxTemp.get(i);
                countErsteJahr++;
            }

            if (year > letzteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()) {
                letzteDate = dates.get(i);
                sumMinTempLetzteJahr = minTemp.get(i);
                sumMaxTempLetzteJahr = maxTemp.get(i);
                countLetzteJahr = 1;
            } else if (year == letzteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()) {
                sumMinTempLetzteJahr += minTemp.get(i);
                sumMaxTempLetzteJahr += maxTemp.get(i);
                countLetzteJahr++;
            }
        }

        float averageErsteTempMin = sumMinTempErsteJahr / countErsteJahr;
        float averageErsteTempMax = sumMaxTempErsteJahr / countErsteJahr;
        float averageLetzteTempMin = sumMinTempLetzteJahr / countLetzteJahr;
        float averageLetzteTempMax = sumMaxTempLetzteJahr / countLetzteJahr;

        float minTempChange = averageLetzteTempMin - averageErsteTempMin;
        float maxTempChange = averageLetzteTempMax - averageErsteTempMax;

        System.out.println("Temperature change at the oldest station:");
        System.out.println("Average minimum temperature change: " + minTempChange + "°C");
        System.out.println("Average maximum temperature change: " + maxTempChange + "°C");
    }

}
